package servlet;

import com.bean.NewsType;
import com.dao.impl.NewsTypeDaoimpl;
import service.impl.NewsTypeServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Types extends HttpServlet {

    /*初始化*/
    String opera ="";
    public void init(ServletConfig config) throws ServletException {
        opera = config.getInitParameter("opera");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*判断*/
        if ("typeAdd".equals(opera)){
            typeAdd(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("typeDelete".equals(opera)){
            this.typeDelete(request,response);
        }
    }
    protected void typeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsTypeServiceImpl newsTypeServiceImpl = new NewsTypeServiceImpl();
        NewsType newsType = new NewsType();
        String typeName = request.getParameter("typeName");
        newsType.setTypeName(typeName);
        int i = newsTypeServiceImpl.insert(newsType);
        if(i>0){
            response.sendRedirect("TypeManage.jsp");
        }
    }

    protected void typeDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsTypeDaoimpl newsTypeDaoimpl = new NewsTypeDaoimpl();
        String strId = request.getParameter("id");
        /*转换类型*/
        int id = Integer.parseInt(strId);
        /*调用删除方法*/
        int i = newsTypeDaoimpl.deleteById(id);
        if (i>0){
            response.getWriter().write("success");
        }else{
        }

    }


}

