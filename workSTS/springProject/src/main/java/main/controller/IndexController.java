package main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kakao.controller.KakaoApi;

@Controller
public class IndexController {
	@RequestMapping(value="/main/index", method=RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		// 카카오 url
		String kakaoUrl = KakaoApi.getAuthorizationUrl(session);
		
		mav.addObject("display","/template/body.jsp");
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("/main/index");
		return mav;
	}
	
}
