package servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.NewsInfor;
import com.bean.NewsType;
import com.bean.User;
import com.dao.impl.NewsInforDaoImpl;
import com.dao.impl.NewsTypeDaoimpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.UserService;
import service.impl.NewsInforServiceImpl;
import service.impl.NewsUserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class News extends HttpServlet {

    /*初始化*/
    String opera =null;
    UserService userService  =new NewsUserServiceImpl();

    public void init(ServletConfig config) throws ServletException {
        opera = config.getInitParameter("opera");
    }
    /*post*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("add".equals(opera)){
            this.add( request,response);
        }
    }
    /*get*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("delete".equals(opera)){
            this.dele(request,response);
        }else if("edit".equals(opera)){
            this.edit(request,response);
        }else if ("myjsp".equals(opera)){
            this.myjsp(request,response);
        }else if ("type".equals(opera)){
            this.type(request,response);
        }
    }

    protected void myjsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageno = Integer.parseInt(request.getParameter("pageno"));
        String typeStr = request.getParameter("type");
        String keywords = request.getParameter("keywords");
        NewsInforServiceImpl newsInforServiceImpl = new NewsInforServiceImpl();

        List<NewsInfor> list = newsInforServiceImpl.selectBypage(pageno,5,typeStr,keywords);
        request.setAttribute("list",list);
        /*总条数*/
        NewsInforDaoImpl newsInforDaoImpl = new NewsInforDaoImpl();
        int countt= newsInforDaoImpl.count();
        /*总页数*/
        int pageSize = newsInforDaoImpl.pageSize();

        // request.getRequestDispatcher("MyJsp.jsp").forward(request,response);
        JSONArray array = new JSONArray();
        response.setCharacterEncoding("utf-8");
        for (int i = 0; i < list.size(); i++) {
            /*对象一*/
            JSONObject jsonObj =new JSONObject();
            jsonObj.put("newsId",list.get(i).getNewsId());
            jsonObj.put("newsTitle",list.get(i).getNewsTitle());
            jsonObj.put("newsType",list.get(i).getNewsType().getTypeName());
            jsonObj.put("sendUser",list.get(i).getSendUser());
            jsonObj.put("sendTime",list.get(i).getSendTime().toString());
            array.add(jsonObj);
        };

        /*杂项数据*/
        /*对象二*/
        JSONObject jsonObjj =new JSONObject();
        /*总页数*/
        jsonObjj.put("pageSize",pageSize);
        /*总条数*/
        jsonObjj.put("countt",countt);
        /*当前页码*/
        jsonObjj.put("pageno",pageno);
        array.add(jsonObjj);

        response.getWriter().write(array.toString());

    }

    protected void type(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");
        JSONArray array = new JSONArray();

        NewsTypeDaoimpl newsTypeDaoimpl = new NewsTypeDaoimpl();
        List<NewsType> typelist = newsTypeDaoimpl.selectAll();
        for (int j = 0; j < typelist.size(); j++) {
            JSONObject jsonObjjj = new JSONObject();
            jsonObjjj.put("type", typelist.get(j));
            array.add(jsonObjjj);
        }
        response.getWriter().write(array.toString());
    }
    /*add*/
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ENTER----add");
        NewsInforServiceImpl newsInforServiceImpl = new NewsInforServiceImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ServletFileUpload file =new ServletFileUpload(new DiskFileItemFactory());//缓存工厂
        file.setHeaderEncoding("UTF-8");
        /*得到请求元素集合*/
        List<FileItem> list = null;
        try {
            list = file.parseRequest(request);
        } catch (FileUploadException e){
            e.printStackTrace();
        }
        NewsInfor newsInfor = new NewsInfor();
        Boolean f = false;
        /*遍历集合元素*/
        for (int i = 0; i < list.size(); i++) {
            /*判断元素是否为表单元素*/
            if(list.get(i).isFormField()){
                /*name值相等则给对象赋值对应value值*/
                /*赋值标题*/
                if("newsTitle".equals(list.get(i).getFieldName())){
                    newsInfor.setNewsTitle(list.get(i).getString("UTF-8"));
                }
                /*赋值内容*/
                if("newsContent".equals(list.get(i).getFieldName())){
                    newsInfor.setNewsContent(list.get(i).getString("UTF-8"));
                }
                /*赋值类型*/
                if("newsType".equals(list.get(i).getFieldName())){
                    /* newsInfor.setNewsType(); */
                    NewsType newsType = new NewsType();

                    newsType.setTypeId(Integer.parseInt(list.get(i).getString("UTF-8")));
                    newsInfor.setNewsType(newsType);
                }
                /*赋值时间*/
                if("sendTime".equals(list.get(i).getFieldName())){
                    try {
                        newsInfor.setSendTime(sdf.parse(list.get(i).getString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                /*非表单元素*/
                /* 判断文件大小是否符合*/
                if(list.get(i).getSize()>5*1024*1024||list.get(i).getSize()<=0){
                    f = false;
                    continue;
                }else{
                    /*获取文件名 */
                    String filename = list.get(i).getName();
                    /* 判断文件名是否为空 */
                    if(filename==null){
                        f = false;
                        continue;
                    }
                    /* 分割文件名，得到文件后缀类型*/
                    String fileType = filename.substring(filename.lastIndexOf("."));
                    /*输出文件类型*/
                    if(!fileType.equals(".jpg")){
                        System.out.print("文件类型不合法");
                        f = false;
                        continue;
                    }

                    /* 重新命名---防止上传文件名冲突*/
                    filename = UUID.randomUUID()+fileType;
                    /*文件上传的位置----得到项目的地址*/
                    String subjectPath = request.getServletContext().getRealPath("/");
                    /*创建文件要存到的文件夹*/
                    File filee = new File(subjectPath+"imgs");
                    /*判断文件夹是否存在*/
                    if(!filee.exists()){
                        /*不存在则创建文件夹*/
                        filee.mkdirs();
                    }
                    /*上传文件*/
                    try {
                        list.get(i).write(new File(filee,filename));
                        f=true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /*newsImg 赋值*/
                    newsInfor.setNewsImg(subjectPath+"imgs\\"+filename);
                }
            }
        }
        /*赋值发布人*/
        User user = new User();
        user.setUserId(((User)request.getSession().getAttribute("login")).getUserId());
        newsInfor.setSendUser(user);
        System.out.println(newsInfor.getSendUser());
        /*文件大小||文件格式不正确跳转*/
        if( f==false){
            request.setAttribute("newsInfor", newsInfor);
            request.getRequestDispatcher("newsadd.jsp").forward(request, response);
            return;
        }
        newsInforServiceImpl.insert(newsInfor);
        response.sendRedirect("MyJsp.jsp");
    }

    /*delete*/
    protected void dele(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsInforServiceImpl newsInfor = new NewsInforServiceImpl();
        String idstr = request.getParameter("id");
        int id = 0;
        try{
            id=  Integer.parseInt(idstr);
        }catch(Exception e ){
            id = 0;
        }
        int delete = newsInfor.delete(id);
        response.setContentType("text/html;charset=utf-8");
        if (delete >0){

            response.getWriter().write("success");
        }else{
        }
    }

    /*edit*/
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        NewsInforServiceImpl newsInforServiceImpl = new NewsInforServiceImpl();
        request.setCharacterEncoding("UTF-8");
        /* 接收参数 */
        String idstr = request.getParameter("sendId");
        int id = 0;
        try{
            id = Integer.parseInt(idstr);
        }catch(Exception e){
            id = 0;
        }

        /*接受参数*/
        String newsTitle = request.getParameter("newsTitle")==null?"":request.getParameter("newsTitle");
        String newsType = request.getParameter("newsType")==null?"0":request.getParameter("newsType");
        String newsContent = request.getParameter("newsContent")==null?"":request.getParameter("newsContent");
        String sendTime = request.getParameter("sendTime")==null?"":request.getParameter("sendTime");
        //String sendUser =(String)request.getSession().getAttribute("login");
        /* 验证 */
        /*if(newsTitle.equals("")){
            request.setAttribute("error1", "请填写新闻标题");
            request.getRequestDispatcher("newsadd.jsp").forward(request, response);
            return;
        }
        if(newsType.equals("0")){
            request.setAttribute("error2", "请选择新闻类型");
            request.getRequestDispatcher("newsadd.jsp").forward(request, response);
            return;
        }
        if(newsContent.equals("")){
            request.setAttribute("error3", "请填写新闻内容");
            request.getRequestDispatcher("newsadd.jsp").forward(request, response);
            return;
        }
        if(sendTime.equals("")){
            request.setAttribute("error4", "请选择发布日期");
            request.getRequestDispatcher("newsadd.jsp").forward(request, response);
            return;
        }*/
        /* 创建新闻对象 */
        NewsInfor newsInfor = new NewsInfor();
        /* 对象赋值newsId*/
        newsInfor.setNewsId(id);
        /*对象赋值标题*/
        newsInfor.setNewsTitle(newsTitle);
        /*对象赋值新闻类型*/

        NewsType newsTypee = new NewsType();
        newsTypee.setTypeId(Integer.parseInt(newsType));
        newsInfor.setNewsType(newsTypee);

        /*对象赋值新闻内 容*/
        newsInfor.setNewsContent(newsContent);
        /*对象赋值时 间*/
        /*日期转换*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newsInfor.setSendTime(sdf.parse(sendTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*对象赋值发布人*/
        User user = new User();
        user.setUserId(((User)request.getSession().getAttribute("login")).getUserId());
        newsInfor.setSendUser(user);
        /*调用实现类方法传对 象*/
        int i = newsInforServiceImpl.update(newsInfor);
        if(i>0){
            response.sendRedirect("myjsp");
        }else{
            request.getRequestDispatcher("myjsp").forward(request,response);
        }
    }
}
