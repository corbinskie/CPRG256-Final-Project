<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>

        <h1>Administration</h1>

        <h4>Menu</h4>

        <a href="login?action=logout">Logout</a><br>


        <h2>Manage Users</h2>
        <table cellpadding="7" border="1">
            <tr>
                <td>Email</td>
                <td>Password</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>User Role</td>
                <td>Active Account</td>
            </tr>

            <tr>

                <c:forEach var="users" items="${usersList}">
                <tr>
                    <td name="email" value="">${users.email}</td>
                    <td name="password" value="">${users.password}</td>
                    <td name="fName" value="">${users.firstName}</td>
                    <td name="lName" value="">${users.lastName}</td>
                    <td name="lName" value="">${users.role.roleName}</td>
                    <td name="lName" value="">${users.active}</td>
                <form method="POST" action="admin">
                    <td><input type="submit" value="Delete">
                        <input type="hidden" name="action" value="deleteUser">
                        <input type="hidden" name="deleteUserButton" value="${users.email}">
                    </td>
                </form>
                <form method="POST" action="admin">
                    <td><input type="submit" value="Edit">
                        <input type="hidden" name="action" value="editUser">
                        <input type="hidden" name="editUserButton" value="${users.email}">
                    </td>
                </form>
            </tr>  
        </c:forEach>

    </tr>

</table>

<h3>${addOrEdit} User</h3>
<form method="POST" action="admin">

    Email: <input type="email" name="addEmail" value="${userForEdit.email}" required></input>
    <br>
    Password: <input type="password" name="addPassword" value="${userForEdit.password}" required></input>
    <br>
    First Name: <input type="text" name="addFName" value="${userForEdit.firstName}" required></input>
    <br>
    Last Name: <input type="text" name="addLName" value="${userForEdit.lastName}" required></input>
    <br>
    Role: <select name="addRole" value="">
        <c:forEach items="${roleList}" var="role">
            <option value="${role.roleId}">${role.roleName}</option>
        </c:forEach>
    </select>
    <br>
    Active Account: <c:if test="${userForEdit.active}"> 
        <input type="checkbox" name="editActivity" value="true" checked>
        <input type="hidden" name="editActivity" value="false">
    </c:if>
    <c:if test="${!userForEdit.active}">
        <input type="checkbox" name="" value="false">
        <input type="hidden" name="editActivity" value="true" checked>
    </c:if>
    <br>
    <input type="submit" value="Save"></input>
    <input type="hidden" name="action" value="save"></input>
</form>
<h2>Manage Categories</h2>
<table cellpadding="7" border="1">
    <tr>
        <td>Category Name</td>
    </tr>

    <tr>

        <c:forEach var="category" items="${categoryList}">
        <tr>
            <td name="category" value="">${category.categoryName}</td>
        <form method="POST" action="admin">
            <td><input type="submit" value="Edit">
                <input type="hidden" name="action" value="editCategory">
                <input type="hidden" name="editCategoryButton" value="${category.categoryName}">
            </td>
        </form>
    </tr>  
</c:forEach>
</tr>
</table>
<h3>Add Category</h3>
<form method="POST" action="admin">
    Category Name: <input type="text" name="addCategory" value="${categoryToEdit}" required></input>
    <br>
    <td><input type="submit" value="Save">
        <input type="hidden" name="action" value="saveCategory">
</form

</body>
</html>