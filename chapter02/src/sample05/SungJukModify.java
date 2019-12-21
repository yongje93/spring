package sample05;

import java.util.List;
import java.util.Scanner;

import lombok.Setter;

@Setter
public class SungJukModify implements SungJuk {
	private List<SungJukDTO> list;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\n수정할 이름 입력 : ");
		String name = scan.next();

		int sw = 0;
		// 이름 있나 확인
		for (SungJukDTO sungJukDTO : list) {
			if (name.equals(sungJukDTO.getName())) {
				System.out.println(sungJukDTO);
				sw = 1;

				System.out.println("\n"+name+"님의 데이터를 수정합니다...");
				System.out.print("국어 점수 입력 : ");
				sungJukDTO.setKor(scan.nextInt());
				System.out.print("영어 점수 입력 : ");
				sungJukDTO.setEng(scan.nextInt());
				System.out.print("수학 점수 입력 : ");
				sungJukDTO.setMath(scan.nextInt());
				// 총점 + 평균 계산
				sungJukDTO.setTot(sungJukDTO.getKor() + sungJukDTO.getEng() + sungJukDTO.getMath());
				sungJukDTO.setAvg(sungJukDTO.getTot() / 3.0);
			}
		}
		if(sw == 0) 
			System.out.println("\n수정할 이름이 없습니다.");
		else {
			System.out.println("\n"+name+"님의 데이터를 수정하였습니다.");
		}
	}

}
