package com.dms.planb.core;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.planb.support.ActionPerformer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

class DmsVerticleEdit extends AbstractVerticle {
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
			
			if(request.method() == HttpMethod.POST) {
				System.out.println("Received POST method");
				
				request.handler(new Handler<Buffer>(){

					@Override
					public void handle(Buffer buffer) {
						totalBuffer.appendBuffer(buffer);
						
					}
					
				});
				
				request.endHandler(v -> {
					System.out.println("fully read");
					
					//command�� ����� �������
					int command = Integer.parseInt(request.getHeader("command"));
					
					//User-Agent�� Ŭ���̾�Ʈ�� ����(�� ����, UUID ��)
					JsonObject obj = new JsonObject(request.getHeader("User-Agent"));
					obj.getString("Version");
					obj.getString("UUID");
					
					try {
						requestObject = new JSONObject().put("testKey", "testValue");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					/*try {
						responseObject = ActionPerformer.perform(command, requestObject);
					} catch (JSONException | SQLException e) {
						e.printStackTrace();
					}*/
					
					response = request.response();
					response.putHeader("content-type", "application/json; charset=utf-8");
					response.setStatusCode(200);
					response.setStatusMessage("Message");
					
					response.end("response result like json");
					
					//reponse����� �����ڿ��� close����� Ŭ���̾�Ʈ�� ���۵�
					response.close();
				}); // endHandler
			} else {
				/*
				 *  If Http method is CONNECT, DELETE, GET, HEAD, OPTIONS, or other ...
				 *  Communication fail.
				 */
			}
		}).listen(10419);
	}
	
	public void stop(@SuppressWarnings("rawtypes") Future stopFuture) throws Exception {
		
	}
}
