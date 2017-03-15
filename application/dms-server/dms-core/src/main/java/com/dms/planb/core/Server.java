package com.dms.planb.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.*;
import io.vertx.core.streams.Pump;
import io.vertx.core.AsyncResultHandler;

public class Server extends AbstractVerticle {

    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest request) {
                System.out.println("uploading " + request.remoteAddress());
                if (request.uri().contains("fileUpload")) {
                    request.setExpectMultipart(true);
                    request.uploadHandler(new Handler<HttpServerFileUpload>() {
                        private AsyncFile mFile;
                        private Pump mPump;

                        @Override
                        public void handle(final HttpServerFileUpload upload) {
                            upload.exceptionHandler(new Handler<Throwable>() {
                                @Override
                                public void handle(Throwable event) {
                                    System.out.println(event.getMessage());
                                }
                            });

                            upload.endHandler(new Handler<Void>() {
                                @Override
                                public void handle(Void arg0) {
                                    if (mFile != null)
                                        mFile.close();
                                }
                            });

                            String uri = request.uri();
                            uri = uri.substring(uri.indexOf("fileUpload") + "fileUpload".length(), uri.length());
                            if (uri.contains("..")) return;
                            String dir = System.getProperty("user.dir") + "/minecrafts" + uri;
                            System.out.println(dir);
                            upload.pause();
                            vertx.fileSystem().open(dir + "/" + upload.filename(),
                                    new OpenOptions().setCreate(OpenOptions.DEFAULT_CREATE),
                                    new AsyncResultHandler<AsyncFile>() {
                                        public void handle(final AsyncResult<AsyncFile> ar) {
                                            System.out.println("asvasv");
                                            if (ar.succeeded()) {
                                                mFile = ar.result();
                                                mPump = Pump.factory.pump(upload, ar.result());
                                                mPump.start();
                                                upload.resume();
                                            }
                                        }
                                    });
                        }
                    });
                }
            }
        });
        server.listen(8080);
    }
}