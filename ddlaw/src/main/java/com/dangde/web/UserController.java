package com.dangde.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.dangde.domain.User;
import com.dangde.service.UserService;
import com.dangde.utils.ServiceUtils;


@Component
@RequestMapping("")//url:模块/资源/{}/细分
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/register" ,method = RequestMethod.POST)//
    @ResponseBody
    public Map register(User user){
		
    	
		Map<String,String> code = new HashMap<String,String>();	
		
		if(this.userService.findUser_exitByTel(user.getTel())!=null){
			code.put("code","1002");//用户已存在			
		    return code;						
			
		}
		
		user.setRegTime(new Date());	
		user.setUsername(user.getTel());
		
		try {
			this.userService.saveUser(user);
			code.put("code","1000");//注册成功

		} catch (Exception e) {
			//e.printStackTrace();
			code.put("code","1001");//注册失败
		} finally{
			return code;
		}
		

    }
	
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(String tel,String password,HttpSession session){
    	
    	Map<String,String> code = new HashMap<String,String>();
		
		User user = this.userService.findUserByTel_Psw(tel, password);
		
		if(user!=null){
			
			code.put("code", "1003");//登录成功
			session.setAttribute("user", user); //user放入session中
			code.put("userid", user.getId().toString());//登录成功

			return code;

		}		
		code.put("code", "1004");//账号密码错误
		return code;
		
    	 	
    }
	
    @RequestMapping("/logout")
	public String logout(HttpSession session){
    	session.removeAttribute("user");
    	return "redirect:/index.html"; 
	}
    
    
    
	@RequestMapping("/users/{id}/my")
	public ModelAndView my(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/my");

		return mv;
	}
	
	@RequestMapping("/users/{id}/my/setting")
	public ModelAndView  mySet(){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/setting");

		return mv;
	}
	
	
	@RequestMapping(value="/users/{id}/my/info" ,method = RequestMethod.GET)
	public ModelAndView myselect(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/info");
		return mv;
	}
	
	@RequestMapping(value="/users/{id}/my/info" ,method = RequestMethod.PUT)
	@ResponseBody
	public Map myupdate_info(User user,HttpSession session){
		//发过来的username,sex,birthday,job,company_type,company_name
				//剩余id,password,tel,email,iconurl
				Map<String,String> code = new HashMap<String,String>();
				User user1 = (User) session.getAttribute("user");
				
				try{
					
					user.setId(user1.getId());
					this.userService.updateUser_info(user);
					
					User user2 = this.userService.getUserByTel(user1.getTel());
					session.setAttribute("user", user2);  //将更新后的user2放入session中
					code.put("code", "1005");  //修改信息成功
					
				}catch (Exception e) {
					
					e.printStackTrace();
					code.put("code","1006");//修改信息失败，服务器出错了
					
				}finally {
					return code;
				}
	}
	

	
	
	@RequestMapping(value="/users/{id}/my/email",method=RequestMethod.GET)
	public ModelAndView myTel(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/email");

		return mv;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/users/{id}/my/email",method=RequestMethod.PUT)
	@ResponseBody
	public Map update_email(String email,HttpSession session){
		//发过来的只有email
		Map<String,String> code = new HashMap<String,String>();
		User user = (User) session.getAttribute("user");
		
		user.setEmail(email);
		try{
			
			this.userService.updateUser_email(user);
			session.setAttribute("user", user);
			code.put("code", "1010");	//修改邮箱成功
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code.put("code","1011");//修改邮箱失败，服务器出错了
		}finally {
			
			return code;
		}
		
		
	}


	
	@RequestMapping(value="/users/{id}/my/password",method = RequestMethod.GET)
	public ModelAndView myPassword(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/password");

		return mv;
	}
	
    
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/users/{id}/my/password" ,method = RequestMethod.PUT)
	@ResponseBody
	public Map update_psw(String beforepassword,String afterpassword,HttpSession session){
		//发过来的只有password
		Map<String,String> code = new HashMap<String,String>();
		User user = (User) session.getAttribute("user");
		
		beforepassword = ServiceUtils.md5(beforepassword);
		
		if(!beforepassword.equals(user.getPassword())){
			code.put("code", "1007");   //修改密码失败，原密码输入错误
			return code;
		}
		user.setPassword(ServiceUtils.md5(afterpassword));

		try{
			
			this.userService.updateUser_password(user);
			session.setAttribute("user", user);
			code.put("code", "1008");	//修改密码成功
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code.put("code","1009");//修改密码失败，服务器出错了
		}finally {
			
			return code;
		}
		
		
	}

    
}
