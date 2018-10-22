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
		return dao.insertPost(postVo);
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
		return dao.updatePost(postVo);
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
		return dao.deletePost(postCode);
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
		return dao.countPost(boardCode);
	}
	
	
	/**
	* Method : totalPageNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 총 현재 페이지의 토탈 페이지를 구한다. 
	*/
	@Override
	public int totalPageNumber(String boardCode){
		Integer count = countPost(boardCode);
		
		int totalPage = count%10==0 ? count/10 : count/10+1;
				
		return totalPage;
	}
	
}
