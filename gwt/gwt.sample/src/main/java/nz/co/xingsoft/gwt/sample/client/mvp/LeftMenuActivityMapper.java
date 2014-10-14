package nz.co.xingsoft.gwt.sample.client.mvp;

import nz.co.ecngroup.shared.web.common.AbstractPlace;
import nz.co.ecngroup.shared.web.common.activity.ActivityProxy;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class LeftMenuActivityMapper
        implements ActivityMapper {

    @Inject
    private Provider<LeftMenuActivity> leftMenuActivityProvider;

    @Inject
    private AsyncProvider<LeftMenuActivity> leftMenuActivity;

    @Override
    public Activity getActivity(final Place place) {
        // return leftMenuActivityProvider.get();
        return new ActivityProxy<LeftMenuActivity>(leftMenuActivity, (AbstractPlace) place);
    }
}
