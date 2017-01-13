package com.dms.planb.support;

/*
 * Command analyzer for parameters from request.
 * Please refer to the 'command list file' in this package.
 */

public class CommandAnalyzer {	
	private int command;
	private int prefixOfCommand;
	
	private String sql;
	
	public String analyze(String command) {
		/*
		 * 1. Analyze command before acting on the client's request.
		 * 2. After analyzation, do action for command.
		 * 3. If have any data to need to response, return JSON object.
		 * 
		 * The command is an integer type. (premise)
		 */
		
		this.command = Integer.parseInt(command);
		prefixOfCommand	= this.command / 100;
		
		//Switch 문을 쓰는건 어때?
		if(prefixOfCommand == 0) { //int를 직접 쓰지 말고 Command 클래스를 만들어서 prefixOfCommand==Command.LOGIN 이런식으로 사용하도록
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
