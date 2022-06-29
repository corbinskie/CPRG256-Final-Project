<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Register New User</h1>
        <form method="POST" action="registration">
            Email: <input type="email" name="registerEmail" value="" required></input>
            <br>
            Password: <input type="password" name="registerPassword" value="" required></input>
            <br>
            First Name: <input type="text" name="registerFName" value="" required></input>
            <br>
            Last Name: <input type="text" name="registerLName" value="" required></input>
            <br>
            <br>
            <input type="submit" value="Regsiter"></input>
            <input type="hidden" name="action" value="register"></input>
        </form>
    </body>
</html>