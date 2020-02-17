<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<a href="users?action=create">Add User</a>
<br><br>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Имя</th>
        <th>Почта</th>
        <th>Роли</th>
        <th>Активный</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.getRoles()}</td>
            <td><input type="checkbox" ${user.isEnabled() ? 'checked' : ''}></td>
            <td><a href="users?action=update&id=${meal.id}">Update</a></td>
            <td><a href="users?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>