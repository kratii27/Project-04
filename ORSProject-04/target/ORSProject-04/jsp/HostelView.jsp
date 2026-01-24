<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.HostelCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add hostel</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.HOSTEL_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.HostelBean"
			scope="request"></jsp:useBean>
		<%
			HashMap map = (HashMap) request.getAttribute("preload");
			List list = (List) request.getAttribute("collegelist");
		%>

		<h1 align="center" style="margin-bottom: -15; color: navy">
			<%
				if (bean != null && bean.getId() > 0) {
			%>Update<%
				} else {
			%>Add<%
				}
			%>
			Hostel
		</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<H3 align="center">
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H3>

			<H3 align="center">
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H3>
		</div>

		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

		<table align="center">
			<tr>
				<th>Name</th>
				<td><input type="text" name="name"
					value="<%=DataUtility.getStringData(bean.getName())%>"
					placeholder="enter hostel name here"></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
			</tr>
			<tr>
				<th>Type</th>
				<td><%=HTMLUtility.getList("type", bean.getType(), map)%></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("type", request)%></font></td>
			</tr>
			<tr>
				<th>Capacity</th>
				<td><input type="text" name="capacity" value="<%=bean.getCapacity() == 0 ? "" : bean.getCapacity()%>"
					placeholder="enter capacity here"></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("capacity", request)%></font></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><input type="text" name="description"
					value="<%=DataUtility.getStringData(bean.getDescription())%>"
					placeholder="enter description here"></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
			</tr>
			<tr>
				<th>College</th>
				<td><%=HTMLUtility.getList("college", String.valueOf(bean.getCollegeId()), list)%></td>
				<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("college", request)%></font></td>
			</tr>
			<tr>
				<th></th>
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<td align="left" colspan="2"><input type="submit"
					name="operation" value="<%=HostelCtl.OP_UPDATE%>"> <input
					type="submit" name="operation" value="<%=HostelCtl.OP_CANCEL%>">
					<%
						} else {
					%>
				<td align="left" colspan="2"><input type="submit"
					name="operation" value="<%=HostelCtl.OP_SAVE%>"> <input
					type="submit" name="operation" value="<%=HostelCtl.OP_RESET%>">
					<%
						}
					%>
			</tr>
		</table>

	</form>


	<%@ include file="Footer.jsp"%>
</body>
</html>