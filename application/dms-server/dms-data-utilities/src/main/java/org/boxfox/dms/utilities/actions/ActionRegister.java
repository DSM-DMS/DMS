package org.boxfox.dms.utilities.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
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
		ActionRegister register = getInstance();
		if (register.getAction(command) != null) {
			if (register.getAction(command).getClass().equals(action.getClass()))
				return;
			throw new RegisterException(EXCEPTION_ALREADY);
		} else
			register.putAction(command, action);
	}

	public static EasyJsonObject executeAction(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		//String UUID = Sender.getUUID();
		//
		//SafeResultSet rs = DataBase.getInstance().executeQuery("select securekey ");
		//if(rs.next()){  }
		
		EasyJsonObject responseObject = null;
		Actionable action = getInstance().getAction(command);
		if (action != null) {
			responseObject = action.action(sender, command, requestObject);;
		}
		return responseObject;
	}

	public static ActionRegister getInstance() {
		if (instance == null) {
			instance = new ActionRegister();
			instance.actionmap = new HashMap<Integer, Actionable>();
			init();
		}
		return instance;
	}

	public static void init(String ... packages) {
		for(String package_s : packages){
			searchActions(package_s);
		}
	}
	
	private static void searchActions(String pacakge){
		Reflections reflections = new Reflections(pacakge);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ActionRegistration.class);
		for (Class<?> c : annotated) {
			ActionRegistration annotation = c.getAnnotation(ActionRegistration.class);
			try {
				registerAction(annotation.command(), (Actionable) c.newInstance());
			} catch (InstantiationException | IllegalAccessException | RegisterException e) {
				e.printStackTrace();
			}
		}
	}

}
