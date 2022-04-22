package com.kh.di.job;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
// @Component 빈 생성시 별도의 ID를 지정해주지 않으면 클래스 명의 앞글자를 소문자로 바꾼 ID를 갖는다. 
// @Component("wizard111") 이렇게 지정해줘도 됨. @Qualifier("wizard") 여기서도 소문자로
@Component
public class Wizard implements Job {
	@Value("마법사")
	private String name;
	
	@Override
	public String work() {		
		return "파이어 볼~";
	}

}
