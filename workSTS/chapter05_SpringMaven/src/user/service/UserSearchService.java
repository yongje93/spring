package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.dao.UserDAO;

@Service
public class UserSearchService implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		String key = null;
		String value = null;
		
		System.out.println("\n1. 이름 검색");
		System.out.println("2. 아이디 검색");
		System.out.print("> 번호 입력 : ");
		int menu = scan.nextInt();
		
		System.out.println();
		if(menu == 1) {
			System.out.print("검색을 원하는 이름 입력 : ");
			key = "name";
			value = scan.next();
		} else if(menu == 2) {
			System.out.print("검색을 원하는 아이디 입력 : ");
			key = "id";
			value = scan.next();
		} else {
			System.out.println("1~2 중 하나만 입력하세요!");
			return;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("value", value);
		
		List<UserDTO> list = userDAO.userSearch(map);
		
		System.out.println("\n이름\t아이디\t비밀번호");
		for (UserDTO userDTO : list) {
			System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
		}
	}

}
