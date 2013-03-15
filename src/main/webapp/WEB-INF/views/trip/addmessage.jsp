<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post A Message</title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.addmessage"/></h2>

        <form method="post" action="/message/add" class="mainstyle tooltips">
            <input type="hidden" name="tripInstanceId" title="tripInstanceId" value="${tripInstanceId}"/>

            <div class="row">
                <span><spring:message code='text.messagecontent'/></span>
                <input name="content" title="<spring:message code='text.messagecontent'/>"/>
            </div>

            <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
        </form>
        <form method="get" action="/trip/view/${tripInstanceId}"
              class="mainstyle tooltips">
            <input type="submit" class="button" value="<spring:message code='text.finishediting'/>"/>
        </form>
    </div>
</div>

</body>
</html>