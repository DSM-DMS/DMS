package com.dms.planb.action.post.parsed;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.parser.dataio.plan.PlanModel;
import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_PLAN)
public class LoadPlan implements Handler<RoutingContext> {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int year = requestObject.getInt("year");
		int month = requestObject.getInt("month");
		
		responseObject.put("result", PlanModel.getPlan(year, month));
		
		return responseObject;
	}
}
