<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>

    .error {
        color: red;
    }

    table {
        width: 50%;
        border-collapse: collapse;
        border-spacing: 0px;
    }

    table td {
        border: 1px solid #565454;
        padding: 20px;
    }

    table.sortable thead {
        background-color: #eee;
        color: #666666;
        font-weight: bold;
        cursor: default;
    }
</style>
<head>
    <title>User Info</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>User Info</h2></caption>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Fio</th>
            <th>Role</th>
        </tr>
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.fio}"/></td>
            <td><c:out value="${user.role}"/></td>
        </tr>
    </table>
</div>
</body>
</html>
