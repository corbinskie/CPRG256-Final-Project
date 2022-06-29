<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <form method="POST" action="login">
            <h4>Login</h4>
            Email: <input type="text" name="email" value="" required>
            <br>
            Password: <input type="password" name="password" value="" required>
            <br>
            <br>
            <input type="submit" value="Log in">
            <br>
            <br>
            <a href="login?action=register">Register New User</a>
        </form>
        <p>${responseMessage}</p>
    </body>
</html>