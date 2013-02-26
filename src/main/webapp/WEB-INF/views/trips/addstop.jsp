<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mitch Va Daele
  Date: 26-2-13
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Stop</title>
        <link href="/css/blue.css" rel="stylesheet"/>
    </head>
    <body>
        <div id="wrapper">
            <div id="topmenu" class="column dark">
                <jsp:include page="/topmenu"/>
            </div>
            <div id="content" class="column light">
                <h2><spring:message code="text.addstop"/></h2>

                <form method="post" action="" class="mainstyle tooltips validate">
                    <div class="row">
                        <span><spring:message code='text.name'/></span>
                        <input type="text" name="name"/>
                    </div>
                    <div class="row">
                        <span><spring:message code='text.type'/></span>
                        <select>
                            <option><spring:message code='text.interactive'/></option>
                            <option><spring:message code='text.informative'/></option>
                        </select>
                    </div>
                    <div class="row">
                        <span><spring:message code='text.rending'/></span>
                        <select>
                            <option><spring:message code='text.normal'/></option>
                            <option>Augmented Reality</option>
                        </select>
                    </div>
                    <!--
                    <div class="row">
                        <div class="gmap">
                        </div>
                    </div>
                    -->
                    <div class="row">
                        <span><spring:message code='text.description'/></span>
                        <input type="text" name="stopText"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>