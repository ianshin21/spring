package com.kh.aop.pet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.aop.annotation.PetBarkRepeat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component   //bean으로 자동 등록됨
public class Cat implements Pet {
	@Value("야용이")
	private String name;
	
	@Override
	@PetBarkRepeat(value = "안녕하세요", repeatCount = 3)
	public String bark() throws Exception {
//		if(true) {
//		throw new Exception();
//	}
		return name + " 야옹~!";
	}
}
