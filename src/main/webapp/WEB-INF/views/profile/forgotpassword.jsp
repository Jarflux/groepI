<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 21/02/13
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/css/blue.css" rel="StyleSheet"/>
    <title>Forgot password</title>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"   />
    </div>
    <div id="content" class="column light">
        <%--<h2><spring:message code="text.resetpassword"/></h2>--%>
                <h2>
                    <c:out value="${message}"/>
                </h2>

        <form method="post" action="doResetPassword" class="mainstyle tooltips validate">

            <div class="row"> <span><spring:message code='text.mail'/></span>
                <input type="text" class="required" name="email" placeholder=""
                       title="Gelieve je e-mailadres in te geven."/>
            </div>
            <div class="row"> <span></span>
                <input type="submit" class="button" value="<spring:message code='text.resetpassword'/>" />
            </div>

        </form>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>

</body>
</html>