package sample05;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {
	//private List<SungJukDTO> list = new ArrayList<SungJukDTO>();
	
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
			System.out.println("    5. 끝");
			System.out.println("******************");
			System.out.print("> 번호 입력 : ");
			menu = scan.nextInt();

			if (menu == 5) break;

			if (menu == 1) sungJuk = context.getBean("sungJukInput", SungJuk.class);
			else if (menu == 2) sungJuk = context.getBean("sungJukOutput", SungJuk.class);
			else if (menu == 3) sungJuk = context.getBean("sungJukModify", SungJuk.class);
			else if (menu == 4) sungJuk = context.getBean("sungJukDelete", SungJuk.class);
			else System.out.println("1~5 중 하나만 입력하세요!");
			
			sungJuk.execute();
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloSpring helloSpring = context.getBean("helloSpring", HelloSpring.class);
		helloSpring.menu(context);
		System.out.println("\n프로그램을 종료합니다.");
	}

}
