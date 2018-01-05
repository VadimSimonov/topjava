<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealsDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
<%--    <h3><spring:message code="meal.title"/></h3>

    <form method="post" action="meals/filter">
        <dl>
            <dt><spring:message code="meal.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="meal.filter"/></button>
    </form>
    <hr>
    <a href="meals/create"><spring:message code="meal.add"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="meal.dateTime"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        &lt;%&ndash;${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}&ndash;%&gt;
                        &lt;%&ndash;<%=TimeUtil.toString(meal.getDateTime())%>&ndash;%&gt;
                        &lt;%&ndash;${fn:replace(meal.dateTime, 'T', ' ')}&ndash;%&gt;
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>
                <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>--%>

    <div class="jumbotron">
        <div class="container">
            <h3><spring:message code="meal.title"/></h3>



            <!-- filter form -->
                      <div class="panel panel-primary">
                            <div class="panel-heading">
                                <button class="btn btn-primary" data-toggle="collapse" data-target="#filter">filter</button>
                            </div>
                            <div class="panel-body collapse" id="filter">
                                <form method="post" action="meals/filter" id="filterForm">
                                    <div class="form-group">
                                        <label for="startDateFilter"><spring:message code="meal.startDate"/>:</label>
                                        <input name="startDate" value="${param.startDate}" id="startDateFilter" autocomplete="off"
                                                                                   onfocus="showDatePicker(this)">
                                    </div>
                                    <div class="form-group">
                                        <label for="endDateFilter"><spring:message code="meal.endDate"/>:</label>
                                        <input name="endDate" value="${param.startDate}" id="endDateFilter" autocomplete="off"
                                                                                   onfocus="showDatePicker(this)">
                                    </div>
                                    <div class="form-group">
                                        <label for="startTimeFilter"><spring:message code="meal.startTime"/>:</label>
                                        <input name="startTime" value="${param.startTime}" id="startTimeFilter" autocomplete="off"
                                                                                   onfocus="showTimePicker(this)">
                                    </div>
                                    <div class="form-group">
                                        <label for="endTimeFilter"><spring:message code="meal.endTime"/>:</label>
                                        <input name="endTime" value="${param.startTime}" id="endTimeFilter" autocomplete="off"
                                                                                   onfocus="showTimePicker(this)">
                                    </div>

                                    <a type="button" class="btn btn-primary" onclick="setFilter()"><span
                                                class="glyphicon glyphicon-filter"></span></a>
                                    <a type="button" class="btn btn-danger" onclick="clearFilter()"><span
                                                class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                                </form>
                            </div>
                        </div>
                        <!-- filter form -->


            <br/>
            <a class="btn btn-primary" onclick="add()">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                <spring:message code="common.add"/>
            </a>
            <table class="table table-striped display" id="datatable">
                <thead>
                <tr>
                    <th><spring:message code="meal.dateTime"/></th>
                    <th><spring:message code="meal.description"/></th>
                    <th><spring:message code="meal.calories"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

            <%--    <c:forEach items="${meals}" var="meal">
                    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                    <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                        <td>
                            &lt;%&ndash;${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}&ndash;%&gt;
                            &lt;%&ndash;<%=TimeUtil.toString(meal.getDateTime())%>&ndash;%&gt;
                            &lt;%&ndash;${fn:replace(meal.dateTime, 'T', ' ')}&ndash;%&gt;
                                ${fn:formatDateTime(meal.dateTime)}
                        </td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>
                        <td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>
                    </tr>
                </c:forEach>
            </table>&ndash;%&gt;
            --%>
                        <c:forEach items="${meals}" var="meal">
                            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                                <td>
                                        ${fn:formatDateTime(meal.dateTime)}
                                </td>
                                <td>${meal.description}</td>
                                <td>${meal.calories}</td>
                                <td><a><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                                <td><a class="delete"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

        </section>
        <jsp:include page="fragments/footer.jsp"/>
        </body>
        </html>