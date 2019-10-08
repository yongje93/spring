package sample02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CalcMul implements Calc {
	//private @Value("25") int x;
	//private @Value("36") int y;
	
	private int x, y;
	// setter, getter는 알아서 생성자 매핑이안된다.
	@Autowired	// setter일 때는 AutoWired를 통해 매핑해야됨
	public void setX(@Value("25") int x) {
		this.x = x;
	}
	
	@Autowired
	public void setY(@Value("36") int y) {
		this.y = y;
	}

	@Override
	public void calculate() {
		System.out.println(x + " * " + y + " = " + (x * y));
	}

}
