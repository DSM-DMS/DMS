package com.dms.planb.core;

import io.vertx.core.Vertx;

public class DmsMain {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new DmsVerticle());
	}
}
