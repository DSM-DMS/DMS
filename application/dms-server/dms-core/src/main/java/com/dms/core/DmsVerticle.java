package com.dms.core;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

import com.dms.secure.RequestSecurePreprocessor;
import com.dms.utilities.routing.RouteRegister;
import com.dms.utilities.support.SecureConfig;

public class DmsVerticle extends AbstractVerticle {
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-files"));
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)).setNagHttps(false));
        router.route().handler(RequestSecurePreprocessor.create());
        RouteRegister.registerRouters(router, "com.dms.secure", "com.dms.api", "com.dms.templates");
        router.route("/js/*").handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("WEB-INF/js/"));
        
        /*
         * @see com.dms.planb.support .TableDropper
		 */
        HttpServerOptions httpOpts = new HttpServerOptions();
        /*System.out.println(SecureConfig.get("SSL_PATH"));
        System.out.println(SecureConfig.get("SSL"));
        httpOpts.setSsl(true)
                .setKeyStoreOptions(new JksOptions().setPath(SecureConfig.get("SSL_PATH")).setPassword(SecureConfig.get("SSL")))
                .setTrustStoreOptions(new JksOptions().setPath(SecureConfig.get("SSL_PATH")).setPassword(SecureConfig.get("SSL")));
        */
        vertx.createHttpServer(httpOpts).requestHandler(router::accept).listen(Integer.parseInt(SecureConfig.get("SERVER_PORT")));
    }
}
