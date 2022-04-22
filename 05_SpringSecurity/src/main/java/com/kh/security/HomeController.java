package com.kh.security;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.security.member.model.vo.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	/*
	 * 컨트롤러에서 인증된 사용자 정보를 가져오는 방법! : admin 정보는 admin\view.jsp에 있음
	 * 
	 */	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	// 4. @AuthenticationPrincipal 사용 (<argument-resolvers> 추가)
	public String home(Locale locale, Model model, @AuthenticationPrincipal Member member) {
		// 2. Principal, JAVA 표준 Principal 객체이며 우리가 참조할수 있는 정보는 name 정보 밖에 없다.
//		public String home(Locale locale, Model model, Principal principal) {
		// 3. Authentication
//		public String home(Locale locale, Model model, Authentication authentication) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// 1. SecurityContextHolder 사용
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//Member member = (Member)authentication.getPrincipal();

		System.out.println(member);
		//System.out.println(principal.getName());		
//		System.out.println((Member)authentication.getPrincipal());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
