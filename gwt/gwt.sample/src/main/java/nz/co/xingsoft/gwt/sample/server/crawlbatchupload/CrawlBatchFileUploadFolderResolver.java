package nz.co.xingsoft.gwt.sample.server.crawlbatchupload;

import java.io.File;

import nz.co.xingsoft.gwt.sample.server.common.FolderResolver;
import nz.co.xingsoft.gwt.sample.server.common.HttpRequestContext;

public class CrawlBatchFileUploadFolderResolver implements FolderResolver<HttpRequestContext> {

    private String crawlBatchFileUploadFolder;

    @Override
    public File locateFolder(final HttpRequestContext t) {

        final File uploadFolder = new File(crawlBatchFileUploadFolder);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        return uploadFolder;
    }

    public void setCrawlBatchFileUploadFolder(final String crawlBatchFileUploadFolder) {
        this.crawlBatchFileUploadFolder = crawlBatchFileUploadFolder;
    }

}
