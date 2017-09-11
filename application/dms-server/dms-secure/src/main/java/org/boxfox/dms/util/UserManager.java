package org.boxfox.dms.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.secure.SecureManager;
import org.json.JSONObject;

import com.dms.utilities.database.DB;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.support.ApplyDataUtil;
import com.dms.utilities.support.JobResult;
import com.dms.utilities.support.SecureConfig;

import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-03-04.
 */
public class UserManager {
    private static AES256 aes;
    private static DataBase database;
    private static SecureManager secureManager;

    static {
        aes = new AES256(SecureConfig.get("AES"));
        secureManager = SecureManager.create(UserManager.class, 10, 8);
    }

    public static AES256 getAES() {
        return aes;
    }

    public UserManager() {
        database = DataBase.getInstance();
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
    
    public static JSONObject getUserInfo(String id) throws SQLException {
        String uid = getUid(id);
        
        String room = "";
        String seat = "";
        ResultSet rs = DB.executeQuery("select class, seat from extension_apply where uid=?", uid);
        if (rs.next()) {
            room = rs.getInt(1) + "";
            seat = rs.getInt(2) + "";
        }
        int value = 0;
        rs = DB.executeQuery("SELECT value FROM stay_apply WHERE uid=?", uid);
        if(rs.next()) {
        	value = rs.getInt("value");
        }
        
        ResultSet rsForStudentData = DB.executeQuery("select * from student_data a join student_score b on a.uid = b.uid where a.uid=?", uid);
        JSONObject result = new JSONObject();
        if (rs.next()) {
            result.put("number", aes.decrypt(rsForStudentData.getString("number") + ""));
            result.put("name", aes.decrypt(rsForStudentData.getString("name")));
            result.put("merit", rsForStudentData.getInt("merit"));
            result.put("demerit", rsForStudentData.getInt("demerit"));
            result.put("room", room);
            result.put("seat", seat);
            result.put("stay_value", value);
        }
        
        return result;
    }

    public JobResult getUserInfo_legacy(String id) throws SQLException {
        JobResult result = new JobResult(false);
        if(id == null) {
        	result.setSuccess(false);
        	return result;
        }
        String uid = getUid(id);
        if (uid != null) {
            String room = "";
            String seat = "";
            SafeResultSet rss = database.executeQuery("select class, seat from extension_apply where uid='", uid, "'");
            if (rss.next()) {
                room = rss.getInt(1) + "";
                seat = rss.getInt(2) + "";
            }
            int value = 0;
            rss = database.executeQuery("SELECT value FROM stay_apply WHERE uid='", uid, "'");
            if(rss.next()) {
            	value = rss.getInt("value");
            }
            SafeResultSet rs = database.executeQuery("select * from student_data a join student_score b on a.uid = b.uid where a.uid='", uid, "'");
            if (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("number", aes.decrypt(rs.getString("number") + ""));
                map.put("name", aes.decrypt(rs.getString("name")));
                map.put("merit", rs.getInt("merit"));
                map.put("demerit", rs.getInt("demerit"));
                map.put("room", room);
                map.put("seat", seat);
                map.put("stay_value", value);
                result.setSuccess(true);
                result.setArgs(map);
            }
        }
        return result;
    }

    public boolean[] getOutStatus(String id) throws SQLException {
        boolean[] list = new boolean[2];
        String uid = getUid(id);
        SafeResultSet rs = DataBase.getInstance().executeQuery("SELECT sat,sun FROM goingout_apply WHERE uid='", uid, "'");
        if (rs.next()) {
            list[0] = rs.getBoolean(1);
            list[1] = rs.getBoolean(2);
        }
        return list;

    }

    public String getStayStatus(String id, String week) {
        String status = "";
        try {
            String uid = getUid(id);
            SafeResultSet rs = database.executeQuery("SELECT value FROM stay_apply WHERE uid='", uid, "' AND week='", week, "'");
            if (rs.next()) {
                int value = rs.getInt(1);
                status = ApplyDataUtil.stayDataToString(value);
            } else {
                rs = database.executeQuery("SELECT value FROM stay_apply_default WHERE uid='", uid, "'");
                if (rs.next()) {
                    int value = rs.getInt(1);
                    status = ApplyDataUtil.stayDataToString(value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getUid(String id) throws SQLException {
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
    
    public boolean resetSession(String id) {
        boolean result = false;
        String sessionKey = createSession();
        try {
            int r = DataBase.getInstance().executeUpdate("Update account set session_key='", sessionKey, "'");
            if (r == 1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean login(String id, String password) throws SQLException {
        boolean check = false;
        SafeResultSet rs = database.executeQuery("select * from account where id='", aes.encrypt(id), "' AND password='", SHA256.encrypt(password), "'");
        if (rs.next()) {
            check = true;
        }
        return check;
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
    
    public static boolean isLogined(RoutingContext context) {
        return ((getIdFromSession(context) == null) ? false : true);
    }
    
    public static String getIdFromSession(RoutingContext context) {
        String sessionKey = SessionUtil.getRegistredSessionKey(context, "UserSession");
        String result = null;
        if (sessionKey != null) {
            try {
                SafeResultSet rs = DataBase.getInstance().executeQuery("select id from account where session_key='", sessionKey, "'");
                if (rs.next()) {
                    result = rs.getString(1);
                }else{
                    //secureManager error session
                    secureManager.invalidRequest(context);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(result != null) {
        	return aes.decrypt(result);
        } else {
        	return null;
        }
    }
    
    public String getSessionKey(String id) throws SQLException {
        String result = null;
        SafeResultSet rs = DataBase.getInstance().executeQuery("select session_key from account where uid = '", getUid(id), "'");
        if (rs.next()) {
            result = rs.getString(1);
        }
        return result;
    }

    public boolean registerSession(RoutingContext context, boolean keepLogin, String id) {
        String idEncrypt = aes.encrypt(id);
        try {
            String sessionKey = getSessionKey(id);
            if (sessionKey == null) {
                sessionKey = SHA256.encrypt(createSession());
            }
            if (keepLogin) {
                SessionUtil.registerCookie(context, "UserSession", sessionKey);
            } else {
                SessionUtil.registerSession(context, "UserSession", sessionKey);
            }
            if (sessionKey != null) {
                DataBase.getInstance().executeUpdate("update account set session_key='", sessionKey, "' where id='", idEncrypt, "'");
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    
    public void removeCookie(RoutingContext context) {
    	String idEncrypt = aes.encrypt(getIdFromSession(context));
    	
    	SessionUtil.removeCookie(context, "UserSession");
    	try {
			DataBase.getInstance().executeUpdate("UPDATE account SET session_key=null WHERE id='", idEncrypt, "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
