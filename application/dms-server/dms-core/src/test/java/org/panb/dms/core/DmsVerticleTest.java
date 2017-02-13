package org.panb.dms.core;

import java.sql.SQLException;

import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;
import org.junit.Test;

import com.dms.planb.support.ActionPerformer;

import com.dms.planb.support.Commands;

public class DmsVerticleTest {
	
	@Test
	public void verticleTest(){
		verticle(Commands.LOAD_NOTICE_LIST,"{\"category\":0,\"page\":2}");
	}
	
	@Test
	public void verticle(int command, String json){
		EasyJsonObject responseObject = null;
		EasyJsonObject requestObject = new EasyJsonObject(json);
		try {
			responseObject = ActionPerformer.perform(command, requestObject);
			
			if(responseObject.containsKey("status")) {
				if(responseObject.getInt("status") == 0) {
					Log.l("Responsed status code : 500");
				} else if(responseObject.getInt("status") == 1) {
					Log.l("Responsed status code : 200");
				} else if(responseObject.getInt("status") == 2) {
					Log.l("Responsed status code : 404");
				}
				responseObject.remove("status");
			} else {
				Log.l("Responsed status code : 200");
			}
			
			Log.l("Responsed object : " + responseObject.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			Log.l("SQLException");
		}
		
		System.out.println(responseObject.toString());
	}

}
