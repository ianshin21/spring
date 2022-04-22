package com.kh.mvc.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public ModelAndView list(
			ModelAndView model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "listLimit", required = false, defaultValue = "10") int listLimit) {
		
		List<Board> list = null;
		int boardCount = service.getBoardCount();
		PageInfo pageInfo = new PageInfo(page, 10, boardCount, listLimit);
		
		System.out.println(boardCount);
		
		list = service.getBoardList(pageInfo);
		
		model.addObject("list", list);
		model.addObject("pageInfo", pageInfo);
		model.setViewName("board/list");
		
		return model;
	}

	//컨트롤러 메소드의 리턴 타입이 void인 경우 Mapping URL을 유추해서 View를 찾는다. 
	@RequestMapping(value="/write", method={RequestMethod.GET})
	public void writeView() {
//		public String writeView() {
		
//		return "/board/write";
	}
	
	@RequestMapping(value="/write", method={RequestMethod.POST})
	public ModelAndView write(
			@SessionAttribute(name="loginMember", required=false) Member loginMember,
			HttpServletRequest request, 
			Board board,
			@RequestParam("upfile") MultipartFile upfile, ModelAndView model) {
//  	@ModelAttribute Board board, 어노테이션 생략 가능(하지만 적어주는 것이 좋다)
//		첨부파일이 여러개일 경우		
//		Board board, @RequestParam("upfile") MultipartFile[] upfile, ModelAndView model) {
//		System.out.println(upfile[0].getOriginalFilename());
//		System.out.println(upfile[1].getOriginalFilename());		
		
		System.out.println(board);
//		파일을 넣지 않으면 ==> ""
//		파일을 넣으면 ==> "파일명"
		System.out.println(upfile.getOriginalFilename());
		
		int result = 0;
		
		if (loginMember.getUserId().equals(board.getUserId())) {
			board.setBoardWriteNo(loginMember.getUserNo());
			
			if(upfile != null && !upfile.isEmpty()) {
				//파일 저장하는 로직 작성
				String renameFileName = saveFile(upfile, request);
				
				System.out.println(renameFileName);
				
				if(renameFileName != null) {
					board.setBoardOriginalFileName(upfile.getOriginalFilename());
					board.setBoardRenamedFileName(renameFileName);
				}
			}
			
			result = service.saveBoard(board);
			
			if(result > 0) {
				model.addObject("msg", "게시글이 정상적으로 등록되었습니다.");
				model.addObject("location", "/board/list");
			} else {
				model.addObject("msg", "게시글 등록을 실패하였습니다.");
				model.addObject("location", "/board/list");
			}
			
		}else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/");
		}
		
		

//		model.setViewName("board/write");
		model.setViewName("common/msg");
		
		return model;
		
	}
	
	
	@RequestMapping(value = "/view", method = {RequestMethod.GET})
	public ModelAndView view(@RequestParam("boardNo") int boardNo, ModelAndView model) {
		Board board = service.findBoardByNo(boardNo);
		
		System.out.println(board);
		
		model.addObject("board", board);
		model.setViewName("board/view");
		
		return model;
	}
	
	/*
	 * HttpEntity
	 * 	- SpringFramework에서 제공하는 클래스로 HTTP 요청 또는 응답에 해당하는 HTTP header와 HTTP Body를 포함하는 클래스이다. 
	 * 	- RequestEntity ResponseEntity 가 있다. 
	 * 
	 * ResponseEntity
	 * 	- HttpEntity를 상속하는 클래스로 HTTP 응답 데이터를 포함하는 클래스이다. 개발자가 직접 HTTP Body, Header, Status, code를 세팅하여 반환할 수 있다. 
	 * 	- 기본적으로 컨트롤로의 리턴값은 View의 이름을 반환함으로써 ViewResolver에서 해당 View를 렌더링하게 되는데
	 * 		컨트롤러에서 ResponseEntity를 리턴하게되면 View에 대한 정보가 아니라 HTTP 정보를 반환하게 된다.
	 * 	- 해당 객체를 반환해주면 클라이언트 측에서 HTTP 정보를 받을 수 있다.
	 * 
	 * @ResponseBody와의 차이점 
	 * 	-  @ResponseBody나 ResponseEntity를 리턴하는 것은 결과적으로 같은 기능이지만 구현 방법이 다르다. 
	 * 	- header 값을 변경시켜야 할 경우엔 @ResponseBody의 경우 파라미터로 Response 객체를 받아서 이 객체에서 header를 변경시켜야 한다. 
	 * 	- ResponseEntity에서는 객체를 생성한 뒤 객체에서 header 값을 변경시키면 된다. 
	 */
	
	
	/**
	 * @param oriname
	 * @param rename
	 * @param header
	 * @return
	 */
	@RequestMapping(value = "/fileDown", method = {RequestMethod.GET})
	public ResponseEntity<Resource> fileDown(
			@RequestParam("oriname") String oriname, @RequestParam("rename") String rename,
			@RequestHeader(name="user-agent") String header){
		
		try {
			
			Resource resource = resourceLoader.getResource("resources/upload/board/" + rename);
			File file = resource.getFile();
			boolean isMSIE = header.indexOf("Trident") != -1 || header.indexOf("MSIE") != -1;
			String encodeRename = "";
			
//			if(!file.exists()) {
//				return ResponseEntity.badRequest().build();
//			}
			
			if(isMSIE) {
				encodeRename = URLEncoder.encode(oriname, "UTF-8");
				encodeRename = encodeRename.replaceAll("\\+", "%20");
				
			}else {
				encodeRename = new String(oriname.getBytes("UTF-8"), "ISO-8859-1");	
			}
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + oriname + "\"")
					.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
					.body(resource);
					
		} catch (IOException e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.GET})
	public ModelAndView updateView(@RequestParam("boardNo") int boardNo, ModelAndView model) {
		Board board = service.findBoardByNo(boardNo);
		
		model.addObject("board", board);
		model.setViewName("board/update");
		
		return model;
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	public  ModelAndView update(
					@SessionAttribute(name = "loginMember", required = false) Member loginMember,
					@RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request,
					Board board, ModelAndView model) {
		
		int result = 0;
		
		if(loginMember.getUserId().equals(board.getUserId())) {
			
			if(reloadFile != null && !reloadFile.isEmpty()) {
				if(board.getBoardRenamedFileName() !=null) {
					// 기존에 저장된 파일 삭제하고 다시 파일 업로드
					deleteFile(board.getBoardRenamedFileName(), request);
				}
				
				String renameFileName = saveFile(reloadFile, request);
				
				if(renameFileName !=null) {
					board.setBoardOriginalFileName(reloadFile.getOriginalFilename());
					board.setBoardRenamedFileName(renameFileName);
				}
			}
			
			result = service.saveBoard(board);
			
			if(result > 0) {
				model.addObject("msg", "게시글이 정상적으로 수정되었습니다.");
				model.addObject("location", "/board/update?boardNo=" + board.getBoardNo());
			}else {
				model.addObject("msg", "게시글 수정이 실패하였습니다.");
				model.addObject("location", "/board/list");
			}
			
		}else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}
	
	private void deleteFile(String fileName, HttpServletRequest request) {
		String rootPath = request.getSession().getServletContext().getRealPath("resources");
		String savePath = rootPath + "/upload/board";
		
		log.debug("Root Path : " + rootPath);
		log.debug("Save Path : " + savePath);
		
		File file = new File(savePath + "/" + fileName);
		
		if(file.exists()) {
			file.delete();
		}		
	}

	private String saveFile(MultipartFile file, HttpServletRequest request) {
		String renamePath = null; 
		String originalFileName = null;
		String renameFileName = null;
		String rootPath = request.getSession().getServletContext().getRealPath("resources");
		String savePath = rootPath + "/upload/board";
		
				
		log.debug("Root Path : " + rootPath);
		log.debug("Save Path : " + savePath);
		
		//savePath가 실제로 존재하지 않으면 폴더를 생성하는 로직
		File folder = new File(savePath);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		originalFileName = file.getOriginalFilename();
		renameFileName = 
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS")) +
				originalFileName.substring(originalFileName.lastIndexOf("."));
		
		renamePath = savePath + "/" + renameFileName;
		
		try {
			// 업로드 한 파일 데이터를 지정한 파일에 저장한다. 
			file.transferTo(new File(renamePath));
		}  catch (IOException e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
			e.printStackTrace();
		}
		
		return renameFileName;
	}
}
