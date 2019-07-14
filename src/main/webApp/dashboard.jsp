<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Welcome Wallet</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h2>Welcome ${pageContext.request.userPrincipal.name}
        <a class="btn btn-outline-info float-right" onclick="document.forms['logoutForm'].submit()">Logout</a>
    </h2>

    <form method="GET" action="/dashboard" class="navbar-form">
        <div class="input-group add-on">
            <input class="form-control" placeholder="search" name="searchTerm" id="searchTerm" type="number"
                   value="${searchTerm}">
            <button class="btn btn-outline-secondary" type="submit">Search</button>
        </div>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Card Number</th>
            <th scope="col">Expiration Date</th>
            <th scope="col">Secret</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cardList}" var="item">
            <tr>
                <td><c:out value="${item.number}"/></td>
                <td><c:out value="${item.month}/${item.year}"/></td>
                <td><c:out value="${item.secret}"/></td>
                <td>
                    <form method="POST" action="${contextPath}/card/${item.id}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button class="btn btn-danger" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-primary" href="${contextPath}/card">Add a new card</a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>