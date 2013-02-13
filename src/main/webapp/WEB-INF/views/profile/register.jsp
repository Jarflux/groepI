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


<%--Deze code geeft de error
<form:form method="post" action="createUser" modelAttribute="userObject">
    <table>
        <tr>
            <td>
                Naam
            </td>
            <td>
                <form:input path="userObject.name"/>
            </td>
        </tr>
    </table>
    tot hier--%>

<form method="post" action="createUser">
    <input type="text" class="loginbox" name="name" placeholder="<spring:message code='text.naam'/>"/> <br>
    <input type="password" class="loginbox" name="password" placeholder="<spring:message code='text.wachtwoord'/>"/> <br>
    <input type="password" class="loginbox" name="password2" placeholder="<spring:message code='text.wachtwoordherhalen'/>"/> <br>
    <input type="text" class="loginbox" name="email" placeholder="<spring:message code='text.mail'/>"/> <br>
    <input type="datetime" class="loginbox" name="dateOfBirth" placeholder="<spring:message code='text.geboortedatum'/>"/> <br>
    <input type="text" class="loginbox" name="profilePicture" placeholder="<spring:message code='text.profielfoto'/>"/> <br>

    <input type="submit" class="button" value="<spring:message code='text.registreren'/>"/> &nbsp;

</form>
</body>
</html>