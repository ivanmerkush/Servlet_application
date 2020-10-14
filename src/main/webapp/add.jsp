<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.0/cerulean/bootstrap.min.css" rel="stylesheet" integrity="sha384-b+jboW/YIpW2ZZYyYdXczKK6igHlnkPNfN9kYAbqYV7rNQ9PKTXlS2D6j1QZIATW" crossorigin="anonymous">
    <title>Add page</title>
</head>
<jsp:include page="buttonsGroup.jsp" />
<body>
<div class="jumbotron">
    <c:choose>
        <c:when test="${sessionScope.model == 'customer'}">
            <form style="text-align: center;" method="POST"  action="${pageContext.request.contextPath}/main">
                <p >
                    <label> <input type="text" name="name" value="" placeholder="Name of Customer"> </label>
                </p>
                <input type="submit" class="btn btn-dark" name="action" value="add">
            </form>
        </c:when>
        <c:otherwise>
            <jsp:useBean id="namesList" class="ivanmerkush.entity.Customers" scope="session"/>
            <jsp:setProperty property="*" name="namesList"/>
            <c:set var="namesList" value="${namesList.namesOfCustomers}" />
            <form  style="text-align: center;" method="POST"  action="${pageContext.request.contextPath}/main">
                <p>
                    <select name="name" style="width: 200px;">
                        <c:forEach items="${namesList}" var="name">
                            <option> ${name}</option>
                        </c:forEach>
                    </select>
                </p>
                <p>
                    <label> <input type="text" name="clothing" value="" placeholder="Clothing"> </label>
                </p>
                <p>
                    <label> <input type="text" name="price" value="" placeholder="Price"> </label>
                </p>
                <p>
                    <label> <input type="text" name="term" value="" placeholder="Term"> </label>
                </p>
                <input type="submit" class="btn btn-dark" name="action" value="add">
            </form>
        </c:otherwise>
    </c:choose>
</div>
</body>