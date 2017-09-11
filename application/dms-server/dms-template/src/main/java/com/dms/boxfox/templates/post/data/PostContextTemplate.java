package com.dms.boxfox.templates.post.data;

import com.dms.boxfox.templates.DmsTemplate;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.QueryUtils;
import com.dms.utilities.database.SafeResultSet;

import freemarker.template.TemplateException;
import org.boxfox.dms.util.AdminManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by boxfox on 2017-03-12.
 */
public class PostContextTemplate {
    private static final String DEFAULT_TEMPLATE = "notice";
    private String targetTemplate;
    private String category;
    private Map<String, String[]> list;

    public PostContextTemplate(String category, String targetTemplate) {
        this.category = category;
        this.targetTemplate = targetTemplate;
        this.list = new HashMap<String, String[]>();
    }

    public PostContextTemplate(String category) {
        this.category = category;
        this.targetTemplate = DEFAULT_TEMPLATE;
        this.list = new HashMap<String, String[]>();
    }

    public PostContextTemplate putTarget(String str, String[] columns) {
        list.put(str, columns);
        return this;
    }

    public String getCategory() {
        return category;
    }

    public String processContent(int no, boolean isWriter, boolean isAdmin) throws IOException, TemplateException, SQLException {
        DmsTemplate template = new DmsTemplate(targetTemplate);
        for (String key : list.keySet()) {
            String[] columns = list.get(key);
            template.put(key, getString(no, columns));
        }
        template.put("isWriter", isWriter);
        template.put("isAdmin", isAdmin);
        return template.process();
    }

    private String getString(int no, String[] columArr) throws SQLException {
        String str = "";
        if (columArr != null && columArr.length > 0) {
            SafeResultSet rs = DataBase.getInstance().executeQuery("select ", QueryUtils.columnArrayToQuery(columArr), " from ", category, " where no=", no);
            if (rs.next()) {
                for (int i = 1; i <= rs.getColumnSize(); i++) {
                    str += rs.getString(i);
                }
            }
        }
        return str;
    }

}
