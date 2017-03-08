package com.dms.planb.support;

import io.vertx.ext.web.RoutingContext;

public class CORSHeader {
	public static RoutingContext putHeaders(RoutingContext context) {
		context.response().putHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type");
		context.response().putHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS, CONNECT");
		context.response().putHeader("Access-Control-Allow-Origin", "http://dsm2015.cafe24.com/*");
		
		return context;
	}
}
