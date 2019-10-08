package sample05;

import org.springframework.context.annotation.ComponentScan;

import lombok.Getter;
import lombok.Setter;

@ComponentScan("com.conf")
@Getter
@Setter
public class SungJukDTO {
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
}
