package com.kh.mvc.member.model.vo;

import java.sql.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

//	@JsonIgnore
	private int userNo;
	
	private String userId;
	
	private String userPwd;
	
	private String userName;
	
	private String userRole;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String hobby;
	
	private String status;
	
	private Date enrollDate;
	
	private Date modifyDate;
	
	
	public Member(String id, String pwd, String name) {
		this.userId = id;
		this.userPwd = pwd;
		this.userName = name;
	}
	
	
}
