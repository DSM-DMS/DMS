package com.planb.dms.support;

import io.vertx.core.MultiMap;

/*
 * Command analyzer for parameters from request.
 * Please refer to the 'command list file' in this package.
 */

public class CommandAnalyzer {	
	private MultiMap params;
	
	private int command;
	private int prefixOfCommand;
	
	private String sql;
	
	public CommandAnalyzer(MultiMap params) {
		this.params = params;
	}
	
	public String analyze() {
		/*
		 * Analyze command before acting on the client's request.
		 * The command is an integer type. (premise)
		 */
		command				= Integer.parseInt(params.get("command"));
		prefixOfCommand	= command / 100;
		
		if(prefixOfCommand == 0) {
			/*
			 * Case that INSERT
			 */
		} else if(prefixOfCommand == 1) {
			/*
			 * Case that UPDATE
			 */
			
		} else if(prefixOfCommand == 2) {
			/*
			 * Case that DELETE
			 */
			
		} else if(prefixOfCommand == 3) {
			/*
			 * Case that SELECT
			 */
		}
		
		return sql;
	}
}
