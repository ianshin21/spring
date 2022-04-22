package com.kh.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.kh.aop.annotation.PetBarkRepeat;

@Aspect			// <aop:aspect ref="characterAspect">와 같은 역할
@Component      //bean으로 자동 등록됨, java-config에 따로 등록할 필요 없다.  
				//root-context에도 <bean id="characterAspect" class="com.kh.aop.aspect.CharacterAspect"/> 이런식으로 등록 필요 없다. 
public class OwnerAspect {
	
//	@Before("execution(* com.kh.aop.pet.*.bark()) && !bean(cat)") 
//	@Before("execution(* com.kh.aop.pet.*.bark()) && bean(cat)") 
//	@Before("execution(* com.kh.aop.pet.*.bark())") 
	public void before() {
		System.out.println("짖어~~");
	}
	
	// @After. AfterReturning, @AfterThrowing 작성해보기 
	
//	@Around("execution(* com.kh.aop.pet.*.bark())")
//	@Around("execution(* com.kh.aop.pet.*.bark())"
//			+ "&& @annotation(com.kh.aop.annotation.NoLogging)")
//	public String around(ProceedingJoinPoint jp) {
//		String result = null;
//		
//		try {
//			System.out.println("짖어~~");
//
//			result = (String)jp.proceed();
//			
//			System.out.println(result);
//		} catch (Throwable e) {
//			System.out.println("왜 .. 안 짖니?..");
//		}
//		
//		return result;
//	}

	
	@Around("@annotation(com.kh.aop.annotation.PetBarkRepeat)")
	public String around(ProceedingJoinPoint jp) {
		String result = null;
		MethodSignature signature = (MethodSignature) jp.getSignature();
		PetBarkRepeat repeat = signature.getMethod().getAnnotation(PetBarkRepeat.class);
		
		System.out.println(repeat);
		
		try {
			System.out.println(repeat.value());

			result = (String)jp.proceed();
			
			for (int i = 0; i < repeat.repeatCount(); i++) {
				
				System.out.println(result);
			}
		} catch (Throwable e) {
			System.out.println("왜 .. 안 짖니?..");
		}
		
		return result;
	}

}
