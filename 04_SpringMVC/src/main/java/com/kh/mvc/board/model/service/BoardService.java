package com.kh.mvc.board.model.service;

import java.util.List;

import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;

public interface BoardService {

	int saveBoard(Board board);

	int getBoardCount();

	List<Board> getBoardList(PageInfo pageInfo);

	Board findBoardByNo(int boardNo);

}
