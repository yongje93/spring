package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HellpSpring {

	public static void main(String[] args) {
		// 스프링 설정 파일 - src/applicationContext.xml
		//ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 부모로 접근해야한다
		MessageBean bean = (MessageBean) context.getBean("messageBean");
		bean.sayHello("Spring");
		System.out.println();
		
		MessageBean bean2 = (MessageBean) context.getBean("messageBean");
		bean2.sayHello("Spring");
		System.out.println();
		
		MessageBean bean3 = (MessageBean) context.getBean("messageBean");
		bean3.sayHello("Spring");
		System.out.println();
		
		((AbstractApplicationContext) context).close();
	}

}
