package com.dms.planb.support;

import com.google.common.net.HttpHeaders;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.ext.web.RoutingContext;

public class PrecedingWork {
	public static RoutingContext putHeaders(RoutingContext context) {
		context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Cookie, Origin, X-Requested-With, Content-Type");
		context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, PATCH, GET, DELETE, OPTIONS, HEAD, CONNECT");
		context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/*");
		context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/");
		context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com");
		context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		Log.l("Requested : " + context.request().host());
		Log.l("Absolute URI : " + context.request().absoluteURI());
		Log.l("Parameters : " + context.request().params());
		
		return context;
	}
}
