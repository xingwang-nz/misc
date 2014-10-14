package nz.co.xingsoft.gwt.sample.client.mvp;

import nz.co.ecngroup.shared.web.common.activity.ParameterableActivity;
import nz.co.xingsoft.gwt.sample.client.view.menu.HeaderMenuView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class HeaderMenuActivity
        extends ParameterableActivity
        implements HeaderMenuView.Presenter {

    @Inject
    private HeaderMenuView view;

    @Inject
    private EventBus eventBus;

    private HandlerRegistration handlerRegistration;

    public HeaderMenuActivity() {
    }

    public HeaderMenuActivity(final HeaderMenuView view) {
        this.view = view;
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {

        // view.setHeaderDetails("", "Yellow", "");
        view.setPresenter(this);
        panel.setWidget(view);
    }

    @Override
    public void onCancel() {
        unRegsterHandler();
    }

    @Override
    public void onStop() {
        unRegsterHandler();
    }

    private void unRegsterHandler() {
        if (handlerRegistration != null) {
            handlerRegistration.removeHandler();
        }
    }

}
