package com.boxfox.dms.board.mapper;

import com.boxfox.dms.meal.dto.DayMealDTO;
import com.boxfox.dms.meal.dto.DayMealTest;

public interface MealMapper {

	public DayMealDTO getMealAtDate(String date);
	public void insertMealAtDate(DayMealTest meal);
}
