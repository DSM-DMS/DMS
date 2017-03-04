package com.dms.planb.action.account;

import java.sql.SQLException;

import io.vertx.ext.auth.User;
import org.boxfox.dms.secure.Guardian;
import org.boxfox.dms.user.UserManager;
import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.REGISTER_STUDENT_ACC)
public class RegisterStudentAcc implements Handler<RoutingContext> {
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
