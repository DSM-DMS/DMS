package org.boxfox.dms.util;

import java.sql.SQLException;
import java.util.UUID;

import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.secure.SecureManager;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.config.SecureConfig;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import io.vertx.ext.web.RoutingContext;

public class AdminManager implements AccountManager {
    private static AES256 aes;
    private static DataBase database;
    private static SecureManager secureManager;

    static {
        aes = new AES256(SecureConfig.get("AES"));
        secureManager = SecureManager.create(AdminManager.class, 10, 5);
    }

    public static AES256 getAES() {
        return aes;
    }

    public AdminManager() {
        database = DataBase.getInstance();
    }

    public static boolean isAdmin(RoutingContext ctx) {
        boolean check = false;
        String sessionKey = SessionUtil.getRegistredSessionKey(ctx, "AdminSession");
        try {
            SafeResultSet rs = DataBase.getInstance().executeQuery("select count(*) from admin_account where session_key='", sessionKey, "'");
            
            if (rs.next() && rs.getInt(1) > 0) {
                check = true;
            } else {
                SessionUtil.removeCookie(ctx, "AdminSession");
                secureManager.invalidRequest(ctx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            secureManager.invalidRequest(ctx);
        }
        return check;
    }

    @Override
    public boolean login(String id, String password) throws SQLException {
        boolean check = false;
        SafeResultSet rs = database.executeQuery("select * from admin_account where id='", aes.encrypt(id), "'AND password='", SHA256.encrypt(password), "'");
        if (rs.next()) {
            check = true;
        }
        return check;
    }

    @Override
    public String createSession() {
        boolean check = true;
        String sessionKey = null;
        do {
            try {
                sessionKey = UUID.randomUUID().toString();
                SafeResultSet rs = database.executeQuery("select count(*) from admin_account where session_key='", sessionKey, "'");
                if (rs.next() && rs.getInt(1) == 0)
                    check = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (check);

        return sessionKey;
    }

    @Override
    public boolean isLogined(RoutingContext context) {
        return ((getIdFromSession(context) == null) ? false : true);
    }

    @Override
    public String getIdFromSession(RoutingContext context) {
        String sessionKey = SessionUtil.getRegistredSessionKey(context, "AdminSession");
        String result = null;
        if (sessionKey != null) {
            try {
                SafeResultSet rs = DataBase.getInstance().executeQuery("select id from admin_account where session_key='", sessionKey, "'");
                if (rs.next()) {
                    result = rs.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aes.decrypt(result);
    }

    @Override
    public String getSessionKey(String id) throws SQLException {
        String result = null;
        SafeResultSet rs = DataBase.getInstance().executeQuery("select session_key from admin_account where id = '", id, "'");
        if (rs.next()) {
            result = rs.getString(1);
        }
        return result;
    }

    @Override
    public boolean registerSession(RoutingContext context, boolean keepLogin, String id) {
        String idEncrypt = aes.encrypt(id);
        try {
            String sessionKey = getSessionKey(id);
            if (sessionKey == null) {
                sessionKey = SHA256.encrypt(createSession());
            }
            if (keepLogin) {
                SessionUtil.registerCookie(context, "AdminSession", sessionKey);
            } else {
                SessionUtil.registerSession(context, "AdminSession", sessionKey);
            }
            if (sessionKey != null) {
                DataBase.getInstance().executeUpdate("update admin_account set session_key='", sessionKey, "' where id='", idEncrypt, "'");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkIdExists(String id) {
        boolean check = false;
        id = aes.encrypt(id);
        try {
            int result = database.executeQuery("select count(*) from admin_account where id='", id, "'").nextAndReturn().getInt(1);
            if (result == 1) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public JobResult register(String id, String password, String name) throws SQLException {
        boolean check = false;
        String message = null;
        if (!checkIdExists(id)) {
            int result = database.executeUpdate("insert into admin_account (id,password,name) values('", aes.encrypt(id), "', '", SHA256.encrypt(password), "', '", aes.encrypt(name), "');");
            if (result == 1) {
                message = "회원가입에 성공했습니다.";
                check = true;
            } else {
                message = "회원가입에 실패했습니다.";
            }
        } else {
            message = "이미 존재하는 아이디 입니다.";
        }
        return new JobResult(check, message);
    }
}
