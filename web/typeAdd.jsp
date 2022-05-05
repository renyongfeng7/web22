<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/5
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<form ACTION="typeAdd" METHOD="post" style="width: 200px;margin: 0 auto;" >
    <div class="form-group" style="margin-top: 100px">
        <label for="newsType">添加新闻类型</label>
        <input type="text" class="form-control" id="newsType" placeholder="国际新闻" name="typeName">
    </div>
    <button type="submit" class="btn btn-default">保存</button>
</form>

</body>
</html>
