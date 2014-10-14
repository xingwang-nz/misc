package nz.co.xingsoft.gwt.sample.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class AppLayoutImpl extends Composite implements AppLayout, IsWidget {

    private static AppLayoutImplUiBinder uiBinder = GWT.create(AppLayoutImplUiBinder.class);
    @UiField
    FlowPanel northPanel;
    @UiField
    FlowPanel southPanel;
    @UiField
    SimpleLayoutPanel centerPanel;

    private DockLayoutPanel splitPanel = new DockLayoutPanel(Unit.PX);
    // private DockLayoutPanel splitPanel = new SplitLayoutPanel();

    private SimplePanel leftMenuPanel = new SimplePanel();
    private SimplePanel contentPanel = new SimplePanel();

    private SimplePanel headerInnerPanel;
    private SimplePanel footerInnerPanel;

    @UiTemplate("AppLayoutImpl.ui.xml")
    interface AppLayoutImplUiBinder extends UiBinder<Widget, AppLayoutImpl> {
    }

    public AppLayoutImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        headerInnerPanel = new SimpleLayoutPanel();
        headerInnerPanel.setSize("100%", "100%");
        northPanel.add(headerInnerPanel);

        footerInnerPanel = new SimpleLayoutPanel();
        footerInnerPanel.setSize("100%", "25px");
        southPanel.add(footerInnerPanel);
        southPanel.getElement().getStyle().setOverflowY(Overflow.VISIBLE);

        centerPanel.add(splitPanel);

        splitPanel.addWest(leftMenuPanel, 310);
        splitPanel.add(contentPanel);

    }

    @Override
    public Widget getMainLayoutPanel() {
        return this;
    }

    @Override
    public AcceptsOneWidget getHeaderMenuLayoutPanel() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(final IsWidget w) {
                final Widget widget = Widget.asWidgetOrNull(w);
                headerInnerPanel.clear();
                headerInnerPanel.setWidget(widget);
            }
        };
    }

    @Override
    public AcceptsOneWidget getFooterMenuLayoutPanel() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(final IsWidget w) {
                final Widget widget = Widget.asWidgetOrNull(w);
                footerInnerPanel.clear();
                footerInnerPanel.setWidget(widget);
            }
        };
    }

    @Override
    public AcceptsOneWidget getContentLayoutPanel() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(final IsWidget w) {
                final Widget widget = Widget.asWidgetOrNull(w);
                contentPanel.clear();
                contentPanel.setWidget(widget);
            }
        };
    }

    @Override
    public AcceptsOneWidget getLeftMenuLayoutPanel() {
        return new AcceptsOneWidget() {
            @Override
            public void setWidget(final IsWidget w) {
                final Widget widget = Widget.asWidgetOrNull(w);
                leftMenuPanel.clear();
                leftMenuPanel.setWidget(widget);
            }
        };
    }

}
