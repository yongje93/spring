package sample04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageBeanImpl2 implements MessageBean {
	private String name;
	private String phone;
	private Outputter outputter; // 이름과 전화번호를 파일로 내보낼때 쓰는 Outputter

	public MessageBeanImpl2(@Value("홍길동") String name) {
		System.out.println("MessageBeanImpl 생성자");
		this.name = name;
	}
	
	@Autowired
	public void setPhone(@Value("010-1234-5678") String phone) {
		System.out.println("setPhone 메소드");
		this.phone = phone;
	}

	@Autowired
	public void setOutputter(Outputter outputter) {
		System.out.println("setOutputter 메소드");
		this.outputter = outputter;
	}

	@Override
	public void helloCall() {
		System.out.println("helloCall 메소드");
		outputter.output("이름 = " + name + "\t 핸드폰 = " + phone);

	}

}
