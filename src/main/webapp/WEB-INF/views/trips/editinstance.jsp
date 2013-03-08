<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code='text.edittrip'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="topmenu" class="column dark">
    <jsp:include page="/topmenu"/>
</div>
<div id="content" class="column light">
    <h2><spring:message code="text.editinstance"/></h2>
    <c:choose>
        <c:when test="${tripInstanceObject != null}">
            <form method="post" action="/trips/updateinstance" class="mainstyle tooltips">
                <input type="hidden" value="${tripInstanceObject.id}" name="tripInstanceId"/>

                <div class="row">
                    <span><spring:message code='text.title'/></span>
                    <input type="text" class="" name="title" placeholder="" value="${tripInstanceObject.title}"
                           title="<spring:message code='text.titletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='text.available'/></span>
                    <input type="checkbox" class="" name="available" placeholder=""
                           checked="${tripInstanceObject.available}"
                           title="<spring:message code='text.availabletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='text.description'/></span>
                    <textarea name="description"
                              title="<spring:message code='text.descriptiontooltip'/>">${tripInstanceObject.description}</textarea>
                </div>
                <div class="row">
                    <span><spring:message code='text.date'/></span>
                    <input type="text" class="date" name="date" title="<spring:message code='text.instancedate'/> "
                           value="${date}">
                </div>
                <div class="row">
                    <span><spring:message code='text.starttime'/></span>
                    <input type="time" name="startTimeString" title="<spring:message code='text.instancestarttime'/>"
                           value="${startTimeString}">
                </div>
                <div class="row">
                    <span><spring:message code='text.endtime'/></span>
                    <input type="time" name="endTimeString" title="<spring:message code='text.instanceendtime'/>"
                           value="${endTimeString}">
                </div>
                <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
            </form>
            <%--<a href="/trips/addstop/${tripObject.id}" class="active"><spring:message code='text.newstop'/></a>--%>
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