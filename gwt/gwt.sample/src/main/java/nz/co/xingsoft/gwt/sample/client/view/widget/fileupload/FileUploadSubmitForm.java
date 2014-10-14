package nz.co.xingsoft.gwt.sample.client.view.widget.fileupload;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Widget;

/**
 * This Submit form is a copied implementation of <code>Uploader.FormFlowPanel</code> It overrides <code>submit</code><br/>
 * method to call <code>CommonFileUploader.validate</code> to provide generic callback validation<br>
 * Actually the standard <code>FormPanel</code> also works with submit method overridden
 */
public class FileUploadSubmitForm extends FormPanel {
    private CommonFileUploader fileUploader;

    private final FlowPanel formElements = new FlowPanel();

    public FileUploadSubmitForm() {
        super.add(formElements);
        formElements.setStyleName("upld-form-elements");
    }

    @Override
    public void add(final Widget w) {
        if (w instanceof Hidden) {
            formElements.insert(w, 0);
        } else {
            formElements.add(w);
        }
    }

    public void add(final Widget w, final int index) {
        formElements.insert(w, Math.max(0, Math.min(index, formElements.getWidgetCount())));
    }

    @Override
    public void clear() {
        formElements.clear();
        if (this.fileUploader != null) {
            add(fileUploader.getFileInput().getWidget());
        }
    }

    @Override
    public void submit() {
        if (fileUploader == null || fileUploader.validate()) {
            fileUploader.setUploadUrl();
            super.submit();
        } else {
            fileUploader.cancel();
        }
    }

    public void setFileUploader(final CommonFileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }
}
