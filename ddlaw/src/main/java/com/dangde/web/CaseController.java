package com.dangde.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.dangde.domain.Case;
import com.dangde.domain.Court;
import com.dangde.domain.Judge;
import com.dangde.domain.Layer;
import com.dangde.domain.Operation;
import com.dangde.domain.Trusteeopposition;
import com.dangde.domain.User;
import com.dangde.domain.model.Judges;
import com.dangde.domain.model.Layers;
import com.dangde.domain.model.Operations;
import com.dangde.domain.model.Trusteeoppositions;
import com.dangde.service.CaseService;
import com.dangde.service.CourtService;
import com.dangde.service.JudgeService;
import com.dangde.service.LayerService;
import com.dangde.service.TrusteeoppositionService;
import com.dangde.service.UserService;
import com.google.gson.Gson;


@Component
@RequestMapping("")//url:模块/资源/{}/细分
public class CaseController {
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private UserService userService;

	
	@Autowired
	private CourtService courtService;
	@Autowired
	private JudgeService judgeService;
	@Autowired
	private LayerService layerService;
	@Autowired
	private TrusteeoppositionService trusteeoppositionService;
	
	
	@RequestMapping("/casetype")
	public String mycase(){
		
		return "case/casetype";
	}
	
	
	@RequestMapping(value="/cases" ,method=RequestMethod.GET)
	public String cases(HttpSession session,Model model){
		
		User user = (User)session.getAttribute("user");

		List<Case> caseList=this.caseService.findAll(user.getId());
		model.addAttribute("caseList",caseList);
		return "case/cases";
	}
	
	
    
	@RequestMapping("/case/new")
	public String newcase(){
		
		return "case/new_step1";
	}
	
	
	
	@RequestMapping(value="/case/new_step1",method=RequestMethod.POST)
	public String createcase(Case case1,Judges judges,Layers layers,Court court,Trusteeoppositions trusteeoppositions,HttpSession session){
		
		
		session.setAttribute("casee",case1);
		session.setAttribute("judges",judges);
		session.setAttribute("layers",layers);
		session.setAttribute("court",court);
		session.setAttribute("trusteeoppositions",trusteeoppositions);

		
		return "case/new_step2";
	}
	
	@RequestMapping(value="/case/new_step2",method=RequestMethod.POST)
	public ModelAndView createcase2(HttpSession session,Operations operations){
		ModelAndView modelAndView=new ModelAndView();
		
		session.setAttribute("operations",operations);
		
		User user=(User) session.getAttribute("user");
		
		List<User> friendList=userService.findFriends(user.getId());
		
		modelAndView.addObject("friendList",friendList);
		
		modelAndView.setViewName("case/new_step3");
		return modelAndView;
	}
	
	@RequestMapping(value="/case/new_step3",method=RequestMethod.POST)
	@ResponseBody
	public Map createcase3(HttpSession session,@RequestBody ArrayList<Long> userids ){
		
		Map<String,String> code = new HashMap<String,String>();
		
		Case case1=(Case) session.getAttribute("casee");
		Judges judges=(Judges) session.getAttribute("judges");
		Layers layers=(Layers) session.getAttribute("layers");
		Court court=(Court) session.getAttribute("court");
		Trusteeoppositions trusteeoppositions=(Trusteeoppositions) session.getAttribute("trusteeoppositions");
		Operations operations=(Operations) session.getAttribute("operations");
		
		Long userid=((User) session.getAttribute("user")).getId();
		
		try {
			Long caseId=caseService.saveCase(case1, judges, layers, court, trusteeoppositions,operations,userids,userid);

			session.removeAttribute("casee");
			session.removeAttribute("judges");
			session.removeAttribute("layers");
			session.removeAttribute("court");
			session.removeAttribute("trusteeoppositions");
			session.removeAttribute("operations");

			//保留case和操作的session
			
			code.put("code", "1013");
			code.put("caseid", caseId.toString());


		} catch (Exception e) {
			// TODO Auto-generated catch block
			code.put("code", "1012");
			//e.printStackTrace();
		}finally{
			return code;
		}
	}
	
	
	
	@RequestMapping(value="/case/{caseId}/todolist" ,method=RequestMethod.GET)
	public String caseetodolist(@PathVariable("caseId") Long caseId,HttpSession session,Model model){
		
		List<Operation> operations=this.caseService.getOperationBycaseId(caseId);
		Case casee= this.caseService.findByid(caseId);	
		
		//case权限
		User user = (User)session.getAttribute("user");
		int privilege_Id = this.caseService.getprivilege_Id(user.getId(),caseId);
		
		session.setAttribute("casee", casee);
		session.setAttribute("operations",operations);
		session.setAttribute("privilege_Id", privilege_Id);

		return "case/todoList";
	}
		
		
	
	//查询case详情
	@RequestMapping(value="/case/{caseId}" ,method=RequestMethod.GET)
	public String casee(@PathVariable("caseId") Long caseId,HttpSession session,Model model){
		
		
		Case casee= (Case)session.getAttribute("casee");	
		
		
		Court court=this.courtService.findByid(casee.getCourt_id());
		
		List<Layer> layers=new ArrayList<Layer>();
		layers=this.layerService.findByCaseId(caseId);
		
		List<Judge> judges=new ArrayList<Judge>();
		judges=this.judgeService.findByCaseId(caseId);
		
		List<Trusteeopposition> trusteeoppositions=new ArrayList<Trusteeopposition>();
		trusteeoppositions=this.trusteeoppositionService.findByCaseId(caseId);
		
		model.addAttribute("court", court);
		model.addAttribute("layers", layers);
		model.addAttribute("judges", judges);
		model.addAttribute("Trusteeoppositions", trusteeoppositions);

		return "case/case";
	}
		
	
	
	
}


