<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${path}/js/jquery-3.5.1.js"></script>
</head>
<body>
 <h2>회원 가입 정보</h2>
	<div>

	 	<form name="memberEnrollFrm" action="${path}/member/enroll" method="post">
	 		<table>
	 			<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="newId" placeholder="아이디(4글자이상)" required>
						<input type="button" id="checkDuplicate" value="중복검사" >
					</td> 			
	 			</tr>
	 			<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="userPwd" id="pass1" required>
					</td> 			
	 			</tr>
	 			<tr>
					<th>패스워드확인</th>
					<td>
						<input type="password" id="pass2">
					</td> 			
	 			</tr>
	 			<tr>
					<th>이름</th>
					<td>
						<input type="text" name="userName" id="userName" required>				
					</td> 			
	 			</tr>
	 			<tr>
					<th>휴대폰</th>
					<td>
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11">								
					</td> 			
	 			</tr>
	 			<tr>
					<th>이메일</th>
					<td>
						<input type="email" placeholder="abc@abc.com" name="email" id="email">												
					</td> 			
	 			</tr>
	 			<tr>
					<th>주소</th>
					<td>
						<input type="text" name="address" id="address">
					</td> 			
	 			</tr>
	 			<tr>
					<th>취미</th>
					<td>
						<label><input type="checkbox" name="hobby" id="hobby0" value="운동">운동</label>
						<label><input type="checkbox" name="hobby" id="hobby1" value="등산">등산</label>
						<label><input type="checkbox" name="hobby" id="hobby2" value="독서">독서</label>
						<label><input type="checkbox" name="hobby" id="hobby3" value="게임">게임</label>
						<label><input type="checkbox" name="hobby" id="hobby4" value="여행">여행</label>
					</td> 			
	 			</tr> 		
	 		</table> 
	 		<input type="submit" id="enrollSubmit" value="가입">	
	 		<input type="reset" value="취소">	
	 	</form>
	 	<!--  
	 	<form name="checkIdForm">
	 		<input type="hidden" name="userId">
	 	</form>  
	 	-->
 	</div>
 
 <script>
   $(document).ready(() => {
      $("#pass2").blur((e) => {
         let pass1 = $("#pass1").val();
         let pass2 = $(e.target).val();
         if(pass1.trim() != pass2.trim()){
            alert("비밀번호가 일치하지 않습니다.");
            $("#pass1").val("");
            $(e.target).val("");
            $("#pass1").focus();
         }
      });      
      
      $("#enrollSubmit").on("click", () => {
         // TODO 전송하기 전에 각 영역에 유효성 검사!
         
         //return false;
      });
      
      // 아이디 중복을 확인 처리 콜백함수
      $("#checkDuplicate").on("click", () => {
    	   // 중복확인전에 아이디 값이 4글자 이상인지 확인
          let id = $("#newId").val().trim();
          
          if (id.length < 4) {
             alert("아이디는 최소 4글자 이상 입력하셔요.")
             
             return;
          }
          
          $.ajax({
        	  type:"get",
        	  url:"${path}/member/idCheck",
        	  dataType: "json",
        	  data: {
        		  id  //컨트롤러의 idCheck(@RequestParam("id") 파라미터의 키값과 위 선언된 변수명이 동일한 경우 
        				// idCheck(@RequestParam("userId")인 경우    userId:id
        	  },
        	  success: function(data){
        		  console.log(data);
        		  
        		 if(data.validate != true){
        			 alert("사용 가능한 아이디입니다.")
        		 }else{
        			 alert("이미 사용중인 아이디입니다.")
        		 }
        	  },
        	  error: function(e){
        		  console.log(e);
        	  }
          });
      });
   });
</script>
 	
</body>
</html>