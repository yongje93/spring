package sample05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.conf")
public class SungJukSort implements SungJuk {
	@Autowired
	private ArrayList<SungJukDTO2> list;
	
	@Override
	public void execute() {
		Collections.sort(list, new Comparator<SungJukDTO2>() {

			@Override
			public int compare(SungJukDTO2 s1, SungJukDTO2 s2) {
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
		for (SungJukDTO2 sungJukDTO2 : list) {
			System.out.println(sungJukDTO2);
		}
	}

}
