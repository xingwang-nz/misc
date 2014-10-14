package nz.co.xingsoft.gwt.sample.client.mvp;

import nz.co.ecngroup.shared.web.common.AbstractPlace;
import nz.co.ecngroup.shared.web.common.activity.ParameterableActivity;
import nz.co.xingsoft.gwt.sample.client.view.imagecrawl.ImageCrawlPalce;
import nz.co.xingsoft.gwt.sample.client.view.menu.LeftMenuView;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkPlace;
import nz.co.xingsoft.gwt.sample.client.view.other.OtherPlace;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class LeftMenuActivity
        extends ParameterableActivity
        implements LeftMenuView.Presenter {

    @Inject
    private LeftMenuView view;
    @Inject
    private PlaceController placeController;
    @Inject
    private EventBus eventBus;

    private HandlerRegistration handlerRegistration;
    private Place previousPlace;

    public LeftMenuActivity() {
    }

    public LeftMenuActivity(final LeftMenuView view, final PlaceController placeController) {
        this.placeController = placeController;
        this.view = view;
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view);
        this.eventBus = eventBus;
        openStack(getPlace());
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

    @Override
    public void openSelectedStack(final int stackNo) {
        switch (stackNo) {
        case SelectedStack.MTURK:
            placeController.goTo(new MTurkPlace());
            break;
        case SelectedStack.IMAGE_CRAWL:
            placeController.goTo(new ImageCrawlPalce());
            break;
        case SelectedStack.OTHER:
            placeController.goTo(new OtherPlace());
            break;
        default:
            placeController.goTo(new MTurkPlace());
        }

    }

    private void openStack(final AbstractPlace place) {
        int stackItem = SelectedStack.MTURK;
        if (place instanceof MTurkPlace) {
            stackItem = SelectedStack.MTURK;
        } else if (place instanceof ImageCrawlPalce) {
            stackItem = SelectedStack.IMAGE_CRAWL;
        } else if (place instanceof OtherPlace) {
            stackItem = SelectedStack.OTHER;
        }

        view.openStack(stackItem);

    }

    private static class SelectedStack {
        public static final int MTURK = 0;
        public static final int IMAGE_CRAWL = 1;
        public static final int OTHER = 2;
    }
}
