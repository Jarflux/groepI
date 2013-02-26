<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 10/02/13
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="/css/blue.css" rel="stylesheet"/>

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
                            <jsp:include page="/topmenu"   />
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.createaccount"/></h2>


<form method="post" action="createUser" class="mainstyle tooltips validate">
    <div class="row">
        <span><spring:message code='text.name'/></span>
        <input type="text" class="required" name="name" placeholder="" title="Gelieve uw volledige naam in te geven"/>
    </div>
    <div class="row">
        <span><spring:message code='text.password'/></span>
        <input type="password" class="required equalsto"  equalsto="password2" name="password" placeholder="" title="Kies een sterk wachtwoord"/>
    </div>
    <div class="row">
        <span><spring:message code='text.repeatpassword'/></span>
        <input type="password" class="required equalsto" equalsto="password" name="password2" placeholder="" title="Herhaal uw wachtwoord"/>
    </div>
     <div class="row">
         <span><spring:message code='text.mail'/></span>
         <input type="text" class="required" name="email" placeholder="" title="Gelieve een geldig e-mailadres in te geven. Dit wordt ook uw gebruikernsaam."/>
    </div>
    <div class="row">
        <span><spring:message code='text.dateofbirth'/></span>
        <input type="datetime" class="required dateregister" name="dateOfBirth" placeholder="" title="Gelieve uw geboortedatum in te geven"/>
    </div>
    <div class="row">
        <span></span>
        <input type="submit" class="button" value="<spring:message code='text.register'/>" />
    </div>

</form>
        </div>
    </div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>

</body>
</html>