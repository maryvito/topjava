<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />

    <title>Add new user</title>
</head>
<body>

<form method="POST" action='meals' name="frmAddMeal" charset="UTF-8">
    Meal ID : <input type="text" readonly="readonly" name="mealId"
                     value="<c:out value="${mealTo.getId()}" />" /> <br />
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${mealTo.getCalories()}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${mealTo.getDescription()}" />" /> <br />
    Date : <input
        type="text" name="date"
        <fmt:parseDate value="${ mealTo.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
        value="<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />" /> <br />

    <input type="submit" value="Submit" />
</form>
</body>
</html>
