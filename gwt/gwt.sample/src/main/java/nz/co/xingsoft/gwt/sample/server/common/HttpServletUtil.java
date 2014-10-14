package nz.co.xingsoft.gwt.sample.server.common;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;



import org.springframework.web.context.WebApplicationContext;

public class HttpServletUtil {
    public static WebApplicationContext getSpringContextFromServletContext(final ServletContext servletContext) {
        return (WebApplicationContext) servletContext
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }

    public static HttpRequestContext buildHttpRequestContext(final HttpServletRequest request) {
        final HttpRequestContext requestContext = new HttpRequestContext();
        final Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            final String name = parameterNames.nextElement();
            requestContext.addParameter(name, request.getParameter(name));
        }
        return requestContext;
    }
}
