package nz.co.xingsoft.gwt.sample.client.view.menu;

import com.google.gwt.user.client.ui.IsWidget;

public interface LeftMenuView extends IsWidget {

    public interface Presenter {
        void openSelectedStack(final int stackNo);
    }

    void setPresenter(Presenter presenter);

    void openStack(int stackItem);

}
