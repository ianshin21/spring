package com.kh.mvc.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.mvc.board.model.vo.Board;

@Mapper
public interface BoardDao {

	int selectCount();

	List<Board> selectBoardList(RowBounds rowBounds);

	Board selectBoardDetail(int boardNo);
	
	int updateBoard(Board board);
	
	int insertBoard(Board board);


}
