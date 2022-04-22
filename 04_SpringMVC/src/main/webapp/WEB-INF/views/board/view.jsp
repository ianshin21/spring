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
<div id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board">
		<tr>
			<th>글번호</th>
			<td>${board.boardNo}</td>
		</tr>
		<tr>
			<th>제 목</th>
			<td>${board.boardTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.userId}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.boardReadCount}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<c:if test="${ !empty board.boardOriginalFileName }">
				<a href="javascript:fileDownload('${board.boardOriginalFileName}', '${board.boardRenamedFileName}');">		
					<img src="${path}/images/file.png" width="20" height="20">
					<c:out value="${board.boardOriginalFileName}" />
				</a>
				<script>
					function fileDownload(oriname, rename) {
						const url="${path}/board/fileDown";
						
						let oName = encodeURIComponent(oriname);
						let rName = encodeURIComponent(rename);
						
						location.assign(url + "?oriname=" + oName + "&rename=" + rName);
					}
				</script>
			</c:if>
			<c:if test="${ empty board.boardOriginalFileName }">			
				<span style="color: gray;"> - </span>
			</c:if>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td>${board.boardContent}</td>
		</tr>
		<%--글작성자/관리자인경우 수정삭제 가능 --%>
		<tr>
			<th colspan="2">
			<c:if test="${ !empty loginMember && (loginMember.userId == board.userId || loginMember.userRole == 'ROLE_ADMIN')}">
				<button type="button" onclick="updateBoard()">수정</button>
				<button type="button" onclick="deleteBoard()">삭제</button>
			</c:if>
				<button type="button" onclick="location.replace('${path}/board/list')">목록으로</button>
			</th>
		</tr>
	</table>
   </div>
<script>
	function updateBoard(){
			location.href = "${path}/board/update?boardNo=${board.boardNo}";
	}
		
	function deleteBoard(){		
		if(confirm("정말로 게시글을 삭제 하시겠습니까?")){
			location.replace('${path}/board/delete?boardNo=${board.boardNo}');
		}
	}
</script>
</body>
</html>


















