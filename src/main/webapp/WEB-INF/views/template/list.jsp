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
                <div class="full">
                    <section>
                        <h3><spring:message code="trip.trips"/>
                            <form method="post" action="add">
                                <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                            </form></h3>
                        <caption><h4><spring:message code="trip.owntrips"/></h4></caption>
                        <table style="width: 100%" class="sorting">                   
                            <thead>
                                <tr>
                                    <th style="width: 30%"><spring:message code="trip.name"/></th>
                                    <th style="width: 50%"><spring:message code="trip.description"/></th>
                                    <th style="width: 20%"><spring:message code="tripinstance.organiser"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="trip" items="${ownTrips}">
                                    <tr>
                                        <td><a href="/template/view/${trip.id}" class="active">
                                                <c:out value="${trip.title}"/>
                                            </a></td>
                                        <td>${trip.description}</td>
                                        <td>${trip.organiser.name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <p></p>
                        <caption><h4><spring:message code="trip.publictrips"/></h4></caption>
                        <table style="width: 100%" class="sorting">

                            <thead>
                                <tr>
                                    <th style="width: 30%"><spring:message code="trip.name"/></th>
                                    <th style="width: 50%"><spring:message code="trip.description"/></th>
                                    <th style="width: 20%"><spring:message code="tripinstance.organiser"/></th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="trip" items="${publicTrips}">
                                    <tr>
                                        <td><a href="/trip/view/${trip.id}" class="active">
                                                <c:out value="${trip.title}"/>
                                            </a></td>
                                        <td>${trip.description}</td>
                                        <td>${trip.organiser.name}</td>
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