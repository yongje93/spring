package user.dao;

import user.bean.UserDTO;

public interface UserDAO {
	
	public void userWrite(UserDTO userDTO);
	
	public UserDTO userSelect(String name);
	
	public void userUpdate(UserDTO userDTO);
	
	public void userDelete(String name);
}
