<%@page import="java.text.*"%>
<%@page import="com.bean.*"%>
<%@page import="com.dao.impl.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
</head>
<%
    String sendidstr = request.getParameter("id");
    int id = 0;
    try{
        id=  Integer.parseInt(sendidstr);
    }catch(Exception e ){
        id = 0;
    }
    NewsInforDaoImpl newsInforDaoImpl = new NewsInforDaoImpl();
    NewsInfor newsInfor = newsInforDaoImpl.select(id);
%>
<body>
<div style="width: 500px;margin: 0 auto;height: 1000px;margin-top: 50px">
    <span>你好，${login.userName}<%--<%= ((User)session.getAttribute("login")).getUserName() %>--%></span>

    <nav class="navbar navbar-default" style="padding: 10px;height: 25px;line-height: 25px;font-size: 20px;font-weight: bolder;">新闻编辑</nav>
    <form class="form-horizontal" action="edit" method="get" id="sub">
        <!-- 新闻标题 -->
        <!--隐藏域-->
        <input type="hidden" name="sendId" value="<%= request.getParameter("id")%>">
        <div class="form-group">
            <label  class="col-sm-2 control-label">新闻标题</label>
            <div class="col-sm-10" >
                <input type="text" class="form-control "  id="newsTitle" placeholder="输入新闻标题" name="newsTitle" value="<%=request.getParameter("newsTitle")==null?newsInfor.getNewsTitle():request.getParameter("newsTitle")%>">
                <span style="color: red;font-weight: bolder;">${error1!=null?error1:""}</span>
            </div>
        </div>
        <!-- 新闻类别-->
        <div class="form-group">
            <label  class="col-sm-2 control-label">新闻类别</label>
            <div class="col-sm-10" >
                <select  class="form-control" id="newsType" name="newsType">
                    <%
                        NewsTypeDaoimpl newsTypeDaoimpl = new NewsTypeDaoimpl();
                        List<NewsType> list = newsTypeDaoimpl.selectAll();
                    %>
                    <option><%= request.getParameter("newsType")==null?newsInfor.getNewsType().getTypeName():request.getParameter("newsType")%></option>
                    <%for (int i = 0; i < list.size(); i++){
                    %>
                    <option value="<%= list.get(i).getTypeId()%>"><%=list.get(i).getTypeName()%>
                    </option>
                    <%
                        }%>
                </select>
            </div>
        </div>
        <!-- 新闻内容-->
        <div class="form-group ">
            <label  class="col-sm-2 control-label">新闻内容</label>
            <div class="col-sm-10">
                <textarea class="form-control"  rows="10" cols="50" name="newsContent" placeholder="输入内容" id="newsContent"><%=request.getParameter("newsContent")==null?newsInfor.getNewsContent():request.getParameter("newsContent")%></textarea>
                <span style="color: red;font-weight: bolder;"><%= request.getAttribute("error3")!=null?request.getAttribute("error3"):""%></span>
            </div>
        </div>
        <div class="form-group ">
            <label  class="col-sm-2 control-label" >发布时间</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" id="sendTime" name="sendTime" value="<%= request.getParameter("sendTime")==null?newsInfor.getSendTime():request.getParameter("sendTime")%>">
                <span style="color: red;font-weight: bolder;"><%= request.getAttribute("error4")!=null?request.getAttribute("error4"):""%></span>

            </div>
        </div>
        <!-- 发布人-->
        <div class="form-group">
            <label  class="col-sm-2 control-label">发布人</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="sendUser" disabled="disabled" value="<%=((User)session.getAttribute("login")).getUserName()%>">
            </div>
        </div>
        <input type="button" value="返回" id="back" class="btn btn-default">
        <input type="button" value="保存" class="btn btn-success" id="subm" >
    </form>
    <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
    <span id="inputSuccess2Status" class="sr-only">(success)</span>
</div>
<script type="text/javascript">
    /* 返回首页 */
    $("#back").click(function(){
        history.back("MyJsp.jsp");
    });

</script>

<script type="text/javascript">

    $("#subm").click(function(){

        var check = null;
        /*验证标题是否为空*********/
        var newsTitle = $("#newsTitle").val();
        if(newsTitle==""){
            $("#newsTitle").parent().addClass("has-error");
            check=  false;
        }else{
            $("#newsTitle").parent().removeClass("has-error").addClass("has-success");
            check = true;
        }
        /*验证类型是否为空**********/
        var newsType = $("#newsType").val();
        if(newsType==""){
            $("#newsType").parent().addClass("has-error");
            check=  false;
        }else{
            $("#newsType").parent().removeClass("has-error").addClass("has-success");
            check = true;
            /*验证内容是否为空**********/
            var newsContent = $("#newsContent").val();
            if(newsContent==""){
                $("#newsContent").parent().addClass("has-error");
                check=  false;
            }else{
                $("#newsContent").parent().removeClass("has-error").addClass("has-success");
                check = true;
            }
            /*验证发布时间是否为空**********/
            var newsTitle = $("#sendTime").val();
            if(newsTitle==""){
                $("#sendTime").parent().addClass("has-error");
                check=  false;
            }else{
                $("#sendTime").parent().removeClass("has-error").addClass("has-success");
                check = true;
            }

            if(check!=false){
                $("#sub").submit();
            }
        }
    });

</script>

</body>
</html>
