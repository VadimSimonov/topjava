<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Meals</title>
    <jsp:useBean id="meal" type="java.util.ArrayList" scope="request"/>
</head>
<body>
<section>
    <c:set var="id" value='<%=request.getParameter("id")%>'></c:set>
    <form method="post" action="meal?id=${id}&action=edit" enctype="application/x-www-form-urlencoded">
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Дата</th>
            <th>Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th>exceed</th>
        </tr>
                <tr>
                    <td><input type="date" name="date" value="${meal.get(id).localDate}"></td>
                    <td><input type="date" name="time" value="${meal.get(id).localTime}"></td>
                    <td><input name="description" value="${meal.get(id).description}"></td>
                    <td><input name="calories" value="${meal.get(id).calories}"></td>
                    <td><input name="exceed" value="${meal.get(id).exceed}"></td>
                </tr>
    </table>
    <button type="submit">Сохранить</button>
    <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>
