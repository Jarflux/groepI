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
    <title><spring:message code='trip.viewinformation'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>
    <link href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code='trip.viewinformation'/></h2>
        <section>
            <h3>
                ${tripObject.title}
            </h3>
            ${tripObject.description}
            <br/>
            <spring:message code='trip.creator'/>:
            <a href="/profile/view/${tripObject.organiser.id}" class="active">${tripObject.organiser.name}</a>
            <br/>
            <c:if test="${tripObject.organiser.id == userObject.id}">
                <a href="/template/edit/${tripObject.id}" class="active"><spring:message code='trip.edit'/></a>
            </c:if>
            <br/>
            <a href="/trip/add/${tripObject.id}" class="active"><button class="button"><spring:message code='trip.planinstance'/></button></a>
        </section>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.stops'/></h3>
                <c:if test="${tripObject.organiser.id == userObject.id}">
                    <a href="/stop/add/${tripObject.id}" class="active"><spring:message code='text.add'/></a>
                </c:if>
                <c:choose>
                    <c:when test="${!empty tripObject.stops}">
                        <ul class='sortable'>
                            <c:forEach var="stop" items="${tripObject.stops}">
                                <li id="stop-${stop.id}">${stop.stopnumber}:
                                    <c:if test="${tripObject.organiser.id == userObject.id}">
                                        <a href="/stop/edit/${stop.id}" class="active">
                                            <c:out value="${stop.name}"/>
                                        </a>
                                    </c:if>
                                    <c:if test="${tripObject.organiser.id != userObject.id}">
                                        <c:out value="${stop.name}"/>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='stop.nostopsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="quarter">
            <section>
                <h3>
                    <spring:message code='requirement.requirements'/>
                    <c:if test="${tripObject.organiser.id == userObject.id}">
                        <img src="/images/add.jpg" class="addrequirement" width="18" instid="${tripObject.id}"/>
                    </c:if>
                </h3>
                <%--<c:if test="${tripObject.organiser.id == userObject.id}">
                    <a href="/requirement/add/${tripObject.id}" class="active"><spring:message
                            code='text.add'/></a>
                </c:if>--%>
                <c:choose>
                    <c:when test="${!empty tripObject.requirements}">
                        <table>
                            <c:forEach var="requirement" items="${tripObject.requirements}">
                                <tr>
                                    <td>
                                        <c:if test="${tripObject.organiser.id == userObject.id}">
                                            <a href="/requirement/edit/${requirement.id}" class="active">
                                                <c:out value="${requirement.name}"/>
                                            </a>
                                        </c:if>
                                        <c:if test="${tripObject.organiser.id != userObject.id}">
                                            <c:out value="${requirement.name}"/>
                                        </c:if>
                                    </td>
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
                        <spring:message code='requirement.norequirementsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="half">
            <section>
                <h3><spring:message code='tripinstance.tripinstancesofthistrip'/></h3>
                <c:choose>
                    <c:when test="${!empty tripInstances}">
                        <table>
                            <c:forEach var="tripInstance" items="${tripInstances}">
                                <tr>
                                    <td>
                                        <a href="/trip/view/${tripInstance.id}" class="active">
                                            <c:out value="${tripInstance.title}"/>
                                        </a>
                                    </td>
                                    <td>${tripInstance.description}</td>
                                    <td>${tripInstanceDates.get(tripInstance.id)}</td>
                                    <td>${tripInstanceStartTimes.get(tripInstance.id)}
                                        - ${tripInstanceEndTimes.get(tripInstance.id)}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='tripinstance.notripinstancesfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
    </div>
</div>

<div id="addRequirement" title="<spring:message code="requirement.create"/>">
    <form method="post" action="/requirement/add" class="mainstyle tooltips">
        <input type="hidden" name="tripId" id="addRequirementTripId"
               title="tripId" value="${tripObject.id}"/>

        <div class="row">
            <span><spring:message code='text.name'/></span>
            <input name="name" id="addRequirementName" title="<spring:message code='requirement.name'/>"/>
        </div>
        <div class="row">
            <span><spring:message code='text.amount'/></span>
            <input name="amount" class="required" id="addRequirementAmount"
                   title="<spring:message code='requirement.amount'/>"/> <%--TODO: number-validation--%>
        </div>
        <div class="row">
            <span><spring:message code='text.description'/></span>
            <textarea name="description" id="addRequirementDescription"
                      title="<spring:message code='requirement.description'/>"></textarea>
        </div>
        <input title="<spring:message code='text.save'/>" type="submit" class="button"
               value="<spring:message code='text.save'/>"/>
    </form>
</div>

<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<script src="/js/functions.js"></script>
<script>maketripsortable();
preparemodal();
preparetables();
</script>
</body>
</html>
