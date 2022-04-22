package com.kh.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kh.security.member.model.dao.MemberDao;
import com.kh.security.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private MemberDao memberDao;	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberDao.selectUserInfoOne(username);
		
		if(member == null) {
			 throw new UsernameNotFoundException("username " + username + " not found");
		}

		log.info("User ID : {}", member.getUsername());
		
		return member;
	}

}
