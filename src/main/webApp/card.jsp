<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Wallet</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Register a new car</h2>

    <div class="container">
        <form:form method="POST" modelAttribute="cardForm" class="navbar-form">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <spring:bind path="number">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="number" path="number" class="form-control" placeholder="Card Number"
                                autofocus="true"/>
                    <form:errors class="error" path="number"/>
                </div>
            </spring:bind>
            <spring:bind path="month">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:select class="form-control" path="month" id="month" name="month"
                                 placeholder="Expiration month">
                        <option value="1" selected>January</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">July</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </form:select>
                    <form:errors class="error" path="month"/>
                </div>
            </spring:bind>
            <spring:bind path="year">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="number" path="year" id="year" name="year"
                                placeholder="Expiration year"/>
                    <form:errors class="error" path="year"/>
                </div>
            </spring:bind>
            <spring:bind path="secret">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="number" path="secret" class="form-control" placeholder="Secret number"/>
                    <form:errors class="error" path="secret"/>
                </div>
            </spring:bind>

            <button class="btn btn-primary" type="submit">Save</button>
            <a class="btn btn-outline-info" href="${contextPath}/dashboard">Cancel</a>
        </form:form>

    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
