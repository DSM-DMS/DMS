package com.dms.planb.core;



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

import java.io.File;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.utilities.actions.RouteRegister;
import org.boxfox.dms.utilities.database.DataBase;

import com.dms.parser.dataio.post.PostChangeDetector;
import com.dms.parser.dataio.post.PostUpdateListener;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

class DmsMain {
	private static Vertx vertx;
	private static VertxOptions options;

	/**
	 * @see com.dms.planb.action.account.ModifyProfileImage
	 */
	private static File profileImgDir = new File("Profile Images");
	/*
	 * Current path, directory which named "Profile Images"
	 */

	private static void initialize() {
		/*
		 * Initializing method when server started.
		 */

		if (!profileImgDir.exists()) {
			profileImgDir.mkdir();
		}
		/*
		 * Create profile image directory if not exists
		 */

		/**
		 * @see org.boxfox.dms.utilities.actions .ActionRegister
		 */
		/*
		 * Using Reflection. Find @ActionRegistration annotation of classes in
		 * package, and register actions to ActionRegister class.
		 */

		/**
		 * @see com.dms.parser.dataio.post .PostChangeDetector
		 */
		// PostChangeDetector.getInstance().start();
		/*
		 * Post(in school web page) change detector(thread)
		 */

		PostChangeDetector.getInstance().setOnCategoryUpdateListener(new PostUpdateListener() {
			/*
			 * Refresh databases at regular intervals
			 */
			@Override
			public void update(int currentCategory) {
				Calendar currentTime = Calendar.getInstance();
				int dayOfWeek = currentTime.get(Calendar.DAY_OF_WEEK);
				int hour = currentTime.get(Calendar.HOUR_OF_DAY);
				if (dayOfWeek == Calendar.MONDAY) {
					try {
						DataBase.getInstance().executeUpdate("delete from goingout_apply");
						/*
						 * Every Monday, refresh goingout_apply table
						 */
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (hour >= 0 && hour <= 8) {
					try {
						DataBase.getInstance().executeUpdate("delete from extension_apply");
						/*
						 * Every day, refresh extension_apply table
						 */
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});

		/**
		 * @see pom.xml : vert.x and netty syntax
		 */
		vertx = Vertx.vertx();
		/*
		 * Get vert.x's instance from Vertx(extends Measured) class
		 */

		options = new VertxOptions();
		// System.setErr(new LogErrorOutputStream(System.err));
	}

	public static void main(String[] args) {
		initialize();
		/*
		 * Branch off initialize() method
		 */

		/**
		 * (non-Javadoc)
		 * 
		 * @see http://vertx.io/docs/apidocs/io/vertx/core/VertxOptions.html
		 */
		options.setMaxEventLoopExecuteTime(2100000000);
		/*
		 * Sets the value of max event loop execute time, in ns.
		 */

		/**
		 * (non-Javadoc)
		 * 
		 * @see http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html
		 * @see com.dms.planb.core .DmsVerticle
		 */
		vertx.deployVerticle(new DmsVerticle());
		/*
		 * Deploy a verticle instance that you have created yourself. Branch off
		 * DmsVerticle class
		 */
	}
}
