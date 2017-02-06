package com.boxfox.dms.users.dto;

public class UserModifyPasswordDTO extends UserDTO{
	private String newPassword;
	
	public UserModifyPasswordDTO(String id, String password, String newPassword) {
		super(id, password);
		this.setNewPassword(newPassword);
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
