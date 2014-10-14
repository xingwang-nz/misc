package nz.co.xingsoft.gwt.sample.client.view.imagecrawl;

import java.util.List;

import nz.co.ecngroup.shared.web.common.IView;
import nz.co.xingsoft.gwt.sample.shared.dto.imagecrawler.ImageResult;

public interface ImageCrawlView
        extends IView {
    public interface Presenter {
        void startCrawler(String domain, int depth);

        void viewImages();
    }

    public void setPresenter(Presenter presenter);

    void startCrawling();

    void completeCrawling(ImageResult result);

    void clearImagePanel();

    void addImages(List<String> imgUrls);

}
