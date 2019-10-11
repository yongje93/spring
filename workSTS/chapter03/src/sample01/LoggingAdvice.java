package sample01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

// 공통모듈(Aspect)
public class LoggingAdvice {
	public void beforeTrace() {
		System.out.println("before trace...");
	}
	
	public void afterTrace() {
		System.out.println("after trace...");
	}
	
	// around 방식
	public void trace(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().toShortString();
		System.out.println("methodName = " + methodName);
		
		StopWatch sw = new StopWatch();
		sw.start(methodName);
		System.out.println("호출 전 = " + methodName);
		
		// 핵심관심사항
		Object ob = joinPoint.proceed();	// proceed()로 핵심관심사항 호출
		System.out.println("ob = " + ob);
		
		sw.stop();
		System.out.println("호출 후 = " + methodName);
		System.out.println("처리시간 = " + sw.getTotalTimeMillis()/1000 + "초");
	}
}
