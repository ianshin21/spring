package com.kh.mvc.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j   //logging 관련 어노테이션
@Controller
@SessionAttributes("loginMember")
public class MemberController {
	
	/*
	 * 사용자의 파라미터를 전송받는 방법(요청시 전달값을 처리하는 방법)
	 * 
	 * 1. HTTPServletRequest를 통해 전송받기(기존 JSP/Servlet 방식)
	 * 	- 매개변수로 작성하면 HttpServletRequest request 메소드 실행시 
	 *     스프링 컨테이너가 자동으로 객체를 인자로 주입해준다.
	 */

//	@RequestMapping("/login")
//	@RequestMapping(value="/login", method= {RequestMethod.POST})
//	public String login(HttpServletRequest request) {
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		
//		log.info("{}, {}", userId, userPwd);
//		return "home";
//		
//	}
	
	/*
	 * 2. @RequestParam 어노테이션 방식
	 * 	- 스프링에서 조급 더 간편하게 파라미터를 받아올 수 있는 방법 중 하나
	 *  - request 객체를 이용해서 데이터를 전송받는 방법
	 *  - 어노테이션을 사용하면 복잡한 코드를 작성할 필요가 없다. 
	 *  - defaultValue 속성을 사용하면 파라미터 name 속성에 값이 없을 경우 기본값을 지정할 수 있다. 
	 *  - 파라미터 name 속성에 없는 값이 넘어올 경우(name="userPwd1") 에러가 발생하지만 
	 *      @RequestParam(name="userPwd1", required = false)로 지정하면 null값을 넘겨준다.
	 *  - 어노테이션 생략도 가능 : 매개변수의 이름과 name값이 동일하게 설정된 경우 자동으로 주입된다. 
	 *        : 어노테이션을 사용하는 것이 아니기 때문에 defaultValue, required 설정이 불가하다. 
	 
	@RequestMapping(value="/login", method= {RequestMethod.POST})
	public String login(@RequestParam("userId") String userId, 
					@RequestParam(name="userPwd", defaultValue = "111") String userPwd) {
//					@RequestParam(name="userPwd1", required = false) String userPwd) {
//	public String login(String userId, String userPwd) {	
		
		System.out.println(userPwd);
		log.info("{}, {}", userId, userPwd);
		return "home";
		
	}
	*/
	
	/* 
	 * 3.패스 파라미터로 요청 받기
	 *  - URL 패스상에 있는 특정 값을 가져오기 위해 사용하는 방법
	 * 	- /board/{id}
	 *  - 브라우저 주소창에 http://localhost:8088/mvc/board/123 입력한다. => 콘솔창에서 확인
		- board 1번을 가져오겠다  뭐 그런 용도로 
		- 변수명이랑 파라미터 값이 동일하면 @PathVariable("id")에서 ("id") 생략 가능 
	
	@RequestMapping(value="board/{id}")
//	public String getBoard(@PathVariable("id") int boardId) {
	public String getBoard(@PathVariable("id") String boardId) {

		log.info("Board ID : {}", boardId);
		
		return "home";
	}
	 */
	
	/*
	 * 4. @ModelAttribute를 이용한 값 전달 방법
	 *  - 요청하는 파라미터가 많을 경우 객체 타입으로 넘겨 받는 방법 : 커맨드 객체 방식이라 한다. 
	 *  - Member 클래스에 기본 생성자, setter가 있어야 한다. @NoArgsConstructor / @Data
	 *  - @Data로 처리하지 않고 setter로 작성할 경우 작성 규칙에 맞아야 한다. 
	 *  - 스프링 컨테이너가 기본 생성자를 통해서 객체를 생성하고 
	 *    파라미터 name 속성의 값과 동일한 인스턴스 변수명을 가진 변수에 값을 주입해 준다. 
	 *  - 어노테이션 생략 가능(하지만 적어주는 것이 좋다)
	
	@RequestMapping(value="/login", method= {RequestMethod.POST})
	public String login(@ModelAttribute Member member) {	
//	public String login(Member member) {	
		
		log.info("{}, {}", member.getUserId(), member.getUserPwd());
		
		return "home";
	}
	 */ 
	
	
	
	
	@Autowired // new 객체 생성 대신에
	private MemberService service;
	
	/*
	
	// 로그인 처리
	//1.HttpSession과 Model 객체 사용
	//  - Model이라는 객체는 컨트롤러에서 데이터를 뷰로 전달하고자 할 때 사용하는 객체
	//  - 전달하고자 하는 데이터를 맵형식(key, value)로 담을 수 있다
	//  - 데이터를 담을 때는 setAttribute()가 아닌 addAttribute()를 사용한다. 
	//  - scope는 request이다. 
	@RequestMapping(value="/login", method= {RequestMethod.POST})
	public String login(HttpSession session, Model model, 
					@RequestParam("userId") String userId, 
					@RequestParam("userPwd") String userPwd) {

		log.info("{}, {}", userId, userPwd);
		
		Member loginMember = service.login(userId, userPwd);
		
//		System.out.println(loginMember);
		
		if(loginMember != null) {
			
			session.setAttribute("loginMember", loginMember);
			
			
//			  return "home"; : forwarding 방식으로 여기서 리턴한 view명이 viewResolver에 의해 
//			  					WEB-INF/views/home.jap로 요청을 넘기고 
//			 return "redirect:/"; : homeController 재실행 
//			  			: redirect 방식으로 여기서 리턴한 경로로 다시 요청을 하도록 반환한다.
			 
			
			return "redirect:/";   
		}else {			
			model.addAttribute("msg", "아이디나 패스워드가 일치하지 않습니다.");
			model.addAttribute("location", "/");
			
			return "common/msg";   
		}
	}
	
	
	
	 	2. @SessionAttributes와 ModelAndView 객체 사용 : model과 view에 대한 정보도 담고 있다.
	 * 		-  @SessionAttributes("키값")은 Model 객체에 Attribute로 해당 키값으로 담기는 데이타를 세션 scope까지 범위를 확장시킨다.
	 *  	- 로그아웃시 기존방법으로 Session을 정리할 수 없고 SessionStatus 객체를 통해서 세션 scope까지 범위가 확장된 데이터를 정리할 수 있다.
	 *  		session.invalidate();로 정리할 수 없고, status.setComplete();로 정리할 수 있다. 
	 *  
	 *     ModelAndView는 컨트롤러에서 데이터를 뷰로 전달하는 기능과, forward 할 뷰에 정보를 담는 객체이다.
	 *     	addAttribute()가 아닌 addObject()를 통해서 데이터를 담을 수 있다.
	 *     
*/
	
	@RequestMapping(value="/login", method= {RequestMethod.POST})
	public ModelAndView login(ModelAndView model, 
					@RequestParam("userId") String userId, 
					@RequestParam("userPwd") String userPwd) {
	
	log.info("{}, {}", userId, userPwd);
		
		Member loginMember = service.login(userId, userPwd);
		
		if(loginMember != null) {
			
			model.addObject("loginMember", loginMember);
			model.setViewName("redirect:/");

		}else {			
			model.addObject("msg", "아이디나 패스워드가 일치하지 않습니다.");
			model.addObject("location", "/");
			model.setViewName("common/msg");
		}
		
		return model;
	}	
	//로그아웃 처리  : 1. 기존 서블릿 방식 
	/*
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	*/
	
	// 로그 아웃 처리 : 2 (SessionStatus 사용)
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
		
		log.info("status.isComplete() : " + status.isComplete());
		
		status.setComplete();
		
		log.info("status.isComplete() : " + status.isComplete());
		return "redirect:/";
	}
	
	@RequestMapping("member/enroll")
	public String enrollView() {
		log.info("회원 가입 페이지 요청");
		
		return "member/enroll";

	}
	
	@RequestMapping(value = "member/enroll", method= {RequestMethod.POST})
	public ModelAndView enroll(ModelAndView model, @ModelAttribute Member member) {
		
		log.info(member.toString());
		
		int result = service.saveMember(member);
		
		log.info(member.toString());
		
		if(result > 0) {
			model.addObject("msg", "회원가입이 정상적으로 완료되었습니다.");
			model.addObject("location", "/");
		}else {
			model.addObject("msg", "회원가입이 실패했습니다.");
			model.addObject("location", "/member/enroll");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}
	
	/*
	 * 아이디 중복확인을 위한 json 실험 Ajax 비동기 통신을 위해서 
	 * @ResponseBody
	 * 	- 일반적으로 컨트롤의 메소드의 반환형이 String 일 경우 뷰의 이름을 반환한다. 
	 * 	- 하지만 @ResponseBody가 붙은 String 반환은 해당 요청을 보낸 클라이언트에 전달할 데이터를 의미한다. 
	 * 		returen "home";으로 연습 : 브라우저에 home이 새겨짐.
	 * 	Map<String, Object> map = new HashMap<>();을 Json 타입으로 변환해서 반환한다. 
	 * 
	 * jackson 라이브러리
	 * 	- 자바 객체를 JSON 형태의 데이터로 변환하기 위한 라이브러리. (GSON, jsonSimple)
	 * 	- 스프링에서 jackson 라이브러리를 추가하고 @ResponseBody를 사용하면 리턴되는 객체를 자동으로 JSON으로 변경해서 응답해준다.
	 */
	@RequestMapping("/member/jackson/test")
	@ResponseBody
	public Object jacksonTest() {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("test1", 1);
		map.put("test2", "ismoon");
		map.put("test3", false);
		
		return map;
		
	}
	
	
	@RequestMapping("/member/idCheck")
	@ResponseBody
	public Object idCheck(@RequestParam("id")String userId) {
		
		log.info("UserId : {}", userId);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("validate", service.validate(userId));
		
		return map;
	}
	
	@RequestMapping("/member/myPage")
	public String myPage() {
		
		return "/member/myPage";
	}
	
	
	@RequestMapping("/member/update")
	public ModelAndView update(@ModelAttribute Member member,
			@SessionAttribute(name="loginMember", required = false) Member loginMember,
			ModelAndView model) {
		
		int result = 0;
		
//		if(loginMember != null) {
		if(loginMember.getUserId().equals(member.getUserId())) {
		 
			member.setUserNo(loginMember.getUserNo());
			
			result = service.saveMember(member);
			
			if(result > 0) {
				model.addObject("loginMember", service.findMemberByUserId(loginMember.getUserId()));
				model.addObject("msg", "회원 정보 수정이 완료되었습니다.");
				model.addObject("location", "/member/myPage");
				
			}else {
				model.addObject("msg", "회원 정보 수정에 실패했습니다.");
				model.addObject("location", "/member/myPage");
			}
		}else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/");
		}
//		}else {
//			model.addObject("msg", "로그인 후 정보를 수정해주세요.");
//			model.addObject("location", "/");
//		}
		model.setViewName("common/msg");
	
		return model;
		
	}
	
	
	@RequestMapping("/member/delete")
	public ModelAndView delete(ModelAndView model,
			@SessionAttribute(name="loginMember", required = false) Member loginMember,
			@RequestParam("userId")String userId) {
	
		int result = 0;
		
		if(loginMember.getUserId().equals(userId)) {
			result = service.deleteMember(userId);
			
			if(result > 0) {
				model.addObject("msg", "정상적으로 탈퇴되었습니다.");
				model.addObject("location", "/logout");
				
			}else {
				model.addObject("msg", "회원 탈퇴를 실패했습니다.");
				model.addObject("location", "/member/myPage");
			}
		}else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/");
		}

		
		model.setViewName("common/msg");
		
		return model;

	}
	
}
