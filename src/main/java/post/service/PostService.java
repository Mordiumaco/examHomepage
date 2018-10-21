package post.service;

import java.util.List;

import post.dao.IPostDao;
import post.dao.PostDao;
import post.vo.PageVO;
import post.vo.PostVO;

public class PostService implements IPostService{
	private IPostDao dao = null;
	private static IPostService service = null;
	
	private PostService() {
		dao = PostDao.getInstance();
	}
	
	public static IPostService getInstance() {
		if(service == null) {
			service = new PostService();
		}
		
		return service;
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
		return dao.nowPostNumber();
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
		return dao.inquiryUp(postCode);
	}

	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 게시물 불러오기
	*/
	@Override
	public List<PostVO> selectPostByPage(String pageNumber, String boardCode) {
		
		PageVO pageVo = new PageVO();
		pageVo.setBoardCode(Integer.parseInt(boardCode));
		pageVo.setPageNumber(Integer.parseInt(pageNumber));
		pageVo.setPageSize(10);
		
		return dao.selectPostByPage(pageVo);
	}
	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 가지고 게시물 불러오기
	*/
	@Override
	public PostVO selectPostByPostCode(String postCode){
		return dao.selectPostByPostCode(postCode);
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
	public int insertPost(PostVO postVo){
		int postCode = service.nowPostNumber()+1;
		postVo.setPostCode(postCode);
		
		return dao.insertPost(postVo);
	}
}
