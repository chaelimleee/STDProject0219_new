package com.javateam.foodCrawlingDemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javateam.foodCrawlingDemo.domain.FoodVO;

@Service
public class FoodCrawlingService {
	
	@Autowired
	private FoodRepository foodRepository;
	
	public void saveCrawData(String foodName, String foodImg, String foodType,
				             String recipe, String ingredient, String minorIngredient,
				             String cookingInstruction, String cook, String reference,
				             String cookingOrigin, String informationProvider) {
		
		FoodVO foodvo = new FoodVO();
		
		foodvo.setFoodName(foodName);
		foodvo.setFoodImg(foodImg);
		foodvo.setFoodType(foodType);
		foodvo.setRecipe(recipe);
		foodvo.setIngredient(ingredient);
		foodvo.setMinorIngredient(minorIngredient);
		foodvo.setCookingInstruction(cookingInstruction);
		foodvo.setCook(cook);
		foodvo.setReference(reference);
		foodvo.setCookingOrigin(cookingOrigin);
		foodvo.setInformationProvider(informationProvider);
        
        foodRepository.save(foodvo);
		
	}
	
	

}
