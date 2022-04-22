<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:set var="path" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${path}/js/jquery-3.5.1.js"></script>
</head>
<body>
	<div id='board-container'>
		<h2>게시판 수정</h2>
		<form action='${path}/board/update' method="post" enctype="multipart/form-data">
			<input type="hidden" name="boardNo" value = "${board.boardNo}">
			<input type="hidden" name="boardOriginalFileName" value = "${board.boardOriginalFileName}">
			<input type="hidden" name="boardRenamedFileName" value = "${board.boardRenamedFileName}">
			
			<table id='tbl-board'>
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle" id="title" value="${board.boardTitle}"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="userId" value="${ loginMember.userId }" readonly></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="reloadFile">
						<c:if test="${ !empty board.boardOriginalFileName }">
							<br>현재 업로드한 파일 : 
							<a href="${ path }/resources/upload/board/${ board.boardRenamedFileName }" download="${ board.boardOriginalFileName }">
								${ board.boardOriginalFileName }
							</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="boardContent" cols="50" rows="15"><c:out value="${board.boardContent}"/></textarea>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="수정">
						<button type="button" onclick="location.replace('${path}/board/list')">목록으로</button>
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
