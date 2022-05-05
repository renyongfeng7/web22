package servlet;
import service.impl.NewsUserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class User extends HttpServlet {

    String opera = "";

    public void init(ServletConfig config) throws ServletException {
        opera = config.getInitParameter("opera");
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("userDelete".equals(opera)) {
            this.userDelete(request, response);
        }
        if("userManage".equals(opera)){
            this.userManage(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("userAdd".equals(opera)) {
            this.userAdd(request, response);
        }
    }

    protected void userDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idstr = request.getParameter("id");
        int id = Integer.parseInt(idstr);
        System.out.println("id---------" + id);
        NewsUserServiceImpl newsUserService = new NewsUserServiceImpl();
        int i = newsUserService.deleteById(id);
        if (i > 0) {
            response.getWriter().write("success");
        }

    }

    protected void userAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        com.bean.User user = new com.bean.User();
        NewsUserServiceImpl newsUserService = new NewsUserServiceImpl();
        System.out.println("userAdd");
        String realName = request.getParameter("realName");

        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        System.out.println(realName + "" + userName + passWord);
        user.setRealName(realName);
        user.setUserName(userName);
        user.setUserPwd(passWord);
        int i = newsUserService.insert(user);
        if (i > 0) {
            response.getWriter().write("y");
        }
    }

    protected void userManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer pagee =1;
        NewsUserServiceImpl newsUserService = new NewsUserServiceImpl();
        List<com.bean.User> list=  newsUserService.selectByPage(pagee,5);
        request.setAttribute("list",list);
        request.getRequestDispatcher("userManage.jsp").forward(request,response);

    }
}