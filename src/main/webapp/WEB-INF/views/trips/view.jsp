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
            <h3>
                ${tripObject.title}
            </h3>
            ${tripObject.description}
            <br/>
            <spring:message code='text.tripcreator'/>:
            <a href="/profile/view/${tripObject.organiser.id}" class="active">${tripObject.organiser.name}</a>
            <br/>


            <c:if test="${tripObject.organiser.id == userObject.id}">
                <a href="/trips/edittrip/${tripObject.id}" class="active"><spring:message code='text.edittrip'/></a>
            </c:if>
            <c:if test="${tripObject.organiser.id != userObject.id}">
                Zo, jij wil deze trip aanpassen? MAG NIET!
            </c:if>

        </section>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.stops'/></h3>
                <%--<form method="get" action="/trips/addstop/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <a href="/trips/addstop/${tripObject.id}" class="active"><spring:message code='text.add'/></a>
                <c:choose>
                    <c:when test="${!empty tripObject.stops}">
                        <ul class='sortable'>
                            <c:forEach var="stop" items="${tripObject.stops}">
                                <li id="stop-${stop.id}">${stop.stopnumber}: <a href="/trips/editStop/${stop.id}"
                                                                                class="active"><c:out
                                        value="${stop.name}"/></a></li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.nostopsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.requirements'/></h3>
                <%--<form method="get" action="/trips/addRequirement/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <a href="/trips/addrequirement/${tripObject.id}" class="active"><spring:message code='text.add'/></a>
                <c:choose>
                    <c:when test="${!empty tripObject.requirements}">
                        <table>
                            <c:forEach var="requirement" items="${tripObject.requirements}">
                                <tr>
                                    <td><a href="/trips/editTriprequirement/${requirement.id}"
                                           class="active">${requirement.name}</a></td>
                                    <td>${requirement.amount}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">${requirement.description}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <br/>
                        <spring:message code='text.norequirementsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.instances'/></h3>
                <%--<form method="post" action="/trips/addinstance/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <a href="/trips/addinstance/${tripObject.id}" class="active"><spring:message code='text.add'/></a>
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
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<script src="/js/functions.js"></script>
<script>makesortable();


</script>
</body>
</html>
