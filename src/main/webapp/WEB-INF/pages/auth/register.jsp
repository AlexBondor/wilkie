<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 1/9/2016
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>${message}</h1>
<form method="post" action="${pageContext.request.contextPath}/auth/register">
    <label for="email">email: </label>
    <input id="email" type="email" name="email">
    <span>${emailRegistered}</span>
    <br>
    <label for="password">password: </label>
    <input id="password" type="password" name="password">
    <br>
    <label for="name">name: </label>
    <input id="name" type="text" name="name">
    <br>
    <input type="submit" value="Register">
    <br>
    <span>${updates}</span>
</form>
</body>
</html>
