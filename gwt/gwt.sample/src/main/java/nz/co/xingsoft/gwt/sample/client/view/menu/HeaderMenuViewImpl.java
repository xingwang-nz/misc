package nz.co.xingsoft.gwt.sample.client.view.menu;

import nz.co.xingsoft.gwt.sample.client.view.resource.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HeaderMenuViewImpl extends Composite implements HeaderMenuView {

    private static HeaderMenuViewImplUiBinder uiBinder = GWT.create(HeaderMenuViewImplUiBinder.class);

    @UiField
    Image appLogo;

    GlobalResources resources = GWT.create(GlobalResources.class);

    @UiTemplate("HeaderMenuViewImpl.ui.xml")
    interface HeaderMenuViewImplUiBinder extends UiBinder<Widget, HeaderMenuViewImpl> {
    }

    @SuppressWarnings("unused")
    private Presenter headerMenuViewPresenter;

    public HeaderMenuViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        appLogo.setResource(resources.yellowLogo());
    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.headerMenuViewPresenter = presenter;

    }

}
