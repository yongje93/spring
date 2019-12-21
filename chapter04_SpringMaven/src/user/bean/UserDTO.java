package user.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component	// 일반 클래스니까 Component
public class UserDTO {
	private String name;
	private String id;
	private String pwd;
}
