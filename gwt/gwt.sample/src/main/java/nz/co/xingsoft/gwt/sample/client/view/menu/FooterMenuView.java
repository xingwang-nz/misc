package nz.co.xingsoft.gwt.sample.client.view.menu;

import com.google.gwt.user.client.ui.IsWidget;

public interface FooterMenuView extends IsWidget {

    public interface Presenter {
        void gotoMainSite();

        void gotoTermsAndCondition();
    }

    void setPresenter(Presenter presenter);
}
