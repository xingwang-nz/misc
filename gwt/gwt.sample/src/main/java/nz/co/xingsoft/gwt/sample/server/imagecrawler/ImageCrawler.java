/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.co.xingsoft.gwt.sample.server.imagecrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.url.URLCanonicalizer;
import edu.uci.ics.crawler4j.url.WebURL;

public class ImageCrawler extends WebCrawler {
    protected static final Logger LOGGER = Logger.getLogger(ImageCrawler.class);

    private static final Pattern filters = Pattern.compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|rm|smil|wmv|swf|wma|zip|rar|gz))$",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern imgPatterns = Pattern.compile(".*(\\.(pdf|bmp|gif|jpe?g|png|tiff?))$", Pattern.CASE_INSENSITIVE);

    private String[] crawlDomains;
    private ImageWriter imageWriter;
    private int minImageSize = -1;
    private int maxImageSize = -1;

    private final List<String> visitedURLs = new ArrayList<String>();

    @Override
    public void onStart() {
        System.out.println("Crawler starting ....................................");
    }

    @Override
    public void onBeforeExit() {
        deleteVisitedUrlFromDb();
    }

    private void deleteVisitedUrlFromDb() {
        System.out.println("Delete all visited url from db to enable re-crawl these urls *************************************");
        final UrlDocIDServer docIDServer = (UrlDocIDServer) getMyController().getDocIdServer();
        for (String url : visitedURLs) {
            if (docIDServer.getDocId(url) > 0) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Delete url from db : " + url);
                }
                docIDServer.deleteDocId(url);
            } else {
                final String canonicalUrl = URLCanonicalizer.getCanonicalURL(url);
                if (docIDServer.getDocId(canonicalUrl) > 0) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Delete url from db : " + url);
                    }
                    docIDServer.deleteDocId(canonicalUrl);
                }
            }
        }
    }

    @Override
    public boolean shouldVisit(final WebURL webUrl) {
        visitedURLs.add(webUrl.getURL());
        final String url = webUrl.getURL().toLowerCase();

        if (filters.matcher(url).matches()) {
            return false;
        }

        if (imgPatterns.matcher(url).matches() || isOnDomain(url)) {

            return true;

        }

        return false;
    }

    private boolean isOnDomain(final String url) {
        for (String domain : crawlDomains) {
            if (url.startsWith(domain.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void visit(final Page page) {
        final String url = page.getWebURL().getURL();

        // We are only interested in processing images
        if (!(page.getParseData() instanceof BinaryParseData)) {
            return;
        }

        if (minImageSize > 0) {
            if (page.getContentData().length < minImageSize) {
                return;
            }
        }

        if (maxImageSize > 0) {
            if (page.getContentData().length > maxImageSize) {
                return;
            }
        }

        if (imageWriter != null) {
            try {
                imageWriter.write(url, page.getContentData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setImageWriter(final ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
    }

    public void setCrawlDomains(final String[] crawlDomains) {
        this.crawlDomains = crawlDomains;
    }

    public void setMinImageSize(final int minImageSize) {
        this.minImageSize = minImageSize;
    }

    public void setMaxImageSize(final int maxImageSize) {
        this.maxImageSize = maxImageSize;
    }

}
