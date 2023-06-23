package com.lec.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.domain.Board;

public interface BoardService {

	Page<Board> getBoardList(Pageable pageable, String searchType, String searchWord);

}
