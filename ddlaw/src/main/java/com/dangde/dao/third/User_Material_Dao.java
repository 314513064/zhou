package com.dangde.dao.third;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface User_Material_Dao {

	
	public boolean insertUser_Material(@Param("user_ids")List<Long> user_ids,@Param("material_id")Long material_id);
}
