package member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import member.bean.CustomUserDetails;
import member.dao.MemberDAO;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		CustomUserDetails member = memberDAO.getUserById(id);
		if(member == null) {
			throw new UsernameNotFoundException(id);
		}
		return member;
	}

}
