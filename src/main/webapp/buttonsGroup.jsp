<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<header>
    <div class="row col-md-12">
        <div class="form-group">
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/main?model=customer">Show Customers</a>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/main?action=getAll&model=order" >Show Orders</a>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/main?action=get&model=order&special=name" >Orders for one Customer</a>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/main?action=get&model=order&special=time" >Special Orders</a>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/main?action=add&model=customer">Add Customer</a>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/main?action=add&model=order">Add Order</a>
        </div>
    </div>
</header>
</html>