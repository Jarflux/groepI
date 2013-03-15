<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gregory
  Date: 15/02/13
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Trip</title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="tripinstance.plannedtrips"/></h2>

        <p>Voor template: ${tripObject.title} </p>

        <form method="post" action="/trip/create" class="mainstyle tooltips">
            <%--<input type="hidden" name="tripId" title="tripId" value="${tripObject.id}"/>--%>
            <input type="hidden" name="tripId" title="tripId" value="${tripObject.id}"/>

            <div class="row">
                <span><spring:message code='text.title'/></span>
                <input type="text" class="" name="title" placeholder=""
                       title="<spring:message code='trip.titletooltip'/>"/>
            </div>
            <div class="row">
                <span><spring:message code='trip.available'/></span>
                <input type="checkbox" class="" name="available" placeholder=""
                       title="<spring:message code='trip.availabletooltip'/>"/>
            </div>
            <div class="row">
                <span><spring:message code='text.description'/></span>
                <textarea name="description" title="<spring:message code='trip.descriptiontooltip'/>"></textarea>
            </div>
            <%--date + starttime + endtime--%>
            <div class="row">
                <span><spring:message code='tripinstance.date'/></span>
                <input type="text" class="date" name="date" title="<spring:message code='tripinstance.datetooltip'/>">
            </div>
            <div class="row">
                <span><spring:message code='tripinstance.starttime'/></span>
                <input type="time" name="startTimeString" title="<spring:message code='tripinstance.starttimetooltip'/>">
            </div>
            <div class="row">
                <span><spring:message code='tripinstance.endtime'/></span>
                <input type="time" name="endTimeString" title="<spring:message code='tripinstance.endtimetooltip'/>">
            </div>

            <c:choose>
                <c:when test="${tripObject.repeatable}">
                    <label><input type="radio" name="repeatable" value="daily" title=""><i><spring:message
                            code='tripinstance.daily'/></i></label><br/>
                    <label><input type="radio" name="repeatable" value="weekly" title=""><i><spring:message
                            code='tripinstance.weekly'/></i></label><br/>

                    <div class="row">
                        <span><spring:message code='tripinstance.dateuntil'/></span>
                        <input type="text" class="date" name="endDate"
                               title="<spring:message code='tripinstance.dateuntiltooltip'/>">
                    </div>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="repeatable" value=""/>
                    <input type="hidden" name="endDate" value=""/>
                </c:otherwise>
            </c:choose>

            <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
        </form>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>