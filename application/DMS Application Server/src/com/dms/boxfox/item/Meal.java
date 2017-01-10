package com.dms.boxfox.item;

import java.util.ArrayList;

public class Meal {
	private ArrayList<String> First_allergy			= new ArrayList<String>();
	private ArrayList<String> Second_allergy	= new ArrayList<String>();
	private ArrayList<String> Third_allergy		= new ArrayList<String>();
	private ArrayList<String> meal_First				= new ArrayList<String>();
	private ArrayList<String> meal_Second		= new ArrayList<String>();
	private ArrayList<String> meal_Third			= new ArrayList<String>();

	public Meal() { }

	public ArrayList<String> getFirst() {
		return meal_First;
	}
	public ArrayList<String> getLunch() {
		return meal_Second;
	}
	public ArrayList<String> getDinner() {
		return meal_Third;
	}

	public ArrayList<String> getAllergyFirst() {
		return First_allergy;
	}
	public ArrayList<String> getAllergyLunch() {
		return Second_allergy;
	}
	public ArrayList<String> getAllergyDinner() {
		return Third_allergy;
	}

	public void setBreakfast(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			meal_First.add(list.get(i));
		}
	}
	public void setLunch(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			meal_Second.add(list.get(i));
		}
	}
	public void setDinner(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			meal_Third.add(list.get(i));
		}
	}

	public void setMeals(String[] args, int count) {
		switch (count) {
		case 0:
			setBreakfast(args);
		case 1:
			setLunch(args);
		case 2:
			setDinner(args);
		}
	}

	public void setBreakfast(String[] args) {
		if (args.length > 2) {
			meal_First = Args.filter(args);
			First_allergy = Args.getAllergy(args);
		} else {
			for (int i = 0; i < args.length; i++) {
				meal_First.add(args[i]);
			}
		}

	}

	public void setLunch(String[] args) {
		if (args.length > 2) {
			meal_Second = Args.filter(args);
			Second_allergy = Args.getAllergy(args);
		} else {
			for (int i = 0; i < args.length; i++) {
				meal_Second.add(args[i]);
			}
		}
	}

	public void setDinner(String[] args) {
		if (args.length > 2) {
			meal_Third = Args.filter(args);
			Third_allergy = Args.getAllergy(args);
		} else {
			for (int i = 0; i < args.length; i++) {
				meal_Third.add(args[i]);
			}
		}
	}

	private static class Args {
		private static boolean allergy[] = new boolean[18];
		private static final String[] checks1 = { "?‘ ", "?‘¡", "?‘¢", "?‘£", "?‘¤", "?‘¥", "?‘¦", "?‘§", "?‘¨", "?‘©", "?‘ª", "?‘«", "?‘¬", "?‘­", "?‘®", "?‘¯", "?‘°", "?‘±" };
		private static final String[] checks2 = { "(1)", "(2)", "(3)", "(4)", "(5)", "(6)", "(7)", "(8)", "(9)", "(10)", "(11)", "(12)", "(13)", "(14)", "(15)", "(16)", "(17)", "(18)" };
		private static final String[] datas = { "?‚œë¥?", "?š°?œ ", "ë©”ë?", "?•…ì½?", "???‘", "ë°?", "ê³ ë“±?–´", "ê²?", "?ƒˆ?š°", "?¼ì§?ê³ ê¸°", "ë³µìˆ­?•„", "?† ë§ˆí† ", "?•„?™©?‚°?—¼", "?˜¸?‘", "?‹­ê³ ê¸°", "?‡ ê³ ê¸°", "?˜¤ì§•ì–´", "ì¡°ê°œë¥?" };

		public static ArrayList<String> filter(String[] args) {
			ArrayList<String> before = new ArrayList<String>();
			for (int i = 0; i < args.length; i++) {
				for (int j = 0; j < checks1.length; j++) {
					if (args[i].contains(checks1[j]) || args[i].contains(checks2[j])) {
						allergy[j] = true;
						args[i] = args[i].replace(checks1[j], "");
						args[i] = args[i].replace(checks2[j], "");
					}
				}
				if (args[i].contains("*")) {
					args[i] = args[i].replace("*", "");
				}
				before.add(args[i]);
			}
			return before;
		}

		public static ArrayList<String> getAllergy(String[] args) {
			ArrayList<String> arrs = new ArrayList<String>();
			for (int i = 0; i < allergy.length; i++) {
				if (allergy[i]) {
					arrs.add(datas[i]);
				}
			}
			return arrs;
		}
	}
}
