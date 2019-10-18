package user.service;

import java.util.List;

import user.bean.UserDTO;

public interface UserService {
	public void write(UserDTO userDTO);
	public List<UserDTO> getList();
	public UserDTO getUser(String id);
	public void modify(UserDTO userDTO);
	public void delete(String id);
	public boolean checkId(String id);
}
