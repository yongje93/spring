package imageboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value="imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;
	
	@RequestMapping(value="imageboardWriteForm", method=RequestMethod.GET)
	public ModelAndView imageboardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display","/imageboard/imageboardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
}
