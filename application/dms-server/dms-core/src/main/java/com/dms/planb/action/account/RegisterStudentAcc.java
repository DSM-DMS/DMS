package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.secure.Guardian;
import org.boxfox.dms.user.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="account/register/student", method={HttpMethod.POST})
public class RegisterStudentAcc implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		
		String uid;
		String id = context.request().getParam("id");
		String password = context.request().getParam("password");
		String sessionKey;
		boolean permission;
		// To account table
		
		int number = Integer.parseInt(context.request().getParam("number"));
		int status = Integer.parseInt(context.request().getParam("status"));
		String name = context.request().getParam("name");
		// To student_data table
		
		try {
			database.executeUpdate("INSERT INTO account(uid, id, password, session_key, permission) VALUES('", uid, "', '", id, "', '", password, "', '", sessionKey, "', ", permission, ")");
			database.executeUpdate("INSERT INTO student_data(uid, number, status, name) VALUES('", uid, "', ", number, ", ", status, ", '", name, "')");
			database.executeUpdate("INSERT INTO stay_apply_default(id, value) VALUES('", id, "', 4");
			database.executeUpdate("INSERT INTO student_score(uid, merit, demerit) VALUES('", uid, "', 0, 0)");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
    private UserManager userManager;

    public RegisterStudentAcc() {
        userManager = new UserManager();
    }

    @Override
    public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
        String uid = asdaasdasd; //학생들에게 배부되는 고유번호
        String id = asdadasd;
        String password = asdasdasd;

        if (Guardian.checkParameters(uid, id, password)) {
            JobResult result = userManager.register(uid, id, password);
            if (result.isSuccess()) {
                result.getMessage();
            } else {
                result.getMessage();
            }
        } else {
            //파라미터중 null이 존재
        }


        return responseObject;
    }
}
