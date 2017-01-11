package com.dms.planb.support;

import io.vertx.core.MultiMap;

/*
 * Command describer for HTTP parameters.
 * Please refer to the 'command list file' in this package.
 */

public class CommandAnalyzer {
	private MultiMap params;
	
	public CommandAnalyzer(MultiMap params) {
		this.params = params;
	}
	
	public void analyze() {
		/*
		 * Analyze command before acting on the client's request
		 * The command is an integer type. (premise)
		 */
		int command = Integer.parseInt(params.get("command"));
		
	}
}
