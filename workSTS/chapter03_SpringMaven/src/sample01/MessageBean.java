package sample01;

public interface MessageBean {
	// 조인포인트 
	public void showPrintBefore();	// pointcut
	public void viewPrintBefore();	// pointcut
	
	public void showPrintAfter();	// pointcut
	public void viewPrintAfter();	// pointcut
	
	public String showPrint(); // around
	public void viewPrint(); // around

	public void display();
}
