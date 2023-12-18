package com.busanit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBManager;

/**
 * Servlet implementation class DeleteIdServlet
 */
@WebServlet("/deleteId")
public class DeleteIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("id");
//		String userPw = request.getParameter("pw");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int resultCount = 0;
		try {
			conn = DBManager.getConnection();
			String sql = "delete from joinMember where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			resultCount = pstmt.executeUpdate();
			
			HttpSession session = request.getSession();
			if(resultCount > 0) {
				session.invalidate();
			}
			
		} catch (Exception e) {
			 e.getMessage();
		}finally {
			DBManager.close(conn, pstmt);
		}
		String url = "/board/deleteId_process.jsp";
		request.setAttribute("resultCount", resultCount);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
