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
	
	/**
	* Method : selectBoardListForBoardMaker
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	public List<BoardVO> selectBoardListForBoardMaker();
	
	/**
	* Method : getBoardName
	* 작성자 : pc20
	* 변경이력 :
	* @param boardCode
	* @return
	* Method 설명 : 게시판 이름 가져오기
	*/
	public String getBoardName(Integer boardCode);
	
	
	/**
	* Method : nowBoardCode
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :현재 최근 boardcode를 얻어온다.
	*/
	public Integer nowBoardCode();
	
	/**
	* Method : insertBoard
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :게시판을 만든다.
	*/
	public int insertBoard(BoardVO boardVo); 
	
	/**
	* Method : updateBoard
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :게시판을 수정한다.
	*/
	public int updateBoard(BoardVO boardVo); 
	
}