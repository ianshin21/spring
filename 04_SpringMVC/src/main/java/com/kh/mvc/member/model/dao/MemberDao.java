package com.kh.mvc.member.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.mvc.member.model.vo.Member;

//@Repository
@Mapper
public interface MemberDao {

	// 어노테이션 바꾸고 interface로 만들고
	//root-context.xml에 mapper 빈 설정 + mapper에 namespace 설정 
	// MemberServiceImpl에서 private SqlSession session; 세션 삭제
	// mapper에 있는 select(쿼리문) id와 동일한 이름의 추상메소드
	// @Param("id")는 쿼리문의 파라미트와 동일
	Member selectMember(@Param("id") String id);

	//@Mapper가 읽어서 insertMember라는 id를 가진 select문을 찾는다.
	int insertMember(Member member);

	int updateMember(Member member);

	int deleteMember(@Param("id") String id);

}
