package com.javateam.STDProject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.javateam.STDProject.domain.StudentVO;

//@SpringBootTest
class StdProject0219ApplicationTests {

	@Test
	void contextLoads() {
		
		StudentVO vo = new StudentVO();
		System.out.println(vo.getTitle());
		
	}

}
