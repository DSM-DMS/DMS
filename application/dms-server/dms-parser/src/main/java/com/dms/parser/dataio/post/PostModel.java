package com.dms.parser.dataio.post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;

import com.dms.parser.dataio.Query;
import com.dms.parser.datamodel.post.Post;

public class PostModel {
	private static String GET_POSTS_FROM_PAGES_QUERY = "select * from app_content where category=? ORDER BY number desc limit ?, 10";
	
	public static List<Post> getPostsAtPage(int category, int page){
		ArrayList<Post> list = new ArrayList<Post>();
		try {
			SafeResultSet rs = DataBase.getInstance().executeQuery(QueryUtils.querySetter(GET_POSTS_FROM_PAGES_QUERY, category, page));
			while(rs.next()){
				Post post = (Post)new Post().fromResultSet(rs);
				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Post getPost(int no) {
		Post post = null;
		try {
			SafeResultSet rs = DataBase.getInstance().executeQuery(Query.POST.selectFormat, " where no=", no);
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
			SafeResultSet rs = DataBase.getInstance().executeQuery(Query.POST.selectFormat, " where title='", title,"'");
			if (rs.next()) {
				post = (Post) new Post().fromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public static Post getPost(int category, int homeNum){
		Post post = null;
		try {
			SafeResultSet rs = DataBase.getInstance().executeQuery(Query.POST.selectFormat, " where category=",category ," AND number=",homeNum);
			if (rs.next()) {
				post = (Post) new Post().fromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}

}
