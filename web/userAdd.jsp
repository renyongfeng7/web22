<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/18
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style href="text/css">
        *{margin: 0;
            padding: 0;}
    </style>

</head>
<body>
<form style="width: 500px;margin: 0 auto" >
    <div class="form-group">
        <label for="realName">姓名</label>
        <input type="text" class="form-control" id="realName" >
    </div>
    <div class="form-group">
        <label for="userName">用户名</label>
        <input type="text" class="form-control" id="userName" >
    </div>
    <div class="form-group">
        <label for="passWord">密码</label>
        <input type="password" class="form-control" id="passWord">
    </div>
    <input type="submit" class="btn btn-default" id="sub">
</form>
</body>
</html>
<script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    alert($("#realName").val())
    $("#sub").click(function () {
        alert(1)

        $.ajax({
            url:"userAdd",
            type:"post",
            data:{
                "realName":$("#realName").val(),

                "userName":$("#userName").val(),
                "passWord":$("#passWord").val()
            },
            dataType:"text",
            success:function (res) {
                if (res == "y") {
                    location.href = "userManage.jsp"
                }
            }
        })
    })


</script>