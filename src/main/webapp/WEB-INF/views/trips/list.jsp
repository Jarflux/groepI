<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <title><spring:message code='text.viewtripinformation' /></title>
        <link href="/css/blue.css" rel="stylesheet" />

    </head>
    <body>
        <div id="wrapper">
            <div id="topmenu" class="column dark">
                <jsp:include page="/topmenu" />
            </div>
            <div id="content" class="column light">
                <h2> <spring:message code='text.trips'/>
                    <form method="post" action="addTrip">
                        <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                    </form>
                </h2>
                <section>
                    <div class="row">
                        <c:choose>
                            <c:when test="${!empty tripListObject}">
                                    <table>
                                        <c:forEach var="trip" items="${tripListObject}">
                                            <tr><td>${trip.id}</td><td>${trip.title}</td><td>${trip.description}</td><td><a href="/trips/view/${trip.id}" class="active"><spring:message code='text.detail'/></a></td></tr>
                                        </c:forEach>
                                    </table>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                <c:when test="${tripListObject == null}">tripList == null</c:when>
                                <c:otherwise><spring:message code='text.notripsfound' /></c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </section>
            </div>
        </div>
        <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
        <script src="/js/functions.js"></script>
    </body>
</html>
