package nz.co.xingsoft.gwt.sample.client.view.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface GlobalResources extends ClientBundle {

    GlobalResources INSTANCE = GWT.create(GlobalResources.class);

    @Source("nz/co/xingsoft/gwt/sample/publicresources/images/yellow-logo.png")
    ImageResource yellowLogo();

    @Source("nz/co/xingsoft/gwt/sample/publicresources/images/yellow-icon.jpg")
    ImageResource yellowIcon();

    @Source("nz/co/xingsoft/gwt/sample/publicresources/images/yellow-icon-small.jpg")
    ImageResource yellowIconSmall();

    @Source("nz/co/xingsoft/gwt/sample/publicresources/images/mturk.png")
    ImageResource mturk();

    @Source("nz/co/xingsoft/gwt/sample/publicresources/images/spider-web.jpg")
    ImageResource spiderWeb();

    @Source("nz/co/xingsoft/gwt/sample/publicresources/images/upload-32.png")
    ImageResource uploadImg();

}
