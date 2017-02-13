package org.boxfox.dms.utilities.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.json.simple.JSONObject;
import org.reflections.Reflections;

/* boxfox 2017.02.13*/

public class ActionRegister {
	private static final String EXCEPTION_ALREADY = "이미 Command를 사용중입니다.";
	private HashMap<Integer, Actionable> actionmap;
	private static ActionRegister instance;

	private ActionRegister() {
	}

	private Actionable getAction(int commnad) {
		return actionmap.get(commnad);
	}

	private void putAction(int command, Actionable action) {
		actionmap.put(command, action);
	}

	public static void registerAction(int command, Actionable action) throws RegisterException {
		if (getInstance().getAction(command) != null) {
			if (getInstance().getAction(command).getClass().equals(action.getClass()))
				return;
			throw new RegisterException(EXCEPTION_ALREADY);
		} else
			getInstance().putAction(command, action);
	}

	public static boolean executeAction(int command, EasyJsonObject requestObject) throws SQLException {
		boolean result = false;
		Actionable action = getInstance().getAction(command);
		if (action != null) {
			action.action(command, requestObject);
			result = true;
		}
		return result;
	}

	public static ActionRegister getInstance() {
		if (instance == null) {
			instance = new ActionRegister();
			instance.actionmap = new HashMap<Integer, Actionable>();
			init();
		}
		return instance;
	}

	public static void init() {
		Reflections reflections = new Reflections("org");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ActionRegisteration.class);
		for (Class<?> c : annotated) {
			ActionRegisteration annotation = c.getAnnotation(ActionRegisteration.class);
			try {
				registerAction(annotation.command(), (Actionable) c.newInstance());
			} catch (InstantiationException | IllegalAccessException | RegisterException e) {
				e.printStackTrace();
			}
		}
	}

}
