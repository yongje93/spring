package sample05;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {
	ArrayList<SungJukDTO> sungJukDTO = new ArrayList<SungJukDTO>();
	
	public void menu() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SungJuk sungJuk;
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.println("***************");
			System.out.println("   1. 입력");
			System.out.println("   2. 출력");
			System.out.println("   3. 수정");
			System.out.println("   4. 삭제");
			System.out.println("   5. 끝");
			System.out.println("***************");
			System.out.print("> 번호 입력 : ");
			int menu = scan.nextInt();

			if (menu == 1) {
				sungJuk = context.getBean("sungJukInput", SungJukInput.class);
			} else if (menu == 2) {
				
			} else if (menu == 3) {
				
			} else if (menu == 4) {
				
			} else if (menu == 5) {
				break;
			} else {
				System.out.println("1~5 중 하나만 입력하세요!");
			}
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloSpring helloSpring = context.getBean("helloSpring", HelloSpring.class);
		helloSpring.menu();
	}

}
