package nz.co.xingsoft.gwt.sample.server.upload;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nz.co.ecngroup.shared.common.utility.StringUtility;
import nz.co.xingsoft.gwt.sample.server.common.HttpRequestContext;
import nz.co.xingsoft.gwt.sample.server.common.HttpServletUtil;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.context.WebApplicationContext;

public class FileUploadServlet
        extends UploadAction {
    private static final String FILE_UPLOAD_HANDLER_CONTROLLER_BEAN_NAME = "fileUploadHandlerController";
    private static final long serialVersionUID = -3380567625588715641L;

    @Override
    public String executeAction(final HttpServletRequest request, final List<FileItem> sessionFiles)
            throws UploadActionException {

        final WebApplicationContext springApplicationContext = HttpServletUtil.getSpringContextFromServletContext(getServletContext());

        final HttpRequestContext uploadContext = HttpServletUtil.buildHttpRequestContext(request);

        final FileUploadHandlerController fileUploadHandlerController = (FileUploadHandlerController) springApplicationContext
                .getBean(FILE_UPLOAD_HANDLER_CONTROLLER_BEAN_NAME);

        int cont = 0;
        for (final FileItem item : sessionFiles) {
            if (false == item.isFormField()) {
                cont++;
                try {
                    final String uploadFilename = item.getName();
                    if (StringUtility.isBlank(uploadFilename)) {
                        removeSessionFileItems(request);
                        return "Please select a file to upload";
                    }
                    final String savedFileName = fileUploadHandlerController.onFileUpload(uploadContext, uploadFilename);

                    final File uploadFolder = fileUploadHandlerController.locateFolder(uploadContext);
                    if (!uploadFolder.exists()) {
                        uploadFolder.mkdirs();
                    }
                    final File file = new File(uploadFolder, savedFileName);
                    item.write(file);
                    fileUploadHandlerController.postFileUpload(uploadContext, file);

                } catch (final Exception e) {
                    removeSessionFileItems(request);
                    throw new UploadActionException(e);
                }
            }
        }

        // / Remove files from session because we have a copy of them
        removeSessionFileItems(request);

        // Send your customized message to the client.
        final FileUploadResponseBuilder responseBuilder = fileUploadHandlerController.getResponseBuilder();
        return responseBuilder.getServerResponse();

    }

    private HttpRequestContext initUploadContext(final HttpServletRequest request) {
        final HttpRequestContext uploadContext = new HttpRequestContext();
        final Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            final String name = parameterNames.nextElement();
            uploadContext.addParameter(name, request.getParameter(name));
        }
        return uploadContext;
    }
}
