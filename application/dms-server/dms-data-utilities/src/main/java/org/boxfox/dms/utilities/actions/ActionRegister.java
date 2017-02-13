package org.boxfox.dms.utilities.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;

/* boxfox 2017.02.13*/

public class ActionRegister {
	private static final String EXCEPTION_ALREADY = "이미 Command를 사용중입니다.";
	private HashMap<Integer, Actionable> actionmap;
	private static ActionRegister instance;

	private ActionRegister() {
	}

	public static void registerAction(int command, Actionable action) throws RegisterException {
		if (getInstance().actionmap.get(command) != null) {
			if (getInstance().actionmap.get(command).getClass().equals(action.getClass()))
				return;
			throw new RegisterException(EXCEPTION_ALREADY);
		} else
			getInstance().actionmap.put(command, action);
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
