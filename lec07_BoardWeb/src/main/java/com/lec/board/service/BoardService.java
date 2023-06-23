package com.lec.board.service;

import org.springframework.data.domain.Page;

import com.lec.board.domain.Board;
import com.lec.board.domain.Search;

public interface BoardService {

	void insertBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(Board board);
	Board getBoard(Board board);
	Page<Board> getBoardList(Search search);
	// Page<Board> getBoardList(Board board);
}
