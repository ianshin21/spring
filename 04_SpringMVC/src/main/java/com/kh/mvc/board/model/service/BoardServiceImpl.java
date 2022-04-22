package com.kh.mvc.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Override
	@Transactional
	public int saveBoard(Board board) {
		int result = 0;
		
		if(board.getBoardNo() != 0) {
			result = boardDao.updateBoard(board);
		} else {
			result = boardDao.insertBoard(board);
		}
		return result;
	}

	@Override
	public int getBoardCount() {
		
		return boardDao.selectCount();
	}

	@Override
	public List<Board> getBoardList(PageInfo pageInfo) {		
		int offset = (pageInfo.getCurrentPage() - 1) * pageInfo.getListLimit();
		RowBounds rowBounds = new RowBounds(offset, pageInfo.getListLimit());
		
		return boardDao.selectBoardList(rowBounds);
	}

	@Override
	public Board findBoardByNo(int boardNo) {
		
		return boardDao.selectBoardDetail(boardNo);
	}

}
