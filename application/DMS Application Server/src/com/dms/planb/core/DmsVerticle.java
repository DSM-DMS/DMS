package com.dms.planb.core;

/*
 * Main verticle for main method in DmsMain class.
 * 
 * 
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;

public class DmsVerticle extends AbstractVerticle {
	private HttpServer server;
	
	@Override
	public void start() throws Exception {
		// Logging : Server started
		server = vertx.createHttpServer();
		server.requestHandler(request -> {
			// Logging : Request from client
			
			Buffer totalBuffer = Buffer.buffer();
			
			if(request.method() == HttpMethod.POST) {
				// Process when http method is only POST
				request.bodyHandler(buffer -> {
					totalBuffer.appendBuffer(buffer);
					
					MultiMap params = request.params();
				});
			} else {
				/*
				 *  If method is CONNECT, DELETE, GET, HEAD, OPTIONS, or other ...
				 *  Communication fail.
				 */
			}
		});
	}
	
	public void stop(Future stopFuture) throws Exception {
		
	}
}
