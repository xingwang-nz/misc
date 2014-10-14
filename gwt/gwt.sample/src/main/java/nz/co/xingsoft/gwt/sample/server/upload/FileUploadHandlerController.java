package nz.co.xingsoft.gwt.sample.server.upload;

import nz.co.xingsoft.gwt.sample.server.common.FolderResolver;
import nz.co.xingsoft.gwt.sample.server.common.HttpRequestContext;

public interface FileUploadHandlerController extends FolderResolver<HttpRequestContext>, FileUploadHandler {

    /**
     * A handler for upload servlet's response message
     * 
     * @return
     */
    FileUploadResponseBuilder getResponseBuilder();
}
