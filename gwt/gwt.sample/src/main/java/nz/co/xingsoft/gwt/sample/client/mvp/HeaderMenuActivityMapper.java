package nz.co.xingsoft.gwt.sample.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class HeaderMenuActivityMapper implements ActivityMapper {

    @Inject
    private Provider<HeaderMenuActivity> headerMenuActivity;

    @Override
    public Activity getActivity(final Place place) {
        return headerMenuActivity.get();
    }
}
