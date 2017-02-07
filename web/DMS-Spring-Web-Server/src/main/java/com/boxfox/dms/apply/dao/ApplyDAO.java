package com.boxfox.dms.apply.dao;


public interface ApplyDAO {
	
	public void extensionApply(String id, int room, int seat);
	public void afterschoolApply(String id, int no);
	public void rewardscoreApply(String id, String target, String content);
	public void stayApply(String id, int value, String data);
	public void goingoutApply(String id,String date, String reason);

}
