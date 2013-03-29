<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 9/02/13
  Time: 16:26
  To change this template use File | Settings | File Templates.

  !! CAN CONTAIN ERRORS !! - WORK IN PROGRESS BY BEN OEYEN
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
                <div class="full">
                    <section>
                        <h3><spring:message code="trip.trips"/>
                            <%--<form method="post" action="add">
                                <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                            </form>--%></h3>
                        <caption><h4><spring:message code="tripinstance.owntripinstances"/></h4></caption>
                        <table style="width: 100%" class="sorting">                   
                            <thead>
                                <tr>
                                    <th style="width: 20%"><spring:message code="trip.name"/></th>
                                    <th style="width: 35%"><spring:message code="trip.description"/></th>
                                    <th style="width: 10%"><spring:message code="tripinstance.numberofparticipants"/></th>
                                    <th style="width: 15%"><spring:message code="text.date"/></th>
                                    <th style="width: 10%"><spring:message code="tripinstance.starttime"/></th>
                                    <th style="width: 10%"><spring:message code="tripinstance.endtime"/></th>
                                </tr>
                            </thead>
                            <tbody>
                        <c:forEach var="tripInstance" items="${ownTripInstances}">
                                    <tr>
                                        <td><a href="/trip/view/${tripInstance.id}" class="active">
                                                ${tripInstance.title}</a></td>
                                        <td>${tripInstance.description}</td>
                                        <td>${tripInstance.participants.size()}</td>
                                        <td>${ownTripInstanceDates.get(tripInstance.id)}</td>
                                        <td>${ownTripInstanceStartTimes.get(tripInstance.id)}</td>
                                        <td>${ownTripInstanceEndTimes.get(tripInstance.id)}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <p></p>
                        <caption><h4><spring:message code="trip.publictrips"/></h4></caption>
                        <table style="width: 100%" class="sorting">

                            <thead>
                                <tr>
                                    <th style="width: 20%"><spring:message code="trip.name"/></th>
                                    <th style="width: 35%"><spring:message code="trip.description"/></th>
                                    <th style="width: 10%"><spring:message code="tripinstance.numberofparticipants"/></th>
                                    <th style="width: 15%"><spring:message code="text.date"/></th>
                                    <th style="width: 10%"><spring:message code="tripinstance.starttime"/></th>
                                    <th style="width: 10%"><spring:message code="tripinstance.endtime"/></th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="tripInstance" items="${publicTripInstances}">
                                    <tr>
                                        <td><a href="/trip/view/${tripInstance.id}" class="active">
                                                ${tripInstance.title}</a></td>
                                        <td>${tripInstance.description}</td>
                                        <td>${tripInstance.participants.size()}</td>
                                <td>${publicTripInstanceDates.get(tripInstance.id)}</td>
                                <td>${publicTripInstanceStartTimes.get(tripInstance.id)}</td>
                                <td>${publicTripInstanceEndTimes.get(tripInstance.id)}</td>
                                <td>
                                        <c:forEach var="participatingBoolean" items="${isUserParticipating}">
                                            <c:if test="${(participatingBoolean.key eq tripInstance.id)
                                                          && (participatingBoolean.value eq false)}">
                                        <form method="post" action="/trip/join">
                                            <input type="hidden" value="${tripInstance.id}" name="tripId"/>
                                            <input type="submit" class="button"
                                                   value="<spring:message code='tripinstance.join'/>"/>
                                        </form>
                                    </c:if>
                                </c:forEach>
                                </td>
                                </tr>                            
                            </c:forEach>
                            </tbody>
                        </table>
                        <br style="clear: both">
                    </section>
                </div>
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