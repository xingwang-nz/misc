package nz.co.xingsoft.gwt.sample.server.imagecrawler;

import java.io.File;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class ImageCrawlLauncher {

    private String crawlRootFolderName;
    private String imageStoragefolderName;

    public ImageCrawlLauncher(final String crawlRootFolderName, final String imageStoragefolderName) {
        if (crawlRootFolderName == null || crawlRootFolderName.trim().equals("")) {
            throw new IllegalArgumentException("crawlRootFolderName should be provided");
        }

        this.crawlRootFolderName = crawlRootFolderName;
        this.imageStoragefolderName = imageStoragefolderName;

        final File crawlRootFolder = new File(this.imageStoragefolderName);
        if (!crawlRootFolder.exists()) {
            crawlRootFolder.mkdirs();
        }

    }

    public void startCrawl(final String[] crawlDomains, final int depth) throws Exception {

        final ImageWriter imageWriter = new ImageFileWriter(imageStoragefolderName);

        final int numberOfCrawlers = 1;

        final CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlRootFolderName);
        /*
         * Since images are binary content, we need to set this parameter to true to make sure they are included in the
         * crawl.
         */
        config.setMaxDepthOfCrawling(depth);
        config.setIncludeBinaryContentInCrawling(true);
        // config.setMaxPagesToFetch(1);
        config.setIncludeHttpsPages(true);

        final PageFetcher pageFetcher = new PageFetcher(config);
        final RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        final RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        final CrawlController controller = new WebCrawlController(config, pageFetcher, robotstxtServer, new CrawlerInstantiateAware() {
            @Override
            public <T extends WebCrawler> void instantiate(final T t, final int number) {
                final ImageCrawler crawler = (ImageCrawler) t;
                crawler.setCrawlDomains(crawlDomains);
                crawler.setImageWriter(imageWriter);
            }
        });

        for (String domain : crawlDomains) {
            controller.addSeed(domain);
        }

        controller.start(ImageCrawler.class, numberOfCrawlers);

        // Wait for 10 seconds
        Thread.sleep(10 * 100);

        // Send the shutdown request and then wait for finishing
        controller.Shutdown();
        controller.waitUntilFinish();

    }

    public static void main(final String[] args) throws Exception {
        final String crawlRootFolderName = "/home/xwang/temp/webcrawler";
        // final String imageStorageFolderName =
        // "/home/xwang/work/ecn/t4/trunk/gwt.sample/target/gwt.sample-1.11/GwtSample/web-crawl-images";
        final String imageStorageFolderName = "/home/xwang/temp/webcrawler/images";

        // String domain = "http://www.auckland.ac.nz";
        // String domain = "http://www.biglittlecity.co.nz/";
        // String domain = "http://www.onetreegrill.co.nz/";

        // String domain = "http://localhost:8080/car/";
        // String domain = "http://localhost:8080/truck/";

        // String domain = "http://www.telecom.co.nz/";

        String domain = "https://community.jboss.org/wiki/JBossApplicationServerOfficialDocumentationPage";
        // String domain = "http://www.trademe.co.nz";
        // String domain = "http://www.canopytreecare.co.nz/";

        // String domain = "http://www.canopytreecare.co.nz";

        // String domain = "https://www.google.co.nz/";
        new ImageCrawlLauncher(crawlRootFolderName, imageStorageFolderName).startCrawl(new String[] { domain }, 2);

    }
}
