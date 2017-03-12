package com.dms.planb.support;

import org.boxfox.dms.utilities.log.Log;

import io.vertx.ext.web.RoutingContext;

public class PrecedingWork {
	public static RoutingContext putHeaders(RoutingContext context) {
		context.response().putHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type");
		context.response().putHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS, HEAD, CONNECT");
		context.response().putHeader("Access-Control-Allow-Origin", "http://dsm2015.cafe24.com/*");
		context.response().putHeader("Access-Control-Allow-Origin", "http://dsm2015.cafe24.com/");
		context.response().putHeader("Access-Control-Allow-Origin", "http://dsm2015.cafe24.com");
		context.response().putHeader("Access-Control-Allow-Origin", "*");
		
		Log.l("Requested : " + context.request().host());
		Log.l("Absolute URI : " + context.request().absoluteURI());
		Log.l("Parameters : " + context.request().params());
		
		return context;
	}
}
