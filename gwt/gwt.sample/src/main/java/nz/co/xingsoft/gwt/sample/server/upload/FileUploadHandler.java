package nz.co.xingsoft.gwt.sample.server.upload;

import java.io.File;

import nz.co.xingsoft.gwt.sample.server.common.HttpRequestContext;

public interface FileUploadHandler {

    /**
     * Before stream out uploaded file
     * 
     * @param uploadContext
     * @param uploadFilename
     * @return the saved filename
     */
    String onFileUpload(final HttpRequestContext uploadContext, String uploadFilename);

    /**
     * After stream out uploaded file
     * 
     * @param uploadContext
     * @param uploadFile
     */
    void postFileUpload(final HttpRequestContext uploadContext, File uploadFile);

}
