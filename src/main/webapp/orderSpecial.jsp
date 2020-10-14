<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.0/cerulean/bootstrap.min.css" rel="stylesheet" integrity="sha384-b+jboW/YIpW2ZZYyYdXczKK6igHlnkPNfN9kYAbqYV7rNQ9PKTXlS2D6j1QZIATW" crossorigin="anonymous">
    <title>Specialized request</title>
</head>
<jsp:include page="buttonsGroup.jsp" />
<body>
    <c:choose>
        <c:when test="${sessionScope.special == 'name'}">
            <h2 class="display-4">
                Choose name
            </h2>

            <jsp:useBean id="namesList" class="ivanmerkush.entity.Customers" scope="session"/>
            <jsp:setProperty property="*" name="namesList"/>
            <c:set var="namesList" value="${namesList.namesOfCustomers}" />
            <form style="text-align: center" method="GET" action="${pageContext.request.contextPath}/main">
                <p>
                    <select name="name" style="width: 200px;">
                        <c:forEach items="${namesList}" var="name">
                            <option> ${name}</option>
                        </c:forEach>
                    </select>
                </p>
                <input type="submit" class="btn btn-dark" name="action" value="Get Orders By Name">
            </form>
        </c:when>
        <c:otherwise>
            <h2 class="display-4">
                Choose name
            </h2>
            <form style="text-align: center" method="GET" action="${pageContext.request.contextPath}/main">
                <p>
                    <input type="text" name="lowerDate" placeholder="Lower Date">
                </p>
                <p>
                    <input type="text" name="upperDate" placeholder="Upper Date">
                </p>
                <input type="submit" class="btn btn-dark" name="action" value="Get Orders By Date">
            </form>
        </c:otherwise>
    </c:choose>
</body>