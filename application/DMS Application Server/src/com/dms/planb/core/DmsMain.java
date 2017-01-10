package com.dms.planb.core;

/*
 * Daedeok Software Meister High School
 * Domitory Management System(DMS) Project
 * 
 * Android Application HTTP Server based Vert.x Framework
 * Developed by Jo Mingyu : PlanB
 * Developer Blog : http://city7310.blog.me/
 * 
 * Work Started at 2017.01.10
 * 
 * Git Repository : https://github.com/rlatjdfo112/DSM-Dormitory-System.git
 * Branch : server
 */

/* :: Library Uses ::
 * [java-json.jar]
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

public class DmsMain {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new DmsVerticle());
	}
}
