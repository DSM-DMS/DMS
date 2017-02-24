package com.boxfox.dms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boxfox.dms.users.dto.UserDTO;
import com.boxfox.dms.users.dto.UserDataDTO;
import com.boxfox.dms.users.dto.UserModifyPasswordDTO;
import com.boxfox.dms.users.dto.UserRenameDTO;

public interface UserMapper {
	public List<String> login(UserDTO user);
	public int rename(UserRenameDTO user);
	public int modifyPassword(UserModifyPasswordDTO user);
	public Integer checkIdExist(@Param("id") String id);
	public List<UserDataDTO> residual();
	public Integer residualAtWeek(@Param("id") String id, @Param("week") String week);
}
