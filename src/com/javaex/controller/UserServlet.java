package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user 진입");
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("joinform".equals(actionName)) {
			System.out.println("joinform 진입");
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinform.jsp");
		} else if("join".equals(actionName)) {
			System.out.println("join 진입");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			System.out.println(userVo.toString());
			
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
		} else if("loginform".equals(actionName)) {
			System.out.println("loginform 진입");
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginform.jsp");
		} else if("login".equals(actionName)) {
			System.out.println("login 진입");
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email+"/"+password);
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(email, password);
			
			if(userVo == null) {
				System.out.println("로그인실패");
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
			} else {
				System.out.println("로그인성공");
				HttpSession session  = request.getSession();
				session.setAttribute("authUser", userVo);
				
				WebUtil.redirect(request, response, "/mysite/main");
			}
		} else if("logout".equals(actionName)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			WebUtil.redirect(request, response, "/mysite/main");
		} else if("modifyform".equals(actionName)) {
			System.out.println("modifyform 진입");
			
			//세션에서 no getNo()
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if(authUser == null) { //비로그인 상태
				//로그인폼으로 리다이렉트
			} else { //로그인 상태
				//로그인회원 no
				int no = authUser.getNo();
				
				//다오에서 가져옴(no)
				UserDao userDao = new UserDao();
				UserVo userVo = userDao.getUser(no);
				
				//데이터 request에 저장
				request.setAttribute("userVo", userVo);	
				
				//포워드(화면에서 작업)
				WebUtil.forword(request, response, "/WEB-INF/views/user/modifyform.jsp");
			}
		} else if("modify".equals(actionName)) {
			System.out.println("modify 진입");
		
			HttpSession session = request.getSession(true);
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if(authUser == null) { //비로그인 상태
				//로그인폼으로 리다이렉트
			} else { //로그인 상태
				//vo(no, name, password, gender)
				int no = authUser.getNo();
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				
				UserVo userVo = new UserVo(no, name, "", password, gender);
				
				//dao.update(vo)
				UserDao userDao = new UserDao();
				userDao.update(userVo);
				
				//session name 값 변경
				authUser.setName(name);
				
				//main 리다이렉트
				WebUtil.redirect(request, response, "/mysite/main");
			}	
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
