package com.boxfox.dms.users.dto;

public class UserRenameDTO extends UserDTO{
	private String newId;
	
	public UserRenameDTO(String id,String newId, String password) {
		super(id, password);
		this.setNewId(newId);
	}

	public String getNewId() {
		return newId;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}

}
