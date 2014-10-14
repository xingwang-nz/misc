package nz.co.xingsoft.gwt.sample.client.view.other;

import nz.co.xingsoft.gwt.sample.client.common.IsEditorWithDriver;
import nz.co.xingsoft.gwt.sample.shared.dto.UserDto;

public interface OtherPanelView extends IsEditorWithDriver<UserDto> {
    public interface Presenter {
        void validateGroup1();

        void validateGroup2();

    }

    void setPresenter(Presenter presenter);

}
