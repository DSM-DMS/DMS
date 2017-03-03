package com.boxfox.dms.response;

public class Guardian {

	public static boolean checkParameters(Object... args) {
		for (Object obj : args) {
			if (obj == null)
				return false;
		}
		return true;
	}

}
