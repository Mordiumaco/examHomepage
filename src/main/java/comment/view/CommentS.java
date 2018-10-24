package comment.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentService;
import comment.service.ICommentService;
import comment.vo.CommentVO;

/**
 * Servlet implementation class PostS
 */
@WebServlet("/commentS")
public class CommentS extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commentCode = request.getParameter("commentCode");
		
		ICommentService commentService = CommentService.getInstance();
		
		int result = commentService.deletePostComment(commentCode);
		
		if(result == -1){
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('DB에 오류가 발생하였습니다');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			
		}else{
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('정상적으로 댓글이 삭제 되었습니다');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			
		}
		
		
	}

}
