package org.boxfox.dms.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.secure.SecureManager;

import com.dms.utilities.database.DB;
import com.dms.utilities.log.Log;
import com.dms.utilities.support.JobResult;

import io.vertx.ext.web.RoutingContext;

public class AdminManager {
    private static SecureManager secureManager;

    static {
        secureManager = SecureManager.create(AdminManager.class, 10, 5);
    }

    public static boolean isAdmin(RoutingContext ctx) {
        boolean check = false;
        String sessionKey = SessionUtil.getRegistredSessionKey(ctx, "AdminSession");
        if (sessionKey != null)
            try {
                ResultSet rs = DB.executeQuery("SELECT COUNT(*) FROM admin_account WHERE session_key=?", sessionKey);
                if (rs.next() && rs.getInt(1) > 0) {
                    check = true;
                } else {
                    SessionUtil.removeCookie(ctx, "AdminSession");
                    Log.l(sessionKey);
                    secureManager.invalidRequest(ctx);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                secureManager.invalidRequest(ctx);
            }
        return check;
    }

    public static boolean login(String id, String password) throws SQLException {
        boolean check = false;
        ResultSet rs = DB.executeQuery("SELECT * FROM admin_account WHERE id=? AND password=?", AES256.encrypt(id), SHA256.encrypt(password));
        if (rs.next()) {
            check = true;
        }
        return check;
    }
    
    public static String createSession() {
        boolean check = true;
        String sessionKey = null;
        do {
            try {
                sessionKey = UUID.randomUUID().toString();
                ResultSet rs = DB.executeQuery("SELECT COUNT(*) FROM admin_account WHERE session_key=?", sessionKey);
                if (rs.next() && rs.getInt(1) == 0)
                    check = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (check);

        return sessionKey;
    }

    public static boolean isLogined(RoutingContext ctx) {
        return ((getIdFromSession(ctx) == null) ? false : true);
    }

    public static String getIdFromSession(RoutingContext ctx) {
        String sessionKey = SessionUtil.getRegistredSessionKey(ctx, "AdminSession");
        String result = null;
        if (sessionKey != null) {
            try {
                ResultSet rs = DB.executeQuery("SELECT id FROM admin_account WHERE session_key=?", sessionKey);
                if (rs.next()) {
                    result = rs.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return AES256.decrypt(result);
    }

    public static String getSessionKey(String id) throws SQLException {
        String result = null;
        ResultSet rs = DB.executeQuery("SELECT session_key FROM admin_account WHERE id=?", id);
        if (rs.next()) {
            result = rs.getString(1);
        }
        return result;
    }

    public static boolean registerSession(RoutingContext ctx, boolean keepLogin, String id) {
        String idEncrypt = AES256.encrypt(id);
        try {
            String sessionKey = getSessionKey(id);
            if (sessionKey == null) {
                sessionKey = SHA256.encrypt(createSession());
            }
            if (keepLogin) {
                SessionUtil.registerCookie(ctx, "AdminSession", sessionKey);
            } else {
                SessionUtil.registerSession(ctx, "AdminSession", sessionKey);
            }
            if (sessionKey != null) {
                DB.executeUpdate("UPDATE admin_account SET session_key=? WHERE id=?", sessionKey, idEncrypt);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkIdExists(String id) {
        boolean check = false;
        id = AES256.encrypt(id);
        try {
            ResultSet rs = DB.executeQuery("SELECT COUNT(*) FROM admin_account WHERE id=?", id);
            if (rs.getInt(1) == 1) {
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
            int result = DB.executeUpdate("INSERT INTO admin_account (id, password, name) VALUES(?, ?, ?)", AES256.encrypt(id), SHA256.encrypt(password), AES256.encrypt(name));
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