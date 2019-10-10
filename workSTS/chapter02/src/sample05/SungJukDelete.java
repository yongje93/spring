package sample05;

import java.util.List;
import java.util.Scanner;

import lombok.Setter;

@Setter
public class SungJukDelete implements SungJuk {
	private List<SungJukDTO> list;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("\n삭제할 이름 입력 : ");
		String name = scan.next();

		int sw = 0;
		// 이름 있나 확인
		for (SungJukDTO sungJukDTO : list) {
			if (name.equals(sungJukDTO.getName())) {
				list.remove(sungJukDTO);
				sw = 1;
			}
		}
		if(sw == 0) 
			System.out.println("\n삭제할 이름이 없습니다.");
		else {
			System.out.println("\n"+name+"님의 데이터를 삭제하였습니다.");
		}
	}

}
