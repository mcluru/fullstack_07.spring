package com.lec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lec.domain.Board;
import com.lec.persistence.BoardRepository;

//테스트클래스 설정 - 스프링 테스트를 위한 필수적인 어노테이션
@RunWith(SpringRunner.class) //JUnit에서 테스트 실행을 위해 SpringRunner클래스(스프링 테스트 컨텍스트를 로드하고 관리하는 역할)를 사용하겠다
@SpringBootTest	//스프링 부트 테스트에 사용되는 어노테이션.스프링 애플리케이션 컨텍스트를 로드하여 테스트 환경을 구성한다
public class BoardRepositoryTest {

	@Autowired // new BoardRepository() 수동생성대신에 자동생성후 주입
	private BoardRepository boardRepo;

//	@Test
//	public void testInsertBoard() {
//		
//		Board board = new Board();
//		board.setTitle("첫 번째 게시글");
//		board.setWriter("테스터");
//		board.setContent("잘 동작이 될까??");
//		board.setCreateDate(new Date());
//		board.setCnt(1L);
//		boardRepo.save(board); // insert into board...
//	}
	
//	@Test
//	public void testGetBoard() {
//		Board board = boardRepo.findById(2L).get();
//		System.out.println(board);
//	}
	
//	@Test
//	public void testUpdateBoard() {
//		System.out.println("=== 2번글 조회 ===");
//		Board board = boardRepo.findById(2L).get();
//		
//		System.out.println("=== 2번글 제목 수정 ===");
//		board.setTitle("제목을 수정함");
//		boardRepo.save(board);
//		System.out.println(boardRepo.findById(2L).get());
//		
//	}
	
//	@Test
//	public void testDeleteBoard() {
//		boardRepo.deleteById(2L);
//	}
	
//	@Test
//	public void testGetBoardList() {
////		System.out.println(boardRepo.findAll().iterator().toString());
//		Iterable<Board> boardIterable = boardRepo.findAll();
//		List<Board> boardList = new ArrayList<>();
//		boardIterable.forEach(boardList::add);
//		
//		for (Board board : boardList) {
//	        System.out.println(board); 
//	    }
//		
//	}
	
}