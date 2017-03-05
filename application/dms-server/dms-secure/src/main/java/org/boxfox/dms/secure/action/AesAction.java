//package org.boxfox.dms.secure.action;
//
//import java.sql.SQLException;
//
//import io.vertx.ext.web.RoutingContext;
//import org.boxfox.dms.algorithm.AES256;
//import org.boxfox.dms.algorithm.RSA;
//import org.boxfox.dms.secure.SecureManager;
//import org.boxfox.dms.utilities.actions.ActionRegistration;
//import org.boxfox.dms.utilities.actions.Actionable;
//import org.boxfox.dms.utilities.actions.RouteRegistration;
//import org.boxfox.dms.utilities.actions.support.Sender;
//import org.boxfox.dms.utilities.database.DataBase;
//import org.boxfox.dms.utilities.json.EasyJsonObject;
//
//import javax.xml.ws.handler.Handler;
//
//@RouteRegistration(path = "/secure/aes")
//public class AesAction implements Handler<RoutingContext> {
//	private SecureManager manager;
//	
//	public AesAction(){
//		manager = SecureManager.getInstance();
//	}
//	
//
//	@Override
//	public boolean handleMessage(RoutingContext context) {
//		String aesKey = RSA.decrypt(requestObject.getString("AESKey"));
//		manager.registerKey(sender, new AES256(aesKey));
//		EasyJsonObject response = new EasyJsonObject();
//		if (aesKey != null) {
//			response.put("Result", true);
//		} else {
//			response.put("Result", false);
//		}
//		return response;
//	}
//}
