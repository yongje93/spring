package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // bean 생성도함
public class HelloController {
	@RequestMapping(value="/hello.do", method=RequestMethod.GET)
	public ModelAndView hello() {	// overload 안됨
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", "Hello Spring!!");
		mav.setViewName("/view/hello"); // dispatcher에 의해서 /view/hello.jsp로 설정됨
		return mav;
	}
	
}