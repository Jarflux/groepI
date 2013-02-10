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
</head>
<body>
<h2><spring:message code="text.maakaccount"/></h2>


Deze code geeft de error
<%--<form:form method="post" action="createUser" modelAttribute="user">
    <table>
        <tr>
            <td>
                Naam
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
    </table>--%>
    tot hier

    Code hieronder stond al in commentaar
    <%--<input type="text" class="loginbox" placeholder="<spring:message code='text.naam'/>"/> <br>--%>
    <%--<input type="password" class="loginbox" placeholder="<spring:message code='text.wachtwoord'/>"/> <br>
    <input type="password" class="loginbox" placeholder="<spring:message code='text.wachtwoordherhalen'/>"/> <br>
    <input type="text" class="loginbox" placeholder="<spring:message code='text.mail'/>"/> <br>
    <input type="text" class="loginbox" placeholder="<spring:message code='text.geboortedatum'/>"/> <br>
    <input type="text" class="loginbox" placeholder="<spring:message code='text.profielfoto'/>"/> <br>--%>
     Tot hier

    <input type="submit" class="button" value="<spring:message code='text.registreren'/>"/> &nbsp;

<%--</form:form>--%>
</body>
</html>