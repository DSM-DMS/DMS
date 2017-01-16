package org.boxfox.dms.utilities;

import org.boxfox.dms.utilities.dataio.meal.MealParser;
import org.boxfox.dms.utilities.dataio.plan.PlanParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PlanParser parser = new PlanParser(2017, 2);
    	parser.parse();
    }
}
