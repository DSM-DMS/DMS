package com.dms.boxfox.networking;

public class HttpBoxException extends RuntimeException {
	
	public HttpBoxException(Object... args) {
		super(getMessage(args));
	}
	
	private static String getMessage(Object ... args){
		StringBuilder builder = new StringBuilder();
		for (Object arg : args) {
			builder.append(arg.toString());
		}
		return builder.toString();
	}
}
