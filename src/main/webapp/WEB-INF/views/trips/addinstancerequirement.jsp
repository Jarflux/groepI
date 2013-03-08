<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 27/02/13
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trip requirements</title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.createRequirement"/></h2>

        <form method="post" action="/trips/doaddinstancerequirement" class="mainstyle tooltips">
            <input type="hidden" name="tripInstanceId" title="tripInstanceId" value="${tripInstanceId}"/>

            <div class="row">
                <span><spring:message code='text.name'/></span>
                <input name="name" title="<spring:message code='text.requirementname'/>"/>
            </div>
            <div class="row">
                <span><spring:message code='text.amount'/></span>
                <input name="amount" class="required"
                       title="<spring:message code='text.requirementamount'/>"/> <%--TODO: number-validation--%>
            </div>
            <div class="row">
                <span><spring:message code='text.description'/></span>
                <textarea name="description" title="<spring:message code='text.requirementdescription'/>"></textarea>
            </div>
            <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
        </form>
        <form method="get" action="/trips/viewinstance/${tripInstanceId}" class="mainstyle tooltips">
            <input type="submit" class="button" value="<spring:message code='text.finishediting'/>"/>
        </form>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>