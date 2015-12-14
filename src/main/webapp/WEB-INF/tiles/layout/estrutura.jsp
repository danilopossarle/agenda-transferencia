<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href='/css/global.css' />
	<tiles:insertAttribute name="scriptsCss" />
	<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<div class="wrap">
				<c:if test="${not empty msgSuccess}">
					<div class="success">Teste</div>
				</c:if>
				<c:if test="${not empty msgError}">
					<div class="error"></div>
				</c:if>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>