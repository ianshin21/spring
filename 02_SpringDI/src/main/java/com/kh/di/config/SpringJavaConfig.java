package com.kh.di.config;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.kh.di.owner.Owner;
import com.kh.di.pet.Cat;
import com.kh.di.pet.Dog;
import com.kh.di.pet.Pet;

// @Configuration을 사용하는 것으로 해당 클래스가 congif, 자바 설정 파일임을 선언한다. 설정용 파일, 비즈니스로직에 영향을 미치지 않는다. (xml 설정을 자바컨피그로 바꿈)
// Spring DI Annotation 방식 빈 스캐닝(Bean Scanning)시 
//     servlet-context.xml에 있는 <context:component-scan base-package=“com.kh.di” /> 태그 대신  
//     SpringJavaConfig.java에 @ComponentScan("com.kh.di")	 이렇게 등록 
@Configuration
@ComponentScan("com.kh.di")				
public class SpringJavaConfig {
	
	//Bean ID를 지정하지 않으면 메소드명dog()으로 Bean의 ID를 지정한다. 
	// owner의 Bean도 두 개이고, pet의 Bean도 두 개 이므로 그냥 돌리면 에러남. 그래서 Bean ID를 각각 정해주고 @Qualifier 사용
	// 이 Bean은 pet-context.xml에서 등록한 pet에 관한 Bean과 같다. (xml 설정을 자바컨피그로 바꿈)
	@Bean
	public Dog dog() {
		return new Dog("멍멍이"); // 생성자를 통한 의존성 주입 (xml 설정을 자바컨피그로 바꿈)
	}
	
	// Bean ID를 kitty로 지정
	@Bean("kitty")
	public Cat cat() {
		Cat cat = new Cat();
		
		cat.setName("kitty"); // Setter 메소드를 통한 의존성 주입 (xml 설정을 자바컨피그로 바꿈)
		
		return cat;
	}
	
	// 이 Bean은 owner-context.xml에 등록한 owner에 관한 Bean과 같다.(xml 설정을 자바컨피그로 바꿈)
	@Bean
	public Owner owner() {
		//dog()메소드는 빈으로 등록되어있기 때문에 호출 시 마다 객체를 생성하는 것(new로 새로 생성)이 아니라
		// 애를리케이션 컨텍스트에 등록된 빈 객체 리턴 
//		return new Owner("서은주", 17, new Cat("야옹"));   // Bean 생성시 직접 호출 
		return new Owner("서은주", 17, dog()); 
	}

//	 Bean ID를 janice로 지정
//	
	@Bean("janice")
	public Owner owner2(/*@Autowired (생략 가능)*/ @Qualifier("kitty") Pet pet) {
										// Bean 생성시 매개변수 사용
		return new Owner("김소요", 17, pet); 
	}
	
	
	// 이 Bean은 Character를 실습하는 Bean이다. 
	// @PropertySource를 생략 후 스프링 프로퍼티 플레이스 홀더를 통해서 common.propertie 프로퍼티를 가져오려는 방식이다.
	@Bean 
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = 
				new PropertySourcesPlaceholderConfigurer();
		
		Resource[] resources = new ClassPathResource[] {
			new ClassPathResource("common.properties"),
			new ClassPathResource("db.properties")
		};
		
		configurer.setLocations(resources);
		
		return configurer;
	}
}
