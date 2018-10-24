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
		
		session.close();
		
		if(boardList != null){
			return boardList;
		}
		
		return null;
	}
	
	/**
	* Method : selectBoardListForBoardMaker
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	@Override
	public List<BoardVO> selectBoardListForBoardMaker(){

		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		List<BoardVO> boardList = session.selectList("boardSQL.selectBoardListForBoardMaker");
		
		session.close();
		if(boardList != null){
			return boardList;
		}
		
		return null;
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
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		String boardName = session.selectOne("boardSQL.getBoardName", boardCode);
		
		session.close();
		if(boardName != null){
			return boardName;
		}
		
		return null;
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
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		
		try {
			Integer nowBoardCode = session.selectOne("boardSQL.nowBoardCode");
			session.close();
			return nowBoardCode;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
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
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try {
			
			int result = session.insert("boardSQL.insertBoard", boardVo);
			session.commit();
			session.close();
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
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
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();

		SqlSession session = factory.openSession();

		try {

			int result = session.update("boardSQL.updateBoard", boardVo);
			session.commit();
			session.close();
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
