package com.kh.di.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
// 동일한 타입의 빈이 여러개 일 때 기본으로 지정
@Primary
@Component
public class Warrior implements Job{
	@Value("전사")
	private String name;

	@Override
	public String work() {		
		return "칼질";
	}
}
