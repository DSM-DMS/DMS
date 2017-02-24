package com.dms.parser.dataio.meal;

import org.junit.Test;

import com.dms.parser.dataio.plan.PlanParser;
import com.dms.parser.datamodel.meals.DayMeal;

public class ParserTest {
	
	@Test
	public void mealParser(){
		System.out.println("Meal Parser Test");
		DayMeal meal = new MealParser(2016,12,21).parse();
		System.out.println(meal.toQuery());
	}
	
	@Test
	public void planParser(){
		System.out.println("Plan Parser Test");
		PlanParser parser = new PlanParser(2016, 3);
		System.out.println(parser.parse().toString());
	}
}
