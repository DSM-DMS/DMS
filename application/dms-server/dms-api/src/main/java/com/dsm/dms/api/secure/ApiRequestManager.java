package com.dsm.dms.api.secure;

import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.sql.SQLException;
import java.util.List;
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

    public boolean addHost(String uid, String key, String host) {
        boolean result = false;
        if (checkApiValid(uid, key)) {
            try {
                if (database.executeUpdate("insert into api_host (api_key, host_name) values('", key, "', '", host, "')") == 1) {
                    result = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public JobResult getApiKeys(String uid) {
        JobResult jobResult = null;
        try {
            SafeResultSet rs = database.executeQuery("select * from api_account where uid='", uid, "'");
            //need join to api_key
            jobResult = new JobResult(true, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
            jobResult = new JobResult(false, e.getMessage(), e);
        }
        return jobResult;
    }

    public String createApiKey(String uid) {
        String result = null;
        try {
            if (database.executeQuery("select count(*) from api_account where uid='", uid, "'").nextAndReturn().getInt(1) == 0) {
                boolean check = false;
                do {
                    result = getRandomString(4) + "-" + getRandomString(5) + "-" + getRandomString(4);
                    check = checkRequestValid(result, null);
                } while (check);
                database.executeUpdate("insert into api_account (uid, api_key) values('", uid, "', '", result, "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkApiValid(String uid, String apiKey) {
        boolean result = false;
        try {
            String query = QueryUtils.queryBuilder("select count(*) from api_account where api_key='", apiKey, "' AND uid='", uid, "'");
            if (database.executeQuery(query).nextAndReturn().getInt(1) == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkRequestValid(String apiKey, String host) {
        boolean result = false;
        try {
            String query = (host == null)
                    ? QueryUtils.queryBuilder("select count(*) from api_account where api_key='", apiKey, "'")
                    : QueryUtils.queryBuilder("select count(*) from api_host where api_key='", apiKey, "' AND host='", host, "'");
            if (database.executeQuery(query).nextAndReturn().getInt(1) == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
