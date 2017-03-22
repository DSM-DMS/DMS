package com.dms.boxfox.templates.xlsx;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.sun.javafx.binding.StringFormatter;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.log.Log;

@RouteRegistration(path = "/stay/download", method = {HttpMethod.GET})
public class ResidualDownloadRouter implements Handler<RoutingContext> {
	private ResidualDownload residualDownload;

	public ResidualDownloadRouter(){
		residualDownload = new ResidualDownload();
	}

	public void handle(RoutingContext context) {
		Log.l("Stay Download "+context.request().remoteAddress());
		int year = Integer.valueOf(context.request().getParam("year"));
		int month = Integer.valueOf(context.request().getParam("month"));
		int week = Integer.valueOf(context.request().getParam("week"));
		String date = StringFormatter.format("%4d-%02d-%02d", year, month, week).getValue();
		context.response().sendFile(residualDownload.readExcel(date));
	}
}
