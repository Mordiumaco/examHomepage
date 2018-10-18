package login.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardService;
import board.service.IBoardService;
import board.vo.BoardVO;
import login.service.ILoginService;
import login.service.LoginService;
import login.vo.UserVO;

@WebServlet("/loginS")
public class LoginS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter pw = resp.getWriter();
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		ILoginService loginService = LoginService.getInstance();
		
		UserVO userVo = loginService.selectUserByIdAndPassword(userId, password);
		
		if(userVo != null){
			
			HttpSession session = request.getSession();
			session.setAttribute("userVo", userVo);
			
			IBoardService boardService = BoardService.getInstance();
			List<BoardVO> boardList =  boardService.selectBoardList();
			
			request.getServletContext().setAttribute("boardList", boardList);
			
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
			
		}else{
			
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
	}

}
