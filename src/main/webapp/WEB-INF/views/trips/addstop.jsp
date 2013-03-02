<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
                    <section>
                        <div class="half big">
                            <div class="gmap" id="map_canvas">
                            </div>
                        </div>
                    </section>
                    <%--<section>
                        <div class="half">
                            <c:choose>
                                <c:when test="${!empty tripObject.stops}">
                                    <ul>
                                        <c:forEach var="stop" items="${tripObject.stops}">
                                            <li>${stop.name}</li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <spring:message code='text.nostopsfound' />
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </section>--%>
                    <section>
                        <div class="half">
                        <form method="post" action="" class="mainstyle tooltips validate">
                            <div class="row">
                                <span><spring:message code='text.trip'/></span>
                                <c:out value="${tripObject.title}"/>
                            </div>
                            <div id="stopDetails">
                                <div class="row">
                                    <span><spring:message code='text.name'/></span>
                                    <input type="text" name="name"/>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.type'/></span>
                                    <select name="stopType">
                                        <option value="0"><spring:message code='text.interactive'/></option>
                                        <option value="1"><spring:message code='text.informative'/></option>
                                    </select>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.rending'/></span>
                                    <select>
                                        <option value="1"><spring:message code='text.normal'/></option>
                                        <option value="2">Augmented Reality</option>
                                    </select>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.description'/></span>
                                    <input type="text" name="stopText"/>
                                </div>
                            </div>
                        </div>
                    </section>
                </form>
            </div>
        </div>
        <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
        <script src="/js/functions.js"></script>
        <script>
            $(function(){google.maps.event.addDomListener(window, 'load', initializeGMaps);})
        </script>
    </body>
</html>