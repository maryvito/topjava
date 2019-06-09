<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Meals</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Meal Id</th>
        <th>Calories</th>
        <th>Description</th>
        <th>Date</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealTos}" var="mealTo">
        <tr>
            <td><c:out value="${mealTo.getId()}" /></td>
            <td><c:out value="${mealTo.getCalories()}" /></td>
            <td><c:out value="${mealTo.getDescription()}" /></td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${meal.getDateTime()}" /></td>
            <td><c:out value="${mealTo.isExcess()}" /></td>
            <td><a href="meals?action=edit&mealId=<c:out value="${mealTo.getId()}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="meals?action=insert">Add Meal</a></p>
</body>
</html>
