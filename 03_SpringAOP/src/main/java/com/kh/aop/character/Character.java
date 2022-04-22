package com.kh.aop.character;


import com.kh.aop.job.Job;
import com.kh.aop.job.Warrior;

import lombok.Data;

@Data
public class Character {
	
	private String name;
	
	private int level;
	
//	private Warrior job;
	private Job job;
	
	public String quest(String questName) throws Exception{
//	public void quest(String questName) throws Exception{
		
//		if(true) {
//			throw new Exception();
//		}									// 예외를 발생시키는 로직
		
//		System.out.println(questName + "퀘스트 수행 중"); // 반환형 String이 없을 때 출력된다. 
							
		return questName + " 퀘스트 수행 중";
		
	}
	
	
}
