package org.boxfox.dms.user;

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
    private DataBase database;

    public UserManager() {
        database = DataBase.getInstance();
    }

    public boolean login(String id, String password) throws SQLException {
        boolean check = false;
        SafeResultSet rs = database.executeQuery("select * from account where id='", id, "' AND password='", password, "'");
        if (rs.next()) {
            check = true;
        }
        return check;
    }

    public JobResult register(String key, String id, String password) throws SQLException {
        boolean check = false;
        String message = null;
        SafeResultSet rs = database.executeQuery("select count(*) from account where uid='", key, "'");
        if (rs.next() && rs.getInt(1) == 1) {
            if (!checkIdExists(id)) {
                int result = database.executeUpdate("update account set id='", id, "', password='", password, "' where uid='", key, "'");
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
//        String uid = getUid(id);
//        if (uid != null) {
            SafeResultSet rs = database.executeQuery("select * from student_data a join student_score b on a.id = b.id where a.id='", id, "'");
            if (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("number", rs.getInt("number"));
                map.put("name", rs.getString("name"));
                map.put("merit", rs.getInt("merit"));
                map.put("demerit", rs.getInt("demerit"));
                result.setSuccess(true);
                result.setArgs(map);
//            }
        }
        return result;
    }

    public JobResult sessionLogin(String sessionKey) throws SQLException {
        JobResult result = new JobResult(false);
        SafeResultSet rs = database.executeQuery("select id from account where session_key='", sessionKey, "'");
        if (rs.next()) {
            String id = rs.getString(1);
            result.setSuccess(true);
            result.setArgs(id);
        }
        return result;
    }

    private String getUid(String id) throws SQLException {
        String uid = null;
        if (checkIdExists(id))
            uid = DataBase.getInstance().executeQuery("select uid from account where id='", id, "'").nextAndReturn().getString(1);
        return uid;
    }

    public boolean checkIdExists(String id) {
        boolean check = false;
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

    public String createSession(String id) {
        boolean check = true;
        String sessionKey = null;
        do {
            try {
                sessionKey = UUID.randomUUID().toString();
                SafeResultSet rs = database.executeQuery("select count(*) from account where session_key='", sessionKey, "'");
                if (rs.next())
                    check = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (check);
        return sessionKey;
    }

}
