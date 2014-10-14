package nz.co.xingsoft.gwt.sample.server.common;

import java.io.File;

public interface FolderResolver<T> {
    File locateFolder(T t);
}
