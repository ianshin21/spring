package com.kh.mvc.member.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

//@Service (빈 ID 지정 )
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired // new 객체 생성 대신에 
	private MemberDao memberDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
//	@Autowired
//	private SqlSession session;

	@Override
	public Member login(String userId, String userPwd) {
		Member loginMember = this.findMemberByUserId(userId);
		
		System.out.println("passwordEncoder.encode() : " + passwordEncoder.encode(userPwd)); 
		
//		return loginMember != null && loginMember.getUserPwd().equals(userPwd) ? loginMember : null;
//		return loginMember != null && loginMember.getUserPwd().equals(passwordEncoder.encode(userPwd)) ? loginMember : null;
		return loginMember != null && 
				passwordEncoder.matches(userPwd, loginMember.getUserPwd()) ? loginMember : null;

		//user111 1234 최신애
		
//		return memberDao.selectMember(session, userId);
	}

	@Override
	@Transactional  //service에 (클래스 말고)메소드에 주로 붙인다. controller에는 붙이지 않는다. 
	public int saveMember(Member member) {
		int result = 0;
		
		if(member.getUserNo() != 0) {
			result = memberDao.updateMember(member);
			
		}else {
			member.setUserPwd(passwordEncoder.encode(member.getUserPwd()));
			
			result = memberDao.insertMember(member);
		}
		
//		if(true) {
//			throw new RuntimeException();
//		}
		
		return result;
	}

	@Override
	public boolean validate(String userId) {

		Member member = this.findMemberByUserId(userId);
		
		return member != null;
	}

	@Override
	public Member findMemberByUserId(String userId) {
	
		return memberDao.selectMember(userId);
	}

	@Override
	@Transactional
	public int deleteMember(String userId) {
		
		return memberDao.deleteMember(userId);
	}

	

}
