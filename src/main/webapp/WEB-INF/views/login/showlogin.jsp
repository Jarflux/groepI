<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 6/02/13
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link href="css/blue.css" rel="StyleSheet" />

    <title></title>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"   />
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.aanmelden"/></h2>


        <form method="post" action="j_spring_security_check" class="mainstyle validate">
            <div class="row"> <span><spring:message code='text.gebruikersnaam'/></span>          	<input type="text" name="j_username" id="j_username" <c:if test="${not empty param.login_error}">value='<%= session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>'</c:if>/>

            </div>

            <div class="row"> <span><spring:message code='text.wachtwoord'/></span>         <input type="password" class="required"  name="j_password" id="j_password" placeholder=""/>
            </div>


            <div class="row"> <span></span>       <input type="submit" class="button" value="<spring:message code='text.registreren'/>" />
            </div>

        </form>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>