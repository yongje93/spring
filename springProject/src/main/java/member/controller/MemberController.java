package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.service.MemberService;

@Controller
@RequestMapping(value = "member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Inject
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "loginForm")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/member/loginForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	@ResponseBody
//	public String login(@RequestParam String id, @RequestParam String pwd, HttpSession session) {
//		String myPwd = memberService.getMember(id).getPwd();
//
//		System.out.println("디비에 저장된 비밀번호 : " + myPwd);
//		System.out.println("입력한 비밀번호 : " + pwd);
//
//		if (passwordEncoder.matches(pwd, myPwd)) {
//			System.out.println("비밀번호 일치");
//		} else {
//			System.out.println("비밀번호 불일치");
//		}
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("pwd", pwd);
//
//		MemberDTO memberDTO = memberService.login(map);
//
//		String loginResult = null;
//		if (memberDTO == null) {
//			loginResult = "fail";
//		} else {
//			session.setAttribute("memName", memberDTO.getName());
//			session.setAttribute("memId", memberDTO.getId());
//			session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
//
//			loginResult = "success";
//		}
//		return loginResult;
//	}

	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public ModelAndView writeForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/member/writeForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String RegisterPost(@ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes rttr)
			throws Exception {
		System.out.println("regeseterPost 진입");
		memberService.regist(memberDTO);
		rttr.addFlashAttribute("msg", "가입시 사용한 이메일로 인증해주세요");
		return "redirect:/";
	}

	// 이메일 인증 코드 컴증
	@RequestMapping(value = "emailConfirm", method = RequestMethod.GET)
	public String emailConfirm(@ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes rttr) {
		System.out.println("cont get user" + memberDTO);
		MemberDTO member = new MemberDTO();
		member = memberService.userAuth(memberDTO);
		if (member == null) {
			rttr.addFlashAttribute("msg", "비정상적인 접근 입니다. 다시 인증해 주세요");
			return "redirect:/";
		}

		model.addAttribute("login", member);
		return "/member/emailOk";
	}

	@RequestMapping(value = "emailOk", method = RequestMethod.GET)
	public ModelAndView emailOk() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/member/emailOk.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	@RequestMapping(value = "write", method = RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute MemberDTO memberDTO) {
		System.out.println("데이터 저장 전 비밀번호 : " + memberDTO.getPwd());

		String encPassword = passwordEncoder.encode(memberDTO.getPwd());
		memberDTO.setPwd(encPassword);
		System.out.println("암호화된 비밀번호 : " + encPassword);

		memberService.write(memberDTO);
	}

	@RequestMapping(value = "checkId", method = RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id) {
		MemberDTO memberDTO = memberService.checkId(id);

		if (memberDTO == null) {
			return "not_exist";
		} else {
			return "exist";
		}
	}

	@RequestMapping(value = "checkPost", method = RequestMethod.GET)
	public ModelAndView checkPost() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/checkPost");
		return mav;
	}

	@RequestMapping(value = "postList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postList(@RequestParam Map<String, String> map) {
		ModelAndView mav = new ModelAndView();
		List<ZipcodeDTO> list = memberService.getZipcodeList(map);

		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}

	@RequestMapping(value = "modifyForm", method = RequestMethod.GET)
	public ModelAndView modifyForm(@RequestParam String id) {
		MemberDTO memberDTO = memberService.getMember(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.addObject("display", "/member/modifyForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute MemberDTO memberDTO) {
		memberService.modify(memberDTO);
	}
}
