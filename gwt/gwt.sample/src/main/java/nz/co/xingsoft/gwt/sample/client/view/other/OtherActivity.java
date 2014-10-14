package nz.co.xingsoft.gwt.sample.client.view.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import nz.co.ecngroup.shared.web.common.AbstractAsyncCallback;
import nz.co.ecngroup.shared.web.widgets.util.WindowUtil;
import nz.co.xingsoft.gwt.sample.client.mvp.GwtSampleActivity;
import nz.co.xingsoft.gwt.sample.client.view.other.OtherPanelView.Presenter;
import nz.co.xingsoft.gwt.sample.services.GwtSampleServiceAsync;
import nz.co.xingsoft.gwt.sample.shared.dto.UserDto;
import nz.co.xingsoft.gwt.sample.shared.validation.group.ValidationGroup2;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class OtherActivity
        extends GwtSampleActivity
        implements Presenter {

    @Inject
    private OtherPanelView view;

    @Inject
    private GwtSampleServiceAsync service;

    @Inject
    private ValidatorFactory validatorFactory;

    private EventBus eventBus;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        this.eventBus = eventBus;
        view.setPresenter(this);
        panel.setWidget(view);
    }

    @Override
    public void validateGroup1() {

        final UserDto dto = view.getEditorDriver().flush();
        //
        // final Validator validator = this.validatorFactory.getValidator();
        // final Set<ConstraintViolation<UserDto>> violations = validator.validate(dto, ValidationGroup1.class);
        //
        // if (!violations.isEmpty()) {
        // final List<String> messages = new ArrayList<String>();
        // for (ConstraintViolation<UserDto> constraintViolation : violations) {
        // messages.add(constraintViolation.getMessage());
        // }
        // WindowUtil.alert(messages);
        // return;
        // }

        service.validateGroup1("dummy1", dto, new AbstractAsyncCallback<String>(eventBus) {
            @Override
            public void success(final String result) {
                WindowUtil.info("OK");
            }
        });

    }

    @Override
    public void validateGroup2() {
        final UserDto dto = view.getEditorDriver().flush();

        final Validator validator = this.validatorFactory.getValidator();
        final Set<ConstraintViolation<UserDto>> violations = validator.validate(dto, ValidationGroup2.class);

        if (!violations.isEmpty()) {
            final List<String> messages = new ArrayList<String>();
            for (final ConstraintViolation<UserDto> constraintViolation : violations) {
                messages.add(constraintViolation.getMessage());
            }
            WindowUtil.alert(messages);
            return;
        }

        service.validateGroup2(dto, "dummy2", new AbstractAsyncCallback<String>(eventBus) {
            @Override
            public void success(final String result) {
                WindowUtil.info("OK");
            }
        });

    }

}
