package com.dms.api.account.modify;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.crypto.SHA256;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/account/password/student", method={HttpMethod.PATCH})
public class ModifyPassword implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = UserManager.getIdFromSession(ctx);
        String uid = null;
        try {
            if (id != null) {
                uid = UserManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		String encryptedPassword = SHA256.encrypt(ctx.request().getParam("password"));
		
		if(!Guardian.checkParameters(uid, encryptedPassword)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
        	return;
        }
		
		DB.executeUpdate("UPDATE account SET password=? WHERE uid=?", encryptedPassword, uid);
		
		ctx.response().setStatusCode(200).end();
		ctx.response().close();
	}
}
