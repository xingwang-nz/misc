package nz.co.xingsoft.gwt.sample.server.upload;

import java.io.File;
import java.text.MessageFormat;
import java.util.Map;

import nz.co.xingsoft.gwt.sample.server.common.FolderResolver;
import nz.co.xingsoft.gwt.sample.server.common.HttpRequestContext;
import nz.co.xingsoft.gwt.sample.shared.dto.enums.RequestParameterName;

public class FileUploadHandlerControllerImpl implements FileUploadHandlerController {

    private static final class DefaultResponseBuilder implements FileUploadResponseBuilder {
        /**
		 * 
		 */
        private static final long serialVersionUID = -6599771903049620532L;
        private String inputFileName;
        private String uploadedFileName;

        @Override
        public String getServerResponse() {
            return MessageFormat.format("Uploaded file {0} to {1}", inputFileName, uploadedFileName);
        }

        public String getInputFileName() {
            return inputFileName;
        }

        public void setInputFileName(final String inputFileName) {
            this.inputFileName = inputFileName;
        }

        public String getUploadedFileName() {
            return uploadedFileName;
        }

        public void setUploadedFileName(final String uploadedFileName) {
            this.uploadedFileName = uploadedFileName;
        }
    }

    private Map<String, FolderResolver<HttpRequestContext>> uploadFolderResolverMap;

    private Map<String, FileUploadHandler> fileUploadHandlerMap;

    private final DefaultResponseBuilder defaultResponseBuilder = new DefaultResponseBuilder();

    @Override
    public File locateFolder(final HttpRequestContext uploadContext) {
        return getUploadFolderResolver(uploadContext).locateFolder(uploadContext);
    }

    @Override
    public void postFileUpload(final HttpRequestContext uploadContext, final File file) {
        getFileUploadHandler(uploadContext).postFileUpload(uploadContext, file);
        defaultResponseBuilder.setUploadedFileName(file == null ? "[]" : file.getAbsolutePath());
    }

    @Override
    public String onFileUpload(final HttpRequestContext uploadContext, final String uploadFilename) {
        defaultResponseBuilder.setInputFileName(uploadFilename);
        return getFileUploadHandler(uploadContext).onFileUpload(uploadContext, uploadFilename);

    }

    private FileUploadHandler getFileUploadHandler(final HttpRequestContext uploadContext) {
        final String requestType = uploadContext.getParameter(RequestParameterName.requestType.toString());
        final FileUploadHandler fileUploadHandler = fileUploadHandlerMap.get(requestType);
        if (fileUploadHandler == null) {
            throw new IllegalArgumentException("Undefined FileUploadHandler: " + requestType);
        }
        return fileUploadHandler;
    }

    private FolderResolver<HttpRequestContext> getUploadFolderResolver(final HttpRequestContext uploadContext) {
        final String requestType = uploadContext.getParameter(RequestParameterName.requestType.toString());
        final FolderResolver<HttpRequestContext> uploadFolderResolver = uploadFolderResolverMap.get(requestType);
        if (uploadFolderResolver == null) {
            throw new IllegalArgumentException("Undefined SubstitutionDataFolderResolver: " + requestType);
        }
        return uploadFolderResolver;
    }

    public void setFileUploadHandlerMap(final Map<String, FileUploadHandler> fileUploadHandlerMap) {
        this.fileUploadHandlerMap = fileUploadHandlerMap;
    }

    public void setUploadFolderResolverMap(final Map<String, FolderResolver<HttpRequestContext>> uploadFolderResolverMap) {
        this.uploadFolderResolverMap = uploadFolderResolverMap;
    }

    @Override
    public FileUploadResponseBuilder getResponseBuilder() {
        return defaultResponseBuilder;
    }
}