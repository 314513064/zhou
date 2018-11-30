package com.dangde.web;


import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dangde.domain.Case;
import com.dangde.domain.Notice;
import com.dangde.service.CaseService;
import com.dangde.service.NoticeService;



@Component
@RequestMapping("")//url:模块/资源/{}/细分
public class WorkController {
	
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private CaseService caseService;
	
	@RequestMapping(value="/users/{id}/works" ,method=RequestMethod.GET)
	public String cases(@PathVariable("id")Long userId,Model model){
		
		List<Notice> noticeList=this.noticeService.findAll(userId);	
		List<Case> caseList=this.caseService.findWork(userId);
		model.addAttribute("caseList",caseList);
		model.addAttribute("noticeList",noticeList);
		return "work";
	}
	

	
	
	
}