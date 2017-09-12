package com.dms.account_manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;

import com.dms.crypto.AES256;
import com.dms.crypto.SHA256;
import com.dms.secure.SecureManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.support.ApplyDataUtil;
import com.dms.utilities.support.JobResult;

import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-03-04.
 */
public class UserManager {
    private static SecureManager secureManager;

    static {
        secureManager = SecureManager.create(UserManager.class, 10, 8);
    }

    public JobResult register(String key, String id, String password) throws SQLException {
        boolean check = false;
        String message = null;
        key = SHA256.encrypt(key);
        ResultSet rs = DB.executeQuery("SELECT * FROM account WHERE uid=?", key);
        if (rs.next() && rs.getString("id") == null) {
            if (!checkIdExists(id)) {
                int result = DB.executeUpdate("UPDATE account set id=?, password=? WHERE uid=?", AES256.encrypt(id), SHA256.encrypt(password), key);
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
        ResultSet rs = DB.executeQuery("SELECT class, seat FROM extension_apply WHERE uid=?", uid);
        if (rs.next()) {
            room = rs.getInt(1) + "";
            seat = rs.getInt(2) + "";
        }
        int value = 0;
        rs = DB.executeQuery("SELECT value FROM stay_apply WHERE uid=?", uid);
        if(rs.next()) {
        	value = rs.getInt("value");
        }
        
        ResultSet rsForStudentData = DB.executeQuery("SELECT * FROM student_data a join student_score b on a.uid = b.uid WHERE a.uid=?", uid);
        JSONObject result = new JSONObject();
        if (rs.next()) {
            result.put("number", AES256.decrypt(rsForStudentData.getString("number") + ""));
            result.put("name", AES256.decrypt(rsForStudentData.getString("name")));
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
            ResultSet rss = DB.executeQuery("SELECT class, seat FROM extension_apply WHERE uid=?", uid);
            if (rss.next()) {
                room = rss.getInt(1) + "";
                seat = rss.getInt(2) + "";
            }
            
            int value = 0;
            rss = DB.executeQuery("SELECT value FROM stay_apply WHERE uid=?", uid);
            if(rss.next()) {
            	value = rss.getInt("value");
            }
            ResultSet rs = DB.executeQuery("SELECT * FROM student_data a join student_score b on a.uid = b.uid WHERE a.uid=?", uid);
            if (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("number", AES256.decrypt(rs.getString("number") + ""));
                map.put("name", AES256.decrypt(rs.getString("name")));
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
        ResultSet rs = DB.executeQuery("SELECT sat, sun FROM goingout_apply WHERE uid=?", uid);
        if (rs.next()) {
            list[0] = rs.getBoolean(1);
            list[1] = rs.getBoolean(2);
        }
        return list;

    }

    public static String getStayStatus(String id, String week) {
        String status = "";
        try {
            String uid = getUid(id);
            ResultSet rs = DB.executeQuery("SELECT value FROM stay_apply WHERE uid=? AND week=?", uid, week);
            if (rs.next()) {
                int value = rs.getInt(1);
                status = ApplyDataUtil.stayDataToString(value);
            } else {
                rs = DB.executeQuery("SELECT value FROM stay_apply_default WHERE uid=?", uid);
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
        String idEncrypt = AES256.encrypt(id);
        if (checkIdExists(id))
            uid = DataBase.getInstance().executeQuery("SELECT uid FROM account WHERE id='", idEncrypt, "'").nextAndReturn().getString(1);
        return uid;
    }

    public static boolean checkIdExists(String id) {
        boolean check = false;
        id = AES256.encrypt(id);
        try {
            ResultSet rs = DB.executeQuery("SELECT COUNT(*) FROM account WHERE id='", id, "'");
            if (rs.getInt(1) == 1) {
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
    
    public static boolean login(String id, String password) throws SQLException {
        boolean check = false;
        ResultSet rs = DB.executeQuery("SELECT * FROM account WHERE id=? AND password=?", AES256.encrypt(id), SHA256.encrypt(password));
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
                ResultSet rs = DB.executeQuery("SELECT COUNT(*) FROM account WHERE session_key=?", sessionKey);
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
                ResultSet rs = DB.executeQuery("SELECT id FROM account WHERE session_key=?", sessionKey);
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
        	return AES256.decrypt(result);
        } else {
        	return null;
        }
    }
    
    public static String getSessionKey(String id) throws SQLException {
        String result = null;
        ResultSet rs = DB.executeQuery("SELECT session_key FROM account WHERE uid=?", getUid(id));
        if (rs.next()) {
            result = rs.getString(1);
        }
        return result;
    }

    public static boolean registerSession(RoutingContext context, boolean keepLogin, String id) {
        String idEncrypt = AES256.encrypt(id);
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
                DB.executeUpdate("UPDATE account set session_key=? WHERE id=?", sessionKey, idEncrypt);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    
    public static void removeCookie(RoutingContext context) {
    	String idEncrypt = AES256.encrypt(getIdFromSession(context));
    	
    	SessionUtil.removeCookie(context, "UserSession");
    	DB.executeUpdate("UPDATE account SET session_key=null WHERE id=?", idEncrypt);
    }
}
