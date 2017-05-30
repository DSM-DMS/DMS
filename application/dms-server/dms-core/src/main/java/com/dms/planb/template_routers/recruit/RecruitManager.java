package com.dms.planb.template_routers.recruit;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-05-30.
 */
public class RecruitManager {
    private UserManager userManager;
    public RecruitManager(){
        userManager = new UserManager();
    }

    public RecruitManager(UserManager userManager){
        this.userManager = userManager;
    }

    public boolean isApply(RoutingContext ctx){
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

    public boolean canApply(RoutingContext ctx){
        boolean result = false;
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if((month==5&&day>29)||(month==6&&day <2)) {
            try {
                SafeResultSet rs = DataBase.getInstance().executeQuery("select number from student_data where uid='",userManager.getUid(userManager.getIdFromSession(ctx)),"'");
                if(rs.next()&&!UserManager.getAES().decrypt(rs.getString(1)).startsWith("3")){
                    result = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
