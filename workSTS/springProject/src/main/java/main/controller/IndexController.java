package main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;

import kakao.controller.KakaoController;

@Controller
public class IndexController {
	
	@RequestMapping(value="/main/index", method=RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		// 카카오 url
		String kakaoUrl = KakaoController.getAuthorizationUrl(session);
		
		mav.addObject("display","/template/body.jsp");
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="/kakaoLogin", produces="application/json", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse respose, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 결과값을 node에 담아줌
		JsonNode node = KakaoController.getAccessToken(code);
		// accessToken에 사용자의 로그인한 모든 정보가 들어있음
		JsonNode accessToken = node.get("access_token");
		// 사용자 정보
		JsonNode userInfo = KakaoController.getKakaoUserInfo(accessToken);
		String id = null;
		String email = null;
		String name = null;
		String gender = null;
		String birthday = null;
		String image = null;
		// 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");
		id = userInfo.path("id").asText();
		email = kakao_account.path("email").asText();
		name = properties.path("nickname").asText();
		gender = kakao_account.path("gender").asText();
		birthday = kakao_account.path("birthday").asText();
		image = properties.path("profile_image").asText();
		
		System.out.println("id : " + id);
		System.out.println("email : " + email);
        System.out.println("name : " + name);
        System.out.println("gender : " + gender);
        System.out.println("birthday : " + birthday);
        
        session.setAttribute("memId", id);
        session.setAttribute("memName", name);
        session.setAttribute("access_token", accessToken);
        
        return new ModelAndView("redirect:/main/index");
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
	    KakaoController.kakaoLogout((String)session.getAttribute("access_token"));
	    session.invalidate();
	    return "index";
	}
}
