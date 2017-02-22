package com.boxfox.dms.mapper;

import java.util.List;

import com.boxfox.dms.users.dto.UserDTO;
import com.boxfox.dms.users.dto.UserDataDTO;
import com.boxfox.dms.users.dto.UserModifyPasswordDTO;
import com.boxfox.dms.users.dto.UserRenameDTO;

public interface UserMapper {
	public List<String> login(UserDTO user);
	public int rename(UserRenameDTO user);
	public int modifyPassword(UserModifyPasswordDTO user);
	public Integer checkIdExist(String id);
	public List<UserDataDTO> residual();
	public Integer residualAtWeek(String id);
}
