package member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public void write(MemberDTO memberDTO) {
		
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		
	}

	@Override
	public MemberDTO login(Map<String, String> map) {		
		return memberDAO.login(map);
	}

	@Override
	public boolean isExistId(String id) {
		return false;
	}

	@Override
	public MemberDTO getMember(String id) {
		return null;
	}
	
	
}
