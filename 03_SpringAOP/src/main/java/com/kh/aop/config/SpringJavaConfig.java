package com.kh.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.kh.aop.annotation.PetBarkRepeat;

//@Configuration 때문에 비즈니스 로직 없는 설정파일 
@Configuration
//"com.kh.aop" 이렇게 basePackage를 지정해주지 않으면 현재 Package(com.kh.aop.config)가 basePackage가 된다. 
@ComponentScan("com.kh.aop")
//@EnableAspectJAutoProxy      // AsepectJ 어노테이션을 사용한 에스펙트 적용을 위한 프록시 설정 = <aop:aspectj-autoproxy/>
@EnableAspectJAutoProxy(proxyTargetClass = true) // = <aop:aspectj-autoproxy proxy-target-class="true" /> 같음
							// interface로 직접 가는 것이 아니라 interface를 구현한 클래스 객체로 바로 간다. 다음 두 경우
							// private Job job => private Warrior job  / 
							//@PetBarkRepeat(value = "안녕하세요", repeatCount = 3) 이 내용이 pet이 아니라 cat에 바로 가 있기 때문
							//proxy 객체가 만들어질 때 interface Pet 을 감싸기 때문에  이를 구현한 class Cat을 바로 감쌀 수 있도록 
public class SpringJavaConfig {

	
}


