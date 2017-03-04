package com.dms.planb.action.account;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.secure.Guardian;
import org.boxfox.dms.user.UserManager;
import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOGIN_STUDENT_REQUEST)
public class LoginStudentRequest implements Handler<RoutingContext> {
    private UserManager userManager;

    public LoginStudentRequest() {
        userManager = new UserManager();
    }

    @Override
    public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
        String id = requestObject.getString("id");
        String password = requestObject.getString("password");

        if (Guardian.checkParameters(id, password)) {
            if (!userManager.login(id, password)) {
                responseObject.put("permit", false);
            } else {
            /*
             * Correct password
			 */
                responseObject.put("permit", true);
                JobResult result = userManager.getUserInfo(id);
                if (result.isSuccess()) {
                    Map<String, Object> datas = (Map) result.getArgs()[0];
                    responseObject.put("number", datas.get("number"));
                    responseObject.put("name", datas.get("name"));
                    responseObject.put("merit", datas.get("merit"));
                    responseObject.put("demerit", datas.get("demerit"));
                } else {

                }
            }
        } else {
            //파라미터중 null이 존재
        }

        return responseObject;
    }
}
