package com.dms.planb.template_routers.xlsx;

import com.dms.planb.template_routers.xlsx.*;
import com.google.common.net.HttpHeaders;

import java.io.UnsupportedEncodingException;

import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.sun.javafx.binding.StringFormatter;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/stay/download", method = {HttpMethod.GET})
public class ResidualDownloadRouter implements Handler<RoutingContext> {
    private com.dms.planb.template_routers.xlsx.ResidualDownload residualDownload;
    private AdminManager adminManager;

    public ResidualDownloadRouter() {
        residualDownload = new com.dms.planb.template_routers.xlsx.ResidualDownload();
        adminManager = new AdminManager();
    }

    public void handle(RoutingContext context) {
        Log.l("Stay Download " + context.request().remoteAddress());
        if(adminManager.isAdmin(context)) {
            int year = Integer.valueOf(context.request().getParam("year"));
            int month = Integer.valueOf(context.request().getParam("month"));
            int week = Integer.valueOf(context.request().getParam("week"));
            String date = StringFormatter.format("%4d-%02d-%02d", year, month, week).getValue();
            String fileName = null;
			try {
				fileName = new String("잔류신청.xlsx".getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			context.response()
				.putHeader(HttpHeaders.CONTENT_DISPOSITION, "filename=" + fileName)
				.sendFile(residualDownload.readExcel(date));
			context.response().close();
        }else{
            context.response().setStatusCode(400);
            context.response().end("You are Not Admin");
            context.response().close();
        }
    }
}
