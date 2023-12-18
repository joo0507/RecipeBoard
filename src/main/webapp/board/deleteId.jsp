<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/board/assets/css/deleteId.css">
</head>
<body>
	<fieldset id="wrap">
		<h1>회원 삭제</h1>	
			<form action="/memberUpdate" method="post">
				<input type="hidden" name="command" value="deleteId">
				<input  type="hidden" name="userId" value="${userId }">
				<p><input class="in" type="password" name="userPw" placeholder="삭제를 원하시면 비밀번호를 입력해주세요">
				<p><input class="submit" type="submit" value="삭제하기">
			</form>
	</fieldset>
</body>
</html>