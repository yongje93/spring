package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardDAO {
	public void boardWrite(BoardDTO boardDTO);
	public List<BoardDTO> getBoardList(Map<String, Integer> map);
	public int getTotalA();
	public BoardDTO getBoard(int seq);
	public List<BoardDTO> boardSearch(Map<String, String> map);
	public int getSearchTotalA(Map<String, String> map);
	public void boardReply(BoardDTO boardDTO);
	public void boardModfiy(BoardDTO boardDTO);
	public void boardDelete(int seq);
}
