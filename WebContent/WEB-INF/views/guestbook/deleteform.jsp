<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% int no  = Integer.valueOf(request.getParameter("no")); %>
<!DOCTYPE html>
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
				<div id="guestbook" class="delete-form">
					
					<form method="get" action="/mysite/gb">
						
						<label>비밀번호</label>
						<input type="password" name="password">
						<input type="submit" value="확인">
						<input type="hidden" value="<%=no%>" name="no">
						<input type="hidden" value="delete" name="a">
					</form>
					<a href="/mysite/gb?a=list">방명록 리스트</a>
					
				</div>
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
		<!-- footer -->
		
	</div> <!-- /container -->

</body>
</html>
