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
@RequestMapping(value="member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="login", method=RequestMethod.POST)
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
	
//	@RequestMapping(value="logout", method=RequestMethod.GET)
//	public ModelAndView logout(HttpSession session) {
//		session.invalidate();
//		return new ModelAndView("redirect:/main/index"); // 페이지 이동 // ajax로는 redirect 안됨.
//	}
	
	@RequestMapping(value="writeForm", method=RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/member/writeForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
//	@RequestMapping(value="writeForm", method=RequestMethod.GET)
//	public String writeForm(Model model) {
//		model.addAttribute("display", "/member/writeForm.jsp");
//		return "/main/index";
//	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute MemberDTO memberDTO) {
		memberService.write(memberDTO);
	}
	
	@RequestMapping(value="checkId", method=RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id) {
		MemberDTO memberDTO = memberService.checkId(id);
		
		if(memberDTO == null) {
			return "not_exist";
		} else {
			return "exist";
		}
	}
	
	@RequestMapping(value="checkPost", method=RequestMethod.GET)
	public ModelAndView checkPost() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/checkPost");
		return mav;
	}
	
	@RequestMapping(value="postList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView postList(@RequestParam Map<String, String> map) {
		ModelAndView mav = new ModelAndView();
		List<ZipcodeDTO> list = memberService.getZipcodeList(map);
		
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public ModelAndView modifyForm(@RequestParam String id) {
		MemberDTO memberDTO = memberService.getMember(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.addObject("display", "/member/modifyForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute MemberDTO memberDTO) {
		memberService.modify(memberDTO);
	}
}
