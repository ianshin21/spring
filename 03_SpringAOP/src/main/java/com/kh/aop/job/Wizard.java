package com.kh.aop.job;

import lombok.Data;

@Data
public class Wizard implements Job {
	
	private String name;
	
	@Override
	public String skill() throws Exception {		
		
//		if(true) {
//		throw new Exception();
//	}		
		return "파이어 볼~";
	}

}
