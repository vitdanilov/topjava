<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border=1>
    <th>Наименование</th>
    <th>Каллории</th>
    <th>Дата</th>

    <c:forEach var="meal" items="${mealsTo}">
        <tr>
            <td><font color=
                      <c:choose>
                              <c:when test="${meal.isExcess()== 'true'}">red</c:when>
                      <c:otherwise>black</c:otherwise>
            </c:choose>
            >${meal.getDescription()} </font></td>
            </td>

            <td>
                <font color=
                      <c:choose>
                              <c:when test="${meal.isExcess()== 'true'}">red</c:when>
                      <c:otherwise>black</c:otherwise>
                </c:choose>
                >${meal.getCalories()}</font>
            </td>

            <td>
                <font color=
                      <c:choose>
                              <c:when test="${meal.isExcess()== 'true'}">red</c:when>
                      <c:otherwise>black</c:otherwise>
                </c:choose>
                >${meal.getDateTime().toLocalDate()} ${meal.getDateTime().toLocalTime()}</font>
            </td>


        </tr>
    </c:forEach>


</table>


</body>
</html>