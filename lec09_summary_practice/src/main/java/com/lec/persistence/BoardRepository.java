package com.lec.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	Page<Board> findByTitleContaining(String searchWord, Pageable pageable);
	Page<Board> findByWriterContaining(String searchWord, Pageable pageable);
	Page<Board> findByContentContaining(String searchWord, Pageable pageable);

}
