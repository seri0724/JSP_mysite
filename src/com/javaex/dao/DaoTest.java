package com.javaex.dao;

import com.javaex.vo.BoardVo;

public class DaoTest {

	public static void main(String[] args) {
		
		/*UserVo userVo = new UserVo();
		userVo.setName("유재석");
		userVo.setEmail("you@gmail.com");
		userVo.setPassword("1234");
		userVo.setGender("male");
		
		UserDao userDao = new UserDao();
		userDao.insert(userVo);*/
		
		/*UserDao userDao = new UserDao();
		UserVo userVo = userDao.getUser("you@gmail.com", "1234");
		System.out.println(userVo.toString());*/
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		vo.setTitle("신과함께");
		vo.setContent("웹툰");
		vo.setUserNo(2);
		
		dao.writer(vo);
	}
}
