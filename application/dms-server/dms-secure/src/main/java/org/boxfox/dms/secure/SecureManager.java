package org.boxfox.dms.secure;

import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.utilities.config.SecureConfig;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecureManager {
    private static AES256 aes;
    private static DataBase db;

    private SecureManager () {
        aes = new AES256(SecureConfig.get("AES"));
        db = DataBase.getInstance();
    }
    private static class Singleton {
        private static final SecureManager instance = new SecureManager();
    }

    public static SecureManager getInstance () {
        return Singleton.instance;
    }

    public boolean isBanned(RoutingContext ctx){
        boolean check = false;
        String ip = getHost(ctx);
        try {
            int count = db.executeQuery("select count(*) from block_list where ip='",ip,"'").nextAndReturn().getInt(1);
            if(count > 0){
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void worngPassword(RoutingContext ctx, String id){
        int count = 0;
        String ip = getHost(ctx);
        try {
            SafeResultSet rs = db.executeQuery("select * from access_log where ip='",ip,"'");
            if(rs.next()){
                String datetime = rs.getString("last_access_time");
                Date date = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime);
                //compare to hours
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private String getHost(RoutingContext ctx){
        return aes.encrypt(ctx.request().remoteAddress().host());
    }


}
