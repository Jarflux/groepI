<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 9/02/13
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code='trip.viewinformation'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>
    <link href="/css/jquery.dataTables.css" rel="stylesheet"/>

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code='trip.trips'/>
            <form method="post" action="add">
                <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
            </form>
        </h2>
        <section>
            <div class="row">
                <table class="sorting">
                    <c:choose>
                        <c:when test="${!empty ownTrips}">
                            <caption><spring:message code="trip.owntrips"/></caption>
                            <thead>
                            <tr>
                                <th><spring:message code="trip.name"/></th>
                                <th><spring:message code="trip.description"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="ownTrips" items="${ownTrips}">
                                <tr>
                                    <td><a href="/template/view/${ownTrips.id}" class="active">${ownTrips.title}</a>
                                    </td>
                                    <td>${ownTrips.description}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </c:when>
                        <c:otherwise>
                            <br/>
                            <caption><spring:message code='trip.noowntripsfound'/></caption>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
            <br/>
            <c:forEach begin="1" end="${ownTrips.size()}">
                <br/>
                <%--TODO: fix this? (zorgt ervoor dat beide lijsten niet boven elkaar staan ... maar wel geen goeie manier)--%>
            </c:forEach>
            <div class="row">
                <table class="sorting">
                    <c:choose>
                        <c:when test="${!empty publicTrips}">
                            <caption><spring:message code="trip.publictrips"/></caption>
                            <thead>
                            <tr>
                                <th><spring:message code="trip.name"/></th>
                                <th><spring:message code="trip.description"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="publicTrip" items="${publicTrips}">
                                <tr>
                                    <td><a href="/template/view/${publicTrip.id}" class="active">${publicTrip.title}</a>
                                    </td>
                                    <td>${publicTrip.description}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </c:when>
                        <c:otherwise>
                            <br/>
                            <caption><spring:message code='trip.nopublictripsfound'/></caption>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </section>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/functions.js"></script>
<script>

    preparetables();
</script>
</body>
</html>