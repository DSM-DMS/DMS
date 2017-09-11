package org.boxfox.dms.secure;

import com.dms.utilities.log.Log;
import com.google.common.net.HttpHeaders;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-05-11.
 */
public class RequestSecurePreprocessor {

    public static Handler<RoutingContext> create() {
        return new SecurePreprocessHandler();
    }

    private static class SecurePreprocessHandler implements Handler<RoutingContext> {
        private SecureManager secureManager;

        public SecurePreprocessHandler() {
            secureManager = SecureManager.create(this.getClass());
        }

        @Override
        public void handle(RoutingContext ctx) {
            ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Cookie, Origin, X-Requested-With, Content-Type");
            ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, PATCH, GET, DELETE, OPTIONS, HEAD, CONNECT");
            ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/*");
            ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/");
            ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com");
            ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            if (secureManager.isBanned(ctx)) {
                ctx.response().setStatusCode(400);
                ctx.response().setStatusMessage("You are banned!");
                ctx.response().putHeader("Content-Type", "text/html; charset=utf-8");
                ctx.response().end("<h1>사이트에서 차단되었습니다.<br> 관리자에게 문의해 주세요 IP:"+ctx.request().remoteAddress().host()+"</h1>");
                ctx.response().close();
                return;
            }

            Log.l(" Method : " ,ctx.request().method()," Absolute URI : " , ctx.request().absoluteURI()," Params size : " , ctx.request().params().size());
            ctx.next();
        }
    }
}
