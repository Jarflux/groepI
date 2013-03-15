<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
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
        <h2><spring:message code="trip.create"/></h2>

        <form method="post" action="create" class="mainstyle tooltips">
            <div class="row">
                <span><spring:message code='text.title'/></span>
                <input type="text" class="" name="title" placeholder=""
                       title="<spring:message code='trip.titletooltip'/>"/>
            </div>
            <div class="row">
                <span><spring:message code='trip.available'/></span>
                <input type="checkbox" class="" name="available" value="true" placeholder=""
                       title="<spring:message code='trip.availabletooltip'/>"/>
            </div>
            <div class="row">
                <span><spring:message code='text.description'/></span>
                <textarea name="description" title="<spring:message code='trip.descriptiontooltip'/>"></textarea>
            </div>
            <div class="row">
                <span><spring:message code='trip.repeatable'/></span>
                <input type="checkbox" name="repeatable" value="true" title="<spring:message code='trip.repeatabletooltip'/>"/>
            </div>
            <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
        </form>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>