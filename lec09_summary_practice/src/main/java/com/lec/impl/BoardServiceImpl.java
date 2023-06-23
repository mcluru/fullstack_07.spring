package com.lec.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.Service.BoardService;
import com.lec.domain.Board;
import com.lec.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;

	@Override
	public Page<Board> getBoardList(Pageable pageable, String searchType, String searchWord) {
		/*
		 	쿼리 메서드
		 	Like : 단순 like
		 	StartingWith : 키워드 + '%'
		 	EndingWith : '%' + 키워드
		 	Containing : '%' + 키워드 " '%'
		 */
		if(searchType.equalsIgnoreCase("title")) {
			return boardRepo.findByTitleContaining(searchWord, pageable);
		} else if(searchType.equalsIgnoreCase("writer")) {
			return boardRepo.findByWriterContaining(searchWord, pageable);
		} else {
			return boardRepo.findByContentContaining(searchWord, pageable);
		}

	}

}
