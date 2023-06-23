package com.lec;

import org.junit.jupiter.api.Test;	//version 5 (jUnit은 version4라 안먹힘)
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.lec.domain.Board;
import com.lec.domain.QBoard;
import com.lec.persistence.DynamicBoardRepository;
import com.querydsl.core.BooleanBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicQueryDslTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@Test
	public void testDynamicQuery() {
		
		String searchCondition = "TITLE";
		String searchKeyword = "테스트 제목 10";
		
		// BooleanBuilder객체는 가변적인 파라미터값에 따라서
		// 동적으로 and, or에 해당하는 조건 추가가 가능하도록 지원한다
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equalsIgnoreCase("title")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equalsIgnoreCase("content")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> boards = boardRepo.findAll(builder, paging);
		
		System.out.println("===> 검색결과");
		for(Board board:boards) {
			System.out.println("..." + board.toString());
		}
		
	}
	
}
