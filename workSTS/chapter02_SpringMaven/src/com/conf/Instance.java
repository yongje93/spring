package com.conf;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample03.SungJukDTO;
import sample03.SungJukImpl;
import sample05.HelloSpring;
import sample05.SungJukDelete;
import sample05.SungJukInput;
import sample05.SungJukModify;
import sample05.SungJukOutput;

@Configuration // Instance라는 클래스를 생성할 필요는 없다. 환경설정으로만 사용한다.
public class Instance { // 생성하는애들 모아놓음

	@Bean(name = "sungJukImpl") // 메소드의 리턴되는 값을 생성한다.
	public SungJukImpl getSungJukImpl() {
		return new SungJukImpl();
	}

	@Bean(name = "sungJukDTO")
	public SungJukDTO getSungJukDTO() {
		return new SungJukDTO();
	}

	@Bean
	public ArrayList<SungJukDTO> array() {
		return new ArrayList<SungJukDTO>();
	}

	@Bean
	public SungJukInput getSungJukInput() {
		return new SungJukInput();
	}

	@Bean
	public SungJukOutput getSungJukOutput() {
		return new SungJukOutput();
	}

	@Bean
	public SungJukModify getSungJukModify() {
		return new SungJukModify();
	}

	@Bean
	public SungJukDelete getSungJukDelete() {
		return new SungJukDelete();
	}
	
	@Bean
	public HelloSpring getHelloSpring() {
		return new HelloSpring();
	}

}
