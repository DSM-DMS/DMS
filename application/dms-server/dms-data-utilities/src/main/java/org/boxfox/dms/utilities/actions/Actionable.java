package org.boxfox.dms.utilities.actions;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;

/**
 * @author JoMingyu
 */
public interface Actionable {
	final DataBase database = DataBase.getInstance();
	 
	final EasyJsonObject responseObject = new EasyJsonObject();
	final EasyJsonArray array = new EasyJsonArray();
	
	public abstract EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException;
}
