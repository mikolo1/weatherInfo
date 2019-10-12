package pl.mikolo.filters;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private static final String UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String encoding;


    @Override
    public void init(FilterConfig config) throws ServletException {

        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = UTF8;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(UTF8);
        chain.doFilter(request, response);
    }
}
