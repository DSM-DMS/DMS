package com.dms.boxfox.templates.post;

import com.dms.boxfox.templates.DmsTemplate;
import com.dms.boxfox.templates.post.data.PostContextTemplate;
import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boxfox on 2017-03-12.
 */
@RouteRegistration(path = "/post/content", method = {HttpMethod.GET})
public class PostContextRouter implements Handler<RoutingContext> {
    private UserManager userManager;
    private List<PostContextTemplate> categories;
    private DataBase db;


    private PostContextTemplate createDefaultPost(String category) {
        PostContextTemplate template = new PostContextTemplate(category);
        template.putTarget("title", new String[]{"title"});
        template.putTarget("content", new String[]{"content"});
        template.putTarget("subinfo", null);
        return template;
    }

    private PostContextTemplate createQnaPost() {
        PostContextTemplate template = new PostContextTemplate("qna", "qna");
        template.putTarget("title", new String[]{"title"});
        template.putTarget("content", new String[]{"question_content"});
        template.putTarget("subinfo", new String[]{"writer", "question_date"});
        template.putTarget("answer_content", new String[]{"answer_content"});
        template.putTarget("answer_subinfo", new String[]{"answer_date"});
        return template;
    }           

    private PostContextTemplate createFacilityPost() {
        PostContextTemplate template = new PostContextTemplate("facility_report", "facility_report");
        template.putTarget("title", new String[]{"title"});
        template.putTarget("content", new String[]{"content"});
        template.putTarget("room", new String[]{"room"});
        template.putTarget("subinfo", new String[]{"writer", "write_date"});
        template.putTarget("result", new String[]{"result"});
        template.putTarget("result_subinfo", new String[]{"result_date"});
        return template;
    }
   private void initCategorys() {
        categories = new ArrayList<PostContextTemplate>();
        categories.add(createDefaultPost("notice").putTarget("subinfo", new String[]{"writer"}));
        categories.add(createDefaultPost("rule"));
        categories.add(createDefaultPost("faq"));
        categories.add(createQnaPost());
        categories.add(createFacilityPost());
    }

    public PostContextRouter() {
        db = DataBase.getInstance();
        userManager = new UserManager();
        initCategorys();
    }

    public void handle(RoutingContext context) {
        int no = getPostNombuer(context);
        String category = context.request().getParam("category");
        try {
            if (checkPostExists(no, category)) {
                boolean isPrivacy = postIsPrivacy(no, category);
                boolean isWriter = checkPostWriter(context, no, category);
                PostContextTemplate postWriter = null;
                if (!isPrivacy || (isPrivacy && isWriter))
                    postWriter = getPostCategory(category);
                if (postWriter != null) {
                    try {
                        context.response().setStatusCode(200);
                        context.response().end(postWriter.processContent(no, isWriter));
                        context.response().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    }
                } else if (isPrivacy) {
                    DmsTemplate template = new DmsTemplate("privacy");
                    context.response().setStatusCode(200);
                    try {
                        context.response().end(template.process());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    }
                    context.response().close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!context.response().closed()) {
            context.response().setStatusCode(204);
            context.response().end("post not found");
            context.response().close();
        }
    }

    private boolean postIsPrivacy(int no, String category) {
        boolean check = false;

        try {
            SafeResultSet rs = DataBase.getInstance().executeQuery("select privacy from ", category, " where no=", no);
            if (rs.next()) {
                check = rs.getBoolean(1);
            }
        } catch (SQLException e) {
        }
        return check;
    }

    private boolean checkPostWriter(RoutingContext ctx, int no, String category) {
        boolean check = false;
        try {
            String currentId = userManager.getUid(userManager.getIdFromSession(ctx));
            if (currentId != null) {
                    SafeResultSet rs = DataBase.getInstance().executeQuery("select uid from ", category, " where no=", no);
                    if (rs.next()) {
                        String writerId = rs.getString(1);
                        if (writerId.equals(currentId))
                            check = true;
                    }
            }
        } catch (SQLException e) {
        }
        return check;
    }

    private boolean checkPostExists(int no, String category) throws SQLException {
        SafeResultSet rs = DataBase.getInstance().executeQuery("select count(*) from ", category, " where no=", no);
        boolean check = false;
        if (rs.next() && rs.getInt(1) > 0) {
            check = true;
        }
        return check;
    }

    private PostContextTemplate getPostCategory(String category) {
        PostContextTemplate result = null;
        if (category != null)
            for (PostContextTemplate template : categories) {
                if (template.getCategory().equals(category)) {
                    result = template;
                }
            }
        return result;
    }

    private int getPostNombuer(RoutingContext context) {
        int no;
        if (context.request().getParam("no") == null) {
            no = 0;
        } else
            no = Integer.valueOf(context.request().getParam("no"));
        return no;
    }
}
