package com.kh.aop.character;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
class CharacterTest {
	/*
	 * AOP 용어 정리
	 * 1. Aspect ; 
	 *  	- AOP는 횡단 관심사를 에스펙트라는 특별한 틀래스 모듈화해서 관리한다. 
	 *  	- 에스펙트는 어드바이스와 포인트커트를 합친 것이다. 
	 *  
	 *  2. JointPoint
	 *  	- 어드바이스를 적용할 수 있는 지점을 조인포인트라고 한다. 
	 *  	- 즉, 조인포인트는 애플리케이션 실행에 어드바이스를 끼워 넣을 수 있는 지점(Point)를 말한다.
	 *  	- 메소드 호출 지점이나 예외 발생, 필드 값 수정 등의 지점을 조인포인트라고 한다. 
	 *  
	 *  3. Advice 
	 *  	- 에스펙트가 해야할 작업을 AOP 용어로 어드바이스라고 한다. 
	 *  	- 에스펙트가 해야할 작업과 언제 그 작업을 수행해야 하는지 정의하는 것을 말한다. 
	 *  	- Spring Aspect는 5종류의 어드바이스를 가진다. 
	 *  		before : 어드바이스 대상 메소드가 호출되기 전에 어드바이스 기능을 수행한다. 
	 *  		after : 결과와 상관없이 어드바이스 대상 메소드가 완료된 후에 어드바이스 기능을 수행한다. 
	 *  		after-returning : 어드바이스 대상 메소드가 성공적으로 완료된 후에 어드바이스 기능을 수행한다.
	 *  		after-throwing : 어드바이스 대상 메소드가 예외를 던진 후에 어드바이스 기능을 수행한다. 
	 *  		around : 어드바이스 대상 메소드를 감싸서 어드바이스 대상 메소드 호출 전과 후에 어드바이스 기능을 수행한다. 
	 *  
	 *  4. PointCut 
	 *  	- 어드바이스가 적용될 조인포인트 영역을 좁히는 것을 정의한다. 
	 *  	- 어드바이스가 에스펙트가 해야할 '작업'과 그 작업을 '언제' 수행할 것인가를 정의하는 것이라면 
	 *  							포인트커트는 어디에 어드바이스를 적용할지 정의하는 것이다.
	 *  	- 포인트커트를 지정하기 위해서는 AspectJ 포인트커트 표현식을 통해서 지정해 줄 수 있다. 
	 *  
	 *  5. Weaving 
	 *  	- 타겟 객체에 에스펙트를 적용해서 새로운 프록시 객체를 생성하는 것을 의미한다. 
	 *  
	 *  * PointCut 표현식
	 *  	- 스프링 AOP에서 포인트커트는 AspectJ의 포인트커트 표현식을 이용해서 정의한다. 
	 *  	- 스프링 AOP에서 지원되는 AspectJ 포인트커트 지정자
	 *  	  execution([접근지정자] 리턴타입 [클래스명].메소드명(파라미터)) : 메소드 실행에 대한 조인포인트를 지정한다. 
	 *  		접근지정자 : public, private (생략가능)
	 *  		리턴타입 : 메소드의 반환값을 의미한다.
	 *  		클래스명 : 클래스의 풀패키지명이 포함된 이름
	 *  		" * " : (리턴타입) 모든 값을 표현한다.
	 *  		" .. " : (매개변수) 0개 이상을 의미, 타입 지정해도 된다. (int) 
	 *  		args() : 타겟 메소드에 전달되는 파라미터 값을 어드바이스에 전달하기 위한 파라미터를 지정한다. 
	 *  		bean() : 포인트커트 표현식 내에서 ID 사용해서 지정 가능
	 *  		@annotation : 조인포인트의 대상 객체가 주어진 어노테이션을 갖는 조인포인트틀 정의한다. 
	 */
	
	@Autowired(required = false)
	private Character character;
	
	@Test
	void test() {
	}
	
	@Test
	void create() {	
		System.out.println(character);
		System.out.println(character.getJob());
		
		assertThat(character).isNotNull();
		assertThat(character.getJob()).isNotNull();
	}
	
	@Test
	void execQuest() throws Exception{
		character.quest("초보사냥");			// 매개변수 (questName = 초보사냥) 으로 전달 
//		assertThat(character.quest("초보사냥")).isNotNull();
	}
	
	@Test
	void bark() throws Exception{
//	void execSkill() throws Exception{
		assertThat(character.getJob().skill()).isNotNull();
//		assertThat(character.getJob().skill()).isNull();
		
	}

}
