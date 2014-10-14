package nz.co.xingsoft.gwt.sample.shared.dto.imagecrawler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageResult implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3700416616950646009L;
    private boolean successful;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(final boolean successful) {
        this.successful = successful;
    }

    private int number;

    private List<String> names = new ArrayList<String>();

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(final List<String> names) {
        this.names = names;
    }

    public void addName(final String name) {
        this.names.add(name);
    }

}
