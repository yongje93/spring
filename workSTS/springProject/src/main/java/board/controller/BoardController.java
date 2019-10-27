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
import board.bean.BoardPaging;
import board.service.BoardService;

@Controller
@RequestMapping(value="board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardPaging boardPaging;
	
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
	
	@RequestMapping(value="boardList", method=RequestMethod.GET)		// pg int로 받으면 numberformatException일어날수있음
	public String boardList(@RequestParam(required=false, defaultValue="1") String pg, Model model) {	//1페이지 일때는 주소로 안넣고, default를 1로함. 나머지 페이지는 주소에 써서보냄
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardList.jsp");
		return "/main/index"; 
	}
	
	@RequestMapping(value="getBoardList", method=RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required=false, defaultValue="1") String pg, HttpSession session) {		
		String memId = (String) session.getAttribute("memId");
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(pg) * 5;
		int startNum = endNum - 4;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardDTO> boardList = boardService.getBoardList(map);
		
		// 페이징 처리
		int totalA = boardService.getTotalA();
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memId", memId);
		mav.addObject("boardList", boardList);
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="boardView", method=RequestMethod.GET)
	public String boardView(@RequestParam String seq, @RequestParam String pg, Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/board/boardView.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="getBoardView", method=RequestMethod.POST)
	public ModelAndView getBoardView(@RequestParam String seq) {
		BoardDTO boardDTO  = boardService.getBoard(Integer.parseInt(seq));
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDTO", boardDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="boardSearch", method=RequestMethod.POST)
	public ModelAndView boardSearch(@RequestParam Map<String, String> map) {		
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		List<BoardDTO> list = boardService.boardSearch(map);
		
		// 페이징 처리
		int totalA = boardService.getSearchTotalA(map);
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		boardPaging.makeSearchPagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("opt", map.get("opt"));
		mav.addObject("condition", map.get("condition"));
		mav.addObject("boardPaging", boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="boardReplyForm", method=RequestMethod.POST)
	public ModelAndView boardReplyForm(@RequestParam int seq, @RequestParam int pg) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pseq", seq);
		mav.addObject("pg", pg);
		mav.addObject("display", "/board/boardReplyForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="boardReply", method=RequestMethod.POST)
	@ResponseBody
	public void boardReply(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
		boardDTO.setId((String)session.getAttribute("memId"));
		boardDTO.setName((String)session.getAttribute("memName"));
		boardDTO.setEmail((String)session.getAttribute("memEmail"));
		
		boardService.boardReply(boardDTO);
	}
	
	@RequestMapping(value="boardModifyForm", method=RequestMethod.POST)
	public ModelAndView boardModifyForm(@RequestParam int seq, @RequestParam int pg) {
		BoardDTO boardDTO = boardService.getBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDTO", boardDTO);
		mav.addObject("seq", seq);
		mav.addObject("pg", pg);
		mav.addObject("display", "/board/boardModifyForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	@RequestMapping(value="boardModify", method=RequestMethod.POST)
	@ResponseBody
	public void boardModify(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
		boardService.boardModify(boardDTO);
	}
	
	
	// summernote 테스트
	
}
