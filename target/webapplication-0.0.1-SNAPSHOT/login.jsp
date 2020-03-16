<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<body class="text-center">
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
<h1>Login Form</h1>

<form name='login' action=login method='post'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value='' autofocus="true"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit"/></td>
        </tr>
    </table>
    <c:if test="${not empty errMsg}">
        <h4 class="error" style="width: 900px">${errMsg}</h4>
    </c:if>
</form>
</body>


</html>