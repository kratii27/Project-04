<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BatchCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Batch</title>
</head>
<body>
    <%@ include file="Header.jsp"%>
    <form action="<%=ORSView.BATCH_CTL%>" method="post">
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.BatchBean"
            scope="request"></jsp:useBean>

        <h1 align="center" style="margin-bottom: -15; color: navy">
            <%
                if (bean != null && bean.getId() > 0) {
            %>Update<%
                } else {
            %>Add<%
                }
            %>
            Batch
        </h1>
        <div style="height: 15px; margin-bottom: 12px">
            <H3 align="center">
                <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
            </H3>
            <H3 align="center">
                <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
            </H3>
        </div>

        <input type="hidden" name="id" value="<%=bean.getId()%>">
        <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
        <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
        <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
        <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

        <table align="center">
            <tr>
                <th>Batch Code</th>
                <td><input type="text" name="batchCode"
                    value="<%=DataUtility.getStringData(bean.getBatchCode())%>"
                    placeholder="enter batch code here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("batchCode", request)%></font></td>
            </tr>
            <tr>
                <th>Batch Name</th>
                <td><input type="text" name="batchName"
                    value="<%=DataUtility.getStringData(bean.getBatchName())%>"
                    placeholder="enter batch name here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("batchName", request)%></font></td>
            </tr>
            <tr>
                <th>Trainer Name</th>
                <td><input type="text" name="trainerName"
                    value="<%=DataUtility.getStringData(bean.getTrainerName())%>"
                    placeholder="enter trainer name here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("trainerName", request)%></font></td>
            </tr>
            <tr>
                <th>Batch Timing</th>
                <td><input type="time" name="batchTiming"
                    value="<%=DataUtility.getStringData(bean.getBatchTiming())%>"
                    placeholder="enter batch timing here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("batchTiming", request)%></font></td>
            </tr>
            <tr>
                <th></th>
                <%
                    if (bean != null && bean.getId() > 0) {
                %>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=BatchCtl.OP_UPDATE%>">
                    <input type="submit" name="operation" value="<%=BatchCtl.OP_CANCEL%>">
                <%
                    } else {
                %>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=BatchCtl.OP_SAVE%>">
                    <input type="submit" name="operation" value="<%=BatchCtl.OP_RESET%>">
                <%
                    }
                %>
                </td>
            </tr>
        </table>
    </form>
    <%@ include file="Footer.jsp"%>
</body>
</html>