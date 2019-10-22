package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String id, @RequestParam String pwd, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		
		MemberDTO memberDTO = memberService.login(map);
		
		String loginResult = null;
		if(memberDTO == null) {
			loginResult = "fail";
		}
		else {
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", memberDTO.getId());
			session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());

			loginResult = "success";
		}
		return loginResult;
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.POST)
	@ResponseBody
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@RequestMapping(value="/member/writeForm", method=RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/member/writeForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="/member/write", method=RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute MemberDTO memberDTO) {
		memberService.write(memberDTO);
	}
	
	@RequestMapping(value="/member/checkId", method=RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id) {
		MemberDTO memberDTO = memberService.checkId(id);
		
		if(memberDTO == null) {
			return "not_exist";
		} else {
			return "exist";
		}
	}
	
	@RequestMapping(value="/member/checkPost", method=RequestMethod.GET)
	public ModelAndView checkPost() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/checkPost");
		return mav;
	}
	
	@RequestMapping(value="/member/postList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView postList(@RequestParam String sido, @RequestParam String sigungu, @RequestParam String roadname) {
		
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("sigungu", sigungu);
		map.put("roadname", roadname);
		
		List<ZipcodeDTO> list = null;
		if(sido != null && roadname!= null) {
			list = memberService.getZipcodeList(map);
		}
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
}
