<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.PortfolioCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Portfolio</title>
</head>
<body>
    <%@ include file="Header.jsp"%>
    <form action="<%=ORSView.PORTFOLIO_CTL%>" method="post">

        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.PortfolioBean"
            scope="request"></jsp:useBean>
        <%
            HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("typeList");
        %>

        <h1 align="center" style="margin-bottom: -15; color: navy">
            <%
                if (bean != null && bean.getId() > 0) {
            %>Update<%
                } else {
            %>Add<%
                }
            %>
            Portfolio
        </h1>

        <div style="height: 15px; margin-bottom: 12px">
            <h3 align="center">
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </h3>

            <h3 align="center">
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </h3>
        </div>

        <input type="hidden" name="id" value="<%=bean.getId()%>"> 
        <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
        <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
        <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
        <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

        <table align="center">
            <tr>
                <th>Portfolio Name</th>
                <td><input type="text" name="portfolioName"
                    value="<%=DataUtility.getStringData(bean.getPortfolioName())%>"
                    placeholder="Enter portfolio name here"></td>
                <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("portfolioName", request)%></font></td>
            </tr>
            <tr>
                <th>Total Value</th>
                <td><input type="text" name="totalValue" 
                    value="<%=DataUtility.getStringData(bean.getTotalValue())%>"
                    placeholder="Enter total value here"></td>
                <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("totalValue", request)%></font></td>
            </tr>
            <tr>
                <th>Created Date</th>
                <td><input type="text" name="createdDate" 
                    value="<%=DataUtility.getDateString(bean.getCreatedDate())%>"
                    placeholder="Enter created date (MM/dd/yyyy)"></td>
                <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("createdDate", request)%></font></td>
            </tr>
            <tr>
                <th></th>
                <%
                    if (bean != null && bean.getId() > 0) {
                %>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=PortfolioCtl.OP_UPDATE%>"> 
                    <input type="submit" name="operation" value="<%=PortfolioCtl.OP_CANCEL%>">
                </td>
                <%
                    } else {
                %>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=PortfolioCtl.OP_SAVE%>"> 
                    <input type="submit" name="operation" value="<%=PortfolioCtl.OP_RESET%>">
                </td>
                <%
                    }
                %>
            </tr>
        </table>

    </form>

    <%@ include file="Footer.jsp"%>
</body>
</html>