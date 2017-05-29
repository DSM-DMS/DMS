package com.dms.planb.template_routers.recruit;

import com.dms.boxfox.templates.DmsTemplate;
import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by boxfox on 2017-05-29.
 */
@RouteRegistration(path = "/recruit", method = {HttpMethod.GET})
public class RecruitRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public RecruitRouter(){
        userManager = new UserManager();
    }

    public void handle(RoutingContext context) {
        boolean isLogin = userManager.isLogined(context);
        boolean canApply = canApply();
        boolean isApply = isApply(context);

        DmsTemplate template = new DmsTemplate("recruit");
        template.put("isLogin", isLogin);
        template.put("isApply", isApply);
        template.put("canApply", canApply);
        try {
            context.response().setStatusCode(200);
            context.response().end(template.process());
            context.response().close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private boolean isApply(RoutingContext ctx){
        boolean result = false;
        try {
            if(DataBase.getInstance().executeQuery("select count(*) from recruit where uid='",userManager.getUid(userManager.getIdFromSession(ctx)),"'").nextAndReturn().getInt(1)>0){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean canApply(){
        boolean result = false;
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if((month==5&&day>29)||(month==6&&day <2)){
            result = true;
        }
        return result;
    }

}
