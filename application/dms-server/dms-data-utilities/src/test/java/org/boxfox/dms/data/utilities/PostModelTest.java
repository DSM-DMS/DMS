package org.boxfox.dms.data.utilities;

import org.boxfox.dms.utilities.dataio.post.PostModel;
import org.junit.Test;

public class PostModelTest {
	
	@Test
	public void getPostsAtPage(){
		PostModel.getPostsAtPage(0, 2);
	}

}
