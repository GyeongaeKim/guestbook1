<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestVo" %>
<%@ page import="com.javaex.dao.GuestDao" %>

<%
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String content = request.getParameter("content");
	String regDate = request.getParameter("regDate");
	
	GuestDao guestDao = new GuestDao();
	GuestVo guestVo = new GuestVo(name, password, content, regDate);
	int count = guestDao.guestInsert(guestVo);
	
	response.sendRedirect("./main.jsp");
%>