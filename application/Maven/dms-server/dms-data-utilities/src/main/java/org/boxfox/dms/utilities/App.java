package org.boxfox.dms.utilities;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.dataio.meal.MealModel;
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
    	System.out.println(MealModel.getMealAtDate(2017, 1, 2).toJSONObject());
    	while(true){
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    }
}
