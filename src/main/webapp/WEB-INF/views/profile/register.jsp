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
    <link href="/css/blue.css" rel="StyleSheet" />

</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">

    </div>
    <div id="content" class="column light">
        <h2><spring:message code="text.maakaccount"/></h2>


<form method="post" action="createUser" class="mainstyle">
    <div class="row"> <span><spring:message code='text.naam'/></span>            <input type="text" class="" name="name" placeholder=""/>
    </div>

    <div class="row"> <span><spring:message code='text.wachtwoord'/></span>         <input type="password" class="" name="password" placeholder=""/>
    </div>
    <div class="row"> <span><spring:message code='text.wachtwoordherhalen'/></span>        <input type="password" class="" name="password2" placeholder=""/>
    </div>

      <div class="row"> <span><spring:message code='text.mail'/></span>             <input type="text" class="" name="email" placeholder=""/>
    </div>
    <div class="row"> <span><spring:message code='text.geboortedatum'/></span>          <input type="datetime" class="" name="dateOfBirth" placeholder=""/>
    </div>
    <div class="row"> <span><spring:message code='text.profielfoto'/></span>           <input type="text" class="" name="profilePicture" placeholder=""/>
    </div>
    <div class="row"> <span></span>       <input type="submit" class="button" value="<spring:message code='text.registreren'/>"/>
    </div>






</form>
        </div>
    </div>
</body>
</html>