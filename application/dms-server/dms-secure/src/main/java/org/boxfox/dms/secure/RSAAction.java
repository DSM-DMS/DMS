package org.boxfox.dms.secure;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command = 7513)
public class RSAAction implements Actionable{
    public static final int COMMAND_RSA = 7513;
    public static final int COMMAND_AES = 7514;

	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		System.out.println("test");
		return null;
	}

}
