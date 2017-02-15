package org.boxfox.dms.secure;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command = 7514)
public class AesAction implements Actionable{

	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String aesKey = requestObject.getString("AESKey");
		
		//User Register 필요
		
		
		
		return new EasyJsonObject();
	}

}
