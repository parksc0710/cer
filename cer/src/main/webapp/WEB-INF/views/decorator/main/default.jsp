<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<%@ include file ="../commonInclude/commonInclude.jsp" %>
	<decorator:head />
    
</head>

<body class="preload bcolor-xxl-bg ukb">
	
	<%@ include file = "header.jspf" %>
	
	<div class="content-block ">
		
		<!-- Container -->
        <div class="container cont-pad-t-sm">
        
			<decorator:body/>
		
		</div>
		
	</div>

	<%@ include file = "footer.jspf" %>
	
</body>
</html>