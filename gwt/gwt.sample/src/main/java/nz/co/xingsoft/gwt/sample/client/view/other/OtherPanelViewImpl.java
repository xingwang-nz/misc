package nz.co.xingsoft.gwt.sample.client.view.other;

import nz.co.xingsoft.gwt.sample.shared.dto.UserDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorDriver;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OtherPanelViewImpl extends Composite implements OtherPanelView {

    private static ContentPanelViewImplUiBinder uiBinder = GWT.create(ContentPanelViewImplUiBinder.class);

    interface ContentPanelViewImplUiBinder extends UiBinder<Widget, OtherPanelViewImpl> {
    }

    interface ContentDriver extends SimpleBeanEditorDriver<UserDto, OtherPanelViewImpl> {
    }

    private final ContentDriver driver = GWT.create(ContentDriver.class);

    private Presenter presenter;

    @UiField
    TextBox name;

    @UiField
    TextBox email;

    @UiField
    TextBox mobile;

    @UiField
    TextBox homePhone;

    public OtherPanelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
        driver.edit(new UserDto());
    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;

    }

    @UiHandler("btnValidate1")
    void onBtnValidate1Click(final ClickEvent event) {
        presenter.validateGroup1();
    }

    @UiHandler("btnValidate2")
    void onBtnValidate2Click(final ClickEvent event) {
        presenter.validateGroup2();
    }

    @Override
    public EditorDriver<UserDto> getEditorDriver() {
        return driver;
    }

    @Override
    public void edit(final UserDto dto) {
        driver.edit(dto);

    }

}
