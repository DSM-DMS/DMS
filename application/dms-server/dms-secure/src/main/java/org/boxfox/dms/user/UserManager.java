package org.boxfox.dms.user;

import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by boxfox on 2017-03-04.
 */
public class UserManager {

    public boolean login(String id, String password) {
        boolean check = false;
        try {
            SafeResultSet rs = DataBase.getInstance().executeQuery("select * from account where id='", id, "' AND password='", password, "'");
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public JobResult register(String key, String id, String password) {
        boolean check = false;
        String message = null;
        try {
            SafeResultSet rs = DataBase.getInstance().executeQuery("select count(*) from account where uid='", key, "'");
            if (rs.next() && rs.getInt(1) == 1) {
                String dbId = rs.getString("id");
                if (dbId == null) {
                    if (!checkIdExists(id)) {
                        int result = DataBase.getInstance().executeUpdate("update account set id='", id, "', password='", password, "' where uid='", key, "'");
                        if (result == 1) {
                            message = "회원가입에 성공했습니다.";
                            check = true;
                        } else {
                            message = "회원가입에 실패했습니다.";
                        }
                    } else {
                        message = "이미 존재하는 아이디 입니다.";
                    }
                }
            } else {
                message = "고유번호를 확인해 주세요";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JobResult(check, message);
    }

    public boolean checkIdExists(String id) {
        boolean check = true;
        try {
            int result = DataBase.getInstance().executeQuery("select count(*) from account where id='", id, "'").nextAndReturn().getInt(1);
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
                SafeResultSet rs = DataBase.getInstance().executeQuery("select count(*) from account where session_key='", sessionKey, "'");
                if (rs.next())
                    check = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (check);
        return sessionKey;
    }

}
