package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gb") //GuestbookServlet -> gb로 이름 바꿔줌
public class GuestbookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("servlet 진입");
		
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		if("list".equals(actionName)) {
			System.out.println("list 진입");
			//dao를 통해서 list 가지고 오기
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();
			//list를 request에 저장
			request.setAttribute("list", list); 
			//requset를 foward
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/list.jsp");
		} else if("add".equals(actionName)) {
			System.out.println("add 진입");
			
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo vo = new GuestbookVo(name, password, content);	
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			WebUtil.redirect(request, response, "/mysite/gb?a=list");
		} else if("deleteform".equals(actionName)) {
			System.out.println("deleteform 진입");
			
			WebUtil.forword(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
		} else if("delete".equals(actionName)) {
			System.out.println("delete 진입");
			
			request.setCharacterEncoding("UTF-8");
			
			String password = request.getParameter("password");
			
			GuestbookDao dao = new GuestbookDao();
			int no = Integer.parseInt(request.getParameter("no"));
			GuestbookVo vo = dao.select(no);
			if (vo.getPassword().equals(password)) {
				dao.delete(no);
				WebUtil.redirect(request, response, "/mysite/gb?a=list");
			
				System.out.println("삭제됨");
			}
		} 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
