package nz.co.xingsoft.gwt.sample.client.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import nz.co.ecngroup.shared.web.common.event.rpc.RpcExceptionEvent;
import nz.co.ecngroup.shared.web.common.event.rpc.RpcExceptionEventHandlerDispatcher;
import nz.co.ecngroup.shared.web.widgets.util.WindowUtil;
import nz.co.xingsoft.gwt.sample.shared.validation.exception.ConstraintViolationException;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class RpcEventHandlerImpl
        implements RpcExceptionEventHandlerDispatcher {

    @Inject
    private EventBus eventBus;

    @Override
    public void registerEventHandlers() {
        eventBus.addHandler(RpcExceptionEvent.TYPE, this);
    }

    @Override
    public void onRpcException(final RpcExceptionEvent event) {
        WindowUtil.hideSpinner();
        final Throwable exception = event.getCaught();

        if (exception instanceof ConstraintViolationException) {
            final Set<ConstraintViolation<Object>> violations = ((ConstraintViolationException) exception).getConstraintViolations();
            final List messages = new ArrayList<String>();
            for (final ConstraintViolation violation : violations) {
                messages.add(violation.getMessage());
            }
            WindowUtil.alert(messages);
        } else {
            WindowUtil.alert("generic exception: " + exception.getMessage());
        }

    }

}
