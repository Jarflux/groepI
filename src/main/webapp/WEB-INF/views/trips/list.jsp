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
                    <form method="post" action="addinstance">
                        <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                    </form>
                </h2>
                <section>
                    <div class="row">
                        <c:choose>
                            <c:when test="${tripListObject != null}">
                                <p>
                                    <table>
                                        <c:forEach var="trip" items="${tripListObject}">
                                            <c:out value="${trip.description}"/>
                                            <tr><td>${trip.id}</td><td>${trip.name}</td><td>${trip.description}</td></tr>
                                        </c:forEach>
                                    </table>
                                </p>
                            </c:when>
                            <c:when test="${tripList == null}">
                                <p>
                                    <spring:message code='text.notripsfound' />
                                </p>
                            </c:when>
                        </c:choose>
                    </div>
                </section>
            </div>
        </div>
        <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
        <script src="/js/functions.js"></script>
    </body>
</html>
