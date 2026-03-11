<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.HostelRoomAllocationCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hostel Room Allocation</title>
</head>
<body>
    <%@ include file="Header.jsp"%>
    <form action="<%=ORSView.HOSTEL_ROOM_ALLOCATION_CTL%>" method="post">
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.HostelRoomAllocationBean"
            scope="request"></jsp:useBean>

        <h1 align="center" style="margin-bottom: -15; color: navy">
            <%if (bean != null && bean.getId() > 0) {%>Update<%} else {%>Add<%}%>
            Hostel Room Allocation
        </h1>
        <div style="height: 15px; margin-bottom: 12px">
            <H3 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></H3>
            <H3 align="center"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></H3>
        </div>

        <input type="hidden" name="id" value="<%=bean.getId()%>">
        <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
        <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
        <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
        <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

        <table align="center">
            <tr>
                <th>Student Name</th>
                <td><input type="text" name="studentName"
                    value="<%=DataUtility.getStringData(bean.getStudentName())%>"
                    placeholder="enter student name here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("studentName", request)%></font></td>
            </tr>
            <tr>
                <th>Room Number</th>
                <td><input type="number" name="roomNumber"
                    value="<%=bean.getRoomNumber() == null || bean.getRoomNumber() == 0 ? "" : bean.getRoomNumber()%>"
                    placeholder="enter room number here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("roomNumber", request)%></font></td>
            </tr>
            <tr>
                <th>Block Name</th>
                <td>
                <%
                HashMap map = new HashMap();
                map.put("Block A", "Block A");
                map.put("Block B", "Block B");
                map.put("Block C", "Block C");
                map.put("Block D", "Block D");
                %><%=HTMLUtility.getList("blockName", bean.getBlockName(), map) %>
                </td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("blockName", request)%></font></td>
            </tr>
            <tr>
                <th>Allotment Date</th>
                <td><input type="date" name="allotmentDate"
                    value="<%=bean.getAllotmentDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(bean.getAllotmentDate()) : ""%>">
                </td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("allotmentDate", request)%></font></td>
            </tr>
            <tr>
                <th></th>
                <%if (bean != null && bean.getId() > 0) {%>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=HostelRoomAllocationCtl.OP_UPDATE%>">
                    <input type="submit" name="operation" value="<%=HostelRoomAllocationCtl.OP_CANCEL%>">
                <%} else {%>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=HostelRoomAllocationCtl.OP_SAVE%>">
                    <input type="submit" name="operation" value="<%=HostelRoomAllocationCtl.OP_RESET%>">
                <%}%>
                </td>
            </tr>
        </table>
    </form>
    <%@ include file="Footer.jsp"%>
</body>
</html>