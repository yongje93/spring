package com.conf;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import sample03.SungJukDTO;
import sample03.SungJukImpl;
import sample05.HelloSpring;
import sample05.SungJukDTO2;
import sample05.SungJukDelete;
import sample05.SungJukInput;
import sample05.SungJukModify;
import sample05.SungJukOutput;
import sample05.SungJukSort;

@Configuration // Instance라는 클래스를 생성할 필요는 없다. 환경설정으로만 사용한다.
public class Instance { // 생성하는애들 모아놓음

	@Bean(name = "sungJukImpl") // 메소드의 리턴되는 값을 생성한다.
	public SungJukImpl getSungJukImpl() {
		return new SungJukImpl();
	}
	
	@Bean(name="sungJukDTO")
	public SungJukDTO getSungJukDTO(){
		return new SungJukDTO();
	}
	
	@Bean
	public HelloSpring helloSpring() {
		return new HelloSpring();
	}
	
	// sample05
	@Bean(name="list")
	public ArrayList<SungJukDTO2> getArrayList() {
		return new ArrayList<SungJukDTO2>();
	}

	@Bean(name="sungJukInput")
	@Scope("prototype")
	public SungJukInput getSungJukInput() {	//bean은 getSungJukInput이라는 이름으로 찾는다
		return new SungJukInput();
	}

	@Bean(name="sungJukOutput")
	public SungJukOutput getSungJukOutput() {
		return new SungJukOutput();
	}

	@Bean(name="sungJukModify")
	public SungJukModify getSungJukModify() {
		return new SungJukModify();
	}

	@Bean(name="sungJukDelete")
	public SungJukDelete getSungJukDelete() {
		return new SungJukDelete();
	}
	
	@Bean(name="sungJukSort")
	public SungJukSort getSungJukSort() {
		return new SungJukSort();
	}

}
