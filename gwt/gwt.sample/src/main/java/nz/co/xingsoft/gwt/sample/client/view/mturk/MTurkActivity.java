package nz.co.xingsoft.gwt.sample.client.view.mturk;

import nz.co.xingsoft.gwt.sample.client.mvp.GwtSampleActivity;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkView.Presenter;
import nz.co.xingsoft.gwt.sample.services.GwtSampleServiceAsync;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class MTurkActivity extends GwtSampleActivity implements Presenter {

    @Inject
    private MTurkView view;

    @Inject
    private GwtSampleServiceAsync service;
    private EventBus eventBus;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        this.eventBus = eventBus;
        view.setPresenter(this);
        panel.setWidget(view);

    }

}
