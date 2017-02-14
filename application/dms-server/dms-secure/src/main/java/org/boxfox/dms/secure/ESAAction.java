package org.boxfox.dms.secure;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=7514)
public class ESAAction implements Actionable{

	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		
		return null;
	}

}
