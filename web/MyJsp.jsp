<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
<head>
	<style type="text/css">
		*{margin: 0}
		.left{background-color: black;color: white;width: 300px;float: left;padding: 10px}
		.left li{list-style: none;}
		.right{color: white;float: left;height: 500px;padding: 10px}
	</style>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>
<h4>你好，${login.realName},${login["realName"]}</h4>
<div class="left">
	<ul>
		<li><a href="">新闻管理</a></li>
		<li><a href="TypeManage.jsp">类别管理</a></li>
		<li><a href="userManage">用户管理</a></li>
	</ul>
</div>
<div class="form-inline">
	<div class="form-group">
		<label for="select" id="type">类型</label>

		<select id="select" class="form-control">
			<option></option>
		</select>
	</div>
	<div class="form-group">
		<label for="keywords">关键词</label>
		<input type="email" class="form-control" id="keywords" placeholder="关键词" value="">
	</div>
	<button type="button" class="btn btn-default" id="serch"> 搜索</button>
</div>

<div class="right">
	<nav class="navbar navbar-default" style="padding: 10px;height: 25px"><a href="newsadd.jsp" style="line-height: 25px">点击添加</a></nav>
	<!-- 表格 -->
	<table class="table table table-striped" id="table">
		<tr>
			<td>新闻ID</td>
			<td>新闻标题</td>
			<td>新闻类别</td>
			<td>发布人</td>
			<td>发布时间</td>
			<td>编辑</td>
		</tr>
		<%--jstl循环--%>
	</table>
	<div style="height: 10px;width: 50px;color: black"><span id="countt"></span></div>
	<ul class="pagination">
		<li>
			<a <%--href="myjsp?page=1"--%> aria-label="Previous">
				<span aria-hidden="true" id="first"><<</span>
			</a>
			<!--url传参注意空格-->
		<li ><a <%--href="myjsp?page=${pageno-1<1?pageno:pageno-1}"--%> id="pre">上一页</a></li>
		<li ><a id="yema"></a></li>
		<li ><a <%--href="myjsp?page=${pageno+1>pageSize?pageno:pageno+1}"--%> id="next">下一页</a></li>
		<li>
			<a <%--href="myjsp?page=${pageSize}"--%> aria-label="Next" >
				<span aria-hidden="true" id="last">>></span>
			</a>
	</ul>
</div>
<div style="clear: both;">
	<h4>[<a href="logout.jsp">退出登录</a>]</h4>
</div>
<script type="text/javascript" src="jq/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	/*类型下拉*/
	$.ajax({
		url:"type",
		type:"get",
		data:{},
		dataType:"json",
		success:function (res) {
			for (let i = 0; i < res.length; i++) {
				$("#select").append(
						"<option>"+res[i].type.typeName+"</option>"
				)
			}
		}
	})
	/**/
	/*ajax实现分页查询*/
	var pageSize =1;
	var countt =1;
	var pageno =1;
	/*显示第一页*/
	page(1)
	function page(page){
		$.ajax({
			url:"myjsp",
			type:"get",
			data:{
				pageno:page,
				type:$("#select").val(),
				keywords:$("#keywords").val(),
			},
			dataType:"json",
			success:function (res){
				$("#table tr:eq(0)").siblings().remove();
				for (var i = 0; i < res.length-1; i++){
					$("#table").append(
							"<tr>" +
							"<td>"+ res[i].newsId+
							"</td><td>"+res[i].newsTitle+
							"</td><td>"+res[i].newsType+
							"</td><td>"+res[i].sendUser.realName+
							"</td><td>"+res[i].sendTime +
							"</td><td>编辑</td><td>删除</td></tr>"
					)
				}
				/*总页数*/
				pageSize = res[res.length-1].pageSize;
				/*总条数*/
				countt = res[res.length-1].countt;
				/*当前页码*/
				pageno = res[res.length-1].pageno;
				$("#yema").text("第"+pageno+"页"+"/"+"共"+pageSize+"页");
				//$("#countt").text("共"+countt+"条");
				/*删除绑定事件*/
				$("tr td:last-child").on("click",function () {
					alert("1")
					var tr = $(this).parent();
					var id = $(this).parent().children("td:first-child").text();
					$.ajax({
						url:"delete",
						type:"get",
						data:{
							"id":id,
						},
						dataType: "text",
						success:function (res) {

							if(confirm("是否删除？")==true){
								if(res=="success"){
									alret("删除成功！")
									tr.remove();
								}
							}
						}
					})
				})
			}
		})
	}


	/*x下一页*/
	$("#next").click(function () {
		/*第几页*/
		pageno++;
		if (pageno>pageSize){
			pageno = pageSize;
		}else{
			page(pageno)
		}
	})
	/*上一页*/
	$("#pre").click(function () {
		/*第几页*/
		pageno--;
		if (pageno<1){
			pageno = 1;
		}else{
			page(pageno);
		}
	})
	/*首页*/
	$("#first").click(function () {
		page(1);
	})
	/*尾页*/
	$("#last").click(function () {
		page(pageSize);
	})
	/*搜索*/
	$("#serch").on("click",function () {
		page(1);

	})
</script>
</body>
</html>
