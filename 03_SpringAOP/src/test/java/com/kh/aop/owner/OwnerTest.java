package com.kh.aop.owner;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.aop.config.SpringJavaConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {SpringJavaConfig.class})
class OwnerTest {
//	@Autowired(required = false) // owner에서 Component 등록으로 required = false 필요없어짐
	@Autowired
	private Owner owner;
	
	@Test
	void test() {
	}
	
	@Test
	void create() {
		
		assertThat(owner).isNotNull();
		assertThat(owner.getName()).isNotNull();
		assertThat(owner.getAge()).isNotNull();
		assertThat(owner.getPet()).isNotNull();
		
	}
	
	@Test
	void execBark() throws Exception {		
//		System.out.println(owner.getPet().bark());
		assertThat(owner.getPet().bark()).isNotNull();
		
	}

}
