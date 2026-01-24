<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page isErrorPage="true"%>

<html>
<head>
<title>Error</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.ERROR_CTL%>/img/logo.png" sizes="16x16" />
<link rel="stylesheet" type="text/css" href="/ORSProject4/css/Error.css">

</head>
<body>

	<br>
	<br>
	<br>
	<br>
	<div align="center">
		<h1 align="center">Oops! Something went wrong.</h1>
		<font style="color: red; font-size: 25px"><b>500</b> : Internal server
			error.</font>

	</div>
	<h4 align="center">
		<font size="5px" color="black"> <a
			href="<%=ORSView.WELCOME_CTL%>" style="color: deepskyblue;">*Please
				click here to Go Back*</a></font>
	</h4>

</body>
</html>