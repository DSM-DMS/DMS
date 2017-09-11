package org.boxfox.dms.util;

import java.sql.SQLException;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;

import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-03-04.
 */
public class Guardian {
    public static final int AES = 1;
    public static final int SHA = 2;

    public static String encrypt(int key, String value) {
        switch (key) {
            case AES:
                //value = aes.encrypt(value);
                break;
            case SHA:
                //value = SHA256.encrypt(value);
                break;
        }
        return value;
    }

    public static boolean checkParameters(Object... args) {
        for (Object obj : args) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkParameters(RoutingContext ctx, String ... keys) {
        for (String key: keys) {
            if (ctx.request().getParam(key) == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean matchParameters(String param, String [] cases){
        boolean check = false;
        for(String str : cases){
            if(param.equals(str)){
                check = true;
                break;
            }
        }
        return check;
    }

    public static boolean isAdmin(RoutingContext ctx) {
        boolean check = false;
        String sessionKey = SessionUtil.getRegistredSessionKey(ctx, "AdminSession");
        if (sessionKey != null)
            try {
                SafeResultSet rs = DataBase.getInstance().executeQuery("select count(*) from admin_account where session_key='", sessionKey, "'");
                if (rs.next() && rs.getInt(1) != 0) {
                    check = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return check;
    }

}
