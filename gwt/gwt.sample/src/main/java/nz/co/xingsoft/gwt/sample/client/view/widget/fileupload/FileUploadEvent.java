package nz.co.xingsoft.gwt.sample.client.view.widget.fileupload;

import gwtupload.client.IUploadStatus.Status;

public class FileUploadEvent {

    private Status status;

    private String fileName;

    private int fileSize;

    private String contentType;

    private String message;
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(final int fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }
    
}
