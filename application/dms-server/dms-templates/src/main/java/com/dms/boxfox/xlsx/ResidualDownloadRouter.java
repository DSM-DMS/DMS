package com.dms.boxfox.xlsx;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.sun.javafx.binding.StringFormatter;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/stay/download", method={HttpMethod.POST})
public class ResidualDownloadRouter implements Handler<RoutingContext> {
	private ResidualDownload residualDownload;

	public ResidualDownloadRouter(){
		residualDownload = new ResidualDownload();
	}

	public void handle(RoutingContext context) {
		int year = Integer.valueOf(context.pathParam("year"));
		int month = Integer.valueOf(context.pathParam("month"));
		int week = Integer.valueOf(context.pathParam("week"));
		String date = StringFormatter.format("%4d-%2d-%2d", year, month, week).getValue();
		context.response().sendFile(residualDownload.readExcel(date));
	}
}
