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
<!-- 
	<c:if test="${row==-1}">
		<script>
			alert("등록된 ID가 없음");
			location.history.back();
		</script>
	</c:if>
	<c:if test="${row==0}">
		<script>
			alert("비번오류");
			location.history.back();
		</script>
	</c:if>
	
	<c:if test="${row==1}">
		<script>
			alert("환영합니다\n로그인 성공");
			location.href="";
		</script>
	</c:if>
 -->
 
 	<c:choose>
 		<c:when test="${row==-1}">
	 		<script>
				alert("등록된 ID가 없음");
				history.back();
			</script>		
 		</c:when>
 		<c:when test="${row==0}">
 			<script>
				alert("비번오류");
				history.back();
			</script>
 		</c:when>
		<c:otherwise>
			<script>
				alert("환영합니다\n로그인 성공");
				location.href="/";
			</script>
		</c:otherwise>
 	</c:choose>	
</body>
</html>