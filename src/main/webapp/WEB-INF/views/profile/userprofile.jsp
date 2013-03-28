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
    <link href="/css/jquery.dataTables.css" rel="stylesheet"/>

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
                                <c:when test="${userObject.profilePicture != null && userObject.FBUserID == null}">
                                    <img src="${userObject.profilePicture}" width="150"
                                         class="profilepic"/>
                                </c:when>
                                <c:when test="${userObject.FBUserID != null}">
                                    <img src="https://graph.facebook.com/${userObject.FBUserID}/picture?type=large" width="150"
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
                                <spring:message code='text.dateofbirth'/>: <c:out value="${dob}"/>
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
                        <table style="width: 100%" class="sorting">
                            <caption><spring:message code="tripinstance.participated"/></caption>
                            <thead>
                            <tr>
                                <th><spring:message code="trip.name"/></th>
                                <th><spring:message code="trip.description"/></th>
                                <th><spring:message code="text.date"/></th>
                                <th><spring:message code="tripinstance.starttime"/></th>
                                <th><spring:message code="tripinstance.endtime"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="tripInstance" items="${userFutureTripInstances}">
                                <tr>
                                    <td><a href="/trip/view/${tripInstance.id}" class="active">
                                        <c:out value="${tripInstance.title}"/>
                                    </a></td>
                                    <td>${tripInstance.description}</td>
                                    <td>${tripInstanceDates.get(tripInstance.id)}</td>
                                    <td>${tripInstanceStartTimes.get(tripInstance.id)}</td>
                                    <td>${tripInstanceEndTimes.get(tripInstance.id)}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tr>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="5"><spring:message code="tripinstance.pasttrips"/></td>
                            </tr>

                            <c:forEach var="tripInstance" items="${userPastTripInstances}">
                                <tr>
                                    <td><a href="/trip/view/${tripInstance.id}" class="active">
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
                <c:otherwise>
                    <jsp:forward page="/error/invaliduser"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>
