package com.dms.secure;

import io.vertx.ext.web.RoutingContext;

import com.dms.crypto.AES256;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.log.Log;
import com.dms.utilities.support.SecureConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecureManager {

    private static AES256 aes;
    private static DataBase db;

    private final String NAME;
    private final int INVALID_REQUEST_MAX_COUNT;
    private final int INVALID_REQUEST_DURATION_MINUTES;

    static {
        aes = new AES256(SecureConfig.get("AES"));
        db = DataBase.getInstance();
    }

    private SecureManager(String name){
        NAME = name;
        INVALID_REQUEST_MAX_COUNT = 10;
        INVALID_REQUEST_DURATION_MINUTES = 10;
    }

    private SecureManager(String name, int count, int minute){
        NAME = name;
        INVALID_REQUEST_MAX_COUNT = count;
        INVALID_REQUEST_DURATION_MINUTES = minute;
    }

    public static SecureManager create(Class<? extends Object> classz){
        return new SecureManager(classz.getName());
    }

    public static SecureManager create(Class<? extends Object> classz, int time, int count){
        return new SecureManager(classz.getName(), count, time);
    }

    public static SecureManager create(String name){
        return new SecureManager(name);
    }

    public static SecureManager create(String name, int time, int count){
        return new SecureManager(name, count, time);
    }

    public boolean isBanned(RoutingContext ctx) {
        boolean check = false;
        String ip = getHost(ctx);
        try {
            int count = db.executeQuery("select count(*) from blacklist where ip='", ip, "'").nextAndReturn().getInt(1);
            if (count > 0) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void invalidRequest(RoutingContext ctx) {
        int count = 1;
        String ip = getHost(ctx);
        try {
            SafeResultSet rs = db.executeQuery("select * from access_log where ip='", ip, "' AND type='",NAME,"'");
            if (rs.next()) {
                Date date = rs.getDate("last_access_time");
                if (calculateMinutes(date, new Date()) <= INVALID_REQUEST_DURATION_MINUTES) {
                    count += rs.getInt("count");
                }
            }
            if (count >= INVALID_REQUEST_MAX_COUNT) {
                ban(ctx, "INVALID ACCESS");
            } else {
                db.executeUpdate("replace into access_log (ip, count, last_access_time, type) values( '", ip, "', ", count, ", NOW(), '",NAME,"')");
                Log.l("Count Invalid request (user : ",ip,")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getBanList() {
        List<String> list = new ArrayList<String>();
        try {
            SafeResultSet rs = db.executeQuery("select ip from blacklist");
            while (rs.next()) {
                list.add(aes.decrypt(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void ban(RoutingContext ctx, String reason) {
        String ip = getHost(ctx);
        if (reason == null)
            reason = "";
        try {
            db.executeUpdate("delete from access_log where ip='", ip, "'");
            db.executeUpdate("INSERT INTO blacklist values('", ip, "', '", reason+" : "+NAME, "', NOW())");
            Log.l("Ban user : ",ip," ",reason);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unban(RoutingContext ctx) {
        unban(ctx.request().remoteAddress().host());
    }

    public void unban(String ip) {
        ip = aes.encrypt(ip);
        try {
            db.executeUpdate("delete from blacklist where ip='", ip, "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.l("Unan user : ",ip);
    }

    private String getHost(RoutingContext ctx) {
        return aes.encrypt(ctx.request().remoteAddress().host());
    }

    private long calculateMinutes(Date old, Date news) {
        int diffInMinutes = (int) ((news.getTime() - old.getTime())
                / (1000 * 60));
        return diffInMinutes;
    }

}
