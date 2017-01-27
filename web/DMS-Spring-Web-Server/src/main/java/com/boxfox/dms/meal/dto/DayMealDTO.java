package com.boxfox.dms.meal.dto;

public class DayMealDTO {
	private String date;
	private String breakfast;
	private String lunch;
	private String dinner;
	private String breakfastAllergy;
	private String lunchAllergy;
	private String dinnerAllergy;
	
	public DayMealDTO(String date, Meal... meals) {
		this.date = date;
		breakfast = meals[0].getMenu().toJSONString();
		lunch = meals[1].getMenu().toJSONString();
		dinner = meals[2].getMenu().toJSONString();
		breakfastAllergy = meals[0].getAllergies().toJSONString();
		lunchAllergy = meals[1].getAllergies().toJSONString();
		dinnerAllergy = meals[2].getAllergies().toJSONString();
	}
	
	public DayMealDTO() {}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}
	public String getBreakfastAllergy() {
		return breakfastAllergy;
	}
	public void setBreakfastAllergy(String breakfastAllergy) {
		this.breakfastAllergy = breakfastAllergy;
	}
	public String getLunchAllergy() {
		return lunchAllergy;
	}
	public void setLunchAllergy(String lunchAllergy) {
		this.lunchAllergy = lunchAllergy;
	}
	public String getDinnerAllergy() {
		return dinnerAllergy;
	}
	public void setDinnerAllergy(String dinnerAllergy) {
		this.dinnerAllergy = dinnerAllergy;
	}
	
	/*private String date;
	private JSONArray breakfast;
	private JSONArray lunch;
	private JSONArray dinner;
	private JSONArray breakfastAllergy;
	private JSONArray lunchAllergy;
	private JSONArray dinnerAllergy;
	
	public DayMealDTO(String date, Meal... meals) {
		this.date = date;
		breakfast = meals[0].getMenu();
		lunch = meals[1].getMenu();
		dinner = meals[2].getMenu();
		breakfastAllergy = meals[0].getAllergies();
		lunchAllergy = meals[1].getAllergies();
		dinnerAllergy = meals[2].getAllergies();
	}
	
	public DayMealDTO() {}
	

	public String getBreakfast() {
		return breakfast.toJSONString();
	}

	public String getLunch() {
		return lunch.toJSONString();
	}

	public String getDinner() {
		return dinner.toJSONString();
	}

	public String getBreakfastAllergy() {
		return breakfastAllergy.toJSONString();
	}

	public String getLunchAllergy() {
		return lunchAllergy.toJSONString();
	}

	public String getDinnerAllergy() {
		return dinnerAllergy.toJSONString();
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public JSONArray getBreakfastJ() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = (JSONArray)JSONValue.parse(breakfast);
	}

	public JSONArray getLunchJ() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = (JSONArray)JSONValue.parse(lunch);
	}

	public JSONArray getDinnerJ() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = (JSONArray)JSONValue.parse(dinner);
	}

	public JSONArray getBreakfastAllergyJ() {
		return breakfastAllergy;
	}

	public void setBreakfastAllergy(String breakfastAllergy) {
		this.breakfastAllergy = (JSONArray)JSONValue.parse(breakfastAllergy);
	}

	public JSONArray getLunchAllergyJ() {
		return lunchAllergy;
	}

	public void setLunchAllergy(String lunchAllergy) {
		this.lunchAllergy = (JSONArray)JSONValue.parse(lunchAllergy);
	}

	public JSONArray getDinnerAllergyJ() {
		return dinnerAllergy;
	}

	public void setDinnerAllergy(String dinnerAllergy) {
		this.dinnerAllergy = (JSONArray)JSONValue.parse(dinnerAllergy);
	}*/

}
