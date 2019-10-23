package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="boardWriteForm", method=RequestMethod.GET)
	public ModelAndView boardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/board/boardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="boardWrite", method=RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
		boardDTO.setId((String)session.getAttribute("memId"));
		boardDTO.setName((String)session.getAttribute("memName"));
		boardDTO.setEmail((String)session.getAttribute("memEmail"));
		
		boardService.boardWrite(boardDTO);
	}
	
	@RequestMapping(value="boardList", method=RequestMethod.GET)
	public String boardList(@RequestParam int pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardList.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="getBoardList", method=RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam int pg) {
		ModelAndView mav = new ModelAndView();
		
		int endNum = pg*5;
		int startNum = endNum - 4;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardDTO> boardList = boardService.boardList(map);
		mav.addObject("boardList", boardList);
		mav.setViewName("jsonView");
		return mav;
	}
}
