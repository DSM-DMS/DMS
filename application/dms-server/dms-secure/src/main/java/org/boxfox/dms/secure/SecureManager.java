package org.boxfox.dms.secure;

import java.util.HashMap;

import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;

public class SecureManager {
	private HashMap<String, AES256> keyMap;
	private static SecureManager instance;
	
	public static SecureManager getInstance(){
		if(instance == null)
			instance = new SecureManager();
		return instance;
	}

	private SecureManager() {
		keyMap = new HashMap<String, AES256>();
	}

	public EasyJsonObject createJsonObject(String buffer, Sender sender) {
		EasyJsonObject requestObject = null;
		AES256 aes = keyMap.get(sender.getUuid());
		if (aes != null) {
			requestObject = new EasyJsonObject(aes.decrypt(buffer));
		} else {
			requestObject = new EasyJsonObject(buffer);
		}
		return requestObject;
	}
	
	public void registerKey(Sender sender, AES256 aes){
		keyMap.put(sender.getUuid(), aes);
	}

}
