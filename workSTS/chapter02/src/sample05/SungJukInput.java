package sample05;

import java.util.Scanner;

import lombok.Setter;

@Setter
public class SungJukInput implements SungJuk {
	private SungJukDTO sungJukDTO;
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		sungJukDTO.setName(scan.next());
		System.out.print("국어 점수 입력 : ");
		
		System.out.print("영어 점수 입력 : ");
		
		System.out.print("수학 점수 입력 : ");
		
		
	}

}
