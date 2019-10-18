package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserDAO {
	public void write(UserDTO userDTO);
	public List<UserDTO> getList();
	public UserDTO getUser(String id);
	public void modify(UserDTO userDTO);
	public void delete(String id);
	public List<UserDTO> search(Map<String, String> map);
	public boolean isExistId(String id);
}
