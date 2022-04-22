package com.kh.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect   //<aop:aspect ref="characterAspect">와 같은 역할
public class CharacterAspect {
	
	@Pointcut("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)")
	public void questPointcut(String questName) {}
	
//	@Before("execution(* com.kh.aop.character.Character.quest(..))")
//	public void beforeQuest(JoinPoint jp) {
	public void beforeQuest() {
		//퀘스트를 수행하기 전에 필요한 부가 작업들 수행
//		System.out.println((String)jp.getArgs()[0] + " 퀘스트 준비중 입니다.");
		System.out.println(" 퀘스트 준비중 입니다.");
	}
	
//	@After("execution(* com.kh.aop.character.Character.quest(..))")
	public void afterQuest() {
		// //퀘스트 수행 결과에 상관없이(예외를 던졌을 때도) 메소드 수행 후에 필요한 부가 작업을 수행
		
		System.out.println(" 퀘스트 수행 완료");
	}
	
//	@AfterReturning(value = "questPointcut(questName)", returning = "result")
	public void questSuccess(String questName, String result) {
		//퀘스트 수행 성공 후에 필요한 부가 작업 수행
		
		System.out.println(result);				// 2. 타겟 메소드에 리턴 값이 있을 경우  :  Character.quest(String questName)에서 return questName + " 퀘스트 수행 중";
		System.out.println(questName + " 퀘스트 완료");
	}
	
//	@AfterThrowing("execution(* com.kh.aop.character.Character.quest(..)) && args(questName)")   아래로 바꿈
//	@AfterThrowing("questPointcut(questName)")
	public void questFail(String questName) {
		//퀘스트 수행시 예외를 던졌을 때 필요한 부가 작업 수행
		
		System.out.println(questName + " 퀘스트 실패");
	}
	
	@Around("execution(* com.kh.aop.character.Character.quest(..))")
	public String aroundQuest(ProceedingJoinPoint jp) {
		String result = null;
		String questName = (String)jp.getArgs()[0];
		
		try {
			//before 어드바이스에 대한 기능 수행
			System.out.println(questName + " 퀘스트 준비중 입니다.");
					// 원래 questName 자리에 (String)jp.getArgs()[0]; 있었음
			
			//1. 타겟 메소드를 실행시키는 메소드이다.  :  Character.quest(String questName)에서 반환형 String이 없을 때 
											//  System.out.println(questName + "퀘스트 수행 중"); 출력
//			jp.proceed();
			
			// 2. 타겟 메소드에 리턴 값이 있을 경우  :  Character.quest(String questName)에서 return questName + " 퀘스트 수행 중"; 												  
//			String result = (String)jp.proceed();     //JoinPoint Interface 형변환 필요 
//			System.out.println(result);				// return값을 String으로 받아서 형변환 후 여기서 출력

			// 3. 타겟 메소드에 파라미터 값을 변경해서 전달해 줄 경우
			result = (String)jp.proceed(new Object[] {new String("[" + questName + "]")});
			System.out.println(result);
			
			// after-returning 어드바이스에 대한 기능 수행
			System.out.println(questName + " 퀘스트 완료");
			
		} catch (Throwable e) {
			// after-throwing 어드바이스에 대한 기능 수행
			System.out.println(questName + " 퀘스트 실패");
			//e.printStackTrace();
		}
		
		return result;
	}
	
	// 실습문제
	@Around("execution(* com.kh.aop.job.*.skill(..))")
//	@Around("execution(* com.kh.aop.job.Job.skill(..))")
//	@Around("execution(* com.kh.aop.job.Wizard.skill(..))")
	public String aroundSkill(ProceedingJoinPoint jp) {
//	public void aroundSkill(ProceedingJoinPoint jp) {
		String result = null;
		
		try {
			System.out.println("스킬을 준비중입니다.");
			
//			jp.proceed();
			result = (String)jp.proceed();
			
			System.out.println(result);
			
			System.out.println("스킬 성공입니다.");
		} catch (Throwable e) {
			System.out.println("스킬 실패입니다.");

		}
		
		return result;					// 어설트댓().isNotNull();로 받는다. 
		
	}

}
