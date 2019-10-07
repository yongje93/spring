package sample04;

import org.springframework.stereotype.Component;

@Component	// id와 클래스명이 같을땐 Component만 써도됨
public class CalcAdd implements Calc {

	@Override
	public void calculate(int x, int y) {
		System.out.println(x + " + " + y +" = " + (x+y));
	}

}
