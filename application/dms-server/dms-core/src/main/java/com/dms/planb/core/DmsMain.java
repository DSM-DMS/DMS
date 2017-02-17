package com.dms.planb.core;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.utilities.actions.ActionRegister;
import org.boxfox.dms.utilities.database.DataBase;

import com.dms.parser.dataio.post.PostChangeDetector;
import com.dms.parser.dataio.post.PostUpdateListener;

/**
 * @author KimSeongrae : Boxfoxs, JoMingyu : PlanB (city7310@naver.com)
 * 
 * -- Daedeok Software Meister High School --
 * Dormitory Management System(DMS) Project
 * 
 * Android Application HTTP Server based Vert.x Framework.
 * 
 * -- Developers' Blog --
 * @see http://boxfoxs.tistory.com/
 * @see http://city7310.blog.me/
 * 
 * -- Work --
 * @since 2017.01.10
 * 
 * @see https://github.com/rlatjdfo112/DSM-Dormitory-System.git
 * @see https://app.asana.com/0/238698310665123/board
 * 
 * -- Libraries -- 
 * @see pom.xml
 */

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

class DmsMain {
	private static Vertx vertx;
	private static VertxOptions options;

	private static void initialize() {
		ActionRegister.init("org.boxfox.dms.secure", "com.dms.planb");
		// -- Singleton
		PostChangeDetector.getInstance().start();
		PostChangeDetector.getInstance().setOnCategoryUpdateListener(new PostUpdateListener() {

			@Override
			public void update(int currentCategory) {
				Calendar currentTime = Calendar.getInstance();
				int dayOfWeek = currentTime.get(Calendar.DAY_OF_WEEK);
				int hour = currentTime.get(Calendar.HOUR_OF_DAY);
				if (dayOfWeek == Calendar.MONDAY) {
					try {
						DataBase.getInstance().executeUpdate("delete from goingout_apply");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (hour >= 0 && hour <= 8) {
					try {
						DataBase.getInstance().executeUpdate("delete from extension_apply");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		vertx = Vertx.vertx();

		options = new VertxOptions();
		// System.setErr(new LogErrorOutputStream(System.err));
	}

	public static void main(String[] args) {
		initialize();
		// Branch off initialize() method

		options.setMaxEventLoopExecuteTime(2100000000);

		vertx.deployVerticle(new DmsVerticle());
		// Branch off DmsVerticle class
	}
}
