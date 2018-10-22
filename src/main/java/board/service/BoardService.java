package board.service;

import java.util.List;

import board.dao.BoardDao;
import board.dao.IBoardDao;
import board.vo.BoardVO;


public class BoardService implements IBoardService{
	private IBoardDao dao = null;
	private static IBoardService service = null;
	
	private BoardService() {
		dao = BoardDao.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(service == null) {
			service = new BoardService();
		}
		
		return service;
	}

	/**
	* Method : selectBoardList
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	@Override
	public List<BoardVO> selectBoardList() {
		return dao.selectBoardList();
	}
	
	/**
	* Method : selectBoardListForBoardMaker
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	public List<BoardVO> selectBoardListForBoardMaker(){
		return dao.selectBoardListForBoardMaker();
	}
	
	
	/**
	* Method : getBoardName
	* 작성자 : pc20
	* 변경이력 :
	* @param boardCode
	* @return
	* Method 설명 : 게시판 이름 가져오기
	*/
	@Override
	public String getBoardName(Integer boardCode){
		return dao.getBoardName(boardCode);
	}
	
	/**
	* Method : nowBoardCode
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :현재 최근 boardcode를 얻어온다.
	*/
	@Override
	public Integer nowBoardCode(){
		return dao.nowBoardCode();
	}
	
	/**
	* Method : insertBoard
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :게시판을 만든다.
	*/
	@Override
	public int insertBoard(BoardVO boardVo){
		return dao.insertBoard(boardVo);
	}
	
	/**
	* Method : updateBoard
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :게시판을 수정한다.
	*/
	@Override
	public int updateBoard(BoardVO boardVo){
		return dao.updateBoard(boardVo);
	}
	
	
}
