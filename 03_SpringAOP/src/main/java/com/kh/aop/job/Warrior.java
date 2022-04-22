package com.kh.aop.job;

import lombok.Data;

@Data
public class Warrior implements Job{
	
	private String name;

	@Override
	public String skill() throws Exception {		
		
//		if(true) {
//			throw new Exception();
//		}
		return "리프어택";
	}
}
