<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code='trip.edit'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="topmenu" class="column dark">
    <jsp:include page="/topmenu"/>
</div>
<div id="content" class="column light">
    <h2><spring:message code="tripinstance.edit"/></h2>
    <c:choose>
        <c:when test="${tripInstanceObject != null}">
            <form method="post" action="/trip/update" class="mainstyle tooltips">
                <input type="hidden" value="${tripInstanceObject.id}" name="tripInstanceId"/>

                <div class="row">
                    <span><spring:message code='text.title'/></span>
                    <input type="text" class="" name="title" placeholder="" value="${tripInstanceObject.title}"
                           title="<spring:message code='trip.titletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='trip.available'/></span>
                    <input type="checkbox" class="" name="available" placeholder=""
                           checked="${tripInstanceObject.available}"
                           title="<spring:message code='trip.availabletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='text.description'/></span>
                    <textarea name="description"
                              title="<spring:message code='trip.descriptiontooltip'/>">${tripInstanceObject.description}</textarea>
                </div>
                <div class="row">
                    <span><spring:message code='tripinstance.date'/></span>
                    <input type="text" class="date" name="date" title="<spring:message code='tripinstance.datetooltip'/> "
                           value="${date}">
                </div>
                <div class="row">
                    <span><spring:message code='tripinstance.starttime'/></span>
                    <input type="time" name="startTimeString" title="<spring:message code='tripinstance.starttimetooltip'/>"
                           value="${startTimeString}">
                </div>
                <div class="row">
                    <span><spring:message code='tripinstance.endtime'/></span>
                    <input type="time" name="endTimeString" title="<spring:message code='tripinstance.endtimetooltip'/>"
                           value="${endTimeString}">
                </div>
                <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
            </form>
            <%--<a href="/stop/add/${tripObject.id}" class="active"><spring:message code='text.newstop'/></a>--%>
        </c:when>
        <c:when test="${tripObject == null}">
            <jsp:forward page="/error/emptyobject"/>
        </c:when>
    </c:choose>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>