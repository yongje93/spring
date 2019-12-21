package sample04;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String phone;
	private Outputter outputter;	// 이름과 전화번호를 파일로 내보낼때 쓰는 Outputter

	public MessageBeanImpl(String name) {
		System.out.println("MessageBeanImpl 생성자");
		this.name = name;
	}

	public void setPhone(String phone) {
		System.out.println("setPhone 메소드");
		this.phone = phone;
	}

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
