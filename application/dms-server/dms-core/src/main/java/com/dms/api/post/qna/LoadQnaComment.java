package com.dms.api.post.qna;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.crypto.AES256;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.json.EasyJsonArray;
import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/qna/comment", method={HttpMethod.GET})
public class LoadQnaComment implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		int no = Integer.parseInt(ctx.request().getParam("no"));
		
		if(!Guardian.checkParameters(no)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			resultSet = database.executeQuery("SELECT * FROM qna_comment WHERE no=", no);
			
			if(resultSet.next()) {
				do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("no", resultSet.getInt("idx"));
				tempObject.put("id", AES256.decrypt(resultSet.getString("id")));
				tempObject.put("comment_date", resultSet.getString("comment_date"));
				tempObject.put("content", resultSet.getString("content"));
				
				tempArray.add(tempObject);
				} while(resultSet.next());
				
				responseObject.put("result", tempArray);
				
				ctx.response().setStatusCode(200);
				ctx.response().end(responseObject.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
