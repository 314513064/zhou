package com.dangde.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangde.dao.UserDao;
import com.dangde.dao.third.User1_User2_Dao;
import com.dangde.domain.User;
import com.dangde.exception.AException;
import com.dangde.service.UserService;
import com.dangde.utils.ServiceUtils;


@Service("userService")
public class UserServiceImpl implements UserService{

    //日志对象
    private Logger logger= LoggerFactory.getLogger(this.getClass());
	//注入Service依赖
    @Autowired //@Resource
	private UserDao userDao;
    
    @Autowired //@Resource
	private User1_User2_Dao user1_User2_Dao;

    @Transactional
	@Override
	public void saveUser(User user){
    	
    	
		try {
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			this.userDao.insertUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 logger.error("saveUser inner error :"+e.getMessage(),e);
	            //所以编译期异常转化为运行期异常
	         throw new RuntimeException("saveUser inner error :"+e.getMessage());
		}		
		
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Transactional
	@Override
	public void updateUser_info(User user) {
		// TODO Auto-generated method stub
		try {
			this.userDao.updateUser_info(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("updateUser_info inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("updateUser_info inner error :"+e.getMessage());
		}	

	}
	
	
	@Transactional
	@Override
	public void updateUser_email(User user) {
		// TODO Auto-generated method stub
		try {
			this.userDao.updateUser_email(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("updateUser_email inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("updateUser_email inner error :"+e.getMessage());
		}	

	}
	
	@Transactional
	@Override
	public void updateUser_password(User user) {
		// TODO Auto-generated method stub
		try {
			this.userDao.updateUser_password(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("updateUser_password inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("updateUser_password inner error :"+e.getMessage());
		}	

	}
	
	

	@Override
	public String findUser_exitByTel(String tel) {
		// TODO Auto-generated method stub
		String exit=this.userDao.getUser_exist_ByTel(tel); 
		if(exit!=null)
			return exit;
		
		return null;
	}

	@Override
	public User findUserByTel_Psw(String tel, String psw) {
		// TODO Auto-generated method stub
	
		return  this.userDao.getUserByTel_Psw(tel, ServiceUtils.md5(psw));
		
	}

	@Override
	public List<User> findFriends(Long userid) {
		// TODO Auto-generated method stub
		return user1_User2_Dao.findFriends(userid);
	}

	@Override
	public User getUserByTel(String tel) {
		// TODO Auto-generated method stub
		return  this.userDao.getUserByTel(tel);
	}		
   
	
	
}
