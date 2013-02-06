<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 6/02/13
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title></title>
</head>
<body>
        Testje                GroepI, I for Insane Awesomeness
<p> <spring:message code="text.springmvc" text="default text" /></p>
        Current Locale : ${pageContext.response.locale}

</body>
</html>