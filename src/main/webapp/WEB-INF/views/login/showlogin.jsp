<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 6/02/13
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="/css/blue.css" rel="StyleSheet"/>

    <title></title>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.login"/></h2>
        <c:if test="${not empty errormsg}">
            <div class="errormsg">
                    ${errormsg}
            </div>
        </c:if>
        <form method="post" action="j_spring_security_check" class="mainstyle validate">
            <div class="row">
                <span><spring:message code='text.email'/></span>
                <input type="text" name="j_username" id="j_username"/>
            </div>
            <div class="row">
                <span><spring:message code='text.password'/></span>
                <input type="password" class="required" name="j_password" id="j_password" placeholder=""/>
            </div>
            <div class="row">
                <input type="submit" class="button" value="<spring:message code='text.login'/>"/>
            </div>
        </form>
        <p id="fblogin" class="button">Login met Facebook</p>
    </div>
</div>
<div id="loader">
    <div id="bowlG">
        <div id="bowl_ringG">

            <div class="ball_holderG">
                <div class="ballG">
                </div>
            </div>
        </div>
        <p> Laden...       </p>
    </div>


</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
</body>
</html>