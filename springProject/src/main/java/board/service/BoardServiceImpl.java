package board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void boardWrite(BoardDTO boardDTO) {
		boardDAO.boardWrite(boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return boardDAO.getBoardList(map);
	}

	@Override
	public int getTotalA() {
		return boardDAO.getTotalA();
	}

	@Override
	public BoardDTO getBoard(int seq) {
		return boardDAO.getBoard(seq);
	}

	@Override
	public List<BoardDTO> boardSearch(Map<String, String> map) {
		return boardDAO.boardSearch(map);
	}

	@Override
	public int getSearchTotalA(Map<String, String> map) {
		return boardDAO.getSearchTotalA(map);
	}

	@Override
	public void boardReply(BoardDTO boardDTO) {
		boardDAO.boardReply(boardDTO);
	}

	@Override
	public void boardModify(BoardDTO boardDTO) {
		boardDAO.boardModfiy(boardDTO);
	}

	@Override
	public void boardDelete(int seq) {
		boardDAO.boardDelete(seq);
	}
}
