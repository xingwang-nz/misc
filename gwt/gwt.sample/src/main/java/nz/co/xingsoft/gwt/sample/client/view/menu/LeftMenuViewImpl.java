package nz.co.xingsoft.gwt.sample.client.view.menu;

import nz.co.xingsoft.gwt.sample.client.view.resource.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class LeftMenuViewImpl extends Composite implements LeftMenuView {

    private static LeftMenuViewImplUiBinder uiBinder = GWT.create(LeftMenuViewImplUiBinder.class);
    @UiField
    SimpleLayoutPanel pnlLeftMain;

    @UiTemplate("LeftMenuViewImpl.ui.xml")
    interface LeftMenuViewImplUiBinder extends UiBinder<Widget, LeftMenuViewImpl> {
    }

    private Presenter leftMenuViewPresenter;

    private FlowPanel imageCrawlerMenuPanel;

    private StackLayoutPanel stackPanel;

    GlobalResources resources = GWT.create(GlobalResources.class);

    public LeftMenuViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        stackPanel = new StackLayoutPanel(Unit.EM);
        stackPanel.getElement().getStyle().setBackgroundColor("#B3E8F9");

        // HTML mturkPanel = new HTML(
        // "The Amazon Mechanical Turk (MTurk) is a crowdsourcing Internet marketplace that enables computer programmers (known as Requesters) to co-ordinate the use of human intelligence to perform tasks that computers are currently unable to do. It is one of the suites of Amazon Web Services.");

        FlowPanel mturkPanel = new FlowPanel();
        mturkPanel.getElement().getStyle().setPaddingTop(5, Unit.PX);
        mturkPanel.add(new Image(resources.mturk()));
        mturkPanel.getElement().getStyle().setBackgroundColor("#B3E8F9");
        stackPanel.add(mturkPanel, new HTML("Amazon MTurk"), 2);

        imageCrawlerMenuPanel = new FlowPanel();
        imageCrawlerMenuPanel.getElement().getStyle().setBackgroundColor("#B3E8F9");
        imageCrawlerMenuPanel.getElement().getStyle().setPaddingLeft(25, Unit.PX);
        imageCrawlerMenuPanel.getElement().getStyle().setPaddingTop(5, Unit.PX);
        imageCrawlerMenuPanel.add(new Image(resources.spiderWeb()));
        stackPanel.add(imageCrawlerMenuPanel, new HTML("Image Crawler"), 2);

        HTML otherPanel = new HTML("Other ....");
        otherPanel.getElement().getStyle().setBackgroundColor("#B3E8F9");
        stackPanel.add(otherPanel, new HTML("Other"), 2);

        // stackPanel.setHeight("200px");
        pnlLeftMain.add(stackPanel);

        stackPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(final SelectionEvent<Integer> event) {
                leftMenuViewPresenter.openSelectedStack(event.getSelectedItem());
            }
        });
    }

    @Override
    public void setPresenter(final Presenter presenter) {
        this.leftMenuViewPresenter = presenter;
    }

    @Override
    public void openStack(final int stackItem) {
        stackPanel.showWidget(stackItem, false);
    }

}
