package main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kakao.controller.KakaoApi;
import naver.controller.NaverLoginBO;

@Controller
public class IndexController {
	private NaverLoginBO naverLoginBO;
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@RequestMapping(value="/main/index", method=RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		// 카카오 url
		String kakaoUrl = KakaoApi.getAuthorizationUrl(session);
		
		// 네이버 url
		String naverUrl = naverLoginBO.getAuthorizationUrl(session);
		
		mav.addObject("display","/template/body.jsp");
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("/main/index");
		return mav;
	}
	
}
