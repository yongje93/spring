package member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import member.bean.CustomUserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService ;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        CustomUserDetails member = (CustomUserDetails) customUserDetailsService.loadUserByUsername(id);
        
        if(!passwordEncoder.matches(password, member.getPassword())) {
        	throw new BadCredentialsException(id);
        }
        
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		// 사용자에게 권한 부여
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		// 스프링 시큐리티 내부 클래스로 인증 토큰 생성
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(id, password, roles);

		return result;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
