package nz.co.xingsoft.gwt.sample.client.mvp;

import nz.co.ecngroup.shared.web.common.activity.ParameterableActivity;
import nz.co.xingsoft.gwt.sample.client.view.menu.FooterMenuView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class FooterMenuActivity
        extends ParameterableActivity
        implements FooterMenuView.Presenter {

    @Inject
    private FooterMenuView view;

    public FooterMenuActivity() {
    }

    public FooterMenuActivity(final FooterMenuView view) {
        this.view = view;
    }

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view);
    }

    @Override
    public void gotoMainSite() {
        Window.open("http://www.yellow.co.nz", "_blank", "");

    }

    @Override
    public void gotoTermsAndCondition() {
        Window.open(GWT.getHostPageBaseURL().replaceAll("/SupplyChainPortal", "") + "josso/RTL_terms.html", "_blank", "");

    }

}
