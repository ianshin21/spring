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
	<h1>커스텀 로그인 페이지</h1>
	
	<form method="post" action="${path}/login">
		아이디 : <input type="text" name="userId"/><br>
		비밀번호 : <input type="password" name="userPwd"/><br>
		<label>로그인 유지 : <input type="checkbox" name="remember-me"></label>
		<input type="submit" value="로그인"/>
		<!-- 
			CSRF 공격 방지용 토큰 추가.
			스프링 시큐러티 4 부터 기본 값이 csrf가 활성화 되어있다. 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		 -->		 
		 <security:csrfInput/>
	</form>
	
	<span style="color: red"><c:out value="${loginFailMsg}"/></span>
	
</body>
</html>