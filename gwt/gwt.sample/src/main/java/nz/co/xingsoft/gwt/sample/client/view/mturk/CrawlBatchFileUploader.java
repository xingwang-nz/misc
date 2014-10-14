package nz.co.xingsoft.gwt.sample.client.view.mturk;

import java.util.List;

import nz.co.ecngroup.shared.web.widgets.util.WindowUtil;
import nz.co.xingsoft.gwt.sample.client.common.UrlRequestParameter;
import nz.co.xingsoft.gwt.sample.client.view.widget.fileupload.CommonFileUploader;
import nz.co.xingsoft.gwt.sample.shared.dto.enums.RequestType;

public class CrawlBatchFileUploader
        extends CommonFileUploader {

    private final static String UPLOAD_FILE_EXTENSION = "csv";

    public CrawlBatchFileUploader() {
        super(RequestType.CRAWL_BATCH_FILE);
        setAutoSubmit(false);
    }

    @Override
    protected boolean validate() {
        if (!getBasename().endsWith("." + UPLOAD_FILE_EXTENSION)) {
            WindowUtil.alert("Invalid file type, please select a csv file");
            return false;
        }

        return true;
    }

    @Override
    protected List<UrlRequestParameter> getRequestParameters() {
        return null;
    }

}
