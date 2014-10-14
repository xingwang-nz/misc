package nz.co.xingsoft.gwt.sample.client.view.menu;

import com.google.gwt.user.client.ui.IsWidget;

public interface HeaderMenuView extends IsWidget {

    public interface Presenter {

    }

    void setPresenter(Presenter presenter);

    // void setHeaderDetails(String userName, String organisationName, String facilityName);

}
