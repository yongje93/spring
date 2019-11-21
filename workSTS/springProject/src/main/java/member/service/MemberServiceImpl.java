package member.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import main.controller.MailHandler;
import main.controller.TempKey;
import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Inject
    private JavaMailSender mailSender;
	@Inject
	private PasswordEncoder passwordEncdoer;
	
	@Override
	public void write(MemberDTO memberDTO) {
		memberDAO.write(memberDTO);
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		memberDAO.modify(memberDTO);
	}

	@Override
	public MemberDTO login(Map<String, String> map) {		
		return memberDAO.login(map);
	}

	@Override
	public MemberDTO getMember(String id) {
		return memberDAO.getMember(id);
	}

	@Override
	public MemberDTO checkId(String id) {
		return memberDAO.checkId(id);
	}

	@Override
	public List<ZipcodeDTO> getZipcodeList(Map<String, String> map) {
		return memberDAO.getZipcodeList(map);
	}

	@Override
	public void regist(MemberDTO memberDTO) throws Exception {
		System.out.println("서비스레지스");
		
		String encPassword = passwordEncdoer.encode(memberDTO.getPwd());
		memberDTO.setPwd(encPassword);
		
		memberDAO.write(memberDTO);
		System.out.println(memberDTO);
		
		// 인증키 생성
		String key = new TempKey().getKey(50, false);
		// 인증키 db 저장
		memberDAO.createAuthKey(memberDTO.getEmail1(), key);
		// 메일 전송
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[멘토맨 서비스 이메일 인증]");
		sendMail.setText(
				new StringBuffer().append("<h1>메일인증</h1>").append("<a href='http://localhost:8080/springProject/member/emailConfirm?email1=").append(memberDTO.getEmail1())
				.append("&memberAuthKey=").append(key).append("' target='_blank'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("yongje1211@gmail.com", "멘토맨 고객센터");
		sendMail.setTo(memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
		sendMail.send();
	}
	
	// 이메일 인증키 검증
	@Override
	public MemberDTO userAuth(MemberDTO memberDTO) {
		MemberDTO member = new MemberDTO();
		System.out.println(memberDTO + "member");
		member = memberDAO.chkAuth(memberDTO);
		
		if(member != null) {
			try {
				System.out.println(member + "member");
				memberDAO.userAuth(memberDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return member;
	}
}
