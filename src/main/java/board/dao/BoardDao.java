package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.other.SQLFactoryBuilder;
import board.vo.BoardVO;

public class BoardDao implements IBoardDao{
	private static IBoardDao dao = null;
	
	private BoardDao() {
	}
	
	public static IBoardDao getInstance() {
		if(dao == null) {
			dao = new BoardDao();
		}
		return dao;
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
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		List<BoardVO> boardList = session.selectList("boardSQL.selectBoardList");
		
		if(boardList != null){
			return boardList;
		}
		
		return null;
	}
	
	
}
