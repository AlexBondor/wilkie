<%@ page import="model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 1/11/2016
  Time: 1:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("/login");
        return;
    }
%>
<h1>Hello there, ${user.name}</h1>
</body>
</html>
