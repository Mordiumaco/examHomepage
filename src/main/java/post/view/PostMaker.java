package post.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.vo.UserVO;
import post.service.IPostService;
import post.service.PostService;
import post.vo.PostVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import file.service.FileService;
import file.service.IFileService;
import file.vo.FileVO;
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize=1024*1024*5*5)
@WebServlet(urlPatterns={"/postMaker", "/postUpdate", "/postDelete"})
public class PostMaker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 uri로 로직 분기
		String uri = request.getRequestURI();
		System.out.println("userServlet doGet => " +uri);

		//게시판 조회
		if(uri.equals("/postMaker")){
			postMaker(request, response);
		}else if(uri.equals("/postUpdate")){
			postUpdate(request, response);
		}else if(uri.equals("/postDelete")){
			doGet(request, response);
		}
		
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		postDelete(request, response);
	}




	private void postMaker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
	
		
		if(userVo == null){
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('먼저 로그인을 해주세요.');");
			script.println("location.href = 'login.jsp';");
			script.println("</script>");
			
		}
		
		//--------------------파일 업로드 부분 -------------------------
			String directory = request.getServletContext().getRealPath("/upload/");
			String encoding ="UTF-8";
			int maxSize = 1024*1024*100;
			
			MultipartRequest multipartRequest = 
					new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
			
		//-----------------------------------------------------------
		String postCon = multipartRequest.getParameter("postCon");
		String postName = multipartRequest.getParameter("postName");
		String boardCode = request.getParameter("boardCode");
		String postRefer = request.getParameter("postRefer");  
		
		//----테스트
		
		System.out.println(postCon);
		System.out.println(postName);
		System.out.println("boardCode"+boardCode);
		System.out.println("postRefer"+postRefer);
		
		//-----
		
		
		
		IPostService postService = PostService.getInstance();
		
		PostVO postVo = new PostVO();
		postVo.setBoardCode(boardCode);
		postVo.setPostCon(postCon);
		postVo.setPostName(postName);
		postVo.setUserId(userVo.getUserId());
		int nextPostCode = postService.nowPostNumber()+1;
		postVo.setPostCode(nextPostCode);
		
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
			
			//파일 업데이트 관련 내용
			Enumeration fileNames = multipartRequest.getFileNames();
			
			while(fileNames.hasMoreElements()){
				String parameter = (String)fileNames.nextElement();
				//파일명
				String fileName = multipartRequest.getOriginalFileName(parameter);
				System.out.println("file name-"+fileName);
				//실제 파일명
				String fileRealName = multipartRequest.getFilesystemName(parameter);
				System.out.println("file Real name-"+fileRealName);
				
				if(fileName == null) continue;
				
				if(!fileName.endsWith(".jpg")&&!fileName.endsWith(".hwp")&&
						!fileName.endsWith(".png")&&!fileName.endsWith(".pdf")&&!fileName.endsWith(".xlsx")&&!fileName.endsWith(".xls")){
					
					
					File file = new File(directory+fileRealName);
					file.delete();
					
					
					response.setContentType("text/html; charset=utf-8");
					PrintWriter script = response.getWriter();
					script.println("업로드할 수 없는 확장자 입니다.");
					
				}else{
					
					IFileService fileSerivce = FileService.getInstance();
					
					FileVO fileVo = new FileVO();
					fileVo.setFileCode(fileSerivce.nowFileCodeNumber()+1);
					fileVo.setFileName(fileName);
					fileVo.setFilePosition(fileRealName);
					fileVo.setPostCode(nextPostCode);
					
					int fileResult = fileSerivce.insertFile(fileVo);
					
					if(result == -1){
						
						response.setContentType("text/html; charset=utf-8");
						PrintWriter script = response.getWriter();
						
						script.println("<script>");
						script.println("alert('파일객체에 대한 DB에서 오류가 발생하였습니다.');");
						script.println("location.href = 'main.jsp';");
						script.println("</script>");
					}
				}
			}
			//--------------------------------------------------------------------
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('성공적으로 게시물이 만들어졌습니다.');");
			script.println("location.href = '/boardPageList?boardCode="+boardCode+"&pageNumber=1';");
			script.println("</script>");
			
		}
	}
	
	
	private void postUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String postCode = request.getParameter("postCode");
		String postName = request.getParameter("postName");
		String postCon = request.getParameter("postCon");
		String boardCode = request.getParameter("boardCode");
		
		PostVO postVo = new PostVO();
		postVo.setPostCon(postCon);
		postVo.setPostName(postName);
		postVo.setPostCode(Integer.parseInt(postCode));
		
		
		IPostService postService = PostService.getInstance();
		
		int result = postService.updatePost(postVo);
		
		
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
			script.println("alert('성공적으로 게시물이 수정되었습니다.');");
			script.println("location.href = '/boardPageList?boardCode="+boardCode+"&pageNumber=1';");
			script.println("</script>");
			
		}
	}
	
	private void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String postCode = request.getParameter("postCode");
		String boardCode = request.getParameter("boardCode");
		
		IPostService postService = PostService.getInstance();
		
		int result = postService.deletePost(postCode);
		
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
			script.println("alert('성공적으로 게시물이 삭제되었습니다.');");
			script.println("location.href = '/boardPageList?boardCode="+boardCode+"&pageNumber=1';");
			script.println("</script>");
			
		}
	}
}
