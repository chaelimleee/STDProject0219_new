package com.javateam.STDProject.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Data
@Table(name = "FOOD_TBL") // 테이블 매핑.
public class FoodVO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "cooking_instruction")
	private String cooking_instruction;
	
	@Column(name = "ingredient")
	private String ingredient;

	@Column(name = "minor_ingredient")
	private String minor_ingredient;

	@Column(name = "reference")
	private String reference;

	@Column(name = "cook")
	private String cook;	
	
	@Column(name = "cooking_origin")
	private String cooking_origin;	
	
	@Column(name = "food_img")
	private String food_img;	
	
	@Column(name = "food_name")
	private String food_name;	
	
	@Column(name = "food_type")
	private String food_type;
	
	@Column(name = "information_provider")
	private String information_provider;	

	@Column(name = "recipe")
	private String recipe;	
	
	
	
}
