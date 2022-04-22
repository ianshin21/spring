package com.kh.di.character;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.SpringJavaConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringJavaConfig.class})  
class CharacterTest {
	
	// required는 빈 주입이 필수로 진행되어야 하는지 설정하는 옵션	
	// true(default)일 경우 빈이 애플리케이션 컨텍스트에 존재하지 않으면 Exception 발생 
	// required가 false일 경우 주입되어야 하는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 null을 주입한다. 그래도 빈 등록이 없으면 어차피 test는 실패로 나타난다.

	// 빈이 애플리케이션 컨텍스트에 존재하게 하려면 어떻게 하나? 빈등록 : Character에 @Component 붙인다. 그리고 빈스캐닝 활성화 : 
	// 빈스캐닝 활성화 : servlet-context.xml에 이미 있다. 그러나 현재 @ContextConfiguration(classes = {SpringJavaConfig.class}) 사용하는 경우
	//               SpringJavaConfig.java에 @ComponentScan("com.kh.di")	 이렇게 등록
	
	@Autowired(required = false)
	private Character character;
	
//	@Value("${common.name}")
//	private String name;
//	
//	@Value("${common.level}")
//	private int level;

	@Test
	void test() {
	}
	
	@Test
	void create() {
		
		System.out.println(character.getName());
		System.out.println(character.getLevel());
//		System.out.println(character.getJob().work());
		
		assertThat(character).isNotNull();
//		assertThat(character.getName()).isNotNull().isEqualTo(name);
//		assertThat(character.getLevel()).isGreaterThan(0).isEqualTo(level);
		assertThat(character.getJob()).isNotNull();
	}
	
	@Test
	void work() {
		
		System.out.println(character.getName());
		System.out.println(character.getLevel());
		System.out.println(character.getJob().work());
		
		assertThat(character.getJob()).isNotNull();
		assertThat(character.getJob().work()).isNotNull();
	}

}
