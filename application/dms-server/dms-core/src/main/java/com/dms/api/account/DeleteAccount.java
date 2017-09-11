package com.dms.api.account;
//package com.dms.planb.action.account;
//
//import java.sql.SQLException;
//
//import org.boxfox.dms.util.AdminManager;
//import org.boxfox.dms.util.Guardian;
//import org.boxfox.dms.util.UserManager;
//import org.boxfox.dms.utilities.actions.RouteRegistration;
//import org.boxfox.dms.utilities.database.DataBase;
//import org.boxfox.dms.utilities.log.Log;
//
//import org.boxfox.dms.utilities.actions.support.PrecedingWork;
//
//import io.vertx.core.Handler;
//import io.vertx.core.http.HttpMethod;
//import io.vertx.ext.web.RoutingContext;
//
//@RouteRegistration(path="/account/student", method={HttpMethod.DELETE})
//public class DeleteAccount implements Handler<RoutingContext> {
//	private UserManager userManager;
//
//	public DeleteAccount() {
//		userManager = new UserManager();
//	}
//
//	@Override
//	public void handle(RoutingContext ctx) {
//		ctx = PrecedingWork.putHeaders(ctx);
//
//		DataBase database = DataBase.getInstance();
//
//		String id = userManager.getIdFromSession(ctx);
//        String uid = null;
//        try {
//            if (id != null) {
//                uid = userManager.getUid(id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if(!Guardian.checkParameters(id, uid)) {
//        	ctx.response().setStatusCode(400).end();
//        	ctx.response().close();
//        	return;
//        }
//
//		try {
//			database.executeUpdate("DELETE FROM account WHERE uid=", uid);
//			ctx.response().setStatusCode(200).end();
//			ctx.response().close();
//		} catch(SQLException e) {
//			ctx.response().setStatusCode(500).end();
//			ctx.response().close();
//
//			Log.l("SQLException");
//		}
//	}
//}
