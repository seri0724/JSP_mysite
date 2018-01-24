<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.GuestbookVo" %>

<%request.setCharacterEncoding("UTF-8");
List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("list");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<!-- header -->
		
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		
		<!-- navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form action="/mysite/gb?a=add" method="get">
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
								<input type="hidden" value="add" name ="a">
							</tr>
						</table>
					</form>
					<ul>
					<% for (GuestbookVo vo : list) { %>
						<li>
							<table>
								<tr>
									<td><%=vo.getNo() %></td>
									<td><%=vo.getName() %></td>
									<td><%=vo.getRegDate() %></td>
									<td><a href="/mysite/gb?a=deleteform&no=<%=vo.getNo() %>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4><%=vo.getContent() %>
									</td>
								</tr>
							</table>
							<br>
						</li>
						<% } %>
					</ul>
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
		<!-- footer -->
		
	</div> <!-- /container -->

</body>
</html>