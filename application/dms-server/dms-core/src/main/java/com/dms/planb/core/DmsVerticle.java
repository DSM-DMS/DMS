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

import org.boxfox.dms.secure.SecureManager;
import org.boxfox.dms.utilities.actions.RouteRegister;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * @author JoMingyu
 * @see http://vertx.io/docs/vertx-core/java/
 */
class DmsVerticle extends AbstractVerticle {
	/** (non-Javadoc)
	 * @see http://vertx.io/docs/apidocs/io/vertx/core/http/HttpServer.html
	 */
	private HttpServer server;
	
	/** (non-Javadoc)
	 * @see http://vertx.io/docs/apidocs/io/vertx/core/http/HttpServerResponse.html
	 */
	private HttpServerResponse response;
	
	/**
	 * @see org.boxfox.dms.utilities.json
	 * .EasyJsonObject
	 */
	private EasyJsonObject clientObject;
	private EasyJsonObject requestObject;
	private EasyJsonObject responseObject;
	
	/**
	 * @see org.boxfox.dms.secure
	 * .SecureManager
	 */
	private SecureManager secureManager;

	/** (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start()
	 */
	
	@Override
	public void start() throws Exception {
		/**
		 * @see org.boxfox.dms.utilities.log
		 * .Log
		 */
		Log.l("Server started "+ ApplicationInfo.VERSION);
		
		secureManager = SecureManager.getInstance();
		
		server = vertx.createHttpServer();
		RouteRegister.registerRouters(Router.router(vertx), "org.boxfox.dms.secure", "com.dms.planb");
		server.requestHandler(request -> {
			Log.l("Received request : " + request.host());
			
			Buffer totalBuffer = Buffer.buffer();
			
			if(request.method() == HttpMethod.POST) {
				/*
				 *  The server will only work if the Http method is POST or OPTIONS.
				 */
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
//					
//					clientObject.getString("Version");
//					clientObject.getString("UUID");
					
					// 1-3. Get request object from buffer.
					// JsonObject이 암호화 되어 올 수 있기 때문에 검사작업
//					requestObject = SecureManager.createJsonObject(totalBuffer.toString(), sender);
					requestObject = new EasyJsonObject(totalBuffer.toString());
					
					// 2. Ready to response to client. Set status code in try-catch
					response = request.response();
					response.putHeader("content-type", "application/json; charset=utf-8");
					response.putHeader("content-type", "application/x-www-form-urlencoded; charset=utf-8");
					
					/**
					 * About CORS
					 * @see http://ooz.co.kr/232
					 * 
					 * About HTTP status code and methods
					 * @see http://gyrfalcon.tistory.com/entry/HTTP-%EC%9D%91%EB%8B%B5-%EC%BD%94%EB%93%9C-%EC%A2%85%EB%A5%98-HTTP-%EB%A9%94%EC%86%8C%EB%93%9C-%EC%A2%85%EB%A5%98
					 */
					response.putHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
					/*
					 * Method allow : POST, OPTIONS
					 * OPTIONS : to receive preflight request
					 */
					
					response.putHeader("Access-Control-Max-Age", "3600");
					/*
					 * Preflight request cash time : 3600s
					 */
					
					response.putHeader("Access-Control-Allow-Headers", "content-type, x-requested-with, command");
					/*
					 * Support AJAX
					 */
					
					response.putHeader("Access-Control-Allow-Origin", "*");
					/*
					 * Allow all of domains
					 */
					
					try {
						/*
						 *  3. Performs the operation.
						 *  Branch off the ActionPerformer class' perform method.
						 */
						Actionable.responseObject.clear();
						Actionable.array.clear();

						responseObject = ActionRegister.executeAction(new Sender(), command, requestObject);
						
						if(responseObject.containsKey("status")) {
							if(responseObject.getInt("status") == 200 || responseObject.getInt("status") == 1) {
								response.setStatusCode(200);
								Log.l("Responsed status code : 200");
								// 200 : Success
							} else if(responseObject.getInt("status") == 404 || responseObject.getInt("status") == 0) {
								response.setStatusCode(204);
								Log.l("Responsed status code : 204");
								// 404 : Can't find, but set 204 because over 400 status code occurs FileNotFound on android client
							} else if(responseObject.getInt("status") == 500) {
								response.setStatusCode(205);
								Log.l("Responsed status code : 205");
								// 500 : Internal Server Error, but set 205
							}
							// Remove key "status" after set status code
							responseObject.remove("status");
						} else {
							// Not contains key "status"
							response.setStatusCode(200);
							Log.l("Responsed status code : 200");
						}
					} catch (SQLException e) {
						/*
						 *  Occurred SQLException
						 *  Ex) Student number is 99999, post number is -5, etc..
						 *  
						 *  Set status code 204
						 */
						response.setStatusCode(204);
						
						e.printStackTrace();
						Log.l("SQLException");
					}
					
					response.end(responseObject.toString());
					Log.l("Responsed object : " + responseObject.toString());
					
					response.close();
				}); // endHandler
			} else if(request.method() == HttpMethod.OPTIONS) {
				response = request.response();
				response.putHeader("Content-type", "text/html; charset=utf-8");
				response.putHeader("content-type", "application/x-www-form-urlencoded; charset=utf-8");
				
				response.putHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
				/*
				 * Method allow : POST, OPTIONS
				 * OPTIONS : to receive preflight request
				 */
				
				response.putHeader("Access-Control-Max-Age", "3600");
				/*
				 * Preflight request cash time : 3600s
				 */
				
				response.putHeader("Access-Control-Allow-Headers", "content-type, x-requested-with, command");
				/*
				 * Support AJAX
				 */
				
				response.putHeader("Access-Control-Allow-Origin", "*");
				/*
				 * Allow all of domains
				 */
				
				response.setStatusCode(205);
				// 205(405) : Method Not Allowed
				
				response.end();
				response.close();
			} else {
				/*
				 * Another methods
				 */
				response = request.response();
				response.putHeader("Content-type", "text/html; charset=utf-8");
				
				response.setStatusCode(205);
				// 205(405) : Method Not Allowed
				
				response.end();
				response.close();
			}
		}).listen(10419);
	}
	
	/** (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#stop(io.vertx.core.Future)
	 */
	@Deprecated
	public void stop(@SuppressWarnings("rawtypes") Future stopFuture) throws Exception {
		
	}
}
