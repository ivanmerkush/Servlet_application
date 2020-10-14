<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.0/cerulean/bootstrap.min.css" rel="stylesheet" integrity="sha384-b+jboW/YIpW2ZZYyYdXczKK6igHlnkPNfN9kYAbqYV7rNQ9PKTXlS2D6j1QZIATW" crossorigin="anonymous">
    <title>Our atelie</title>
</head>
<jsp:include page="buttonsGroup.jsp" />
<body>


<div class="jumbotron">
    <table class="table table-hover">
        <c:choose>
            <c:when test="${sessionScope.model == 'customer' || sessionScope.model == null}">
                <h2 class="display-4">
                    Customers
                </h2>
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Birth</th>
                    <th scope="col">Delete</th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="customersList" class="ivanmerkush.entity.Customers" scope="session"/>
                <jsp:setProperty property="*" name="customersList"/>
                <c:set var="list" value="${customersList.customers}" />
                <c:forEach items="${list}" var="customer">
                    <form method="GET" action="${pageContext.request.contextPath}/main">
                        <tr>
                            <td>${customer.idCustomer}</td>
                            <td><label>
                                <input type="text" name="name" value="${customer.name}" readonly>
                            </label></td>
                            <td>${customer.birth}</td>
                            <td><input type="submit" class="btn btn-dark" name="action" value="Delete Customer"></td>
                        </tr>
                    </form>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2 class="display-4">
                    Orders
                </h2>
            <thead>
            <tr>
                <th scope="col">Id Order</th>
                <th scope="col">Clothing</th>
                <th scope="col">Price</th>
                <th scope="col">Term</th>
                <th scope="col">Id Customer</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
                <tbody>
                    <jsp:useBean id="ordersList" class="ivanmerkush.entity.Orders" scope="session"/>
                    <jsp:setProperty property="*" name="ordersList"/>
                    <c:choose>
                        <c:when test="${sessionScope.special == null}">
                            <c:set var="orders" value="${ordersList.orders}" />
                        </c:when>
                        <c:otherwise>
                            <c:if test="${sessionScope.special == 'name'}">
                                <c:set var="orders" value="${ordersList.getOrdersByName(sessionScope.name)}" />
                            </c:if>
                            <c:if test="${sessionScope.special == 'time'}">
                                <c:set var="orders" value="${ordersList.getOrdersByDate(sessionScope.lowerDate, sessionScope.upperDate)}" />
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach items="${orders}" var="order">
                    <form method="GET" action="${pageContext.request.contextPath}/main">
                        <tr>
                            <td>${order.idOrder}</td>
                            <td><label>
                                <input type="text" name="clothing" value="${order.clothing}" readonly>
                            </label></td>
                            <td><label>
                                <input type="text" name="price" value="${order.price}" readonly>
                            </label></td>
                            <td>${order.term}</td>
                            <td>${order.idCustomer}</td>
                            <td><input type="submit" class="btn btn-dark" name="action" value="Delete Order"></td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </c:otherwise>
        </c:choose>

    </table>
</div>
</body>
</html>