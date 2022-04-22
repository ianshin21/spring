 package com.kh.di.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.kh.di.job.Job;

import lombok.Data;
import lombok.Getter;

// 2-2. 다른 클래스에서도 /@PropertySource("classpath:common.properties")를 사용해야 한다면 일일이 넣어주지 말고 아래 1), 2)와 같이.  
//     아래의 @PropertySource를 생략 후 스프링 프로퍼티 플레이스 홀더를 통해서 common.propertie 프로퍼티를 가져오려면 
//   1) servlet-context.xml 설정 파일에 <context:property-placeholder location="classpath:common.properties"/> 추가
//         여러개의 properties를 지정할 때는 location 속성에 , 로 구분해서 나열한다. (classPath:*.properties)
//   2) SpringJavaConfig.java 설정 파일에 PropertySourcesPlaceholderConfigurer 빈 등록 및 코드 작성

//@Data
@Component
@Getter
//@PropertySource("classpath:common.properties")
public class Character {
	
	// 2-1. 스프링 프로퍼티 플레이스 홀더를 통해서 common.properties에 설정된 값을 읽어올 수 있다. 
	//     common.properties에서 / name=ismoon  level=90 => @Value("${name}") @Value("${level}")
	// 3. common.properties에 설정된 값을 name1, level1 등으로 바꾸고
	//       @Value("${name:공원산}"), @Value("${level:70}")으로 설정하면 이 값을 먼저 읽어 온다. ${key:기본값} 
	// 4. 여러 properties를 사용하는 경우를 위해서 최종적으로 common 추가 
	@Value("${common.name}")
//	@Value("김길동")             
	private String name;
	
	@Value("${common.level}")
//	@Value("20")				// @Value(“Spring”)은 <property .. value=“Spring”/>와 동일한 역할을 한다.
	private int level;
	
	@Autowired					// @Autowired 는 <property>, <constructor-arg> 태그와 동일한 역할을 한다
	@Qualifier("wizard")		// 클래스에서 @Primary 사용해도 된다. 어쨌던 이런 설정이 없으면 어플리케이션 컨텍스트가 빈을 읽어올 때 같은 빈이 두 개라서 에러남 
	private Job job;
//	
//	 1. Environment 객체를 통해서 common.properties 에 설정된 값을 읽어올 수 있다. 
//	public Character(Environment env) {
//		System.out.println(env.getProperty("common.name"));
//		System.out.println(env.getProperty("common.level"));
//	}
	
//  어노테이션을 사용하는 방식 중 Setter 메소드를 통한 의존성 주입	
//	@Autowired
//	public void setJob(Job job) {
//		this.job = job;
//	}
	
	// 어노테이션을 사용하는 방식 중 생성자를 통한 의존성 주입
	// 에플리케이션 컨텍스트에서 기본생성자가 없어서 Character 빈을 생성하기 위해서는 아래의 생성자를 통해서만 생성할 수 있는데
	// 애플리케이션 컨텍스트에서 Job 타입으로 생성된 빈이 존재하면 자동으로 주입해주기 때문에 @Autowired 생략 가능 
	// @Qualifier("wizard") (잡 앞에) 사용 가능	
//	public Character(@Value("문인수")String name, @Value("90")int level, /*@Autowired (생략 가능)*/@Qualifier("wizard")Job job) {
//	
//		this.name = name;
//		this.level = level;
//		this.job = job;
//	}
}
