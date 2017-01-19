package org.boxfox.dms.utilities;

import org.boxfox.dms.utilities.dataio.meal.MealParser;
import org.boxfox.dms.utilities.dataio.plan.PlanParser;
import org.boxfox.dms.utilities.dataio.post.PostChangeDetector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PostChangeDetector.getInstance().start();
    	while(true){
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
}
