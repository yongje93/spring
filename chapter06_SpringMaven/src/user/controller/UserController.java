package user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import user.bean.UserDTO;
import user.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/writeForm", method=RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
	@RequestMapping(value="/user/write", method=RequestMethod.POST)
	@ResponseBody // viewResolver에 영향을 받지 않게함. jsp의 뷰의 이름으로 인식 하지말라.
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}
	
	@RequestMapping(value="/user/list", method=RequestMethod.GET)
	public String list() {
		return "/user/list";
	}
	
//	@RequestMapping(value="/user/getList", method=RequestMethod.POST)
//	public ModelAndView getList() {
//		List<UserDTO> list = userService.getList();	// list를 json으로 변경 해야됨
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", list);
//		mav.setViewName("jsonView"); //servlet-context의 jsonView를 거쳐라. (viewResolver가 아니라)
//		return mav;
//	}
	
	@RequestMapping(value="/user/getList", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList() {
		List<UserDTO> list = userService.getList();	
		
		JSONArray jsonArray = JSONArray.fromObject(list); //fromObject - static 함수	// 이름이 없는 형태 [ {" "," "} ]
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", jsonArray);
		
		//System.out.println(map);
		return map;
	}
	
	@RequestMapping(value="/user/modifyForm", method=RequestMethod.GET)
	public String modifyForm() {
		return "/user/modifyForm";
	}
	
	@RequestMapping(value="/user/modify", method=RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute UserDTO userDTO) {
		userService.modify(userDTO);
	}

	@RequestMapping(value="/user/getUser", method=RequestMethod.POST)
	public ModelAndView getUser(@RequestParam String id) {
		UserDTO userDTO = userService.getUser(id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("userDTO", userDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/user/deleteForm", method=RequestMethod.GET)
	public String deleteForm() {
		return "/user/deleteForm";
	}
	
	@RequestMapping(value="/user/delete", method=RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestParam String id) {
		userService.delete(id);
	}
	
	@RequestMapping(value="/user/checkId", method=RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id) {
		UserDTO userDTO = userService.checkId(id);
		
		if(userDTO == null) {
			return "not_exist";
		} else {
			return "exist";
		}
	}
	
	@RequestMapping(value="/user/search", method=RequestMethod.POST)
	//public ModelAndView search(@RequestParam String option, @RequestParam String keyword) {
	public ModelAndView search(@RequestBody Map<String, String> map) {
		//System.out.println("option : " + option + " , keyword : " + keyword );
		//Map<String, String> map = new HashMap<String, String>();
		//map.put("option", option);
		//map.put("keyword", keyword);
		List<UserDTO> searchList = userService.search(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchList", searchList);
		mav.setViewName("jsonView");
		return mav;
	}
	
}
