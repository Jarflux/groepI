<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>User Profile</title>
    <link href="/css/blue.css" rel="stylesheet" />

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"   />
    </div>
    <div id="content" class="column light">
<h2>Greetings visitor, this will one day be a glorious profile page.</h2>

<p>
    <c:choose>
        <c:when test="${userObject != null}">
            <c:out value="Profile of user #${userObject.id}"/>
            <c:out value="Name ${userObject.name}"/>
            <c:out value="Mail ${userObject.email}"/>
            <c:out value="Password ${userObject.password}"/>
            <c:out value="Date of birth ${userObject.dateOfBirth}"/>
            <c:out value="Profile picture ${userObject.profilePicture}"/>
        </c:when>
        <c:when test="${userObject == null}">
            <c:out value="Profile of user #${userId}"/>
        </c:when>

    </c:choose></p>
        </div></div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>