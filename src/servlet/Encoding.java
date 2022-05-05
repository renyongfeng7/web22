package servlet;

import javax.servlet.*;
import java.io.IOException;

public class Encoding implements Filter {
    /*解决中文乱码*/
    String encoding;
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding= filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
