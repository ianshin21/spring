<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${path}/js/jquery-3.5.1.js"></script>
</head>
<body>
<section id="content">
	<div id='board-container'>
		<h2>게시판 작성</h2>
		<form action='${path}/board/write' method="post" enctype="multipart/form-data">
			
			<table id='tbl-board'>
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle" id="title"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="userId" value="${ loginMember.userId }" readonly></td>
				</tr>
				<tr>
					<th>첨부파일1</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				<!--  
				<tr>
					<th>첨부파일2</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				-->
				<tr>
					<th>내용</th>
					<td><textarea name="boardContent" cols="50" rows="15" ></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="등록">
						<input type="reset" value="취소">
					</th>
				</tr>
			</table>
		</form>
	</div>
</section>
</body>
</html>
