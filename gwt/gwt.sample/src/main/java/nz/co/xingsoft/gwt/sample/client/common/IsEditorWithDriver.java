package nz.co.xingsoft.gwt.sample.client.common;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;

public interface IsEditorWithDriver<T> extends HasEditorDriver<T>, IsWidget, Editor<T> {
}
