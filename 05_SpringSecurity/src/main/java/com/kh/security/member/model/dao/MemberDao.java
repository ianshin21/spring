package com.kh.security.member.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.security.member.model.vo.Member;

@Mapper
public interface MemberDao {
	public Member selectUserInfoOne(@Param("userId") String userId);

}
