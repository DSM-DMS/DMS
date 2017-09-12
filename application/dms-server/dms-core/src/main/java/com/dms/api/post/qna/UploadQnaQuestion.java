package com.dms.api.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/post/qna/question", method = {HttpMethod.POST})
public class UploadQnaQuestion implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {
        DataBase database = DataBase.getInstance();

        String title = ctx.request().getParam("title");
        String content = ctx.request().getParam("content");
        boolean privacy = Boolean.parseBoolean(ctx.request().getParam("privacy"));
        String uid = null;
        try {
            uid = UserManager.getUid(UserManager.getIdFromSession(ctx));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (Guardian.checkParameters(title, content, privacy, uid)) {
            try {
            	SafeResultSet rs = database.executeQuery("SELECT name FROM student_data WHERE uid='", uid, "'");
            	rs.next();
            	String name = AES256.decrypt(rs.getString("name"));
                database.executeUpdate("INSERT INTO qna(title, question_content, question_date, privacy, owner, writer) VALUES('", title, "', '", content, "', now(), ", privacy, ", '", uid, "', '", name, "')");

                ctx.response().setStatusCode(201).end();
                ctx.response().close();
            } catch (SQLException e) {
                ctx.response().setStatusCode(500).end();
                ctx.response().close();
            }
        }
        if (!ctx.response().closed()) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        }
    }
}