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
    <div id="logincontainer" class="column light">
        <sec:authorize access="isAnonymous()">
            <div class="loginmiddlebox"><h2>Login</h2>

                <form action="j_spring_security_check" method="post">
                    <input type="text" name="j_username" id="j_username" class="loginbox"
                           placeholder="<spring:message code='text.username'/>"/> <br>
                    <input type="password" class="loginbox" name="j_password" id="j_password"
                           placeholder="<spring:message code='text.password'/>"/> <br>
                    <input type="submit" class="button" value="<spring:message code='text.login'/>"/> &nbsp;
                <span class="small"><a href="/profile/reset/forgotPassword"><spring:message
                        code="text.resetpasswordlink"/></a></span>
                </form>
                <p id="fblogin" class="button">Login met FB</p>
                <a href="profile/register"><spring:message code='text.createaccount'/></a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <h2>Hallo</h2>
            <span>Welkom, ${userObject.name}</span><br/>
            <a href="j_spring_security_logout" class="button"><spring:message code="text.logout" /> </a>
        </sec:authorize>
    </div>
    <div id="maincontainer" class="column dark">
        <div class="loginmiddlebox">
            <h2><spring:message code='home.contentTitle'/></h2>
            <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin commodo, magna quis accumsan euismod, urna
                purus viverra nulla, non commodo
                erat risus ac sapien. Duis quis odio est. Mauris luctus odio eget dui accumsan nec mollis massa
                vulputate. Sed elementum lobortis lobortis.
                Nunc dapibus, est ac laoreet laoreet, justo purus pharetra massa, eget dictum odio dolor eu libero. Nunc
                leo odio, dapibus sit amet tristique eget,
                accumsan eu nunc. Quisque id purus lectus. Curabitur ac odio in urna faucibus rhoncus eu dictum elit.
                Fusce ac lorem id ligula venenatis rutrum
                sed vel est. Aliquam quis turpis eget nisl iaculis pulvinar nec iaculis erat. Vivamus auctor augue id
                nunc vulputate sit amet ultrices nulla porttitor.
                Quisque vulputate pellentesque ipsum at consequat. Praesent nec eros lorem, ut pretium nibh. </p>--%>
            <sec:authorize access="isAnonymous()">
                <p>Log in met een van volgende gebruikers:<br/>
                    "django@candyland.com", "Django" <br/>
                    "Ben@trippie.com", "ben"<br/>
                    "Tim@trippie.com", "tim"<br/>
                    "Mitch@trippie.com", "mitch"<br/>
                    "Vincent@trippie.com", "vincent"<br/>
                    "Gregory@trippie.com", "gregory"<br/>
                    "Dave@trippie.com", "dave"</p>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <p class="fp_navigation"><a href="/trips/list"><spring:message code='home.organizeTrip'/></a></p>

                <p class="fp_navigation"><a href="/trips/listinstance"><spring:message
                        code='home.participateOnTrip'/></a></p>
            </sec:authorize>
        </div>
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
        <p> Laden... </p>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<div id="fb-root"></div>
<script src="/js/placeholder.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
<script src="/js/functions.js"></script>
</body>
</html>