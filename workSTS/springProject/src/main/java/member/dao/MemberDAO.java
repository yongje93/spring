package member.dao;

import java.util.Map;

import member.bean.MemberDTO;

public interface MemberDAO {
	public void write(MemberDTO memberDTO);
	public void modify(MemberDTO memberDTO);
	public MemberDTO login(Map<String, String> map);
	public boolean isExistId(String id);
	public MemberDTO getMember(String id);
}
