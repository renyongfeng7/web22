<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>登录</title>
	<style type="text/css">
	</style>
	<script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
</head>

<body>
<%
	/*  application多用户之s间数据共享*/
	int count = 0;
	if (application.getAttribute("count") != null) {
		count = (Integer) application.getAttribute("count");
		count++;
	} else {
		count = 1;
	}
	application.setAttribute("count", count);
	String userName = "";
	String userPw = "";
	Cookie[] cos = request.getCookies();
	if (cos != null) {
		for (Cookie ck : cos) {
			if (ck.getName().equals("userName")) {
				userName = ck.getValue();
			} else if (ck.getName().equals("userPw")) {
				userPw = ck.getValue();
			}
		}
	}
%>
<div style="width: 300px;margin: 0 auto;">
	<div style="margin-left: 90px;font-weight: bolder;font-size: 20px;">用户登录</div>
	<div style="margin: 20px"></div>
	<div>
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="userName" id="userName"
						   value="<%=request.getParameter("userName")==null?userName:request.getParameter("userName")%>">
				</td>
				<%----%>
			</tr>
			<tr>
				<td>手机号</td>
				<td><input type="text" name="userPhone"></td>
			</tr>
			<tr id="tr">
				<td>密码</td>
				<td><input type="password" name="userPw" id="userPw" value="${param.userPw==null?userPw:""}"></td>

				<%--<%=request.getParameter("userPw")==null?userPw:""%>--%>
			</tr>
		</table>
		<div style="height: 8px;width: 100%;padding: 5px">
			<span id="ts"></span>
		</div>
		<input type="checkbox" name="hobby" value="篮球">篮球
		<input type="checkbox" name="hobby" value="网球">网球
		<input type="checkbox" name="hobby" value="羽毛球">羽毛球</br>
		<input type="checkbox" name="remember">记住密码
		<input type="button" id="buttonn" value="登录">
		<h4>
			<%--<%= request.getAttribute("error")!=null?request.getAttribute("error"):""%>--%>
			${error!=null?error:""}
			<%--sessionid：<%= session.getId() %>
            最大失效时间：<%= session.getMaxInactiveInterval() %>秒--%>
		</h4>
	</div>
</div>

<script type="text/javascript">

	$("#buttonn").click(function(){
		/*判断账号密码是否为空*/
		if ($("#userName").val() == "") {
			$("#userName").focus();
			return;
		}
		if ($("#userPw").val() == "") {
			$("#userPw").focus();
			return;
		}
		/*使用Ajax登录验证*/
		$.ajax({
			url: "login",
			type: "post",
			data: {
				userName: $("#userName").val(),
				userPw: $("#userPw").val(),
			},
			dataType: "text",
			success: function (res) {
				if (res == "1") {
					alert("登录成功")
					location.href = "MyJsp.jsp"
				}else{
					$("#userPw").css("color","red")
					var t =3;
					var timer= setInterval(function(){
						$("#ts").text("输入正确账号或密码").css({"color":"red","font-size":"5px"})
						t--;
						if(t == 0 ){
							clearInterval(timer);
							$("#ts").text("")
						}
					},1000)
				}
			},
			error:function(){
				alert("请求失败");
			},
		})
	});
</script>
</body>
</html>
