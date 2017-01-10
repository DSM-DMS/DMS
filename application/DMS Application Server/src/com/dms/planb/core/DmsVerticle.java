package com.dms.planb.core;

/*
 * Main verticle for main method in DmsMain class.
 * Communicate by POST
 * 
 * 1. Create HTTP Server
 * 2. Discriminate HTTP Method
 * 3. Analyze command in requester's parameter.
 */

import com.dms.planb.support.CommandAnalyzer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

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
					// get parameters from request
					CommandAnalyzer analyzer = new CommandAnalyzer(params);
					// analyze parameter
					analyzer.analyze();
					
					HttpServerResponse response = request.response();
				});
			} else {
				/*
				 *  If method is CONNECT, DELETE, GET, HEAD, OPTIONS, or other ...
				 *  Communication fail.
				 */
			}
		}).listen(10419);
	}
	
	public void stop(Future stopFuture) throws Exception {
		
	}
}
