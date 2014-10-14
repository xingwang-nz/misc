package nz.co.xingsoft.gwt.sample.injector;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import nz.co.ecngroup.shared.web.common.event.rpc.RpcExceptionEventHandlerDispatcher;
import nz.co.xingsoft.gwt.sample.client.mvp.ContentPanelActivityMapper;
import nz.co.xingsoft.gwt.sample.client.mvp.FooterMenuActivityMapper;
import nz.co.xingsoft.gwt.sample.client.mvp.GwtSamplePlaceHistoryMapper;
import nz.co.xingsoft.gwt.sample.client.mvp.HeaderMenuActivityMapper;
import nz.co.xingsoft.gwt.sample.client.mvp.LeftMenuActivityMapper;
import nz.co.xingsoft.gwt.sample.client.mvp.PermanentlyCachingActivityMapper;
import nz.co.xingsoft.gwt.sample.client.rpc.RpcEventHandlerImpl;
import nz.co.xingsoft.gwt.sample.client.view.AppLayout;
import nz.co.xingsoft.gwt.sample.client.view.AppLayoutImpl;
import nz.co.xingsoft.gwt.sample.client.view.imagecrawl.ImageCrawlView;
import nz.co.xingsoft.gwt.sample.client.view.imagecrawl.ImageCrawlViewImpl;
import nz.co.xingsoft.gwt.sample.client.view.menu.FooterMenuView;
import nz.co.xingsoft.gwt.sample.client.view.menu.FooterMenuViewImpl;
import nz.co.xingsoft.gwt.sample.client.view.menu.HeaderMenuView;
import nz.co.xingsoft.gwt.sample.client.view.menu.HeaderMenuViewImpl;
import nz.co.xingsoft.gwt.sample.client.view.menu.LeftMenuView;
import nz.co.xingsoft.gwt.sample.client.view.menu.LeftMenuViewImpl;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkPlace;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkView;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkViewImpl;
import nz.co.xingsoft.gwt.sample.client.view.other.OtherPanelView;
import nz.co.xingsoft.gwt.sample.client.view.other.OtherPanelViewImpl;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GwtSampleGinModuleConfiguration
        extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(Place.class).to(MTurkPlace.class).in(Singleton.class); // default place
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceHistoryMapper.class).to(GwtSamplePlaceHistoryMapper.class).in(Singleton.class);
        bind(RpcExceptionEventHandlerDispatcher.class).to(RpcEventHandlerImpl.class).in(Singleton.class);

        bind(LeftMenuView.class).to(LeftMenuViewImpl.class).in(Singleton.class);
        bind(HeaderMenuView.class).to(HeaderMenuViewImpl.class).in(Singleton.class);
        bind(FooterMenuView.class).to(FooterMenuViewImpl.class).in(Singleton.class);

        bind(MTurkView.class).to(MTurkViewImpl.class).in(Singleton.class);
        bind(OtherPanelView.class).to(OtherPanelViewImpl.class).in(Singleton.class);
        bind(ImageCrawlView.class).to(ImageCrawlViewImpl.class).in(Singleton.class);

    }

    @Provides
    @Singleton
    PlaceHistoryHandler getHistoryHandler(final PlaceController placeController, final PlaceHistoryMapper historyMapper, final EventBus eventBus,
            final Place defaultPlace) {
        final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);
        return historyHandler;
    }

    @Provides
    @Singleton
    PlaceController getPlaceController(final EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    AppLayout getAppLayout(final EventBus eventBus, final ContentPanelActivityMapper contentActivityMapper,
            final HeaderMenuActivityMapper headerMenuActivityMapper, final LeftMenuActivityMapper leftMenuActivityMapper,
            final FooterMenuActivityMapper footerMenuActivityMapper, final Place defaultPalce) {

        final AppLayout appLayout = new AppLayoutImpl();

        // HEADER PANEL
        ActivityManager manager = new ActivityManager(new PermanentlyCachingActivityMapper(headerMenuActivityMapper), eventBus);
        manager.setDisplay(appLayout.getHeaderMenuLayoutPanel());
        //
        // // LEFT MENU
        manager = new ActivityManager(new PermanentlyCachingActivityMapper(leftMenuActivityMapper), eventBus);
        manager.setDisplay(appLayout.getLeftMenuLayoutPanel());
        //
        // // FOOTER
        manager = new ActivityManager(new PermanentlyCachingActivityMapper(footerMenuActivityMapper), eventBus);
        manager.setDisplay(appLayout.getFooterMenuLayoutPanel());

        // CONTENT PANEL
        manager = new ActivityManager(contentActivityMapper, eventBus);
        manager.setDisplay(appLayout.getContentLayoutPanel());

        return appLayout;
    }

    @Provides
    @Singleton
    ValidatorFactory getValidatorFactory() {
        return Validation.byDefaultProvider().configure().buildValidatorFactory();
    }

}
