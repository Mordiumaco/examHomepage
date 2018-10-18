package board.dao;

import java.util.List;

import board.vo.BoardVO;

public interface IBoardDao {
	
	/**
	* Method : selectBoardList
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	public List<BoardVO> selectBoardList();
	
}
