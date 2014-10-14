package nz.co.xingsoft.gwt.sample.server.imagecrawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.frontier.Frontier;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.URLCanonicalizer;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Provides the call back method for <code>Crawler</code> constructing<br/>
 * 
 */
public class WebCrawlController extends CrawlController {
    private static final Logger logger = Logger.getLogger(WebCrawlController.class.getName());

    private final CrawlerInstantiateAware crawlerInstantiateListener;

    public WebCrawlController(final CrawlConfig config, final PageFetcher pageFetcher, final RobotstxtServer robotstxtServer) throws Exception {
        this(config, pageFetcher, robotstxtServer, null);
    }

    public WebCrawlController(final CrawlConfig config, final PageFetcher pageFetcher, final RobotstxtServer robotstxtServer,
            final CrawlerInstantiateAware crawlerInstantiateListener) throws Exception {

        super(config, pageFetcher, robotstxtServer);

        this.crawlerInstantiateListener = crawlerInstantiateListener;

        initController(config);
    }

    private void initController(final CrawlConfig config) {
        final EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        envConfig.setTransactional(config.isResumableCrawling());
        envConfig.setLocking(config.isResumableCrawling());

        final File envHome = new File(config.getCrawlStorageFolder() + "/frontier");

        final Environment env = new Environment(envHome, envConfig);
        final UrlDocIDServer docIdServer = new UrlDocIDServer(env, config);
        super.setDocIdServer(docIdServer);

        final Frontier frontier = new Frontier(env, config, docIdServer);
        super.setFrontier(frontier);

    }

    @Override
    protected <T extends WebCrawler> void start(final Class<T> _c, final int numberOfCrawlers, final boolean isBlocking) {
        try {
            finished = false;
            crawlersLocalData.clear();
            final List<Thread> threads = new ArrayList<Thread>();
            final List<T> crawlers = new ArrayList<T>();

            for (int i = 1; i <= numberOfCrawlers; i++) {
                final T crawler = _c.newInstance();
                if (crawlerInstantiateListener != null) {
                    crawlerInstantiateListener.instantiate(crawler, i);
                }

                Thread thread = new Thread(crawler, "Crawler " + i);
                crawler.setThread(thread);
                crawler.init(i, this);
                thread.start();
                crawlers.add(crawler);
                threads.add(thread);
                logger.info("Crawler " + i + " started.");
            }

            final CrawlController controller = this;

            Thread monitorThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        synchronized (waitingLock) {

                            while (true) {
                                sleep(10);
                                boolean someoneIsWorking = false;
                                for (int i = 0; i < threads.size(); i++) {
                                    Thread thread = threads.get(i);
                                    if (!thread.isAlive()) {
                                        if (!shuttingDown) {
                                            logger.info("Thread " + i + " was dead, I'll recreate it.");
                                            T crawler = _c.newInstance();
                                            thread = new Thread(crawler, "Crawler " + (i + 1));
                                            threads.remove(i);
                                            threads.add(i, thread);
                                            crawler.setThread(thread);
                                            crawler.init(i + 1, controller);
                                            thread.start();
                                            crawlers.remove(i);
                                            crawlers.add(i, crawler);
                                        }
                                    } else if (crawlers.get(i).isNotWaitingForNewURLs()) {
                                        someoneIsWorking = true;
                                    }
                                }
                                if (!someoneIsWorking) {
                                    // Make sure again that none of the threads are alive.
                                    logger.info("It looks like no thread is working, waiting for 10 milseconds to make sure...");
                                    sleep(10);

                                    someoneIsWorking = false;
                                    for (int i = 0; i < threads.size(); i++) {
                                        Thread thread = threads.get(i);
                                        if (thread.isAlive() && crawlers.get(i).isNotWaitingForNewURLs()) {
                                            someoneIsWorking = true;
                                        }
                                    }
                                    if (!someoneIsWorking) {
                                        if (!shuttingDown) {
                                            long queueLength = frontier.getQueueLength();
                                            if (queueLength > 0) {
                                                continue;
                                            }
                                            logger.info("No thread is working and no more URLs are in queue waiting for another 10 milseconds to make sure...");
                                            sleep(10);
                                            queueLength = frontier.getQueueLength();
                                            if (queueLength > 0) {
                                                continue;
                                            }
                                        }

                                        logger.info("All of the crawlers are stopped. Finishing the process...");
                                        // At this step, frontier notifies the threads that were
                                        // waiting for new URLs and they should stop
                                        frontier.finish();
                                        for (T crawler : crawlers) {
                                            crawler.onBeforeExit();
                                            crawlersLocalData.add(crawler.getMyLocalData());
                                        }

                                        logger.info("Waiting for 10 seconds before final clean up...");
                                        sleep(10);

                                        frontier.close();
                                        docIdServer.close();
                                        pageFetcher.shutDown();

                                        finished = true;
                                        waitingLock.notifyAll();

                                        return;
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            monitorThread.start();

            if (isBlocking) {
                waitUntilFinish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSeed(final String pageUrl, int docId) {
        String canonicalUrl = URLCanonicalizer.getCanonicalURL(pageUrl);
        if (canonicalUrl == null) {
            logger.error("Invalid seed URL: " + pageUrl);
            return;
        }
        if (docId < 0) {
            docId = docIdServer.getDocId(canonicalUrl);
            if (docId > 0) {
                // This URL is already seen, re-crawl.
                // return;
            } else {
                docId = docIdServer.getNewDocID(canonicalUrl);
            }

        } else {
            try {
                docIdServer.addUrlAndDocId(canonicalUrl, docId);
            } catch (Exception e) {
                logger.error("Could not add seed: " + e.getMessage());
            }
        }

        WebURL webUrl = new WebURL();
        webUrl.setURL(canonicalUrl);
        webUrl.setDocid(docId);
        webUrl.setDepth((short) 0);
        if (!robotstxtServer.allows(webUrl)) {
            logger.info("Robots.txt does not allow this seed: " + pageUrl);
        } else {
            frontier.schedule(webUrl);
        }
    }

}
