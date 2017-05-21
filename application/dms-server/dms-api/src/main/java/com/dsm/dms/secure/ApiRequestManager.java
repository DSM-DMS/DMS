package com.dsm.dms.secure;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.sql.SQLException;

/**
 * Created by user on 2017-05-10.
 */
public class ApiRequestManager {
    private DataBase database;

    public boolean checkRequestValid(String apiKey){
        boolean result = false;
        try {
            if(database.executeQuery("select count(*) from api_key where api_key='",apiKey,"'").nextAndReturn().getInt(1)==1){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
