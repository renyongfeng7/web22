package servlet;

import com.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Filter_servlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        System.out.println("init go");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*转型*/
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        /*判断是否有登录成功标识*/
        User obj = (User) request.getSession().getAttribute("login");
        /*有登录标识通过*/
        if(obj!=null){
            filterChain.doFilter(request,response);
        }else{
            /*无登录标识*/
            /*得到请求的uri*/
            String uri = request.getRequestURI();
            System.out.println(uri);
            String [] obj1 = new String[]{"/login","/css/","/js/","/jq/"};
            Boolean flag = false;
            for (int i = 0; i < obj1.length; i++) {
                /*判断uri是否属于可通过*/
                if (uri.startsWith(obj1[i])) {
                    System.out.println(uri);
                    /*属于可通过uri*/
                    try {
                        filterChain.doFilter(request,response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    flag = true;
                    break;
                }
            }
            if (flag==false) {
                response.sendRedirect("login.jsp");
            }
        }
    }
    @Override
    public void destroy() {
        System.out.println("init destroy");

    }
}
