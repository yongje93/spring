package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardDTO;

@Transactional
@Repository
public class BoardDAOMybatis implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void boardWrite(BoardDTO boardDTO) {
		sqlSession.insert("boardSQL.boardWrite", boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("boardSQL.getBoardList", map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("boardSQL.getTotalA");
	}

	@Override
	public BoardDTO getBoard(int seq) {
		return sqlSession.selectOne("boardSQL.getBoard", seq);
	}

	@Override
	public List<BoardDTO> boardSearch(Map<String, Object> map) {
		return sqlSession.selectList("boardSQL.boardSearch", map);
	}

	@Override
	public int getSearchTotalA(Map<String, Object> map) {
		return sqlSession.selectOne("boardSQL.getSearchTotalA", map);
	}

}
