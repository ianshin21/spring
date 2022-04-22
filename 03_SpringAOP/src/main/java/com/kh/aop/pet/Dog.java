package com.kh.aop.pet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.kh.aop.annotation.NoLogging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component   //bean으로 자동 등록됨
@Primary
public class Dog implements Pet{
	@Value("뽀삐")
	private String name;
	
	@Override
	@NoLogging
	public String bark() throws Exception  {
		
		return name + " 왈왈~!";
	}
}
