package sample05;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.conf")
public class SungJukModify implements SungJuk {
	@Qualifier("list")
	@Autowired
	private List<SungJukDTO2> list;
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\n수정할 이름 입력 : ");
		String name = scan.next();

		int sw = 0;
		// 이름 있나 확인
		for (SungJukDTO2 sungJukDTO2 : list) {
			if (name.equals(sungJukDTO2.getName())) {
				System.out.println(sungJukDTO2);
				sw = 1;

				System.out.println("\n"+name+"님의 데이터를 수정합니다...");
				System.out.print("국어 점수 입력 : ");
				sungJukDTO2.setKor(scan.nextInt());
				System.out.print("영어 점수 입력 : ");
				sungJukDTO2.setEng(scan.nextInt());
				System.out.print("수학 점수 입력 : ");
				sungJukDTO2.setMath(scan.nextInt());
				// 총점 + 평균 계산
				sungJukDTO2.setTot(sungJukDTO2.getKor() + sungJukDTO2.getEng() + sungJukDTO2.getMath());
				sungJukDTO2.setAvg(sungJukDTO2.getTot() / 3.0);
			}
		}
		if(sw == 0) 
			System.out.println("\n수정할 이름이 없습니다.");
		else {
			System.out.println("\n"+name+"님의 데이터를 수정하였습니다.");
		}
	}

}
