<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Page</title>
    </head>
    <body>

        <h1>Account Settings</h1>

        <h4>Menu</h4>

        <a href="inventory?action=inventory">My Inventory</a><br>
        <a href="account?action=settings">Account Settings</a><br>
        <a href="login?action=logout">Logout</a><br>


        <h3>Edit Account Information for ${userForEdit.firstName} ${userForEdit.lastName}</h3>
        <form method="POST" action="account">

            Active Account: <c:if test="${userForEdit.active}"> 
                <input type="checkbox" name="editActivity" value="true" checked>
                <input type="hidden" name="editActivity" value="false"><br>
                (Warning: If you disable your account you will need to contact an Admin to re-enable it!)
                <br>
            </c:if>
            <c:if test="${!userForEdit.active}">
                <input type="checkbox" name="" value="false">
                <input type="hidden" name="editActivity" value="true" checked>
            </c:if>
            <br>
            Password: <input type="text" name="editPassword" value="${userForEdit.password}" required></input>
            <br>
            First Name: <input type="text" name="editFName" value="${userForEdit.firstName}" required></input>
            <br>
            Last Name: <input type="text" name="editLName" value="${userForEdit.lastName}" required></input>
            <br>

            <input type="submit" value="Save"></input>
        </form>
        <p>${responseMessage}</p
    </body>
</html>
