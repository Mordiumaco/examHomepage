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
	
}
