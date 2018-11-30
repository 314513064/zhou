package com.dangde.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dangde.config.Configdangde;
import com.dangde.domain.Approve;
import com.dangde.domain.Case;
import com.dangde.domain.Comment;
import com.dangde.domain.Material;
import com.dangde.domain.User;
import com.dangde.domain.model.QueryInfo;
import com.dangde.domain.model.XQ_approveVO;
import com.dangde.domain.model.XQ_commentVO;
import com.dangde.service.CaseService;
import com.dangde.service.MaterialService;
import com.dangde.service.NoticeService;
import com.dangde.service.UserService;
import com.google.gson.Gson;

@Component
@RequestMapping("")
public class MaterialController {

	@Autowired
	MaterialService materialService;
	@Autowired
	CaseService caseService;
	@Autowired
	UserService userService;
	@Autowired
	NoticeService noticeService;	
	
	//材料列表
	@RequestMapping(value="/case/{caseId}/operation/{id}/materials",method=RequestMethod.GET)
	public ModelAndView showmaterial1(@PathVariable("id") Long operation_id,int operation_type,int material_type,Integer currentpage, HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		User user = (User)session.getAttribute("user");
		
		
		QueryInfo queryInfo =new QueryInfo();
		if(currentpage!=null){
			queryInfo.setCurrentpage(currentpage);

		}
		List<Object> materialsAndPagebean = materialService.getMaterials(operation_id, material_type,user.getId()
				,queryInfo);
		
		session.setAttribute("operation_id", operation_id);
		session.setAttribute("operation_type", operation_type);

		mv.addObject("materialsAndPagebean", materialsAndPagebean);
		mv.addObject("material_type", material_type);

		if(material_type==100){
			mv.setViewName("material/materialUI");

		}else {
			mv.setViewName("material/materialUI2");
		}
		return mv;
	}
	
	//材料详情
		@RequestMapping(value="/case/{caseId}/operation/{operation_id}/material/{id}",method=RequestMethod.GET)
		public ModelAndView showmaterial2(@PathVariable("id") Long material_id){
			ModelAndView mv = new ModelAndView();
			
			Material material = materialService.getMaterial(material_id);
			mv.addObject("material", material);
			List<XQ_commentVO> xQ_commentVOs = materialService.getCommentXiangQing(material_id);
			List<XQ_approveVO> xQ_approveVOs = materialService.getApproveXiangQing(material_id);
			
			mv.addObject("xQ_commentVOs", xQ_commentVOs);
			mv.addObject("xQ_approveVOs", xQ_approveVOs);
			
			if(material.getFile_type()==0)
				mv.setViewName("material/detail_picture");
			else if(material.getFile_type()==1)
				mv.setViewName("material/detail_document");
			return mv;
		}
	
	

	//材料上传,只跟协作者有关
	@RequestMapping(value="/case/{caseId}/operation/{operation_id}/material",method=RequestMethod.POST)
	@ResponseBody
	public Map upload(Material material,@PathVariable("operation_id") Long operation_id,@RequestParam("file_head") MultipartFile[] files,HttpSession session,int file_type,HttpServletRequest request){
		//前段发过来的是title,introduce,file_type,flag_output
				//file_type: 0代表图片上传    1代表文档上传
				String dir = "";
				material.setFile_type(file_type);
				material.setOperation_id(operation_id);
				if(file_type==0)
					dir = Configdangde.UPLOAD_PATH_IMAGE;
				else if(file_type==1)
					dir = Configdangde.UPLOAD_PATH_DOCUMENT;
				
				User user = (User)session.getAttribute("user");
				
				Long case_id = ((Case)session.getAttribute("casee")).getId();
				
				List<Long> userids_coopandcreate = caseService.selectCoopidsAndCreateids(case_id);//得到所有协作者和创建者的用户id
				
				Map<String,String> code = new HashMap<String,String>();							
				
				Case casee= (Case)session.getAttribute("casee");	


				try{
					materialService.insertMaterial(material,files,dir,userids_coopandcreate,user.getId(),file_type,casee.getSummary(),user.getUsername(),casee.getId());
					//上传成功
					code.put("code","1014");				
					return code;
									
				}catch(Exception e){
					code.put("code", "1015");
					return code;
				}



	}
	
	
	
	//包含其他材料上传界面
	@RequestMapping("/case/{caseId}/operation/{id}/material/pictureUI")
	public ModelAndView upload_picture(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("material/picture_uploadUI");
		return mv;
	}
	//具体材料上传界面
	@RequestMapping("/case/{caseId}/operation/{id}/material/pictureUI2")
	public ModelAndView upload_picture(int material_type){
		ModelAndView mv = new ModelAndView();
		mv.addObject("material_type",material_type);
		mv.setViewName("material/picture_uploadUI2");
		return mv;
	}
	
	//成果图片上传界面

	@RequestMapping("/case/{caseId}/operation/{id}/material/pictureUI3")
	public ModelAndView upload_picture3(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("material/picture_uploadUI3");
		return mv;
	}
	

	//包含其他文档上传界面
	@RequestMapping("/case/{caseId}/operation/{operation_id}/material/documentUI")
	public ModelAndView upload_wendang(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("material/document_uploadUI");
		return mv;
	}
	//具体文档上传界面
	@RequestMapping("/case/{caseId}/operation/{operation_id}/material/documentUI2")
	public ModelAndView upload_wendang(int material_type){
			ModelAndView mv = new ModelAndView();
			mv.addObject("material_type",material_type);
			mv.setViewName("material/document_uploadUI2");
			return mv;
		}
	
	//成果文档上传界面

	@RequestMapping("/case/{caseId}/operation/{operation_id}/material/documentUI3")
	public ModelAndView upload_wendang3(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("material/document_uploadUI3");
		return mv;
	}
	

		
		//转跳发送材料联系人界面
		@RequestMapping("/case/{caseId}/operation/{operation_id}/material/{id}/sendUI")
		public ModelAndView sendMaterialUI(@PathVariable("id")Long material_id,HttpSession session){
			ModelAndView mv = new ModelAndView();
			
			User user=(User) session.getAttribute("user");	
			List<User> friendList=userService.findFriends(user.getId());
			
			mv.addObject("material_id", material_id);
			mv.addObject("friendList",friendList);
			mv.setViewName("material/sendUI");
			
			return mv;
		}
		
		
		//发送材料
		@RequestMapping("/case/{caseId}/operation/{operation_id}/material/{id}/send")
		@ResponseBody
		public Map send_Material(@RequestBody List<Long> user_ids,@PathVariable("id")Long material_id,
				@PathVariable("caseId")Long caseId,@PathVariable("operation_id")Long operation_id,
				HttpSession session){
			Map<String,String> code = new HashMap<String,String>();
			
			Case casee= (Case)session.getAttribute("casee");	
			User user = (User)session.getAttribute("user");

			
			try {
				materialService.send_Material(user_ids, material_id,caseId,casee.getSummary(),user.getUsername(),operation_id);
				code.put("code","1016");
				return code;
			} catch (Exception e) {
				e.printStackTrace();
				code.put("code","1017");
				return code;
			}	
		}

		
		
		//发表评论
		@RequestMapping(value="/case/{caseId}/operation/{operation_id}/material/{id}/comment")
		@ResponseBody
		public Map comment(@PathVariable("id")Long material_id,Comment comment,HttpSession session){
			Map<String,String> code = new HashMap<String,String>();
			
			User user = (User)session.getAttribute("user");
			comment.setUser_id(user.getId());
			comment.setMaterial_id(material_id);
			comment.setCreate_time(new Date());
			try {
				materialService.insert_Comment(comment);
				code.put("code","1018");
				return code;
			} catch (Exception e) {
				e.printStackTrace();
				code.put("code","1019");
				return code;
			}
		}
		
		
		//材料审批
		@RequestMapping(value="/case/{caseId}/operation/{operation_id}/material/{id}/approve")
		@ResponseBody
		public Map approve(@PathVariable("id")Long material_id,String note,int flag,HttpSession session){
			Map<String,String> code = new HashMap<String,String>();
			
			User user = (User)session.getAttribute("user");
			
			Approve approve = new Approve();
			approve.setUser_id(user.getId());
			approve.setMaterial_id(material_id);
			approve.setNote(note);
			approve.setCreate_time(new Date());
			approve.setFlag(flag);
			
			try {
				materialService.approve(approve);
				code.put("code","1020");
				return code;
			} catch (Exception e) {
				e.printStackTrace();
				code.put("code","1021");
				return code;
			}
			
			
		}

		//材料详情从通知过去
		@RequestMapping(value="/case/{caseId}/operation/{operation_id}/material/{id}/from_notice",method=RequestMethod.GET)
		public ModelAndView showmaterial2(@PathVariable("id") Long material_id,
				@RequestParam("readtype")String readtype,@RequestParam("noticeId") Long noticeId){
			ModelAndView mv = new ModelAndView();
			
			Material material = materialService.getMaterial(material_id);
			mv.addObject("material", material);
			List<XQ_commentVO> xQ_commentVOs = materialService.getCommentXiangQing(material_id);
			List<XQ_approveVO> xQ_approveVOs = materialService.getApproveXiangQing(material_id);
			
			mv.addObject("xQ_commentVOs", xQ_commentVOs);
			mv.addObject("xQ_approveVOs", xQ_approveVOs);
			
			if(readtype.equals("notice"))
				this.noticeService.updateById(noticeId);
			
			if(material.getFile_type()==0)
				mv.setViewName("material/detail_picture");
			else if(material.getFile_type()==1)
				mv.setViewName("material/detail_document");
			return mv;
		}	
		
///////////////////////////////////////////////////////	
		//材料输出
		@RequestMapping(value="/case/{caseId}/operation/{operation_id}/material/output" ,method=RequestMethod.POST)
		@ResponseBody
		public Map material_output(@RequestParam("id")Long material_id,@RequestParam("type")int type,HttpSession session){
			Map<String,String> code = new HashMap<String,String>();
			
			Long operation_id = (Long)session.getAttribute("operation_id");
			
			try {
				materialService.material_Output(material_id, operation_id, type);
				code.put("code","1022");
				return code;
			} catch (Exception e) {
				code.put("code","1023");
				return code;
			}

		}
		
		//材料输出列表
		@RequestMapping("/case/{caseId}/operation/{id}/outputUI")
		public ModelAndView material_outputUI(@PathVariable("id")Long operation_id,int operation_type,HttpSession session){
			ModelAndView mv = new ModelAndView();
			
			User user = (User)session.getAttribute("user");
			List<Material> materials = materialService.getMaterials_Output(operation_id,user.getId());
			
			session.setAttribute("operation_id", operation_id);
			session.setAttribute("operation_type", operation_type);
			
			mv.addObject("materials", materials);
			mv.setViewName("material/outputUI");

			return mv;

		}
		
		
		//删除成果,只重设输出标记
		@RequestMapping("/case/{caseId}/operation/{id}/outputDelete")
		@ResponseBody
		public Map output_delete(@RequestBody List<Long> material_ids) {
			Map<String,String> code = new HashMap<String,String>();
			
			try {
				materialService.output_Delete(material_ids);
				code.put("code","1024");
				return code;
			} catch (Exception e) {
				code.put("code","1025");
				return code;
			}

		}
		
		
		
	
}
