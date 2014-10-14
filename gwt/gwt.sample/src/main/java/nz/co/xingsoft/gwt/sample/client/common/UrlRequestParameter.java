package nz.co.xingsoft.gwt.sample.client.common;

import com.google.gwt.http.client.URL;

public class UrlRequestParameter {
    private final String name;
    private final String value;

    public UrlRequestParameter(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public UrlRequestParameter(final String name, final Long value) {
        this.name = name;
        this.value = value == null ? "" : value.toString();
    }

    public UrlRequestParameter(final String name, final Integer value) {
        this.name = name;
        this.value = value == null ? "" : value.toString();
    }

    public UrlRequestParameter(final String name, final Boolean value) {
        this.name = name;
        this.value = value == null ? "" : value.toString();
    }

    public String toUrl() {
        return name + "=" + URL.encode(value);
    }
}
