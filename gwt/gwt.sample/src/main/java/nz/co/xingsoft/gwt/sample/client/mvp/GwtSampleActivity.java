package nz.co.xingsoft.gwt.sample.client.mvp;

import nz.co.ecngroup.shared.web.common.activity.ParameterableActivity;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public abstract class GwtSampleActivity
        extends ParameterableActivity {

    @Inject
    protected PlaceController placeController;

}
