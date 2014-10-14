package nz.co.xingsoft.gwt.sample.client;

import nz.co.xingsoft.gwt.sample.client.view.AppLayout;
import nz.co.xingsoft.gwt.sample.injector.GwtSampleGinInjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtSample implements EntryPoint {

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        final GwtSampleGinInjector injector = GWT.create(GwtSampleGinInjector.class);
        final AppLayout appLayout = injector.getAppLayout();
        RootLayoutPanel.get().add(appLayout.getMainLayoutPanel());
        injector.getHistoryHandler().handleCurrentHistory();
        injector.getRpcExceptionEventHandlerDispatcher().registerEventHandlers();
    }
}
