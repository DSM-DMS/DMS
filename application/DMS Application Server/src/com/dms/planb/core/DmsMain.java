package com.dms.planb.core;

/*
 * Daedeok Software Meister High School
 * Domitory Management System Project
 * 
 * Android Application Server developed by Jo Mingyu - PlanB
 * 
 * Work started at 2017.01.10
 * 
 * Git repository : https://github.com/rlatjdfo112/DSM-Dormitory-System.git
 * branch : server
 */

import io.vertx.core.Vertx;

public class DmsMain {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new DmsVerticle());
	}
}
