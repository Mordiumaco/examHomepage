package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import board.service.BoardService;
import board.service.IBoardService;
import board.vo.BoardVO;

public class BoardTest {
	
	private IBoardService boardService;
	
	@Before
	public void setup(){
		boardService = BoardService.getInstance();
	}
	
	/**
	* Method : selectBoardLisTest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 게시물 호출 테스트(게시물이 잘 불러와지는지 확인) 
	*/
	@Test
	public void selectBoardLisTest() {
		
		List<BoardVO> boardList = boardService.selectBoardList();
		
		assertEquals(3, boardList.size());
	}
	
	@Test
	public void getBoardNameTest() {
		
		Integer boardCode = 1;
		String boardName = boardService.getBoardName(boardCode);
		
		
		assertEquals("자유 게시판", boardName);
	}

}
