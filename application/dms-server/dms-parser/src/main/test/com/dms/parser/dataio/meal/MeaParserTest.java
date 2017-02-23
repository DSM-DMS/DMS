package com.dms.parser.dataio.meal;

import org.junit.Test;

import com.dms.parser.datamodel.meals.DayMeal;

public class MeaParserTest {
	
	@Test
	public void mealParser(){
		System.out.println("test");
		DayMeal meal = new MealParser(2016,12,21).parse();
		System.out.println(meal.toQuery());
	}
}
