<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>

</head>
<body>
<h2><a href="index.html">Home</a></h2>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Дата</th>
            <th>Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th>exceed</th>
            <th>Редактирование</th>
            <th>Удаление</th>
        </tr>
    <c:forEach items="${meal}" var="meal" varStatus="isindex">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <c:set var="exceed" value="${meal.exceed}"></c:set>
        <c:set var="id" value="${isindex.index+1}" />
        <c:if test="${exceed==true}">
            <tr>
                <td style="background-color:red;color:white;">${meal.localDate}</td>
                <td style="background-color:red;color:white;">${meal.localTime}</td>
                <td style="background-color:red;color:white;">${meal.description}</td>
                <td style="background-color:red;color:white;">${meal.calories}</td>
                <td style="background-color:red;color:white;">${meal.exceed}</td>
                <td><a href="meal?id=${id-1}&action=edit"><img src="img/pencil.png"></a></td>
                <td><a href="meal?id=${id-1}&action=delete"><img src="img/delete.png"></a></td>
            </tr>
        </c:if>
        <c:if test="${exceed==false}">
        <tr>
        <td style="background-color:green;color:white;">${meal.localDate}</td>
        <td style="background-color:green;color:white;">${meal.localTime}</td>
        <td style="background-color:green;color:white;">${meal.description}</td>
        <td style="background-color:green;color:white;">${meal.calories}</td>
        <td style="background-color:green;color:white;">${meal.exceed}</td>
        <td><a href="meal?id=${id-1}&action=edit"><img src="img/pencil.png"></a></td>
        <td><a href="meal?id=${id-1}&action=delete"><img src="img/delete.png"></a></td>
        </tr>
        </c:if>
    </c:forEach>
    </table>
</section>
</body>
</html>
