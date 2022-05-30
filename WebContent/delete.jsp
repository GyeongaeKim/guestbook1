<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestVo" %>
<%@ page import="com.javaex.dao.GuestDao" %>
<%@ page import="java.util.*" %>

<%	
	int no = Integer.parseInt(request.getParameter("no"));
	String pInput = request.getParameter("password");
	
	GuestDao guestDao = new GuestDao();
	GuestVo guestVo = guestDao.getGuest(no);
	System.out.println(guestVo);
	String password = guestVo.getPassword();
	
	if(pInput.equals(password)){
		int count = guestDao.guestDelete(no);
		response.sendRedirect("./main.jsp");
	} else {
		response.sendRedirect("./deleteForm.jsp?no="+no);
	}
	
%>