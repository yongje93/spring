package member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam String id, @RequestParam String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView();
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
		}
		mav.addObject("display","/template/body.jsp");
		mav.addObject("loginResult", loginResult);
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.invalidate();
		mav.addObject("display", "/member/logout.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	//@RequestMappint(value="/member/modifyForm", method=RequestMethod)
}
