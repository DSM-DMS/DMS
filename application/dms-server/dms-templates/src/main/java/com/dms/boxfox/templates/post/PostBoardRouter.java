package com.dms.boxfox.templates.post;

import com.dms.boxfox.templates.DmsTemplate;
import com.dms.boxfox.templates.post.data.PostTemplate;
import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boxfox on 2017-03-10.
 */

@RouteRegistration(path = "/post/", method = {HttpMethod.GET})
public class PostBoardRouter implements Handler<RoutingContext> {
    private static List<PostTemplate> categories;
    private DataBase db;

    static {
        categories = new ArrayList<PostTemplate>();
        categories.add(new PostTemplate("notice", "공지사항", new String[]{"no", "title", "writer"}, new String[]{"#", "제목", "작성자"}));
        categories.add(new PostTemplate("rule", "기숙사 규칙", new String[]{"no", "title"}, new String[]{"#", "제목"}));
        categories.add(new PostTemplate("qna", "Q&A", new String[]{"no", "title", "writer"}, new String[]{"#", "제목", "작성자"}));
        categories.add(new PostTemplate("faq", "FAQ", new String[]{"no", "title"}, new String[]{"#", "제목"}));
        categories.add(new PostTemplate("facility_report", "시설고장신고", new String[]{"no", "title", "writer", " write_date"}, new String[]{"#", "제목", "작성자", "날짜"}));
    }

    public PostBoardRouter() {
        db = DataBase.getInstance();
    }

    public void handle(RoutingContext context) {
        PostTemplate postTemplate = getCategory(context);
        if (postTemplate != null) {
            int page = getPageNumber(context);

            try {
                DmsTemplate templates = new DmsTemplate("listpage");
                SafeResultSet rs = db.executeQuery("select ", columnArrayToQuery(postTemplate.getColumns()), " from ", postTemplate.getCategory(), " order by no desc limit ", page, ", ", page + 10, "");
                templates.put("Title", postTemplate.getKoreanName());
                templates.put("Heads", postTemplate.getHeads());
                templates.put("Columns", postTemplate.getColumns());
                templates.put("List", rs.toHashMap());

                rs.toHashMap();
                context.response().setStatusCode(200);
                context.response().end(templates.process());
                context.response().close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } else {
            context.response().setStatusCode(404);
            context.response().end("page not found");
            context.response().close();
        }
    }

    private String columnArrayToQuery(String[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String str = ((i > 0) ? ", " : "") + arr[i];
            builder.append(str);
        }
        return builder.toString();
    }

    private PostTemplate getCategory(RoutingContext context) {
        PostTemplate result = null;
        String category = context.request().getParam("category");
        if (category == null) category = "notice";
        for (PostTemplate template : categories) {
            if (template.getCategory().equals(category)) {
                result = template;
            }
        }
        return result;
    }

    private int getPageNumber(RoutingContext context) {
        int page;
        if (context.request().getParam("page") == null) {
            page = 0;
        } else
            page = Integer.valueOf(context.request().getParam("page"));
        page *= 10;
        return page;
    }
}
