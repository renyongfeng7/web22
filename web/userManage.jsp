<%@ page import="service.impl.NewsUserServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bean.User" %>
<%@ page import="com.bean.NewsType" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/15
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div style="height: 50px ;width: 150px">
    <span ><a href="MyJsp.jsp">首页</a></span>
    <span> <a href="userAdd.jsp">添加</a></span>
</div>
<table class="table table-striped" style="width: 500px;margin: 0 auto" >
    <tr>
        <td>ID</td>
        <td>姓名</td>
        <td>用户名</td>
        <td>密码</td>
        <td>操作</td>
    </tr>
    <%
        List<User> list = (List<User>) request.getAttribute("list");
        for(int i = 0; i <list.size() ; i++) {%>
    <tr>
        <td><%=list.get(i).getUserId()%></td>
        <td><%=list.get(i).getRealName()%></td>
        <td><%=list.get(i).getUserName()%></td>
        <td><%=list.get(i).getUserPwd()%></td>
        <td><a >编辑</a></td>
        <td><a >删除</a></td>
    </tr>
    <%}%>
</table>
<ul class="pagination">
    <li ><a href="#" aria-label="Previous" title="首页" id="fristPage" ><<</a></li>
    <li ><a href=MyJsp.jsp?page=(page-1)  >上一页 </a></li>
    <li ><a href="#">下一页 </a></li>
    <li ><a href="#" aria-label="Previous" title="尾页"><span aria-hidden="true">>></span></a></li>
</ul>
</body>
</html>
<script type="text/javascript">

    $("tr td:last-child").on("click",function () {
        var id = $(this).parent().children("td:first-child").text();
        alert(id)
        $.ajax({
            url:"deleteUser",
            type:"get",
            data:{
                id:id
            },
            dataType:"text",
            success:function (re) {
                location.href="userManage.jsp";
            }
        })
    })
    $("#fristPage").click(function () {

    })

</script>
