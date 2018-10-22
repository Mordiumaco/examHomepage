package post.service;

import java.util.List;

import post.vo.PageVO;
import post.vo.PostVO;

public interface IPostService {

	/**
	* Method : nowPostNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 현재 게시물 번호를 알수 있다.
	*/
	public int nowPostNumber();
	 
	
	/**
	* Method : inquiryUp
	* 작성자 : pc20
	* 변경이력 :
	* @param boardCode
	* @return
	* Method 설명 : 조회수 증가
	*/
	public int inquiryUp(String postCode);
	
	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 게시물 불러오기
	*/
	public List<PostVO> selectPostByPage(String pageNumber, String boardCode);
	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 가지고 게시물 불러오기
	*/
	public PostVO selectPostByPostCode(String postCode);
	
	
	/**
	* 
	* Method : insertPost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 객체로 정보 넣어주기
	* 
	*/
	public int insertPost(PostVO postVo);
	
	/**
	* Method : updatePost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 객체로 수정하기
	* 
	*/
	public int updatePost(PostVO postVo);
	
	/**
	* Method : deletePost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 이용해서 postavailable = 2 로 바꾸기
	* 
	*/
	public int deletePost(String postCode);
	
	/**
	* Method : countPost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 총 현재 게시물 수를 구한다. 
	*/
	public Integer countPost(String boardCode);
	
	
	/**
	* Method : totalPageNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 총 현재 페이지의 토탈 페이지를 구한다. 
	*/
	public int totalPageNumber(String boardCode);
	
}
