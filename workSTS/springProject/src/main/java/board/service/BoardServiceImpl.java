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
	public List<BoardDTO> boardList(Map<String, Integer> map) {
		return boardDAO.boardList(map);
	}
}
