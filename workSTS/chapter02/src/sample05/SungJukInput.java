package sample05;

import java.util.List;
import java.util.Scanner;

import lombok.Setter;

@Setter
public class SungJukInput implements SungJuk {
	private List<SungJukDTO> list;
	private SungJukDTO sungJukDTO;
	
	@Override
	public void execute() {
		// 데이터 입력
		Scanner scan = new Scanner(System.in);
		System.out.print("\n이름 입력 : ");
		String name = scan.next();

		// 이름 중복 확인
		for (SungJukDTO dto : list) {
			if(name.equals(dto.getName())) {
				System.out.println("\n이름이 중복 됩니다!");
				return;
			}
		}
		sungJukDTO.setName(name);
		System.out.print("국어 점수 입력 : ");
		sungJukDTO.setKor(scan.nextInt());
		System.out.print("영어 점수 입력 : ");
		sungJukDTO.setEng(scan.nextInt());
		System.out.print("수학 점수 입력 : ");
		sungJukDTO.setMath(scan.nextInt());
		// 총점 + 평균 계산
		sungJukDTO.setTot(sungJukDTO.getKor() + sungJukDTO.getEng() + sungJukDTO.getMath());
		sungJukDTO.setAvg(sungJukDTO.getTot() / 3.0);
		// 리스트에 저장
		list.add(sungJukDTO);
		
		System.out.println("\n총 "+list.size()+"개의 데이터가 저장되어 있습니다.");
	}

}
