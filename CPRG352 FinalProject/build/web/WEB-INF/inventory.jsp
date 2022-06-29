<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>

        <h1>Home Inventory</h1>

        <h4>Menu</h4>

        <a href="inventory?action=inventory">My Inventory</a><br>
        <a href="account?action=settings">Account Settings</a><br>
        <a href="login?action=logout">Logout</a><br>


        <h2>Inventory for ${user.firstName} ${user.lastName}</h2>
        <table cellpadding="7" border="1">
            <tr>
                <td>Category</td>
                <td>Name</td>
                <td>Price</td>
            </tr>
            <c:forEach var="items" items="${itemList}">
                <tr>
                <form method="POST" action="inventory">
                    <td name="category" value="">${items.category.categoryName}</td>
                    <td name="name" value="">${items.itemName}</td>
                    <td name="price" value="">$${items.price}</td>
                    <td><input type="submit" value="Delete" name="">
                        <input type="hidden" value="delete" name="action">
                        <input type="hidden" name="deleteButton" value="${items.itemId}">
                    </td>
                </form>
                <form method="POST" action="inventory">
                    <td><input type="submit" value="Edit" name="">
                        <input type="hidden" value="edit" name="action">
                        <input type="hidden" name="editButton" value="${items.itemId}">
                    </td>  
                </form>    
            </tr>
        </c:forEach>
    </table>

    <h3>${addOrEdit} Item</h3>
    <form method="POST" action="inventory">

        <select name="add/EditCategory" value="${itemForEdit.category.categoryId}">
            <c:forEach items="${categoriesList}" var="category">
                <option value="${category.categoryId}">${category.categoryName}</option>
            </c:forEach>
        </select>
        <br>
        Name: <input type="text" name="add/EditName" value="${itemForEdit.itemName}"></input>
        <br>
        Price: <input type="number" name="add/EditPrice" value="${itemForEdit.price}" min="0"></input>
        <br>

        <input type="submit" value="Save"></input>
        <input type="hidden" name="action" value="save"></input>
    </form>
    <p>${responseMessage}</p>
</body>
</html>
