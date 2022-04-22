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
	<h2>게시판 </h2>
	<div id="board-container">
		<c:if test="${loginMember != null}">
			<button type="button" id="btn-add"
			onclick="location.href ='${path}/board/write'">글쓰기</button>
		</c:if>
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${list == null}">
				<tr>
					<td colspan="6">
						조회된 게시글이 없습니다.
					</td>
				</tr>	
			</c:if>
			<c:if test="${list != null}">
				<c:forEach var="board" items="${list}">
					<tr>
						<td><c:out value="${board.boardNo}"/></td>
						<td>
							<a href="${path}/board/view?boardNo=${board.boardNo}">
								<c:out value="${board.boardTitle}"/>
							</a>
						</td>
						<td><c:out value="${board.userId}"/></td>
						<td><fmt:formatDate type="both" value="${board.boardCreateDate}"/></td>
						<td>
	            			<c:if test="${board.boardOriginalFileName != null}">
            					<img src="${path}/images/file.png" width="16">
            				</c:if>
	            			<c:if test="${board.boardOriginalFileName == null}">
            					<span> - </span>
            				</c:if>
						</td>
						<td><c:out value="${board.boardReadCount}"/></td>
					</tr>		
				</c:forEach>
			</c:if>
		</table>
		<div id="pageBar">
			<!-- 맨 처음으로 -->
			<button onclick="location.href='${path}/board/list?page=1'">&lt;&lt;</button>
			
			<!-- 이전 페이지로 -->
			<button onclick="location.href='${path}/board/list?page=${pageInfo.prvePage}'">&lt;</button>

			<!--  10개 페이지 목록 -->
			<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="status">
				<c:if test="${status.current == pageInfo.currentPage}">
					<button disabled><c:out value="${status.current}"/></button>
   				</c:if>
				<c:if test="${status.current != pageInfo.currentPage}">
					<button onclick="location.href='${path}/board/list?page=${status.current}'"><c:out value="${status.current}"/></button>
   				</c:if>
			</c:forEach>
			
			<!-- 다음 페이지로 -->
			<button onclick="location.href='${path}/board/list?page=${pageInfo.nextPage}'">&gt;</button>
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='${path}/board/list?page=${pageInfo.maxPage}'">&gt;&gt;</button>
		</div>
	</div>
</body>
</html>