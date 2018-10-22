package test;

import static org.junit.Assert.*;

import java.util.List;

import login.service.ILoginService;
import login.service.LoginService;

import org.junit.Before;
import org.junit.Test;

import post.service.IPostService;
import post.service.PostService;
import post.vo.PostVO;

public class PostTest {
	
	private IPostService postService;
	
	@Before
	public void setup(){
		postService = PostService.getInstance(); 
	}
	
	/**
	* Method : nowPostNumberTest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 현재 게시물번호 최고 숫자 확인
	*/
	@Test
	public void nowPostNumberTest() {
		
		int postCode = postService.nowPostNumber();
		
		assertEquals(8, postCode);
	}
	
	
	/**
	* Method : inquiryUpTest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 조회수 증가
	*/
	@Test
	public void inquiryUpTest() {
		
		String postCode = "1";
				
		int result = postService.inquiryUp(postCode);
		
		assertEquals(1, result);
		
	}
	
	/**
	* Method : selectPostByPageTest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 게시물 뽑아오기
	*/
	@Test
	public void selectPostByPageTest() {
		
		List<PostVO> postList = postService.selectPostByPage("1", "1");
		
		assertEquals(8, postList.size());
	}
	
	/**
	* Method : postCountTest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 게시물 수 뽑아오기
	*/
	@Test
	public void postCountTest() {
		
		String boardCode = "1";
		
		Integer count = postService.countPost(boardCode);
		
		assertEquals((Integer)10, count);
	}
}
