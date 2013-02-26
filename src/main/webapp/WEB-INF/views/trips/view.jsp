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
    <title><spring:message code='text.bekijktripinfo' /></title>
    <link href="/css/blue.css" rel="stylesheet" />

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"   />
    </div>
    <div id="content" class="column light">
<h2> <spring:message code='text.bekijktripinfo' /> </h2>
<section>
    <div class="gmap">

    </div>
    <div class="row">
        <c:choose>
            <c:when test="${tripObject != null}">
                <p>
                    <c:out value="${tripObject.title}"/>
                    <br/>
                    <c:out value="${tripObject.description}"/>
                </p>
            </c:when>
            <c:when test="${tripObject == null}">
                <jsp:forward page="/error/invalidtrip" />
            </c:when>
        </c:choose>
    </div>

</section>
</div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>