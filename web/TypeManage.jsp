<%@ page import="com.dao.impl.NewsTypeDaoimpl" %>
<%@ page import="com.bean.NewsType" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/5
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<span><a href="MyJsp.jsp">首页</a></span><a href="typeAdd.jsp">添加</a>
<table class="table table-striped">
    <%NewsTypeDaoimpl newsTypeDaoimpl = new NewsTypeDaoimpl();
        List<NewsType> list = newsTypeDaoimpl.selectAll();%>
    <tr>
        <td>类别ID</td>
        <td>类别名称</td>
        <td>操作</td>
    </tr>
    <%for(int i =0;i< list.size();i++){%>
    <tr>
        <td><%=list.get(i).getTypeId() %></td>
        <td><%=list.get(i).getTypeName()%></td>
        <td><a href="#" id="edit">编辑</a></td>
        <td><a href="#" id="delete">删除</a></td>
    </tr>
    <%}%>
</table>
</body>
</html>
<script type="text/javascript">
    $("tr td:last-child").on("click",function () {
        var ture =confirm("确认删除？");
        if (ture){
            var id = $(this).parent().children("td:first-child").text();
            $.ajax({
                url: "deleteType",
                type: "get",
                data: {
                    id: id
                },
                dataType: "text",
                success: function (res) {
                    if (res == "success") {
                        alert("删除成功！")
                        location.href = "TypeManage.jsp"
                    }
                }
            })
        }
    })


</script>