//package org.boxfox.dms.secure.action;
//
//import java.math.BigInteger;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.sql.SQLException;
//
//import io.vertx.ext.web.RoutingContext;
//import org.boxfox.dms.algorithm.RSA;
//import org.boxfox.dms.utilities.actions.ActionRegistration;
//import org.boxfox.dms.utilities.actions.Actionable;
//import org.boxfox.dms.utilities.actions.support.Sender;
//import org.boxfox.dms.utilities.json.EasyJsonObject;
//
//import javax.xml.ws.handler.Handler;
//
//@ActionRegistration(command = 7513)
//public class RsaAction implements Handler<RoutingContext> {
//    public static final int COMMAND_RSA = 7513;
//    public static final int COMMAND_AES = 7514;
//
//	@Override
//	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
//		EasyJsonObject obj = new EasyJsonObject();
//		obj.put("PublicKey", RSA.PUBLIC_KEY);
//		obj.put("KeyPice", getRandomKeyPice());
//		return obj;
//	}
//	
//	private String getRandomKeyPice(){
//		  SecureRandom random = new SecureRandom();
//		return new BigInteger(130, random).toString(32).substring(0, 9);
//	}
//
//}
