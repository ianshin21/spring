package com.kh.mvc.board.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int boardNo;
	
	private int boardWriteNo;
	
	private String userId;	
	
	private String boardTitle;
	
	private String boardContent;
	
	private int boardReadCount;
	
	private String status;
	
	private String boardOriginalFileName;
	
	private String boardRenamedFileName;
	
	private List<Reply> replies;
	
	private Date boardCreateDate;
}
