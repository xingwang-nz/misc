package nz.co.xingsoft.gwt.sample.server.imagecrawler;

import edu.uci.ics.crawler4j.crawler.WebCrawler;

public interface CrawlerInstantiateAware {

    <T extends WebCrawler> void instantiate(T t, int number);

}
