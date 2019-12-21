package sample05;

import java.util.List;

import lombok.Setter;

@Setter
public class SungJukOutput implements SungJuk {
	private List<SungJukDTO> list;
	
	@Override
	public void execute() {
		System.out.println("\n이름\t국어\t영어\t수학\t총점\t평균");
		for (SungJukDTO sungJukDTO : list) {
			System.out.println(sungJukDTO);
		}
	}

}