package sample05;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloSpring {

	public void menu(ApplicationContext context) {
		Scanner scan = new Scanner(System.in);
		SungJuk sungJuk = null;
		int menu;
		
		while (true) {
			System.out.println("\n******************");
			System.out.println("    1. 입력");
			System.out.println("    2. 출력");
			System.out.println("    3. 수정");
			System.out.println("    4. 삭제");
			System.out.println("    5. 정렬(총점으로 내림차순)");
			System.out.println("    6. 끝");
			System.out.println("******************");
			System.out.print("> 번호 입력 : ");
			menu = scan.nextInt();

			if (menu == 6) break;

			if (menu == 1) sungJuk = (SungJuk) context.getBean("sungJukInput");
			else if (menu == 2) sungJuk = (SungJuk) context.getBean("sungJukOutput");
			else if (menu == 3) sungJuk = (SungJuk) context.getBean("sungJukModify");
			else if (menu == 4) sungJuk = (SungJuk) context.getBean("sungJukDelete");
			else if (menu == 5) sungJuk = (SungJuk) context.getBean("sungJukSort");
			else System.out.println("1~6 중 하나만 입력하세요!");
			
			sungJuk.execute();
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");		
		HelloSpring helloSpring = (HelloSpring) context.getBean("helloSpring");
		helloSpring.menu(context);
	}

}
