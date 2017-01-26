package org.boxfox.dms.data.utilities;

import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.dataio.meal.MealModel;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest 
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    public void testApp()
    {
    	DataSaveAble data = MealModel.getMealAtDate(2017, 1, 26);
    	System.out.println(data.toString());
        Assert.assertNotNull(data);
    }
}
