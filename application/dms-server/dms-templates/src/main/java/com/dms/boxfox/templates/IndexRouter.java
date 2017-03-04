package com.dms.boxfox.templates;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import java.io.IOException;

@RouteRegistration(path = "/", method={HttpMethod.POST})
public class IndexRouter implements Handler<RoutingContext> {
	public void handle(RoutingContext context) {

		DmsTemplate templates = new DmsTemplate("index");
		try {
			System.out.println(templates.process());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
