package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import user.bean.UserDTO;

@Repository("userDAO")	// Component와 같은 개념 Repository는 DB와의 연동을 한다는 것을 알려줌
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {

//	public UserDAOImpl(DataSource dataSource) {
//		setDataSource(dataSource);
//	}
	
	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	// NamedParameterJdbcDaoSupport를 상속하면 NamedParameterJdbcTemplate, JdbcTemplate를 준다. Setter로 JdbcTemplate 안받아도됨.
	@Override
	public void userWrite(UserDTO userDTO) {
		// String sql = "insert into usertable values(?,?,?)";
		// getJdbcTemplate().update(sql, userDTO.getName(), userDTO.getId(),
		// userDTO.getPwd()); //NamedParameterJdbcDaoSupport가 getJdbcTemplate 가짐

		String sql = "insert into usertable values(:name,:id,:pwd)"; // 이걸로 할려면 Map을 써야됨
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		getNamedParameterJdbcTemplate().update(sql, map); // 이름으로 매칭해서 sql 실행함
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql = "select * from usertable";
		// RowMapper -> DTO에 name, id, pwd를 mapping 한다. (같은 이름일 경우) 알아서 list에 담아옴
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
	}

	@Override
	public UserDTO getUser(String id) {
		String sql = "select * from usertable where id=:id";
		try {
			return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void userUpdate(Map<String, String> map) {
		String sql = "update usertable set name=:name, pwd=:pwd where id=:id";
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public void userDelete(String id) {
		String sql = "delete from usertable where id=:id";
		getJdbcTemplate().update(sql, id);
	}

}