package com.dms.planb.core;

/*
 * -- Daedeok Software Meister High School --
 * Domitory Management System(DMS) Project
 * 
 * Android Application HTTP Server based Vert.x Framework.
 * Developed by Kim Seongrae : Boxfoxs, Jo Mingyu : PlanB
 * Developers' Blog : http://boxfoxs.tistory.com/ | http://city7310.blog.me/
 * 
 * Work Started at 2017.01.10
 * 
 * Git Repository : https://github.com/rlatjdfo112/DSM-Dormitory-System.git
 * Branch : server
 */

/* :: Library Uses ::
 * [java-json.jar]
 * [mysql-connector-java-5.1.37-bin.jar]
 * [vertx-core-3.3.3.jar]
 * [netty-buffer-4.1.5.Final.jar]
 * [netty-codec-4.1.5.FInal.jar]
 * [netty-codec-dns-4.1.5.Final.jar]
 * [netty-codec-http-4.1.5.Final.jar]
 * [netty-common-4.1.5.Final.jar]
 * [netty-handler-4.1.5.Final.jar]
 * [netty-resolver-4.1.5.Final.jar]
 * [netty-resolver-dns-4.1.5.Final.jar]
 * [netty-transport-4.1.5.Final.jar]
 */

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class DmsMain {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		VertxOptions options = new VertxOptions();
		options.setMaxEventLoopExecuteTime(2100000000);
		vertx.deployVerticle(new DmsVerticle());
	}
}
