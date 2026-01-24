<%@page import="in.co.rays.proj4.controller.ORSView"%>
<html>
<head>
<title>Welcome to ORS</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@ include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h1 align="center">
		<font size="10px" color="navy">Welcome to ORS</font>
	</h1>

	<%
		if (user != null && user.getRoleId() == RoleBean.STUDENT) {
	%>

	<h2 align="center">
		<a href="<%=ORSView.GET_MARKSHEET_CTL%>" style="color: maroon;">
			get your Marksheet here </a>
	</h2>


	<%
		}
	%>

	<%@ include file="Footer.jsp"%>
</body>
</html>