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
	
	
}
