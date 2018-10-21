package comment.service;

import java.util.List;

import comment.dao.CommentDao;
import comment.dao.ICommentDao;
import comment.vo.CommentVO;

public class CommentService implements ICommentService{
	private ICommentDao dao = null;
	private static ICommentService service = null;
	
	private CommentService() {
		dao = CommentDao.getInstance();
	}
	
	public static ICommentService getInstance() {
		if(service == null) {
			service = new CommentService();
		}
		
		return service;
	}
	
	/**
	* Method : selectPostCommentByPostCode
	* 작성자 : pc20
	* 변경이력 :
	* @return List<CommentVO> list 반환
	* Method 설명 : 해당 PostCode에 대한 댓글들을 불러온다.
	*/
	public List<CommentVO> selectPostCommentByPostCode(String postCode){
		return dao.selectPostCommentByPostCode(postCode);
	}
	
	/**
	* Method : nowPostCommentNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return Integer 반환
	* Method 설명 : 현재 CommentCode의 최신 게시물 번호를 반환한다. 
	*/
	@Override
	public Integer nowPostCommentNumber(){
		return dao.nowPostCommentNumber();
	}
	
	/**
	* Method : insertPostComment
	* 작성자 : pc20
	* 변경이력 :
	* @return int 반환
	* Method 설명 : 성공여부를 반환한다. sql 문제시 -1 , 성공할시 1
	*/
	@Override
	public int insertPostComment(CommentVO commentVo){
		
		//댓글에 대한 코드 최신번호 조회 후 + 1 해주기 
		int commentCode = nowPostCommentNumber()+1;
		commentVo.setCommentCode(commentCode);
		
		System.out.println("commentCode service => "+commentCode);
		
		return dao.insertPostComment(commentVo);
	}
	

}
