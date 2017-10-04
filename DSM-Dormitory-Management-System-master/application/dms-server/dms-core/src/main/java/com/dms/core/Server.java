package com.dms.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerFileUpload;

public class Server extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            if (req.uri().startsWith("/form")) {
                req.setExpectMultipart(true);
                req.uploadHandler(upload -> {
                    upload.exceptionHandler(exception -> {
                        exception.printStackTrace();
                    });
                    upload.exceptionHandler(cause -> {
                        req.response().setChunked(true).end("Upload failed");
                    });
                    upload.endHandler(v -> {
                        req.response().setChunked(true).end("Successfully uploaded to " + upload.filename());
                    });
                    upload.streamToFileSystem(upload.filename());
                });
            } else {
                req.response().setStatusCode(404);
                req.response().end();
            }
        }).listen(8080);
    }

}