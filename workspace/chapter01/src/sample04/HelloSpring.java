package sample04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Calc calc;
		calc = (Calc) context.getBean("calcAdd");
		calc.calculate(25, 36);
		
		calc = context.getBean("calcMul", Calc.class);	// 뒤에는 캐스팅 타입(부모)
		calc.calculate(25, 36);
		
		((AbstractApplicationContext)context).close();
	}

}
