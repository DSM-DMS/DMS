package com.dms.templates.recruit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.dms.account_manager.AdminManager;
import com.dms.account_manager.UserManager;
import com.dms.crypto.AES256;
import com.dms.templates.DmsTemplate;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.routing.Route;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-05-31.
 */@Route(path = "/recruit/lookup", method = {HttpMethod.GET})
public class LookupRecruitRouter implements Handler<RoutingContext> {
    public void handle(RoutingContext context) {
        int code = 400;
        String content = null;
        if(AdminManager.isAdmin(context)){
            try {
                SafeResultSet rs = DataBase.getInstance().executeQuery("select student_data.number as 학번, student_data.name as 이름, recruit.language as 언어, recruit.project as 프로젝트, recruit.content as 다짐, recruit.area as 분야 from recruit LEFT JOIN student_data ON student_data.uid=recruit.uid");
                List<HashMap<String,Object>> list = rs.toHashMap();
                for(HashMap<String, Object> map : list){
                    map.put("이름", AES256.decrypt((String)map.get("이름")));
                    map.put("학번", AES256.decrypt((String)map.get("학번")));
                }
                List<String> columns = rs.getColumns();
                DmsTemplate template = new DmsTemplate("listpage");
                template.put("Title", "DMS 인턴 신청자");
                template.put("Heads", columns);
                template.put("Columns", columns);
                template.put("List", list);
                content = template.process();
                code = 200;
            } catch (SQLException | TemplateException | IOException e) {
                e.printStackTrace();
                code = 500;
            }
        }
        context.response().setStatusCode(code);
        if(content!=null)
            context.response().end(content);
        else context.response().end();
        context.response().close();
    }
}
