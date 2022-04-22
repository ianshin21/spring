package com.kh.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Annotation
 * 	- JDK5부터 추가된 기능으로 자바 코드에 추가적인 정보를 제공하는 메타데이터(데이터를 설명하는 데이터/데이터에 대한 정보를 담고 있는 데이터)
 * 	- 비지니스 로직에 영향을 주지는 않지만 컴파일 과정에서 유효성 체크, 코드를 어떻게 컴파일하고 처리할지 알려주는 정보 
 * 	- 자바 리플렉션 API 을 이용하면 다양한 ... 구현 가능
 * 	- 어노테이션은 클래스, 메소드, 변수, 매개변수 등에 추가할 수 있다. 
 */

//어노테이션을 적용할 위치(대상)를 지정한다. : 메소드, type 에만 적용하도록 다음과 같이 한다. Dog에서 확인
// 지정하지 않은 곳에 annotation을 붙이면 에러난다. ex) Dog에서 METHOD, TYPE(클래스)에는 @NoLogging 붙여도 되지만 멤버변수 같은 데 붙이면 에러남
@Target({ElementType.METHOD, ElementType.TYPE})
// 부모클래스에 어노테이션을 선언하면 자식클래스에도 상속된다. 
//@Inherited
//어노테이션의 유효범위를 지정할 때 : 여기서 지정하고 target에서 Dog.class 찾아서 확인 
//RetentionPolicy.SOURCE : 코드상에만 유효
//RetentionPolicy.CLASS : 컴파일러가 클래스를 참조할 때까지 유효
//RetentionPolicy.RUNTIME :컴파일 이후에도 jVm에 의해서 참조 가능  : 여기서는  AOP에서 실험해보고자 하니 RUNTIME으로 설정 
@Retention(RetentionPolicy.RUNTIME)
public @interface NoLogging {

	// 파일 생성시에 annotation으로 생성했기 때문에 annotation 파일이 된다. @interface로 구분해 준다.
}
