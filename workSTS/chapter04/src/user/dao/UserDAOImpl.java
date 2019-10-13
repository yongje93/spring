package user.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import lombok.Setter;
import user.bean.UserDTO;

public class UserDAOImpl implements UserDAO {
	@Setter
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void userWrite(UserDTO userDTO) {
		String sql = "insert into usertable values(?,?,?)";
		jdbcTemplate.update(sql, userDTO.getName(), userDTO.getId(), userDTO.getPwd());
	}

	@Override
	public UserDTO userSelect(String name) {
		String sql = "select * from usertable where name=?";
		return null;
	}

	@Override
	public void userUpdate(UserDTO userDTO) {
		String sql = "update usertable set id=?, pwd=? where name=?";
		
	}

	@Override
	public void userDelete(String name) {
		String sql = "delete from usertable where name=?";
	}
}
