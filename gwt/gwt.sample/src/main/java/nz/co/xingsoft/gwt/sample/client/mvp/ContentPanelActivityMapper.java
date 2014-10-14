package nz.co.xingsoft.gwt.sample.client.mvp;

import nz.co.ecngroup.shared.web.common.activity.ActivityProxy;
import nz.co.xingsoft.gwt.sample.client.view.imagecrawl.ImageCrawlActivity;
import nz.co.xingsoft.gwt.sample.client.view.imagecrawl.ImageCrawlPalce;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkActivity;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkPlace;
import nz.co.xingsoft.gwt.sample.client.view.other.OtherActivity;
import nz.co.xingsoft.gwt.sample.client.view.other.OtherPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ContentPanelActivityMapper
        implements ActivityMapper {

    @Inject
    private AsyncProvider<OtherActivity> otherActivity;

    @Inject
    private AsyncProvider<MTurkActivity> mturkActivity;

    @Inject
    private AsyncProvider<ImageCrawlActivity> imageCrawlActivity;

    @Override
    public Activity getActivity(final Place place) {
        if (place instanceof MTurkPlace) {
            return new ActivityProxy<MTurkActivity>(mturkActivity, (MTurkPlace) place);
        } else if (place instanceof ImageCrawlPalce) {
            return new ActivityProxy<ImageCrawlActivity>(imageCrawlActivity, (ImageCrawlPalce) place);
        } else if (place instanceof OtherPlace) {
            return new ActivityProxy<OtherActivity>(otherActivity, (OtherPlace) place);
        }
        return null;
    }

}
