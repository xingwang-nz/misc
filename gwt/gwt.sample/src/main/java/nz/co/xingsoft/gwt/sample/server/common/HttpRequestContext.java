package nz.co.xingsoft.gwt.sample.server.common;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestContext {
    private final Map<String, String> contextMap = new HashMap<String, String>();

    public void addParameter(final String name, final String value) {
        contextMap.put(name, value);
    }

    public Long getParameterAsLong(final String name) {
        return contextMap.get(name) != null ? Long.parseLong(contextMap.get(name)) : null;
    }

    public String getParameter(final String name) {
        return contextMap.get(name);
    }

    public Integer getParameterAsInt(final String name) {
        return contextMap.get(name) != null ? Integer.parseInt(contextMap.get(name)) : null;
    }

    public boolean getParameterAsBoolean(final String name) {
        return contextMap.get(name) != null ? Boolean.parseBoolean(contextMap.get(name)) : false;
    }
}
