<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add A Cost</title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.addcost"/></h2>

        <form method="post" action="/trips/doaddcost" class="mainstyle tooltips">
            <input type="hidden" name="tripInstanceId" title="tripInstanceId" value="${tripInstanceId}"/>

            <div class="row">
                <span><spring:message code='text.costdescription'/></span>
                <input name="description" title="<spring:message code='text.costdescription'/>"/>
            </div>

            <div class="row">
                <span><spring:message code='text.costamount'/></span>
                <input name="amount" title="<spring:message code='text.costamount'/>"/>
            </div>

            <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
        </form>
        <form method="get" action="/trips/viewinstance/${tripInstanceId}"
              class="mainstyle tooltips">
            <input type="submit" class="button" value="<spring:message code='text.finishediting'/>"/>
        </form>
    </div>
</div>

</body>
</html>