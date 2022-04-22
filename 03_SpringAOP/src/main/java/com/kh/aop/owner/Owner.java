package com.kh.aop.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.aop.pet.Pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component   //bean으로 자동 등록됨, java-config에 따로 등록할 필요 없다.
public class Owner {
	
	@Value("김희득")
	private String name;
	
	@Value("30")
	private int age;
	
	@Autowired
	@Qualifier("cat")
	private Pet pet;

}
