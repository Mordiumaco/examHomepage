package post.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.vo.UserVO;
import comment.service.CommentService;
import comment.service.ICommentService;
import comment.vo.CommentVO;
import post.service.IPostService;
import post.service.PostService;
import post.vo.PostVO;

@WebServlet(urlPatterns={"/postDetail","/postCommentInsert","/postSend"})
public class PostS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 uri로 로직 분기
		String uri = request.getRequestURI();
		System.out.println("userServlet doGet => " +uri);
		
		//게시판 조회
		if(uri.equals("/postDetail")){
			postDetail(request, response);
		}else if(uri.equals("/postSend")){
			
			postSend(request, response);
			
		}else if(uri.equals("/postCommentInsert")){
			
			//post 메서드 방식으로 들어가있으니까 doPost로 전환해준다. 
			doPost(request, response);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		postCommentInsert(request, response);
	}



	private void postCommentInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String postCode = request.getParameter("postCode");
		String commentCon =  request.getParameter("commentCon");
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
		
		if(userVo == null){
			//세션에 아이디가 없는경우
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('먼저 로그인을 해주세요.');");
			script.println("location.href = 'login.jsp';");
			script.println("</script>");
		}
		
		//댓글 객체에 정보 집어 넣어주기
		CommentVO commentVo = new CommentVO();
		commentVo.setCommentCon(commentCon);
		commentVo.setPostCode(Integer.parseInt(postCode));
		commentVo.setUserId(userVo.getUserId());
		
		ICommentService commentService = CommentService.getInstance();
		int insertResult = commentService.insertPostComment(commentVo);
		
		
		if(insertResult == -1){
			//db에서 문제가 발생할 경우
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('댓글 달기에 실패하였습니다.');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			
		}
		
		//다시 게시물 조회 메서드를 통해 게시물로 돌아간다. 
		postDetail(request, response);
		
	}
	
	
	
	private void postDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시물 코드가 파라미터로 넘어옴 
		String postCode = request.getParameter("postCode");
		
		//게시물 코드로 게시물 객체를 받아온다.(postVO)
		IPostService postSerivce = PostService.getInstance();
		
		PostVO postVo = postSerivce.selectPostByPostCode(postCode);
		System.out.println(postVo);
		if(postVo == null){
			//게시물이 없는경우
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('존재하는 게시물이 없습니다.');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			
			
		}else{
			//댓글에 대한 리스트
			ICommentService commentService = CommentService.getInstance();
			List<CommentVO> commentList = commentService.selectPostCommentByPostCode(postCode);
			
			System.out.println(commentList);
			request.setAttribute("commentList", commentList);
			
			//포스트 자체 정보에 대한 postVo
			request.setAttribute("postVo", postVo);
			RequestDispatcher rd = request.getRequestDispatcher("/postDetail.jsp");
			rd.forward(request, response);
			
		}

	}
	
	
	private void postSend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardCode = request.getParameter("boardCode");
		String postRefer = request.getParameter("postRefer");
		
		request.setAttribute("boardCode", boardCode);
		request.setAttribute("postRefer", postRefer);
		
		RequestDispatcher rd = request.getRequestDispatcher("/postWrite.jsp");
		rd.forward(request, response);
	}
}
