package com.kh.di.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Qualifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.di.config.SpringJavaConfig;
import com.kh.di.pet.Cat;
import com.kh.di.pet.Dog;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringJavaConfig.class})  
//@ContextConfiguration(locations = {"classpath:WEB-INF/spring/root-context.xml"})  // 경로가 여러개면 , , 로 연결 가능
class OwnerTest {

	// @Autowired : 에플리케이션 컨텍스트 상에서 클래스 타입과 일치하는 빈을 자동으로 주입시켜준다. 
	// 동일한 클래스 타입이 존재할 경우 @Qualifier("빈 ID"), 를 명시적으로 넣어서 우선권을 준다. 
	//                         @Qualifier("owner1") : root-context에 owner가 여러개 일때
	@Autowired
	@Qualifier("janice")		// SpringJavaConfig.class 사용시 우선권 부여
	private Owner owner;
	
	@Test
	void nothing() {
		
	}
	
	@Test
	void create() {
		// 기존에 자바 에플리케이션에서 인터페이스와 생성자를 통해 객체간의 결합을 (약간)느슨하게 만들어주는 방법
		Owner owner = new Owner("문인수", 20, new Cat("뽀삐"));
		Owner owner2 = new Owner("공원상", 20, new Dog("멍멍"));
		
		System.out.println(owner.getPet().bark());
		System.out.println(owner2.getPet().bark());
		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
	}
	
	@Test
	void contextTest() {
		//스프링의 에플리케이션 컨텍스트를 활용하여 객체간의 결합을 더욱 느슨하게 만들어준다. 
		//GenericXmlApplicationContext("클래스패스 상의 설정 xml 파일 위치 지정)
		// target아래에 WEB-INF가 없어서 root-context.xml를 읽어올 수 없다. 
		//   java Build Path에서 source => Add folder =>wepapp 추가
		// target에 있는 classes(클래스패스의 시작점) 밑에 WEB-INF가 새로 생성된다. => spring => root-context.xml을 읽어온다. 
		
		// 대부분은 @Autowired로 한다. 아래와 같이 안한다 => void autoWiredTest() 이걸로
		ApplicationContext context = 
				new GenericXmlApplicationContext("WEB-INF/spring/root-context.xml");
			
		Owner owner = (Owner)context.getBean("owner");  //형변환
//		Owner owner = context.getBean("owner", Owner.class);  // Owner.class : Owner를 클래스 형식으로 넘겨준다. 결과적으로 위의 형변환과 같다.

//		AnnotationConfigApplicationContext annotationContext = 
//				new AnnotationConfigApplicationContext(SpringJavaConfig.class);
//		Owner owner = annotationContext.getBean("owner", Owner.class);
		
		System.out.println(owner);
		System.out.println(owner.getPet());
		System.out.println(owner.getPet().bark());
		
		assertThat(owner).isNotNull();
		assertThat(owner.getPet()).isNotNull();
		
	}
	
	@Test
	void autoWiredTest() {
		System.out.println(owner);
		System.out.println(owner.getPet());
		System.out.println(owner.getPet().bark());
		
		assertThat(owner).isNotNull();

	}

}
