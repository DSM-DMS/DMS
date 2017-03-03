package org.boxfox.dms.utilities.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.json.simple.JSONObject;
import org.reflections.Reflections;

import io.vertx.core.Vertx;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/* boxfox 2017.02.13*/

public class RouteRegister {
	private static final String EXCEPTION_ALREADY = "이미 Command를 사용중입니다.";
	private static RouteRegister instance;

	private Router router;

	private RouteRegister(Router router) {
		this.router = router;
	}

	public void registerRouter(String path, Handler<RoutingContext> handler) throws RegisterException {
		if (router.get(path) != null) {
			throw new RegisterException(EXCEPTION_ALREADY);
		} else
			router.route(path).handler(handler);
	}

	public void registerRouter(HttpMethod method, String path, Handler<RoutingContext> handler)
			throws RegisterException {
		if (router.get(path) != null) {
			throw new RegisterException(EXCEPTION_ALREADY);
		} else
			router.route(method, path).handler(handler);
	}

	public static void registerRouters(Router router, String... packages) {
		RouteRegister register = new RouteRegister(router);
		for (String package_s : packages) {
			searchRouters(register, package_s);
		}
	}

	private static void searchRouters(RouteRegister register, String pacakge) {
		Reflections reflections = new Reflections(pacakge);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(RouteRegisteration.class);
		for (Class<?> c : annotated) {
			RouteRegisteration annotation = c.getAnnotation(RouteRegisteration.class);
			try {
				if (annotation.method().length > 0)
					register.registerRouter(annotation.method()[0], annotation.path(),
							(Handler<RoutingContext>) c.newInstance());
				else
					register.registerRouter(annotation.path(), (Handler<RoutingContext>) c.newInstance());
			} catch (InstantiationException | IllegalAccessException | RegisterException e) {
				e.printStackTrace();
			}
		}
	}

}