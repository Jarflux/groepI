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
    <h2><spring:message code="text.createtrip"/></h2>
    <c:choose>
        <c:when test="${tripObject != null}">
            <form method="post" action="updateTrip" class="mainstyle tooltips">
                <div class="row">
                    <span><spring:message code='text.title'/></span>
                    <input type="text" class="" name="title" placeholder="" value="${tripObject.title}"
                           title="<spring:message code='text.titletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='text.available'/></span>
                    <input type="checkbox" class="" name="available" placeholder="" checked="${tripObject.available}"
                           title="<spring:message code='text.availabletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='text.description'/></span>
                    <textarea name="description" title="<spring:message code='text.descriptiontooltip'/>">${tripObject.description}</textarea>
                </div>
                <div class="row">
                    <span><spring:message code='text.repeatabletrip'/></span>
                    <input type="checkbox" name="repeatable" value="true" title="Is je trip herhaalbaar?" checked="${tripObject.repeatable}"/>
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
</body>
</html>