<%@ page import="com.softserve.itacademy.AddressBook" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record List</title>
</head>
<body>
<%@include file="header.html"%>
<p>Sort by: <a href="/records/list?sort=asc">ascending</a> <a href="/records/list?sort=desc">descending</a></p>
<table border="1">
    <tr>
        <th>No.</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Address</th>
        <th colspan="3">Operation</th>
    </tr>
    <%
        List<Map<String, String>> list = (List<Map<String, String>>)request.getAttribute("records");
        int i = 0;
        for (Map<String, String> item : list) {
            i++;
    %>
    <tr>
        <td><%=i%></td>
        <td><%=item.get("first-name")%></td>
        <td><%=item.get("last-name")%></td>
        <td><%=item.get("address")%></td>
        <td><a href="/records/read?first-name=<%=item.get("first-name")%>&last-name=<%=item.get("last-name")%>">Read</a></td>
        <td><a href="/records/update?first-name=<%=item.get("first-name")%>&last-name=<%=item.get("last-name")%>&address=<%=item.get("address")%>">Update</a></td>
        <td><a href="/records/delete?first-name=<%=item.get("first-name")%>&last-name=<%=item.get("last-name")%>">Delete</a></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
