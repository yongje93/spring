package member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import member.bean.CustomUserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        CustomUserDetails member = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        
        if(!passwordEncoder.matches(password, member.getPassword())) {
        	throw new BadCredentialsException(username);
        }
        
//        if(!member.isEnabled()) {
//            throw new BadCredentialsException(username);
//        }
        
        return new UsernamePasswordAuthenticationToken(username, password, member.getAuthorities());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
