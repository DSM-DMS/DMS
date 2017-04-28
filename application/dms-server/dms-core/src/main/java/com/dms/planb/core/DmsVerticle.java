package com.dms.planb.core;

import com.dms.parser.dataio.post.PostChangeDetector;
import com.dms.parser.dataio.post.PostUpdateListener;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.JksOptions;
import org.boxfox.dms.utilities.actions.RouteRegister;


import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.boxfox.dms.utilities.config.SecureConfig;

public class DmsVerticle extends AbstractVerticle {
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-files"));
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
        RouteRegister.registerRouters(router, "org.boxfox.dms.secure", "com.dms.planb", "com.dms.boxfox.templates");
        router.route().handler(StaticHandler.create());
        
        /*
         * @see com.dms.planb.support .TableDropper
		 */
        PostChangeDetector.getInstance().start();
        PostChangeDetector.getInstance().setOnCategoryUpdateListener(new PostUpdateListener() {
            @Override
            public void update(int currentCategory) {

            }
        });
        HttpServerOptions httpOpts = new HttpServerOptions();
        /*System.out.println(SecureConfig.get("SSL_PATH"));
        System.out.println(SecureConfig.get("SSL"));
        httpOpts.setSsl(true)
                .setKeyStoreOptions(new JksOptions().setPath(SecureConfig.get("SSL_PATH")).setPassword(SecureConfig.get("SSL")))
                .setTrustStoreOptions(new JksOptions().setPath(SecureConfig.get("SSL_PATH")).setPassword(SecureConfig.get("SSL")));
        */vertx.createHttpServer(httpOpts).requestHandler(router::accept).listen(80);
    }
}
