package nz.co.xingsoft.gwt.sample.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * For caching menu activities. Note that this will only refresh the activity if done manually!
 * 
 * @see com.google.gwt.activity.shared.CachingActivityMapper for a mapper that caches only for the same place.
 * 
 * @author spaul
 * 
 */
public class PermanentlyCachingActivityMapper implements ActivityMapper {

    private ActivityMapper mapper;
    private Activity cachedActivity;

    public PermanentlyCachingActivityMapper(final ActivityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Activity getActivity(final Place place) {
        if (cachedActivity == null) {
            cachedActivity = mapper.getActivity(place);
        }
        return cachedActivity;
    }

    public void resetActivity() {
        cachedActivity = null;
    }
}
