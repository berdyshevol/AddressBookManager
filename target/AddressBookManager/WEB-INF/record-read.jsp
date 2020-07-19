<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record Read</title>
</head>
<body>
    <%@include file="header.html"%>
    <table>
        <tr>
            <td>First name:</td>
            <td><b><%=request.getParameter("first-name")%></b></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><b><%=request.getParameter("last-name")%></b></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><b><%=request.getAttribute("address")%></b></td>
        </tr>
    </table>
</body>
</html>
