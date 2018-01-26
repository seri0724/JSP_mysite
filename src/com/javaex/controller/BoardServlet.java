package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board") 
public class BoardServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("servlet 진입");
		
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		if("list".equals(actionName)) {
			System.out.println("list 진입");

			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.getList();
	
			request.setAttribute("list", list); 
	
			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
		} else if("writeform".equals(actionName)) {
			System.out.println("writeform 진입");
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/write.jsp");
		} else if("write".equals(actionName)) {
			System.out.println("write 진입");
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardVo vo = new BoardVo();
			
			vo.setUserNo(authUser.getNo());
			vo.setTitle(title);
			vo.setContent(content);
			
			BoardDao dao = new BoardDao();
			
			dao.writer(vo);
		
			WebUtil.redirect(request, response, "board?a=list");
		} else if("view".equals(actionName)) {
			System.out.println("view 진입");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			BoardVo vo = dao.select(no);

			request.setAttribute("vo", vo); 
			
			//조회수 올리기
			dao.update(no);
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/view.jsp");
		} else if("modifyform".equals(actionName)) {
			System.out.println("modifyform 진입");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			BoardVo vo = dao.select(no);

			request.setAttribute("vo", vo);
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/modify.jsp");
		} else if("modify".equals(actionName)) {
			System.out.println("modify 진입");

			BoardVo vo = new BoardVo();
			
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			
			BoardDao dao = new BoardDao();
			
			dao.modify(vo);

			WebUtil.redirect(request, response, "board?a=list");
		} else if("delete".equals(actionName)) {
			System.out.println("delete 진입");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			
			dao.delete(no);
			
			WebUtil.redirect(request, response, "board?a=list");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
