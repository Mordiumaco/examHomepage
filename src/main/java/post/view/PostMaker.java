package post.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.service.IPostService;
import post.service.PostService;
import post.vo.PostVO;
import login.vo.UserVO;

@WebServlet("/postMaker")
public class PostMaker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
		String postCon = request.getParameter("postCon");
		String postName = request.getParameter("postName");
		String boardCode = request.getParameter("boardCode");
		String postRefer = request.getParameter("postRefer");
		
		//----테스트
		
		System.out.println(postCon);
		System.out.println(postName);
		System.out.println("boardCode"+boardCode);
		System.out.println("postRefer"+postRefer);
		
		//-----
		
		
		if(userVo == null){
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('먼저 로그인을 해주세요.');");
			script.println("location.href = 'login.jsp';");
			script.println("</script>");
			
		}
		
		IPostService postService = PostService.getInstance();
		
		PostVO postVo = new PostVO();
		postVo.setBoardCode(boardCode);
		postVo.setPostCon(postCon);
		postVo.setPostName(postName);
		postVo.setUserId(userVo.getUserId());
		
		if(postRefer == null || "".equals(postRefer)){
			postVo.setPostRefer(null);
		}
		
		postVo.setPostRefer(postRefer);
		
		
		int result = postService.insertPost(postVo);
		
		if(result == -1){
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('DB에서 오류가 발생하였습니다.');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			
		}else{
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('성공적으로 게시물이 만들어졌습니다.');");
			script.println("location.href = '/boardPageList?boardCode="+boardCode+"&pageNumber=1';");
			script.println("</script>");
			
		}
		
		
		
		
	}

}
