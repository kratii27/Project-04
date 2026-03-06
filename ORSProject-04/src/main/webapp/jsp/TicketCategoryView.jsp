<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.TicketCategoryCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Ticket Category</title>
</head>
<body>
    <%@ include file="Header.jsp"%>
    <form action="<%=ORSView.TICKET_CATEGORY_CTL%>" method="post">
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.TicketCategoryBean"
            scope="request"></jsp:useBean>

        <h1 align="center" style="margin-bottom: -15; color: navy">
            <%if (bean != null && bean.getId() > 0) {%>Update<%} else {%>Add<%}%>
            Ticket Category
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
                <th>Category Name</th>
                <td><input type="text" name="categoryName"
                    value="<%=DataUtility.getStringData(bean.getCategoryName())%>"
                    placeholder="enter category name here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("categoryName", request)%></font></td>
            </tr>
            <tr>
                <th>Price</th>
                <td><input type="number" name="price"
                    value="<%=bean.getPrice() == null || bean.getPrice() == 0 ? "" : bean.getPrice()%>"
                    placeholder="enter price here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("price", request)%></font></td>
            </tr>
            <tr>
                <th>Available Seats</th>
                <td><input type="number" name="availableSeats"
                    value="<%=bean.getAvailableSeats() == null || bean.getAvailableSeats() == 0 ? "" : bean.getAvailableSeats()%>"
                    placeholder="enter available seats here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("availableSeats", request)%></font></td>
            </tr>
            <tr>
                <th></th>
                <%if (bean != null && bean.getId() > 0) {%>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=TicketCategoryCtl.OP_UPDATE%>">
                    <input type="submit" name="operation" value="<%=TicketCategoryCtl.OP_CANCEL%>">
                <%} else {%>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=TicketCategoryCtl.OP_SAVE%>">
                    <input type="submit" name="operation" value="<%=TicketCategoryCtl.OP_RESET%>">
                <%}%>
                </td>
            </tr>
        </table>
    </form>
    <%@ include file="Footer.jsp"%>
</body>
</html>