package nz.co.xingsoft.gwt.sample.client.view.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FooterMenuViewImpl extends Composite implements FooterMenuView {

    private Presenter footerMenuViewPresenter;
    private static FooterMenuViewImplUiBinder uiBinder = GWT.create(FooterMenuViewImplUiBinder.class);
    // @UiField
    // Anchor copyRightLbl;
    @UiField
    Label pipe1Lbl;
    // @UiField
    // Anchor termsAndConditionsLink;
    @UiField
    Label pipe2Lbl;
    @UiField
    Anchor poweredByEcnLink;

    interface FooterMenuViewImplUiBinder extends UiBinder<Widget, FooterMenuViewImpl> {
    }

    public FooterMenuViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.footerMenuViewPresenter = presenter;
    }

    @UiHandler("poweredByEcnLink")
    void onEcnSiteClick(final ClickEvent event) {
        footerMenuViewPresenter.gotoMainSite();
    }

    // @UiHandler("termsAndConditionsLink")
    // void onTACClick(final ClickEvent event) {
    // footerMenuViewPresenter.gotoTermsAndCondition();
    // }

}
