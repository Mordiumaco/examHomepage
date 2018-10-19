package board.service;

import java.util.List;

import board.vo.BoardVO;

public interface IBoardService {
	
	/**
	* Method : selectBoardList
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	public List<BoardVO> selectBoardList();
	
	
	/**
	* Method : getBoardName
	* 작성자 : pc20
	* 변경이력 :
	* @param boardCode
	* @return
	* Method 설명 : 게시판 이름 가져오기
	*/
	public String getBoardName(Integer boardCode);
}	
