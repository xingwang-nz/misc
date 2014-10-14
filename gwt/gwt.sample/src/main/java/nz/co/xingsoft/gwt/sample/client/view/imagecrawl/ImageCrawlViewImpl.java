package nz.co.xingsoft.gwt.sample.client.view.imagecrawl;

import java.util.List;

import nz.co.xingsoft.gwt.sample.shared.dto.imagecrawler.ImageResult;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ImageCrawlViewImpl extends Composite implements ImageCrawlView {

    private static ImageCrawlViewImplUiBinder uiBinder = GWT.create(ImageCrawlViewImplUiBinder.class);

    interface ImageCrawlViewImplUiBinder extends UiBinder<Widget, ImageCrawlViewImpl> {
    }

    private Presenter presenter;

    @UiField
    TextBox domain;

    @UiField
    Button btnCrawl;

    @UiField
    Label labelWait;

    @UiField
    Button btnView;

    @UiField
    ScrollPanel imagePanel;

    @UiField
    ListBox depth;

    @UiField
    Label labelResult;

    private FlowPanel imagePanelHolder;

    public ImageCrawlViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void onCloseView() {

    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;

    }

    @UiHandler("btnCrawl")
    void onBtnCrawlClick(final ClickEvent event) {
        launchCrawling();
    }

    // @UiHandler("domain")
    // void onDomainKeyPress(final KeyPressEvent event) {
    // if (KeyCodes.KEY_ENTER == event.getNativeEvent().getCharCode()) {
    // launchCrawling();
    // }
    // }

    @UiHandler("domain")
    void onDomainKeyDown(final KeyDownEvent event) {
        if (KeyCodes.KEY_ENTER == event.getNativeEvent().getCharCode()) {
            launchCrawling();
        }
    }

    private void launchCrawling() {
        presenter.startCrawler(domain.getText(), Integer.parseInt(depth.getItemText(depth.getSelectedIndex())));
    }

    @UiHandler("btnView")
    void onBtnViewClick(final ClickEvent event) {
        presenter.viewImages();
    }

    @Override
    public void startCrawling() {
        btnCrawl.setEnabled(false);
        labelWait.getElement().getStyle().setDisplay(Display.BLOCK);
        labelResult.getElement().getStyle().setDisplay(Display.NONE);
        btnView.setEnabled(false);
        domain.setEnabled(false);
    }

    @Override
    public void completeCrawling(final ImageResult imageResult) {
        btnCrawl.setEnabled(true);
        btnView.setEnabled(true);
        domain.setEnabled(true);
        addImages(imageResult.getNames());

    }

    private void showImageResultLabel(final int number) {
        labelWait.getElement().getStyle().setDisplay(Display.NONE);
        labelResult.getElement().getStyle().setDisplay(Display.BLOCK);
        labelResult.setText(number + " images found");
    }

    @Override
    public void clearImagePanel() {
        if (imagePanelHolder != null) {
            imagePanelHolder.removeFromParent();
            imagePanelHolder = null;
        }
    }

    @Override
    public void addImages(final List<String> imageFilenames) {
        if (imagePanelHolder == null) {
            imagePanelHolder = new FlowPanel();
        }
        imagePanel.add(imagePanelHolder);

        showImageResultLabel(imageFilenames.size());

        for (String imageName : imageFilenames) {
            imagePanelHolder.add(new Image(GWT.getModuleBaseURL() + "web-crawl-images/" + imageName));
        }

    }

}
