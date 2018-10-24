package board.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.service.IBoardService;
import board.vo.BoardVO;
import login.vo.UserVO;

/**
 * Servlet implementation class BoardMaker
 */
@WebServlet(urlPatterns={"/boardInsert", "/boardUpdate","/boardMakerSend"})
public class BoardMaker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 uri로 로직 분기
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		System.out.println("userServlet doGet => " +uri);

		//게시판 조회
		if(uri.equals("/boardInsert")){
			boardInsert(request, response);
		}else if(uri.equals("/boardUpdate")){
			boardUpdate(request, response);
		}else if(uri.equals("/boardMakerSend")){
			doGet(request, response);
		}
	}
	
	



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boardMakerSend(request, response);
	}





	private void boardInsert(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
		
		//로그인이 안되있을 경우
		if(userVo == null){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('먼저 로그인을 해주세요.');");
			script.println("location.href = 'login.jsp';");
			script.println("</script>");
		}
		
		
		//권한이 없는 아이디 일 경우
		if(userVo.getRight() != 3){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('해당 권한이 없는 아이디 입니다..');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
		}
		
		//게시판 생성을 위한 boardService 받아오기
		IBoardService boardService = BoardService.getInstance();
		
		String boardName = request.getParameter("boardName");
		//게시판 코드를 위한 service 이용
		int boardCode = boardService.nowBoardCode()+1;
		String userId = userVo.getUserId();
		
		//게시판 생성을 위한 게시판 객체 생성 
		BoardVO boardVo = new BoardVO();
		boardVo.setBoardCode(boardCode);
		boardVo.setBoardName(boardName);
		boardVo.setUserId(userId);
		
		int result = boardService.insertBoard(boardVo);
		
		if(result == -1){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('DB에서 오류가 발생하였습니다');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			
		}else{
			
			List<BoardVO> boardList =  boardService.selectBoardList();
			
			request.getServletContext().setAttribute("boardList", boardList);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('정상적으로 게시판 생성이 완료 되었습니다.');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
		}
		
		
		
	}
	
	private void boardMakerSend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		IBoardService boardService = BoardService.getInstance();
		List<BoardVO> boardListForBoardMaker =  boardService.selectBoardListForBoardMaker();
		request.setAttribute("boardListForBoardMaker", boardListForBoardMaker);
		
		RequestDispatcher rd = request.getRequestDispatcher("boardMaker.jsp");
		rd.forward(request, response);
		
	}
	
	
	private void boardUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
		
		//로그인이 안되있을 경우
		if(userVo == null){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();

			script.println("<script>");
			script.println("alert('먼저 로그인을 해주세요.');");
			script.println("location.href = 'login.jsp';");
			script.println("</script>");
		}


		//권한이 없는 아이디 일 경우
		if(userVo.getRight() != 3){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();

			script.println("<script>");
			script.println("alert('해당 권한이 없는 아이디 입니다..');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
		}
		
		String boardCode = request.getParameter("boardCode");
		String boardName = request.getParameter("boardName");
		String boardAvailable = request.getParameter("boardAvailable");
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoardCode(Integer.parseInt(boardCode));
		boardVo.setBoardName(boardName);
		boardVo.setBoardAvailable(Integer.parseInt(boardAvailable));
		
		IBoardService boardService = BoardService.getInstance();
		
		int result = boardService.updateBoard(boardVo);
		
		if(result == -1){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('DB에서 오류가 발생하였습니다');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			
		}else{
			
			List<BoardVO> boardList =  boardService.selectBoardList();
			
			request.getServletContext().setAttribute("boardList", boardList);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('정상적으로 게시판 수정이 완료 되었습니다.');");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
		}
		
	}
}
