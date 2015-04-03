package com.zsgj.foodsecurity.bean;

import java.util.List;

/** 
 * @author Homer
 * @version 2015-4-3 上午9:43:30
 */
public class Recipes extends DtoBase {
	 private List<Recipe> Recipes;

	public List<Recipe> getRecipes() {
		return Recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		Recipes = recipes;
	}
	 

}
