package com.boxfox.dms.meal.dao;

import com.boxfox.dms.meal.dto.DayMealDTO;

public interface MealDAO {
	public DayMealDTO getMeal(int year, int month, int day);
	public DayMealDTO getMealToday();
}
