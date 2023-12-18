package com.busanit.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busanit.controller.DAO.BoardDAO;
import com.busanit.controller.DTO.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO bVo = new BoardVO();
		
		PrintWriter out = response.getWriter();
		String image = null;
		String msg = "게시글 작성 완료!";
		System.out.println("파일올리기 check");
		MultipartRequest multi = 
				new MultipartRequest(request,"C:\\upload",5*1024*1024,"utf-8", new DefaultFileRenamePolicy());
			
//				Enumeration params = multi.getParameterNames();
//				
//				while(params.hasMoreElements()){
//					String name = (String) params.nextElement();
//					String value = multi.getParameter(name);
//					out.println(name+ " : " + value + "<br>");
//				}
//				out.println("-----------------------------------------<br>");
				
				Enumeration files = multi.getFileNames();
						
				while(files.hasMoreElements()){
					String name = (String) files.nextElement();
					image = multi.getFilesystemName(name);
					
				}
		
		bVo.setUserId(multi.getParameter("userId"));
		bVo.setFood(multi.getParameter("food"));
		bVo.setNick(multi.getParameter("nick"));
		bVo.setLevel(multi.getParameter("level"));
		bVo.setImage(image);
		bVo.setTitle(multi.getParameter("title"));
		bVo.setContent(multi.getParameter("content"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertBoard(bVo);
		
		// 게시글 등록 후 게시글 목록 화면으로 이동
		out.write("<script>alert('"+msg+"');</script>");
		new BoardListAction().execute(request, response);
	}

}



