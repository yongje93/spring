package imageboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageboardMybatis implements ImageboardDAO {
	@Autowired
	private SqlSession sqlSession;
	
}
