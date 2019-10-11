package sample05;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.conf")
public class SungJukInput implements SungJuk {
//	@Autowired
//	private ArrayList<SungJukDTO2> list;
	
	@Qualifier("list")
	@Autowired
	private List<SungJukDTO2> list;
	
	@Autowired
	private SungJukDTO2 sungJukDTO2;

	@Override
	public void execute() {
		// 데이터 입력
		Scanner scan = new Scanner(System.in);
		System.out.print("\n이름 입력 : ");
		String name = scan.next();

		// 이름 중복 확인
		for (SungJukDTO2 dto : list) {
			if (name.equals(dto.getName())) {
				System.out.println("\n이름이 중복 됩니다!");
				return;
			}
		}
		sungJukDTO2.setName(name);
		System.out.print("국어 점수 입력 : ");
		sungJukDTO2.setKor(scan.nextInt());
		System.out.print("영어 점수 입력 : ");
		sungJukDTO2.setEng(scan.nextInt());
		System.out.print("수학 점수 입력 : ");
		sungJukDTO2.setMath(scan.nextInt());
		// 총점 + 평균 계산
		sungJukDTO2.setTot(sungJukDTO2.getKor() + sungJukDTO2.getEng() + sungJukDTO2.getMath());
		sungJukDTO2.setAvg(sungJukDTO2.getTot() / 3.0);
		// 리스트에 저장
		list.add(sungJukDTO2);

		System.out.println("\n총 " + list.size() + "개의 데이터가 저장되어 있습니다.");

	}

}
