<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 2020-07-19
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create record</title>
</head>
<body>
<%@include file="header.html"%>
<form action="/records/create" method="post">
    <table>
        <p><%=request.getAttribute("error-message")%></p>
        <tr>
            <td><label for="first-name">First name: </label></td>
            <td><input type="text" id="first-name" name="first-name" value="<%=request.getAttribute("first-name")%>"></td>
        </tr>
        <tr>
            <td><label for="last-name">Last name: </label></td>
            <td><input type="text" id="last-name" name="last-name" value="<%=request.getAttribute("last-name")%>"></td>
        </tr>
        <tr>
            <td><label for="address">Address: </label></td>
            <td><textarea id="address" name="address" rows="3" col="10"><%=request.getAttribute("address")%></textarea></td>
        </tr>
        <tr>
            <td><input type="submit" value="Create"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>
</form>
</body>
</html>
