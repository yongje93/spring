package sample05;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.conf")
public class SungJukOutput implements SungJuk {
	
	@Override
	public void execute() {
		
	}

}