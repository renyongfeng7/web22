<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	/* 销毁session对象，sessionId改变 session.invalidate(); */
	/* sessionId不变 */
	 session.removeAttribute("login"); 
	 response.sendRedirect("login.jsp"); 
 %>
