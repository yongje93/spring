package member.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.Data;

@Data
public class LoginFailureHandler implements AuthenticationFailureHandler {
	private String loginIdName;
	private String loginPwdName;
	private String errorMsgName;
	private String defaultFailureUrl;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter(loginIdName);
		String password = request.getParameter(loginPwdName);
		String errormsg = "아이디나 비밀번호를 잘못 입력했습니다.";
		
		request.setAttribute(loginIdName, username);
		request.setAttribute(loginPwdName, password);
		request.setAttribute(errorMsgName, errormsg);
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}

}
