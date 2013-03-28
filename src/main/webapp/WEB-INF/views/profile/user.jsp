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
    <title><spring:message code='text.viewprofile'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code='text.viewprofile'/></h2>
        <section>
            <c:choose>
                <c:when test="${userObject != null}">
                    <div class="quarter">
                        <c:choose>
                            <c:when test="${userObject.profilePicture == null && userObject.FBUserID== null}">
                                <img src="/images/noprofile.jpg" width="150"
                                     class="profilepic"/>
                            </c:when>
                            <c:when test="${userObject.profilePicture != null && userObject.FBUserID == null}">
                                <img src="${userObject.profilePicture}" width="150"
                                     class="profilepic"/>
                            </c:when>
                            <c:when test="${userObject.FBUserID != null}">
                                <img src="https://graph.facebook.com/${userObject.FBUserID}/picture?type=large" width="150"
                                     class="profilepic"/>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="three-quarter">
                            <%--<div class="row">--%>
                            <%--<c:out value="Profile of user #${userObject.id}"/>--%>
                            <%--</div>--%>
                        <div class="row">
                            <spring:message code='text.name'/>: <c:out value="${userObject.name}"/>
                        </div>
                        <div class="row">
                            <spring:message code='text.email'/>: <c:out value="${userObject.email}"/>
                        </div>
                        <div class="row">
                            <spring:message code='text.dateofbirth'/>: <c:out value="${dob}"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <%--<c:when test="${userObject == null}">--%>
                    <jsp:forward page="/error/invaliduser"/>
                    <%--</c:when>--%>
                </c:otherwise>
            </c:choose>
        </section>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/jquery.dataTables.min.js"></script>

<script src="/js/functions.js"></script>
<script>preparetables()</script>

</body>
</html>