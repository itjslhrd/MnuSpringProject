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
	<c:if test="${row==1}">
		<script>
			alert("수정되었습니다");
			location.href="board_list?page=${page}";
		</script>
	</c:if>
	<c:if test="${row==0}">
		<script>
			alert("비번오류입니다");
			history.back();
		</script>
	</c:if>
	
</body>
</html>