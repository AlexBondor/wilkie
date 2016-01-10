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
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<form method="post" action="/login">
    <label for="email">email: </label>
    <input id="email" type="email" name="email">
    <span>${wrongEmail}</span>
    <br>
    <label for="password">password: </label>
    <input id="password" type="password" name="password">
    <span>${wrongPassword}</span>
    <br>
    <input type="submit" value="Login">
    <br>
    <span>${updates}</span>
</form>
</body>
</html>
