<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>사용자 전용 페이지</h1>
	
		<security:authorize access="isAuthenticated()">
		<c:url var="logoutUrl" value="/logout"/>
		
		<form action="${logoutUrl}" method="post">
			<input type="submit" value="로그아웃">
			 <security:csrfInput/>
		</form>
	</security:authorize>

</body>
</html>