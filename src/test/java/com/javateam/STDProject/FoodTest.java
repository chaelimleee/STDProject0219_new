package com.javateam.STDProject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.javateam.STDProject.domain.FoodVO;

@SpringBootTest
public class FoodTest {
	
	@Test
	void testFoodGetter(){
		
		FoodVO vo = new FoodVO();
		List<FoodVO> list = new ArrayList<FoodVO>();
		vo.getFood_name();
		System.out.println(list);
		System.out.println(vo.getFood_name());
		
	}

}
