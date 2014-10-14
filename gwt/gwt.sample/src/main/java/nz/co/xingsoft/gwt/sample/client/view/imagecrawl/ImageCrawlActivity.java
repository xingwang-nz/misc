package nz.co.xingsoft.gwt.sample.client.view.imagecrawl;

import java.util.List;

import nz.co.ecngroup.shared.web.common.AbstractAsyncCallback;
import nz.co.ecngroup.shared.web.widgets.util.WindowUtil;
import nz.co.xingsoft.gwt.sample.client.mvp.GwtSampleActivity;
import nz.co.xingsoft.gwt.sample.client.view.imagecrawl.ImageCrawlView.Presenter;
import nz.co.xingsoft.gwt.sample.services.GwtSampleServiceAsync;
import nz.co.xingsoft.gwt.sample.shared.dto.imagecrawler.ImageResult;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ImageCrawlActivity
        extends GwtSampleActivity
        implements Presenter {

    @Inject
    private ImageCrawlView view;

    @Inject
    private GwtSampleServiceAsync service;

    private EventBus eventBus;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        this.eventBus = eventBus;
        view.setPresenter(this);
        panel.setWidget(view);

    }

    @Override
    public void startCrawler(final String domain, final int depth) {
        if (domain == null || domain.trim().equals("")) {
            WindowUtil.alert("Please enter the domain");
            return;
        }

        view.startCrawling();
        view.clearImagePanel();
        WindowUtil.showSpinner();
        service.crawl(domain.trim(), depth, new AbstractAsyncCallback<ImageResult>(eventBus) {
            @Override
            public void success(final ImageResult result) {
                WindowUtil.info("Crawling " + (result.isSuccessful() ? "completed, click View Image button to browse the images" : "falied"));
                view.completeCrawling(result);
                WindowUtil.hideSpinner();

            }

            @Override
            public void failure(final Throwable caught) {
                super.failure(caught);
                WindowUtil.hideSpinner();
            }
        });

    }

    @Override
    public void viewImages() {
        service.vieImages(new AbstractAsyncCallback<List<String>>(eventBus) {

            @Override
            protected void success(final List<String> response) {
                view.clearImagePanel();
                view.addImages(response);
                if (response.size() == 0) {
                    WindowUtil.info("No images available");
                }
            }

        });

    }

}
