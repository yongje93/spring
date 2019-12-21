package member.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.Data;
import member.bean.MemberDTO;
import member.service.MemberService;

@Data
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private MemberService memberService;
	
	private static int TIME = 60 * 60 * 24; // 하루 
	private String defaultUrl;

	private RequestCache reqCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		MemberDTO memberDTO = memberService.getMember(authentication.getName());
		System.out.println(memberDTO);
		HttpSession session = request.getSession();
		session.setAttribute("memId", memberDTO.getId());
		session.setAttribute("memName", memberDTO.getName());
		session.setAttribute("memEmail", memberDTO.getEmail1());
		session.setMaxInactiveInterval(TIME);
		resultRedirectStrategy(request, response, authentication);

	}

	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SavedRequest savedRequest = reqCache.getRequest(request, response);

		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(request, response, targetUrl);
		} else {
			redirectStratgy.sendRedirect(request, response, defaultUrl);
		}

	}

}
