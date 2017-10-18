package com.dms.utilities.routing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.vertx.core.http.HttpMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Route {

	String path();

	HttpMethod[] method() default {};

}
