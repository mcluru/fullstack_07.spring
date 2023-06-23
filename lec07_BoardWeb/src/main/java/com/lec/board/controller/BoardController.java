package com.lec.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.board.domain.Board;
import com.lec.board.domain.Search;
import com.lec.board.security.SecurityUser;
import com.lec.board.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model, Board board) {
//		Page<Board> boardList = boardService.getBoard(board);
//		model.addAttribute("boardList", boardList);
//		return "board/getBoardList";
//	}

	@GetMapping("/getBoardList")
	public String getBoardList(Model model, Search search) {
		
		if(search.getSearchCondition() == null) search.setSearchCondition("TITLE");
		if(search.getSearchKeyword() == null) search.setSearchKeyword("");		
		
		Page<Board> boardList = boardService.getBoardList(search);
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	private String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "board/getBoard";
	}

	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "board/insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser pricipal) {
		board.setMember(pricipal.getMember());
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}	

}
