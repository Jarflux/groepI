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
    <title><spring:message code='text.viewprofile' /></title>
    <link href="/css/blue.css" rel="stylesheet" />

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"   />
    </div>
    <div id="content" class="column light">
<h2> <spring:message code='text.myprofile' /> </h2>

         <section>
            <div class="quarter"><img src="/images/noprofile.png" width='150' class="profilepic"/></div>
            <div class="three-quarter">
            <c:choose>
                 <c:when test="${userObject != null}">
                    <div class="row">
                        <c:out value="Profile of user #${userObject.id}"/>
                    </div>
                    <div class="row"><c:out value="Name ${userObject.name}"/>           </div>
                    <div class="row"><c:out value="Mail ${userObject.email}"/></div>
                    <div class="row"><c:out value="Date of birth ${userObject.dateOfBirth}"/></div>
                    <div class="row"><c:out value="Profile picture ${userObject.profilePicture}"/></div>
                    <div class="row">
                        <form action="/profile/myprofile/edit">
                            <input type="submit" class="button" value="<spring:message code='text.edit'/>" />
                        </form>
                    </div>
                </c:when>
                <c:when test="${userObject == null}">
                    <jsp:forward page="/error/invaliduser" />
                </c:when>

            </c:choose>
                 </div>
             </section>
        </div></div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>
