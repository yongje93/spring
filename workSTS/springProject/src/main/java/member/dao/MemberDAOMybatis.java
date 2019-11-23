package member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.CustomUserDetails;
import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

@Repository
@Transactional
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void write(MemberDTO memberDTO) {
		sqlSession.insert("memberSQL.write", memberDTO);
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		sqlSession.update("memberSQL.update", memberDTO);
	}

	@Override
	public MemberDTO login(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.login", map);
	}

	@Override
	public MemberDTO getMember(String id) {
		return sqlSession.selectOne("memberSQL.getMember", id);
	}

	@Override
	public MemberDTO checkId(String id) {
		return sqlSession.selectOne("memberSQL.checkId", id);
	}

	@Override
	public List<ZipcodeDTO> getZipcodeList(Map<String, String> map) {
		return sqlSession.selectList("memberSQL.getZipcodeList", map);
	}
	
	// 해당 email에 인증 키 업데이트
	@Override
	public void createAuthKey(String email1, String key) {
		MemberDTO member = new MemberDTO();
		member.setMemberAuthKey(key);
		member.setEmail1(email1);
		sqlSession.update("memberSQL.createAuthKey", member);
	}
	
	// 이메일 인증 코드 확인
	@Override
	public MemberDTO chkAuth(MemberDTO member) {
		return sqlSession.selectOne("memberSQL.chkAuth", member);
	}
	
	// 인증 후 계정 활성화
	@Override
	public void userAuth(MemberDTO memberDTO) {
		sqlSession.update("memberSQL.userAuth", memberDTO);
	}
	
	@Override
	public CustomUserDetails getUserById(String username) {
		return sqlSession.selectOne("memberSQL.getUserById", username);
	}
}
