package com.dsm.dms.secure;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.sql.SQLException;
import java.util.Random;

/**
 * Created by user on 2017-05-10.
 */
public class ApiRequestManager {
    private DataBase database;

    private static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
        for (int i = 0; i < length; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }

    public String createApiKey(String uid) {
        String result = null;
        try {
            if (database.executeQuery("select count(*) from api_register where uid='", uid, "'").nextAndReturn().getInt(1) == 0) {
                boolean check = false;
                do {
                    result = getRandomString(4) + "-" + getRandomString(5) + "-" + getRandomString(4);
                    check = checkRequestValid(result);
                } while (check);
                database.executeUpdate("insert into api_register (uid, api_key) values('", uid, "', '", result, "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkRequestValid(String apiKey) {
        boolean result = false;
        try {
            if (database.executeQuery("select count(*) from api_register where api_key='", apiKey, "'").nextAndReturn().getInt(1) == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
