package com.kh.security.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonController {
	
	// 서버 돌리면 (요청이 있으면) HomeController 가 바로 실행되지 않고 
	// web.xml의 security filter + security-context 설정(<security:http + In Memory 방식 설정)에 의해, 처음에는 자체 로그인 창이 떳으나, 
	// login.jsp 만들고 security-context에서 security:form-login 빈 등록해서 여기로 왔다가 login.jsp(커스텀 로그인 페이지 창)을 띄운다.
	@RequestMapping("/login")
	public String login() {
		log.info("login() call");
		
		return "login";
	}

	// CustomLoginSucessHandler.java에서 else 경로 지정에 따라 이동
	@GetMapping("/admin/view")
	public String admin() {
		log.info("admin() call");
		
		return "admin/view";
	}
	
	// CustomLoginSucessHandler.java에서 else 경로 지정에 따라 이동
	@GetMapping("/member/view")
	public String member() {
		log.info("member() call");
		
		return "member/view";
	}
	
	// user로 로그인 한 다음 url창에 admin/view 입력하면 <security:access-denied-handler error-page="/accessError"/>에 의해
	// 이 메소드와 accessError.jsp 가 연동하여 화면 출력
	@GetMapping("/accessError")
	public String accessDenied(Model model) {
		log.info("accessDenied() call");
		
		model.addAttribute("msg", "접근 권한이 없습니다.");
		
		return "common/accessError";
	}

}
