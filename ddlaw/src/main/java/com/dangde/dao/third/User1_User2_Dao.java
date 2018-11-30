package com.dangde.dao.third;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dangde.domain.User;

public interface User1_User2_Dao {
	
	
	
	public void insertUser1_User2(@Param("user1_id")Long user1_id, @Param("user2_id")Long user2_id);
	
	public List<User> findFriends(@Param("userid")Long userid);



}
