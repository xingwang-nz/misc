package nz.co.xingsoft.gwt.sample.server.imagecrawler;

import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.frontier.DocIDServer;

public class UrlDocIDServer extends DocIDServer {

    public UrlDocIDServer(final Environment env, final CrawlConfig config) throws DatabaseException {
        super(env, config);
    }

    public void deleteDocId(final String url) {
        synchronized (mutex) {
            try {
                DatabaseEntry key = new DatabaseEntry(url.getBytes());
                docIDsDB.delete(null, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
