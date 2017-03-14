package org.boxfox.dms.util;

import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by boxfox on 2017-03-04.
 */
public class UserManager {
    private static AES256 aes;
    private static DataBase database;

    static {
        aes = new AES256(".s@!31VAsv!@312231");
    }

    public static AES256 getAES() {
        return aes;
    }

    public UserManager() {
        database = DataBase.getInstance();
    }

    public boolean login(String id, String password) throws SQLException {
        boolean check = false;
        SafeResultSet rs = database.executeQuery("select * from account where id='", aes.encrypt(id), "' AND password='", SHA256.encrypt(password), "'");
        if (rs.next()) {
            check = true;
        }
        return check;
    }

    public JobResult register(String key, String id, String password) throws SQLException {
        boolean check = false;
        String message = null;
        key = SHA256.encrypt(key);
        SafeResultSet rs = database.executeQuery("select * from account where uid='", (key), "'");
        if (rs.next() && rs.getString("id") == null) {
            if (!checkIdExists(id)) {
                int result = database.executeUpdate("update account set id='", aes.encrypt(id), "', password='", SHA256.encrypt(password), "' where uid='", (key), "'");
                if (result == 1) {
                    message = "회원가입에 성공했습니다.";
                    check = true;
                } else {
                    message = "회원가입에 실패했습니다.";
                }
            } else {
                message = "이미 존재하는 아이디 입니다.";
            }
        } else {
            message = "고유번호를 확인해 주세요";
        }
        return new JobResult(check, message);
    }

    public JobResult getUserInfo(String id) throws SQLException {
        JobResult result = new JobResult(false);
        String uid = getUid(id);
        if (uid != null) {
            SafeResultSet rs = database.executeQuery("select * from student_data a join student_score b on a.uid = b.uid where a.uid='", uid, "'");
            if (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("number", aes.decrypt(rs.getInt("number") + ""));
                map.put("name", aes.decrypt(rs.getString("name")));
                map.put("merit", rs.getInt("merit"));
                map.put("demerit", rs.getInt("demerit"));
                result.setSuccess(true);
                result.setArgs(map);
            }
        }
        return result;
    }

    public String getUid(String id) throws SQLException {
        String uid = null;
        String idEncrypt = aes.encrypt(id);
        if (checkIdExists(id))
            uid = DataBase.getInstance().executeQuery("select uid from account where id='", idEncrypt, "'").nextAndReturn().getString(1);
        return uid;
    }

    public static boolean checkIdExists(String id) {
        boolean check = false;
        id = aes.encrypt(id);
        try {
            int result = database.executeQuery("select count(*) from account where id='", id, "'").nextAndReturn().getInt(1);
            if (result == 1) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public JobResult sessionLogin(RoutingContext context) throws SQLException {
        String sessionKey = SHA256.encrypt(getRegistredSessionKey(context));
        JobResult result = new JobResult(false);
        if (sessionKey != null) {
            SafeResultSet rs = database.executeQuery("select id from account where session_key='", sessionKey, "'");
            if (rs.next()) {
                String id = rs.getString(1);
                result.setSuccess(true);
                result.setArgs(id);
            }
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    public String createSession() {
        boolean check = true;
        String sessionKey = null;
        do {
            try {
                sessionKey = UUID.randomUUID().toString();
                SafeResultSet rs = database.executeQuery("select count(*) from account where session_key='", sessionKey, "'");
                if (rs.next() && rs.getInt(1) == 0)
                    check = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (check);

        return sessionKey;
    }

    public boolean isLogined(RoutingContext context) {
        return ((getIdFromSession(context) == null) ? false : true);
    }

    public String getIdFromSession(RoutingContext context) {
        String sessionKey = SHA256.encrypt(getRegistredSessionKey(context));
        String result = null;
        if (sessionKey != null) {
            try {
                SafeResultSet rs = DataBase.getInstance().executeQuery("select id from account where session_key='", sessionKey, "'");
                if (rs.next()) {
                    result = rs.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aes.decrypt(result);
    }

    public String getRegistredSessionKey(RoutingContext context) {
        String key = null;
        if (context.session() != null) {
            key = context.session().get("UserSession");
        }
        if (key == null && context.getCookie("UserSession") != null) {
            key = context.getCookie("UserSession").getValue();
        }
        return key;
    }

    public boolean registerSession(RoutingContext context, boolean keepLogin, String id) {
        String idEncrypt = aes.encrypt(id);
        try {
            String sessionKey = createSession();
            if (keepLogin) {
                Cookie cookie = Cookie.cookie("UserSession", sessionKey);
                String path = "/";
                cookie.setPath(path);
                cookie.setMaxAge(356 * 24 * 60 * 60);
                context.addCookie(cookie);
            } else {
                context.session().put("UserSession", sessionKey);
            }
            if (sessionKey != null) {
                DataBase.getInstance().executeUpdate("update account set session_key='", SHA256.encrypt(sessionKey), "' where id='", idEncrypt, "'");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
