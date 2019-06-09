
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>meals</title>
</head>
<body>
<style>
    .green{color: green}
    .red{color: red}
</style>
<h3><a href="index.jsp">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <!-- here should go some titles... -->
    <tr>
        <th>Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
    </tr>
    <c:forEach var="mealTo" items="${mealsTo}" >
        <tr>
            <td>
                <c:out value="${mealTo.getFormatedDateTime()}" />
            </td>
            <td>
                <c:out value="${mealTo.getDescription()}" />
            </td>
            <td>
                <c:out value="${mealTo.getCalories()}" />
            </td>
            <td class="${mealTo.isExcess() ? 'red':'green'}">
                <c:out value="${mealTo.isExcess()}" />
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
