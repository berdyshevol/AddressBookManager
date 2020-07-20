<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record Update</title>
</head>
<body>
<%@include file="header.html"%>
<form action="/records/update" method="post">
    <table>
        <tr>
            <td><label for="first-name">First name: </label></td>
            <td><input type="text" id="first-name" name="first-name" value="<%=request.getParameter("first-name")%>" readonly></td>
        </tr>
        <tr>
            <td><label for="last-name">Last name: </label></td>
            <td><input type="text" id="last-name" name="last-name" value="<%=request.getParameter("last-name")%>" readonly></td>
        </tr>
        <tr>
            <td><label for="address">Address: </label></td>
            <td><textarea id="address" name="address" rows="3" col="10"><%=request.getAttribute("address")%></textarea></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update"></td>
            <td><input type="reset" value="Clear"></td>
        </tr>
    </table>
</form>
</body>
</html>
