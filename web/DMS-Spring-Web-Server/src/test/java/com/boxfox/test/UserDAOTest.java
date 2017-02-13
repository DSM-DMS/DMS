package com.boxfox.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.boxfox.dms.users.dao.UserDAOImpl;

public class UserDAOTest {
	
	@Autowired
    private UserDAOImpl userDAO;
	
	@Test
	public void loginTest(){
		String sessionKey = userDAO.login("test", "1234");
		Assert.assertNotNull(sessionKey);
	}

}
