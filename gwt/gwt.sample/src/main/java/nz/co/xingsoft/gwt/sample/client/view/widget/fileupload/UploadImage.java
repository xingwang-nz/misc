package nz.co.xingsoft.gwt.sample.client.view.widget.fileupload;

import nz.co.xingsoft.gwt.sample.client.view.resource.GlobalResources;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.user.client.ui.Image;

public class UploadImage extends Image {
    public UploadImage() {
        super(GlobalResources.INSTANCE.uploadImg());
        getElement().getStyle().setCursor(Cursor.POINTER);
    }
}
