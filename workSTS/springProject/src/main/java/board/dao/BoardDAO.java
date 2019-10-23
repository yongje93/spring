package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardDAO {

	public void boardWrite(BoardDTO boardDTO);

	public List<BoardDTO> boardList(Map<String, Integer> map);

}
