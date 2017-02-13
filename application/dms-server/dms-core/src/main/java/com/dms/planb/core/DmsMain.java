package com.dms.planb.core;

import org.boxfox.dms.utilities.actions.ActionRegister;
import org.boxfox.dms.utilities.dataio.post.PostChangeDetector;

/*
 * -- Daedeok Software Meister High School --
 * Dormitory Management System(DMS) Project
 * 
 * Android Application HTTP Server based Vert.x Framework.
 * Managed by Maven
 * Developed by : Kim Seongrae - Boxfoxs | Jo Mingyu - PlanB
 * Developers' Blog : http://boxfoxs.tistory.com/ | http://city7310.blog.me/
 * 
 * Work Started at 2017.01.10
 * 
 * Git Repository				: https://github.com/rlatjdfo112/DSM-Dormitory-System.git
 * Branch							: server
 * 
 * Issues Management	: https://app.asana.com/0/238698310665123/board
 */

/* :: Library Uses ::
 * Reference pom.xml
 */

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

class DmsMain {
	public static void main(String[] args) {
		ActionRegister.init();
		PostChangeDetector.getInstance().start();
		//System.setErr(new LogErrorOutputStream(System.err));
		Vertx vertx = Vertx.vertx();
		VertxOptions options = new VertxOptions();
		options.setMaxEventLoopExecuteTime(2100000000);
		vertx.deployVerticle(new DmsVerticle());
		// Branch off DmsVerticle class
	}
}
