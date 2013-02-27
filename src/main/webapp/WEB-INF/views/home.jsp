<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 6/02/13
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <link href="/css/blue.css" rel="StyleSheet"/>

    <title>My Trip Assistant</title>
</head>
<body>
<div id="container">
    <div id="logincontainer" class="column light"> <sec:authorize access="isAnonymous()">

    <div class="loginmiddlebox"><h2>Login</h2>

            <form action="j_spring_security_check" method="post">
                <input type="text" name="j_username" id="j_username" class="loginbox" placeholder="<spring:message code='text.username'/>"/> <br>
                <input type="password" class="loginbox" name="j_password" id="j_password" placeholder="<spring:message code='text.password'/>"/> <br>

                <input type="submit" class="button" value="<spring:message code='text.login'/>"/> &nbsp;

                <span class="small"><a href="/profile/reset/forgotPassword"><spring:message
                        code="text.resetpasswordlink"/></a></span>
            </form>
            <a href="profile/register"><spring:message code='text.createaccount'/></a>


        </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
                           <h2>Hallo</h2> Welkom,${userObject.name}
            </sec:authorize>
    </div>
    <div id="maincontainer" class="column dark">

        <div class="loginmiddlebox"><h2>My Trip Assistant</h2>

            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin commodo, magna quis accumsan euismod, urna
                purus viverra nulla, non commodo
                erat risus ac sapien. Duis quis odio est. Mauris luctus odio eget dui accumsan nec mollis massa
                vulputate. Sed elementum lobortis lobortis.
                Nunc dapibus, est ac laoreet laoreet, justo purus pharetra massa, eget dictum odio dolor eu libero. Nunc
                leo odio, dapibus sit amet tristique eget,
                accumsan eu nunc. Quisque id purus lectus. Curabitur ac odio in urna faucibus rhoncus eu dictum elit.
                Fusce ac lorem id ligula venenatis rutrum
                sed vel est. Aliquam quis turpis eget nisl iaculis pulvinar nec iaculis erat. Vivamus auctor augue id
                nunc vulputate sit amet ultrices nulla porttitor.
                Quisque vulputate pellentesque ipsum at consequat. Praesent nec eros lorem, ut pretium nibh. </p>

            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin commodo, magna quis accumsan euismod, urna
                purus viverra nulla, non commodo
                erat risus ac sapien. Duis quis odio est. Mauris luctus odio eget dui accumsan nec mollis massa
                vulputate. Sed elementum lobortis lobortis.
                Nunc dapibus, est ac laoreet laoreet, justo purus pharetra massa, eget dictum odio dolor eu libero. Nunc
                leo odio, dapibus sit amet tristique eget,
                accumsan eu nunc. Quisque id purus lectus. Curabitur ac odio in urna faucibus rhoncus eu dictum
                elit. </p>

        </div>
    </div>


</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="/js/placeholder.js"></script>
<script src="/js/functions.js"></script>


</body>
</html>