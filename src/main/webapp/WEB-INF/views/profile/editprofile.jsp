<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Document   : editprofile
    Created on : Feb 26, 2013, 1:46:01 PM
    Author     : Ben Oeyen
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
        <h2><spring:message code='text.myprofile'/></h2>

        <section>
            <div class="quarter">
                <c:choose>
                    <c:when test="${userObject.profilePicture == null}">
                        <img src="/images/noprofile.jpg" width='150' class="profilepic"/>
                    </c:when>
                    <c:when test="${userObject.profilePicture != null}">
                        <img src="${userObject.profilePicture}" width="150" class="profilepic"/>
                    </c:when>
                </c:choose>
            </div>
            <div class="three-quarter">
                <c:choose>
                    <c:when test="${userObject != null}">
                        <div class="row">
                            <c:out value="Profile of user #${userObject.id}"/>
                        </div>
                        <form method="post" action="/profile/editUser" class="mainstyle tooltips validate"
                              enctype="multipart/form-data">
                            <div class="row">
                                <span><spring:message code='text.name'/></span>
                                <input type="text" class="required" name="name" placeholder=""
                                       title="Gelieve uw volledige naam in te geven" value="${userObject.name}"/>
                            </div>
                            <div class="row">
                                <span><spring:message code='text.email'/></span>
                                <input type="text" class="required" name="email" placeholder=""
                                       title="Gelieve een geldig e-mailadres in te geven. Dit wordt ook uw gebruikernsaam."
                                       value="${userObject.email}"/>
                            </div>
                            <div class="row">
                                <span><spring:message code='text.dateofbirthedit'/></span>
                                <input type="text" class="required dateregister" name="dob" placeholder=""
                                       title="Gelieve uw geboortedatum in te geven" value="${dob}"/>
                            </div>
                            <div class="row">
                                <span><spring:message code='text.profilepicture'/></span>
                                <input type="file" name="photo">
                                    <%--<input type="text" class="" name="profilePicture" placeholder=""
                                           title="Hier kan u uw profielfoto instellen" value="${userObject.profilePicture}"/>--%>
                            </div>
                            <div class="row">
                                <span></span>
                                <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
                                    <input type="reset" class="button" value="<spring:message code='text.cancel'/>"/>

                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <jsp:forward page="/error/invaliduser"/>
                    </c:otherwise>

                </c:choose>
            </div>
        </section>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>
