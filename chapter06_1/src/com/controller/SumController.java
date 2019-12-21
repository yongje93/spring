package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.SumDTO;

@Controller
public class SumController {
//	@RequestMapping(value="/input.do", method=RequestMethod.GET)
//	public ModelAndView input() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/sum/input");
//		return mav;
//	}
	
	// 리턴타입이 String이면 단순 문자열이 아니라 view의 이름으로 인식한다
	// 뷰 이름이 아니라 단순 문자열로 리턴하려면 @ResponseBody를 써야 한다
	@RequestMapping(value="/input.do", method=RequestMethod.GET)
	public String input() {
		return "/sum/input";
	}
	
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public ModelAndView result(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		sumDTO.setX(Integer.parseInt(request.getParameter("x")));
//		sumDTO.setY(Integer.parseInt(request.getParameter("y")));
//		mav.addObject("sumDTO", sumDTO);
//		mav.setViewName("/sum/result");
//		return mav;
//	}
	
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public ModelAndView result(@RequestParam int x, @RequestParam int y) {
//		ModelAndView mav = new ModelAndView();
//		sumDTO.setX(x);
//		sumDTO.setY(y);
//		mav.addObject("sumDTO", sumDTO);
//		mav.setViewName("/sum/result");
//		return mav;
//	}
	
	// int 형으로 받으면 입력안하면 numberformat exception 생기니까 String 으로 받고, required를 통해서 입력 안했을때 기본값 설정
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public ModelAndView result(@RequestParam(required=false, defaultValue="0") String x, @RequestParam(required=false, defaultValue="0") String y) {
//		ModelAndView mav = new ModelAndView();
//		sumDTO.setX(Integer.parseInt(x));
//		sumDTO.setY(Integer.parseInt(y));
//		mav.addObject("sumDTO", sumDTO);
//		mav.setViewName("/sum/result");
//		return mav;
//	}
	
//	@RequestMapping(value="/result.do", method=RequestMethod.GET)
//	public String result(@RequestParam Map<String, String> map, ModelMap modelMap) {	//map은 데이터 입력 ModelMap은 데이터 출력
//		modelMap.put("x", map.get("x"));
//		modelMap.put("y", map.get("y"));
//		return "/sum/result";
//	}
	
	@RequestMapping(value="/result.do", method=RequestMethod.GET)
	public String result(@ModelAttribute SumDTO sumDTO, Model model) {
		model.addAttribute("sumDTO", sumDTO);
		return "/sum/result";
	}
}
