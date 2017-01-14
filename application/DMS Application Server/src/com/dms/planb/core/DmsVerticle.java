package com.dms.planb.core;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.planb.support.ActionPerformer;
import com.dms.planb.support.Commands;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

class DmsVerticle extends AbstractVerticle {
	
	private HttpServer server;
	private HttpServerResponse response;
	
	private JSONObject requestObject;
	private JSONObject responseObject;
	/*
	 *  org.json.JSONObject
	 *  I created instance of io.vertx.core.json.JsonObject, but can't find a way about serialize
	 */

	/*
	 * (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start()
	 */
	@Override
	public void start() throws Exception {
		System.out.println("Server started");
		
		server = vertx.createHttpServer();
		server.requestHandler(request -> {
			System.out.println("Received request");
			
			Buffer totalBuffer = Buffer.buffer();
			MultiMap params = request.params();
			// Get parameters from request
			
			if(request.method() == HttpMethod.POST) {
				// The server will only work if the Http method is POST.
				System.out.println("Received POST method");
				
				request.bodyHandler(buffer -> {
					// The bodyHandler is called once when all the body has been received
					totalBuffer.appendBuffer(buffer);
				}); // bodyHandler
				
				request.endHandler(v -> {
					 // The endHandler of the request is invoked when the entire request, including any body has been fully read.
					System.out.println("fully read");
					
					// 1-1. Get command from parameter.
					int command = Integer.parseInt(params.get("command"));
					
					// 1-2. Get request object from buffer.
					try {
//						requestObject = new JSONObject(totalBuffer.getString(0, totalBuffer.length()));
						requestObject = new JSONObject().put("testKey", "testValue");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					// 2. Get prefix of command before acting on the client's request.
					int prefixOfCommand = command / 100;
					
					// 3. Ready the response to client.
					response = request.response();
					response.putHeader("content-type", "application/json; charset=utf-8");
					
					// 4. Performs the operation appropriate to the command.
					if(prefixOfCommand				== Commands.INSERT) {
						responseObject = ActionPerformer.doInsert(command, requestObject);
						response.end(responseObject.toString());
					} else if(prefixOfCommand	== Commands.UPDATE) {
						responseObject = ActionPerformer.doUpdate(command, requestObject);
						response.end(responseObject.toString());
					} else if(prefixOfCommand	== Commands.DELETE) {
						responseObject = ActionPerformer.doDelete(command, requestObject);
						response.end(responseObject.toString());
					} else if(prefixOfCommand	== Commands.SELECT) {
						responseObject = ActionPerformer.doSelect(command, requestObject);
						response.end(responseObject.toString());
					}
				}); // endHandler
			} else {
				/*
				 *  If Http method is CONNECT, DELETE, GET, HEAD, OPTIONS, or other ...
				 *  Communication fail.
				 */
			}
		}).listen(10419);
	}
	
	public void stop(Future stopFuture) throws Exception {
		
	}
}
