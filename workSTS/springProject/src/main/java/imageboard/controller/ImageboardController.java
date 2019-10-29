package imageboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value = "imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;

	@RequestMapping(value = "imageboardWriteForm", method = RequestMethod.GET)
	public ModelAndView imageboardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/imageboard/imageboardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	// name="img"가 한개일 경우
//	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO, @RequestParam MultipartFile img, Model model) {
//		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage";
//		String fileName = img.getOriginalFilename();
//		File file = new File(filePath, fileName);
//		
//		try {
//			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
//		imageboardDTO.setImage1(fileName);
//		
//		imageboardService.imageboardWrite(imageboardDTO);
//	}

	// name="img"가 2개이상일 경우
//	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO, @RequestParam MultipartFile[] img, Model model) {
//		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage";
//		String fileName;
//		File file;
//		
//		if(img[0] != null) {
//			fileName = img[0].getOriginalFilename();
//			file = new File(filePath, fileName);
//
//			try {
//				FileCopyUtils.copy(img[0].getInputStream(), new FileOutputStream(file));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}	
//			imageboardDTO.setImage1(fileName);
//		} else {
//			imageboardDTO.setImage1(null);
//		}
//		
//		if(img[1] != null) {
//			fileName = img[1].getOriginalFilename();
//			file = new File(filePath, fileName);
//
//			try {
//				FileCopyUtils.copy(img[1].getInputStream(), new FileOutputStream(file));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}	
//			imageboardDTO.setImage2(fileName);
//		} else {
//			imageboardDTO.setImage2(null);
//		}
//		
//		imageboardService.imageboardWrite(imageboardDTO);
//	}

	// 드래그 해서 한번에 여러개의 파일을 선택했을 때
	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO, @RequestParam("img[]") List<MultipartFile> list, Model model) {
		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage";
		
		for(MultipartFile img : list) {
			String fileName = img.getOriginalFilename();
			File file = new File(filePath, fileName);
			
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageboardDTO.setImage1(fileName);
			imageboardDTO.setImage2("");
			
			//DB
			imageboardService.imageboardWrite(imageboardDTO);		
		}//for
	}
}