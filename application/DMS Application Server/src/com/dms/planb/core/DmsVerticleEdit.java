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
					
					int command = Integer.parseInt(request.getHeader("Command"));
					
					//클라이언트 정보
					try {
						JSONObject obj = new JSONObject(request.getHeader("User-Agent"));
						obj.getString("Version");
						obj.getString("UUID");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					
					try {
						requestObject = new JSONObject().put("testKey", "testValue");
					} catch (JSONException e) {
						e.printStackTrace();
					}

					//클라이언트가 전송한 데이터(JSON)
					try {
						JSONObject requestObject = new JSONObject(totalBuffer.toString());
						System.out.println(requestObject.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					/*try {
						responseObject = ActionPerformer.perform(command, requestObject);
					} catch (JSONException | SQLException e) {
						e.printStackTrace();
					}*/
					
					response = request.response();
					//어차피 JSON으로 통신하지만 규격상 포함시키는 정보
					response.putHeader("Content-type", "application/json; charset=utf-8");
					
					//아래 3가지 정보는 반드시 들어가야 하는 정보임
					response.setStatusCode(200); //성공, 실패등 상태 코드
					response.setStatusMessage("Message"); //해당 상태에 대한 메소드
					//클라이언트한테 전송
					response.end(requestObject.toString());
					response.close();
				});
			} else {
				
			}
		}).listen(10419);
	}
	
	public void stop(@SuppressWarnings("rawtypes") Future stopFuture) throws Exception {
		
	}
}
