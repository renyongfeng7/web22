package servlet;
import com.bean.User;
import service.impl.NewsUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login_Servlet extends HttpServlet {

    NewsUserServiceImpl Newsuser = new NewsUserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        String userPw = request.getParameter("userPw");
        String userPhone =  request.getParameter("userPhone");
        String remember = request.getParameter("remember");
        String last = request.getParameter("last");
        /*  get传递解决中文乱码 */
        userName = new String(userName.getBytes("UTF-8"));
    	 /*  session.setAttribute("hobby", hobby);
   		session.setAttribute("login1", userPw);
   		session.setAttribute("login2", userPhone); */
        /* 设置标识*/
        User user1 = Newsuser.select(userName, userPw);
        if(user1!=null){
            /**/
            request.getSession().setAttribute("login", user1);
            String UserName = user1.getUserName();
            String UserPwd = user1.getUserPwd();
            if(userName.equals(UserName)&&userPw.equals(UserPwd)){
                /*cookie */
                Cookie co = null;
                Cookie co1=null;
                co = new Cookie("userName",userName);
                response.addCookie(co);
                response.getWriter().write("1");
                if(remember!=null){
                    co1 = new Cookie("userPw",userPw);
                    response.addCookie(co1);
                }else{
                    /* co1 = new Cookie("userPw",""); */
                    if(co!=null&&co1!=null){
                        co1.setMaxAge(0);
                        response.addCookie(co1);
                    }
                }
            }
        }
    }
}
