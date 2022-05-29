<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteForm</title>
</head>
<body>
	<form action="./delete.jsp" method="post">
		비밀번호<input type="password" id="pass" name="password" value="">
		<button type="submit">확인</button>
		<input type="hidden" name="no" value="<%=request.getParameter("no")%>">
	</form>
	<a href="./main.jsp">메인으로 돌아가기</a><br>
</body>
</html>