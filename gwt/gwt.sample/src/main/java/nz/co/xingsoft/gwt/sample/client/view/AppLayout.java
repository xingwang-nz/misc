package nz.co.xingsoft.gwt.sample.client.view;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

public interface AppLayout {

    AcceptsOneWidget getHeaderMenuLayoutPanel();

    AcceptsOneWidget getFooterMenuLayoutPanel();

    AcceptsOneWidget getLeftMenuLayoutPanel();

    AcceptsOneWidget getContentLayoutPanel();

    Widget getMainLayoutPanel();

}
