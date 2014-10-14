package nz.co.xingsoft.gwt.sample.client.view.widget.fileupload;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.ModalUploadStatus;
import gwtupload.client.SingleUploaderModal;

import java.util.ArrayList;
import java.util.List;

import nz.co.ecngroup.shared.common.utility.StringUtility;
import nz.co.xingsoft.gwt.sample.client.common.UrlRequestParameter;
import nz.co.xingsoft.gwt.sample.shared.dto.enums.RequestParameterName;
import nz.co.xingsoft.gwt.sample.shared.dto.enums.RequestType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public abstract class CommonFileUploader
        extends SingleUploaderModal {
    private static final String UPLOAD_BASE_URL = GWT.getModuleBaseURL() + "fileUpload?";

    private final String uploadUrl;

    private List<FileUploadEventHandler> fileUploadEventHandlers = new ArrayList<FileUploadEventHandler>();

    public CommonFileUploader(final RequestType requestType) {
        // this(requestType, FileInputType.CUSTOM.with(new UploadImage()), null, new FileUploadSubmitForm());
        this(requestType, FileInputType.BROWSER_INPUT, new Button("Upload"), new FileUploadSubmitForm());
    }

    private CommonFileUploader(final RequestType requestType, final FileInputType fileInputType, final Widget submitButton, final FileUploadSubmitForm formPanel) {
        super(fileInputType, new ModalUploadStatus(), submitButton, formPanel);

        if (formPanel != null) {
            formPanel.setFileUploader(this);
        }

        this.setAvoidRepeatFiles(false);
        setAutoSubmit(true);
        uploadUrl = UPLOAD_BASE_URL + RequestParameterName.requestType.toString() + "=" + requestType.toString();

        addOnFinishUploadHandler(new OnFinishUploaderHandler() {
            @Override
            public void onFinish(final IUploader uploader) {
                final UploadedInfo info = uploader.getServerInfo();
                final FileUploadEvent event = new FileUploadEvent();
                event.setFileName(info.name);
                event.setContentType(info.ctype);
                event.setFileSize(info.size);
                event.setMessage(info.message);

                if (uploader.getStatus() == Status.SUCCESS) {
                    final String uploadFilename = info.name;
                    if (!StringUtility.isBlank(uploadFilename)) {
                        event.setStatus(Status.SUCCESS);
                        for (final FileUploadEventHandler handler : fileUploadEventHandlers) {
                            handler.onSuccess(event);
                        }
                        // WindowUtil.info(uploadFilename + "(" + info.size + ") has been uploaded successfully");
                    } else {
                        event.setStatus(Status.INVALID);
                        for (final FileUploadEventHandler handler : fileUploadEventHandlers) {
                            handler.onFailure(event);
                        }
                    }

                } else {
                    if (isUploadFailed(uploader.getStatus())) {
                        event.setStatus(uploader.getStatus());
                        for (final FileUploadEventHandler handler : fileUploadEventHandlers) {
                            handler.onFailure(event);
                        }
                    }
                }
            }
        });
    }

    private boolean isUploadFailed(final Status status) {
        return Status.ERROR.equals(status) || Status.INVALID.equals(status) || Status.UNINITIALIZED.equals(status);
    }

    public void addFileUploadEventHandler(final FileUploadEventHandler handler) {
        fileUploadEventHandlers.add(handler);
    }

    @Override
    protected void onStartUpload() {
        super.onStartUpload();
    }

    protected abstract boolean validate();

    protected abstract List<UrlRequestParameter> getRequestParameters();

    public void setUploadUrl() {
        setServletPath(getUploadUrl(getRequestParameters()));
    }

    private String getUploadUrl(final List<UrlRequestParameter> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return uploadUrl;
        }

        final StringBuilder url = new StringBuilder(uploadUrl);
        for (final UrlRequestParameter parameter : parameters) {
            url.append("&");
            url.append(parameter.toUrl());
        }

        return url.toString();
    }

}