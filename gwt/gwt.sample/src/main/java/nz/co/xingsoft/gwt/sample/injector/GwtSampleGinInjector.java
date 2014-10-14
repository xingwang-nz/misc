package nz.co.xingsoft.gwt.sample.injector;

import nz.co.ecngroup.shared.web.common.event.rpc.RpcExceptionEventHandlerDispatcher;
import nz.co.xingsoft.gwt.sample.client.view.AppLayout;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;

@GinModules({ GwtSampleGinModuleConfiguration.class })
public interface GwtSampleGinInjector
        extends Ginjector {
    PlaceHistoryHandler getHistoryHandler();

    AppLayout getAppLayout();

    RpcExceptionEventHandlerDispatcher getRpcExceptionEventHandlerDispatcher();
}
