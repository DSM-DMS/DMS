package org.boxfox.dms.utilities.dataio.post;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.datamodel.post.Post;

public class PostModel {

	public static Post getPost(int no) {
		Post post = null;
		try {
			ResultSet rs = DataBase.getInstance().executeQuery(Query.POST.selectFormat, "where no=", no);
			if (rs.next()) {
				post = (Post) new Post().fromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public static Post getPost(String title){
		Post post = null;
		try {
			ResultSet rs = DataBase.getInstance().executeQuery(Query.POST.selectFormat, "where title='", title,"'");
			if (rs.next()) {
				post = (Post) new Post().fromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}

}
