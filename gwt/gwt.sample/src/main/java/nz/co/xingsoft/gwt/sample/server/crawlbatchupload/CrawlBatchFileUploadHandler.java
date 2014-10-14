package nz.co.xingsoft.gwt.sample.server.crawlbatchupload;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import nz.co.xingsoft.gwt.sample.server.common.HttpRequestContext;
import nz.co.xingsoft.gwt.sample.server.upload.FileUploadHandler;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class CrawlBatchFileUploadHandler implements FileUploadHandler {

    @Override
    public String onFileUpload(final HttpRequestContext uploadContext, final String uploadFilename) {
        return FilenameUtils.getBaseName(uploadFilename) + "-" + new Date().getTime() + "." + FilenameUtils.getExtension(uploadFilename);
    }

    @Override
    public void postFileUpload(final HttpRequestContext uploadContext, final File uploadFile) {
        try {
            final File fileWillBeMoved = new File(crawlBatchFileProcessFolder, uploadFile.getName());
            if (fileWillBeMoved.exists()) {
                throw new RuntimeException("Upload file name already exists, please change a name");
            }
            FileUtils.moveFileToDirectory(uploadFile, new File(crawlBatchFileProcessFolder), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private String crawlBatchFileProcessFolder;

    public void setCrawlBatchFileProcessFolder(final String crawlBatchFileProcessFolder) {
        this.crawlBatchFileProcessFolder = crawlBatchFileProcessFolder;
    }

}
