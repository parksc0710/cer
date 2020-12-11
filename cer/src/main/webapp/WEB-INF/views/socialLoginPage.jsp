<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="style-1">

<body style="display:none;">
	<fieldset>
	<form action="${pageContext.request.contextPath}/socialLogin/socialLoginCheck" method="post" id="frm">
		<input type="text" name="user_email" value="${socialMail }" style="display:none;">
		<button class="btn btn-default btn-lg btn-block" style="display:none;">sign in</button>
	</form>
	</fieldset>
	<script>
		// alert("${socialMail }");
		document.getElementById('frm').submit();
	</script>
	
</body>
</html>
