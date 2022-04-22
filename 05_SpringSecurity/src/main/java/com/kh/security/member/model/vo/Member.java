package com.kh.security.member.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

	private static final long serialVersionUID = -6385822650564866775L;

	private int userNo;
	
	private String userGuid;
	
	private String userId;
		
	private String userPwd;
	
	private String userRole;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String hobby;
	
	private String status;
	
	private Date enrollDate;
	
	private Date modifyDate;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(userRole));
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return this.userPwd;
	}

	@Override
	public String getUsername() {
		
		return this.userId;
	}

	@Override
	// 계정이 만료 되지 않았는가?
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	// 계정이 잠기지 않았는가?
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	// 패스워드가 만료되지 않았는가?
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	// 계정이 활성화 되었는가?
	public boolean isEnabled() {
		
		return this.status.equals("Y");
	}
}
