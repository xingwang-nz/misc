package nz.co.xingsoft.gwt.sample.client.view.mturk;

import nz.co.ecngroup.shared.web.widgets.table.PagerDataGrid;
import nz.co.ecngroup.shared.web.widgets.table.SortableTextHeader;
import nz.co.ecngroup.shared.web.widgets.table.event.OnMouseOverRowEvent;
import nz.co.ecngroup.shared.web.widgets.table.event.OnMouseOverRowEventHandler;
import nz.co.ecngroup.shared.web.widgets.util.WindowUtil;
import nz.co.xingsoft.gwt.sample.client.view.widget.fileupload.FileUploadEvent;
import nz.co.xingsoft.gwt.sample.client.view.widget.fileupload.FileUploadEventHandler;
import nz.co.xingsoft.gwt.sample.shared.dto.yellow.CrawlBatchDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class MTurkViewImpl
        extends Composite
        implements IsWidget, MTurkView {

    private static MTurkViewImplUiBinder uiBinder = GWT.create(MTurkViewImplUiBinder.class);

    private Presenter presenter;

    @UiField(provided = true)
    PagerDataGrid<CrawlBatchDto> batchTable = new PagerDataGrid<CrawlBatchDto>(10);
    @UiField
    FlowPanel uploadPanel;

    interface MTurkViewImplUiBinder
            extends UiBinder<Widget, MTurkViewImpl> {
    }

    public MTurkViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        initUploadPanel();
        buildBatchTable();
    }

    private void initUploadPanel() {
        final CrawlBatchFileUploader fileUploader = new CrawlBatchFileUploader();
        fileUploader.addFileUploadEventHandler(new FileUploadEventHandler() {
            @Override
            public void onSuccess(final FileUploadEvent event) {
                WindowUtil.alert(event.getFileName() + " has been uploaded successfully and is being processed");
            }

            @Override
            public void onFailure(final FileUploadEvent event) {
                WindowUtil.alert("Upload failed (status : " + event.getStatus() + ")");
            }
        });

        uploadPanel.add(fileUploader);

    }

    private void buildBatchTable() {
        batchTable.addOnMouseOverRowEventHandler(new OnMouseOverRowEventHandler() {
            @Override
            public void onMouseOverRow(final OnMouseOverRowEvent event) {
                DOM.setStyleAttribute(getElement(), "cursor", "pointer");
            }
        });

        final TextColumn<CrawlBatchDto> idCol = new TextColumn<CrawlBatchDto>() {

            @Override
            public String getValue(final CrawlBatchDto dto) {
                return dto.getId().toString();
            }
        };
        batchTable.addColumn(idCol, new SortableTextHeader("Batch ID", "batchId"));

        final TextColumn<CrawlBatchDto> createdDateCol = new TextColumn<CrawlBatchDto>() {

            @Override
            public String getValue(final CrawlBatchDto dto) {
                return DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss").format(dto.getCreatedDateTime());
            }
        };
        batchTable.addColumn(createdDateCol, new SortableTextHeader("Created At", "createdAt"));

        final TextColumn<CrawlBatchDto> statusCol = new TextColumn<CrawlBatchDto>() {

            @Override
            public String getValue(final CrawlBatchDto dto) {
                return dto.getStatus();
            }
        };
        batchTable.addColumn(statusCol, new SortableTextHeader("Status", "status"));

        batchTable.setWidth("100%");

    }

    @Override
    public void onCloseView() {

    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;

    }

}
