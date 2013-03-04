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
        <title><spring:message code='text.viewtripinformation'/></title>
        <link href="/css/blue.css" rel="stylesheet"/>

    </head>
    <body>
        <div id="wrapper">
            <div id="topmenu" class="column dark">
                <jsp:include page="/topmenu"/>
            </div>
            <div id="content" class="column light">
                <h2><spring:message code='text.viewtripinformation'/></h2>
                <section>
                    <h2>
                        ${tripObject.id}
                        ${tripObject.title}
                    </h2>
                    ${tripObject.description}
                </section>

                <section>
                    <h2><spring:message code='text.stops'/>
                        <form method="post" action="/trips/addstop/${tripObject.id}">
                            <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                        </form></h2>

                        <c:choose>
                            <c:when test="${!empty stopListObject}">
                                <table>
                                    <c:forEach var="stop" items="${stopListObject}">
                                        <tr>
                                            <td>${stop.id}</td>
                                            <td>${stop.stopnumber}</td>
                                            <td>${stop.type}</td>
                                            <td>${stop.displaymode}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <spring:message code='text.nostopsfound'/>
                            </c:otherwise>
                        </c:choose>
                </section>

                <section>
                    <h2><spring:message code='text.instances'/>
                        <form method="post" action="/trips/addinstance/${tripObject.id}">   
                            <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                        </form></h2>
                        <c:choose>
                            <c:when test="${!empty tripInstanceListObject}">
                                <table>
                                    <c:forEach var="tripInstance" items="${tripInstanceListObject}">
                                        <tr>
                                            <td>${tripInstance.id}</td> 
                                            <td>${tripInstance.description}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <spring:message code='text.notripinstancesfound'/>
                            </c:otherwise>
                        </c:choose>
                </section>

            </div>
        </div>
        <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
        <script src="/js/functions.js"></script>
    </body>
</html>
