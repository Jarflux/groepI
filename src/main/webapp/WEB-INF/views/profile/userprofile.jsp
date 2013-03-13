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
    <title><spring:message code='text.viewprofile'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code='text.dashboard'/></h2>

        <div class="full">
            <c:choose>
                <c:when test="${userObject != null}">
                    <section>

                        <h3><spring:message code='text.mydata'/></h3>

                        <div class="quarter">
                            <c:choose>
                                <c:when test="${userObject.profilePicture == null}">
                                    <img src="/images/noprofile.jpg" width="150"
                                         class="profilepic"/>
                                </c:when>
                                <c:when test="${userObject.profilePicture != null}">
                                    <img src="${userObject.profilePicture}" width="150"
                                         class="profilepic"/>
                                </c:when>
                            </c:choose>
                        </div>

                        <div class="three-quarter">

                            <div class="row">
                                <spring:message code='text.name'/>: <c:out value="${userObject.name}"/>
                            </div>
                            <div class="row">
                                <spring:message code='text.email'/>: <c:out value="${userObject.email}"/>
                            </div>
                            <div class="row">
                                <spring:message code='text.dateofbirth'/>: <c:out value="Date of birth ${dob}"/>
                            </div>
                            <div class="row">
                                <form action="/profile/myprofile/edit">
                                    <input type="submit" class="button" value="<spring:message code='text.edit'/>"/>
                                </form>
                            </div>

                        </div>
                        <br style="clear: both"/>
                    </section>
                    <br style="clear: both"/>
                    <section>
                        <h3><spring:message code="text.mytrips"/></h3>
                        <table style="width: 100%">
                            <caption><spring:message code="text.tripsparticipated"/></caption>
                            <tr>
                                <th><spring:message code="text.tripname"/></th>
                                <th><spring:message code="text.tripdescription"/></th>
                                <th><spring:message code="text.date"/></th>
                                <th><spring:message code="text.starttime"/></th>
                                <th><spring:message code="text.endtime"/></th>
                            </tr>
                                <%--
                                                                <tr>
                                                                    <td rowspan="4"><spring:message code="text.plannedtrips"/></td>
                                                                </tr>--%>
                                <%--<tr>--%>
                            <c:forEach var="tripInstance" items="${userFutureTripInstances}">
                                <tr>
                                    <td><a href="/trips/viewinstance/${tripInstance.id}" class="active">
                                        <c:out value="${tripInstance.title}"/>
                                    </a></td>
                                    <td>${tripInstance.description}</td>
                                    <td>${tripInstanceDates.get(tripInstance.id)}</td>
                                    <td>${tripInstanceStartTimes.get(tripInstance.id)}</td>
                                    <td>${tripInstanceEndTimes.get(tripInstance.id)}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="5"><spring:message code="text.pasttrips"/></td>
                            </tr>

                            <c:forEach var="tripInstance" items="${userPastTripInstances}">
                                <tr>
                                    <td><a href="/trips/viewinstance/${tripInstance.id}" class="active">
                                        <c:out value="${tripInstance.title}"/>
                                    </a></td>
                                    <td>${tripInstance.description}</td>
                                    <td>${tripInstanceDates.get(tripInstance.id)}</td>
                                    <td>${tripInstanceStartTimes.get(tripInstance.id)}</td>
                                    <td>${tripInstanceEndTimes.get(tripInstance.id)}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br style="clear: both">
                    </section>
                </c:when>
                <%--<c:when test="${userObject == null}">--%>
                <c:otherwise>
                    <jsp:forward page="/error/invaliduser"/>
                </c:otherwise>
                <%--</c:when>--%>
            </c:choose>
        </div>
    </div>
</div>

<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>
