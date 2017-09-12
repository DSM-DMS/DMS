package com.dms.parser.dataio.post;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dms.parser.dataio.Parser;
import com.dms.parser.dataio.ParserUtils;
import com.dms.parser.dataio.Query;
import com.dms.parser.datamodel.post.Attachment;
import com.dms.parser.datamodel.post.AttachmentList;
import com.dms.parser.datamodel.post.Post;
import com.dms.utilities.database.DB;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.DataSaveable;
import com.dms.utilities.database.QueryUtils;

public class PostParser<T> extends Parser {
	public static final int CATEGORY_BROAD = 0;
	public static final int CATEGORY_FAMILER = 1;
	public static final int CATEGORY_CHALLENGE = 2;

	private int category, postNum;

	public PostParser(int category, int postNum, Object[] args) {
		this.category = category;
		this.postNum = postNum;
		url = LINK + ParserUtils.getUrl(URL_BOARD_PARAMATER, args);
	}

	public static String getUrlFromCategory(int category) {
		String url = null;
		switch (category) {
		case CATEGORY_BROAD:
			url = URL_BROAD;
			break;
		case CATEGORY_FAMILER:
			url = URL_FAMILER;
			break;
		case CATEGORY_CHALLENGE:
			url = URL_CHALLENGE;
			break;
		}
		return url;
	}

	@Override
	public DataSaveable[] parseAll() {
		throw new RuntimeException("Is not Implemented method!");
	};

	@Override
	public DataSaveable parse() {
		DataSaveable asd;
		Document doc = ParserUtils.getDoc(url);
		Element article = doc.getElementsByTag("article").get(0);
		String title = article.getElementsByTag("h1").get(1).text().replaceAll("제목", "");
		String writer = article.getElementsByClass("infoBox").get(0).getElementsByTag("li").get(0).text().replaceAll("작성자","");
		String dateTime = article.getElementsByClass("infoBox").get(0).getElementsByTag("li").get(1).text().replaceAll("작성일","");
		String content = article.getElementsByClass("viewBox").get(0).html();

		Post post = null;
		try {
			int num = 0;
			ResultSet rs = DB.executeQuery(Query.POST.selectFormat + " ORDER BY `no` DESC LIMIT 1");
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			DB.executeUpdate(
					QueryUtils.querySetter(Query.POST.insertFormat, num, category, postNum, "", "", "1999-01-01", ""));
			post = PostModel.getPost(category, postNum);
			if (post != null) {
				AttachmentList list = new AttachmentList();
				if (article.getElementsByTag("dd").size() != 0) {
					Elements files = article.getElementsByTag("dd").get(0).getElementsByTag("a");
					for (int i = 0; i < files.size(); i++) {
						Element item = files.get(i++);
						Attachment attachment = new Attachment(num, item.text(), item.attr("href"));
						list.add(attachment);
					}
				}
				post.setTitle(title);
				post.setWriter(writer);
				post.setDateTime(dateTime);
				post.setContent(content);
				post.setFileList(list);
				DataBase.getInstance().execute(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}

}
