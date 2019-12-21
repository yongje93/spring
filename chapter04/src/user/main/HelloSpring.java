package user.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.service.UserService;

public class HelloSpring {
	
	public void menu(ApplicationContext context) {
		Scanner scan = new Scanner(System.in);
		UserService userService = null;
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

			if (menu == 1) {
				userService = (UserService) context.getBean("userInsertService"); // 입력
			} else if (menu == 2) {
				userService = (UserService) context.getBean("userSelectService"); // 출력
			} else if (menu == 3) {
				userService = (UserService) context.getBean("userUpdateService"); // 수정
			} else if (menu == 4) {
				userService = (UserService) context.getBean("userDeleteService"); // 삭제
			} else {
				System.out.println("1~5 중 하나만 입력하세요!");
			}
			userService.execute();
		} // While
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		HelloSpring helloSpring = (HelloSpring) context.getBean("helloSpring");
		helloSpring.menu(context);
		System.out.println("\n프로그램을 종료합니다");
	}

}
