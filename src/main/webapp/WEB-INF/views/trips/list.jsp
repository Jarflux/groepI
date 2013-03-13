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
    <title><spring:message code='text.viewtripinformation'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code='text.trips'/>
            <form method="post" action="addtrip">
                <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
            </form>
        </h2>
        <section>
            <div class="row">
                <spring:message code="text.owntrips"/>
                <c:choose>
                    <c:when test="${!empty ownTrips}">
                        <table>
                            <tr>
                                <td><spring:message code="text.tripname"/></td>
                                <td><spring:message code="text.tripdescription"/></td>
                                <td>tripInstancesCount? Wat kan er nog bij?</td>
                            </tr>
                            <c:forEach var="ownTrips" items="${ownTrips}">
                                <tr>
                                    <td><a href="/trips/view/${ownTrips.id}" class="active">${ownTrips.title}</a></td>
                                    <td>${ownTrips.description}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.noowntripsfound'/>
                    </c:otherwise>
                </c:choose>
            </div>
            <br/>
            <c:forEach begin="1" end="${ownTrips.size()}">
                <br/>
                <%--TODO: fix this? (zorgt ervoor dat beide lijsten niet boven elkaar staan ... maar wel geen goeie manier)--%>
            </c:forEach>
            <div class="row">
                <spring:message code="text.publictrips"/>
                <c:choose>
                    <c:when test="${!empty publicTrips}">
                        <table>
                            <tr>
                                <td><spring:message code="text.tripname"/></td>
                                <td><spring:message code="text.tripdescription"/></td>
                                <td>wat kan er nog bij?</td>
                            </tr>
                            <c:forEach var="publicTrip" items="${publicTrips}">
                                <tr>
                                    <td><a href="/trips/view/${publicTrip.id}" class="active">${publicTrip.title}</a>
                                    </td>
                                    <td>${publicTrip.description}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.notripsfound'/>
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