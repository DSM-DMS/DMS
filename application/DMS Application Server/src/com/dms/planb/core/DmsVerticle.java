package com.dms.planb.core;

/*
 * Main verticle for main method of DmsMain class.
 * Communicates with a POST request.
 * 
 * 1. Create HTTP Server
 * 2. Discriminate HTTP Method
 * 3. Analyze command in requester's integer parameter.
 * 4. And performs an operation corresponding to the command.
 * 
 * -- Prerequisites --
 * 1. The command is provided as a parameter.
 * 2. All of the data is provided in JSON format.(Both server and client)
 */

import com.dms.boxfox.database.DataBase;
import com.dms.boxfox.logging.Log;

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
		System.out.println("Server started");
//		Log.l("Server started");
		server = vertx.createHttpServer();
		
		server.requestHandler(request -> {
			System.out.println("Received request");
//			Log.l("Received request");
			
			Buffer totalBuffer = Buffer.buffer();

			MultiMap params = request.params();
			// Get parameters from request
			
			if(request.method() == HttpMethod.POST) {
				// The server will only work if the Http method is POST.
				System.out.println("Received POST method");
//				Log.l("Received POST method");
				
				request.bodyHandler(buffer -> {
					// The bodyHandler is called once when all the body has been received
					totalBuffer.appendBuffer(buffer);
				});
				
				request.endHandler(v -> {
					 // The endHandler of the request is invoked when the entire request, including any body has been fully read.
					System.out.println("fully read");
//					Log.l("Fully read requester's data");
					
					CommandAnalyzer analyzer = new CommandAnalyzer();
					// Create instance of CommandAnalyzer class.
					
					String sql = analyzer.analyze(params.get("command"));
					// Analyze the parameters.
					
					DataBase dataBase = DataBase.getInstance();
					// Get instance from singleton pattern of the DataBase class.
				});
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
