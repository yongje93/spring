package sample05;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Setter;

@Setter
public class SungJukSort implements SungJuk {
	private List<SungJukDTO> list;
	
	@Override
	public void execute() {
		
		Collections.sort(list, new Comparator<SungJukDTO>() {

			@Override
			public int compare(SungJukDTO s1, SungJukDTO s2) {
				if(s1.getTot() > s2.getTot()) {
					return -1;
				} else if(s1.getTot() < s2.getTot()) {
					return 1;
				} else {
					return 0;
				}
			}

		});
		
		System.out.println("\n이름\t국어\t영어\t수학\t총점\t평균");
		for (SungJukDTO sungJukDTO : list) {
			System.out.println(sungJukDTO);
		}
	}

}
