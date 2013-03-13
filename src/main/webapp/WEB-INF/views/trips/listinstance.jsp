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
        <h2><spring:message code='text.tripinstances'/>
            <%--<form method="post" action="addinstance">
                <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
            </form>--%>
        </h2>
        <section>

            <div class="row">
                <spring:message code="text.owntripinstances"/>
                <c:choose>
                    <c:when test="${!empty ownTripInstances}">
                        <table>
                            <tr>
                                <td>ID - moet nog weg</td>
                                <td><spring:message code="text.tripname"/></td>
                                <td><spring:message code="text.tripdescription"/></td>
                                <td><spring:message code="text.tripnumberofparticipants"/></td>
                                <td><spring:message code="text.tripinstancedate"/></td>
                                <td><spring:message code="text.tripinstancestarttime"/></td>
                                <td><spring:message code="text.tripinstanceendtime"/></td>
                            </tr>
                            <c:forEach var="tripInstance" items="${ownTripInstances}">
                                <tr>
                                    <td>${tripInstance.id}</td>
                                    <td><a href="/trips/viewinstance/${tripInstance.id}" class="active">
                                            ${tripInstance.title}</a></td>
                                    <td>${tripInstance.description}</td>
                                    <td>${tripInstance.participants.size()}</td>
                                    <td>${ownTripInstanceDates.get(tripInstance.id)}</td>
                                    <td>${ownTripInstanceStartTimes.get(tripInstance.id)}</td>
                                    <td>${ownTripInstanceEndTimes.get(tripInstance.id)}</td>
                                    <td>
                                        <form method="post" action="jointrip">
                                            <input type="hidden" value="${tripInstance.id}" name="tripId"/>
                                            <input type="submit" class="button"
                                                   value="<spring:message code='text.jointrip'/>"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${ownTripInstanceListObject == null}">tripInstanceList == null</c:when>
                            <c:otherwise><spring:message code='text.notripinstancesfound'/></c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
            <br/>
            <br/>
            <c:forEach begin="1" end="${ownTripInstances.size()}">
                <br/>
                <br/>
            </c:forEach>

            <div class="row">
                <spring:message code="text.publictripinstances"/>
                <c:choose>
                    <c:when test="${!empty publicTripInstances}">
                        <table>
                            <tr>
                                <td>ID - moet nog weg</td>
                                <td><spring:message code="text.tripname"/></td>
                                <td><spring:message code="text.tripdescription"/></td>
                                <td><spring:message code="text.tripnumberofparticipants"/></td>
                                <td><spring:message code="text.tripinstancedate"/></td>
                                <td><spring:message code="text.tripinstancestarttime"/></td>
                                <td><spring:message code="text.tripinstanceendtime"/></td>
                            </tr>
                            <c:forEach var="tripInstance" items="${publicTripInstances}">
                                <tr>
                                    <td>${tripInstance.id}</td>
                                    <td><a href="/trips/viewinstance/${tripInstance.id}" class="active">
                                            ${tripInstance.title}</a></td>
                                    <td>${tripInstance.description}</td>
                                    <td>${tripInstance.participants.size()}</td>
                                    <td>${publicTripInstanceDates.get(tripInstance.id)}</td>
                                    <td>${publicTripInstanceStartTimes.get(tripInstance.id)}</td>
                                    <td>${publicTripInstanceEndTimes.get(tripInstance.id)}</td>
                                        <%--<c:forEach var="date" items="${publicTripInstanceDates}">
                                            <c:choose>
                                                <c:when test="${date.key == tripInstance.id}">
                                                    <td>${date.value}</td>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        <c:forEach var="startTime" items="${publicTripInstanceStartTimes}">
                                            <c:choose>
                                                <c:when test="${startTime.key == tripInstance.id}">
                                                    <td>${startTime.value}</td>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        <c:forEach var="endTime" items="${publicTripInstanceEndTimes}">
                                            <c:choose>
                                                <c:when test="${endTime.key == tripInstance.id}">
                                                    <td>${endTime.value}</td>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        <td><a href="/trips/viewinstance/${tripInstance.id}" class="active"><spring:message
                                                code='text.detail'/></a></td>  --%>
                                    <td>
                                        <form method="post" action="jointrip">
                                            <input type="hidden" value="${tripInstance.id}" name="tripId"/>
                                            <input type="submit" class="button"
                                                   value="<spring:message code='text.jointrip'/>"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${publicTripInstanceListObject == null}">tripInstanceList == null</c:when>
                            <c:otherwise><spring:message code='text.notripinstancesfound'/></c:otherwise>
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