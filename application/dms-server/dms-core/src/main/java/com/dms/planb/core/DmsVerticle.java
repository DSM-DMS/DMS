package com.dms.planb.core;

/*
 * Communication : HTTP Protocol, POST method, JSON exchange
 * 
 * Action Request : Command in header, reference Commands class
 * Request Data : JSON
 * 
 * HTTP Status codes : https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C
 */

import java.sql.SQLException;

import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.ActionPerformer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

class DmsVerticle extends AbstractVerticle {
	private HttpServer server;
	private HttpServerResponse response;
	
	private EasyJsonObject clientObject;
	private EasyJsonObject requestObject;
	private EasyJsonObject responseObject;
	// org.json.simple.JSONObject

	/*
	 * (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start()
	 */
	@Override
	public void start() throws Exception {
		Log.l("Server started "+ ApplicationInfo.VERSION);
		// org.boxfox.dms.utilities.Log
		
		server = vertx.createHttpServer();
		server.requestHandler(request -> {
			Log.l("Received request : " + request.host());
			
			Buffer totalBuffer = Buffer.buffer();
			
			if(request.method() == HttpMethod.POST) {
				// The server will only work if the Http method is POST.
				Log.l("Received POST method : " + request.host());
				Log.l("Header : " + request.getHeader("command"));
				
				request.handler(buffer -> {
					totalBuffer.appendBuffer(buffer);
				});
				
				request.endHandler(v -> {
					 // The endHandler of the request is invoked when the entire request, including any body has been fully read.
					Log.l("Fully read : " + totalBuffer);
					
					// 1-1. Get command from header.
					int command = Integer.parseInt(request.getHeader("command"));
					
					// 1-2. Get client's info
//					clientObject = new EasyJsonObject(request.getHeader("User-Agent"));
//					clientObject.getString("Version");
//					clientObject.getString("UUID");
					
					// 1-3. Get request object from buffer.
					requestObject = new EasyJsonObject(totalBuffer.toString());
					
					// 2. Ready to response to client. Set status code in try-catch
					response = request.response();
					response.putHeader("Content-type", "application/json; charset=utf-8");
					
					try {
						/*
						 *  3. Performs the operation.
						 *  Branch off the ActionPerformer class' perform method.
						 */
						responseObject = ActionPerformer.perform(command, requestObject);
						
						if(responseObject.containsKey("status")) {
							if(responseObject.getInt("status") == 0) {
								response.setStatusCode(500);
								Log.l("Responsed status code : 500");
								// 500 : Internal Server Error
							} else if(responseObject.getInt("status") == 1) {
								response.setStatusCode(200);
								Log.l("Responsed status code : 200");
								// 200 : Success
							} else if(responseObject.getInt("status") == 2) {
								response.setStatusCode(404);
								Log.l("Responsed status code : 404");
								// 404 : Can't find
							}
							responseObject.remove("status");
						} else {
							response.setStatusCode(200);
							Log.l("Responsed status code : 200");
						}
						
						Log.l("Responsed object : " + responseObject.toString());
					} catch (SQLException e) {
						response.setStatusCode(404);
						
						e.printStackTrace();
						Log.l("SQLException");
					}
					
					response.end(responseObject.toString());
					response.close();
				}); // endHandler
			} else {
				response = request.response();
				response.putHeader("Content-type", "text/html; charset=utf-8");
				response.setStatusCode(405);
				// 405 : Method Not Allowed
				
				response.end();
				response.close();
			}
		}).listen(10419);
	}
	
	public void stop(@SuppressWarnings("rawtypes") Future stopFuture) throws Exception {
		
	}
}
