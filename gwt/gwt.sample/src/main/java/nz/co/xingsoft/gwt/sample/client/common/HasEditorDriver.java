package nz.co.xingsoft.gwt.sample.client.common;

import com.google.gwt.editor.client.EditorDriver;

public interface HasEditorDriver<T> {
    
    EditorDriver<T> getEditorDriver();
    
    void edit(T dto);
    
}
