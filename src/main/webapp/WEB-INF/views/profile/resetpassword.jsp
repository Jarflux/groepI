<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 21/02/13
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset password</title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.resetpassword"/></h2>

        <form method="post" action="setNewPassword" class="mainstyle tooltips validate">
            <input type="hidden" name="passwordResetString" value="${passwordResetString}"/>

            <div class="row"><span><spring:message code='text.password'/></span>
                <input type="password" class="required equalsto" equalsto="password2" name="password"
                       placeholder="" title="Kies een sterk wachtwoord"/>
            </div>
            <div class="row"><span><spring:message code='text.repeatpassword'/></span>
                <input type="password" class="required equalsto" equalsto="password" name="password2"
                       placeholder="" title="Herhaal uw wachtwoord"/>
            </div>
            <div class="row"><span></span>
                <input type="submit" class="button" value="<spring:message code='text.resetpassword'/>"/>
            </div>
        </form>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>