package post.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import board.vo.BoardVO;
import post.vo.PageVO;
import post.vo.PostVO;
import util.other.SQLFactoryBuilder;


public class PostDao implements IPostDao{
	private static IPostDao dao = null;
	
	private PostDao() {
	}
	
	public static IPostDao getInstance() {
		if(dao == null) {
			dao = new PostDao();
		}
		return dao;
	}
	
	/**
	* Method : nowPostNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 현재 게시물 번호를 알수 있다.
	*/
	@Override
	public int nowPostNumber() {
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		Integer nowPostCode = session.selectOne("postSQL.nowPostNumber");
		session.close();
		if(nowPostCode != null){
			return nowPostCode;
		}
		
		return -1;
	}

	/**
	* Method : inquiryUp
	* 작성자 : pc20
	* 변경이력 :
	* @param boardCode
	* @return
	* Method 설명 : 조회수 증가
	*/
	@Override
	public int inquiryUp(String postCode) {
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		int result = session.update("postSQL.inquiryUp", postCode);
		session.commit();
		session.close();
		
		if(result != -1){
			return result;
		}
		
		return -1;
	}

	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 게시물 불러오기
	*/
	@Override
	public List<PostVO> selectPostByPage(PageVO pageVo) {
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		List<PostVO> postList = session.selectList("postSQL.selectPostByPage", pageVo);
		session.close();
		if(postList != null){
			return postList;
		}
		
		return null;
	}
	
	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 가지고 게시물 불러오기
	*/
	@Override
	public PostVO selectPostByPostCode(String postCode) {
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try{
			PostVO postVo= session.selectOne("postSQL.selectPostByPostCode", postCode);
			session.close();
			return postVo;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	* 
	* Method : insertPost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 객체로 정보 넣어주기
	* 
	*/
	
	@Override
	public int insertPost(PostVO postVo){
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try{
			System.out.println(postVo);
			int result = session.insert("postSQL.insertPost", postVo);
			
			session.commit();
			session.close();
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : updatePost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 객체로 수정하기
	* 
	*/
	@Override
	public int updatePost(PostVO postVo){
		
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try{
			System.out.println(postVo);
			int result = session.insert("postSQL.updatePost", postVo);
			
			session.commit();
			session.close();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : deletePost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 이용해서 postavailable = 2 로 바꾸기
	* 
	*/
	@Override
	public int deletePost(String postCode){
		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try{
			System.out.println(postCode);
			int result = session.insert("postSQL.deletePost", postCode);
			
			session.commit();
			session.close();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : countPost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 총 현재 게시물 수를 구한다. 
	*/
	@Override
	public Integer countPost(String boardCode){

		SqlSessionFactory factory = SQLFactoryBuilder.getSqlSessionFactory();
		
		SqlSession session = factory.openSession();
		
		try{
			
			Integer count = session.selectOne("postSQL.countPost", boardCode);
			session.close();
			return count;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}
}
