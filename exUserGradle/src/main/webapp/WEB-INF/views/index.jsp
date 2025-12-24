<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>exUserGradle(회원관리)</h1>
<c:if test="${empty user}">	
	<h3><a href="/User/user_login">로그인</a></h3>
</c:if>	
<c:if test="${!empty user}">	
	<h3><a href="/User/user_login">로그아웃</a></h3>
	<h3><a href="/User/user_modify">정보수정</a></h3>
</c:if>	
</body>
</html>