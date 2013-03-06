<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
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
        <section>
            <div class="half">
                <c:choose>
                <c:when test="${stopObject != null}">
                    <form method="post" action="/trips/createStop" class="mainstyle tooltips validate">
                        <div class="row">
                            <span><spring:message code='text.trip'/></span><c:out value="${stopObject.trip.title}"/>
                        </div>
                        <div id="stopDetails">
                            <div class="row">
                                <span><spring:message code='text.name'/></span>
                                <input type="text" name="name" value="${stopObject.name}"/>
                            </div>
                            <%--<div class="row">
                                <span><spring:message code='text.description'/></span>
                                <textarea name="stopText" title="<spring:message code='text.descriptiontooltip'/>"><c:out value="${stopObject.stopText}"/></textarea>
                            </div>--%>
                            <div class="row">
                                <span><spring:message code='text.type'/></span>
                                <select name="type">
                                    <option value="0"><spring:message code='text.interactive'/></option>
                                    <option value="1"><spring:message code='text.informative'/></option>
                                </select>
                            </div>
                            <div class="row">
                                <span><spring:message code='text.rending'/></span>
                                <select name="displayMode">
                                    <option value="0"><spring:message code='text.normal'/></option>
                                    <option value="1"><spring:message code='text.ar'/></option>
                                </select>
                            </div>
                            <div class="row">
                                <input type="hidden" name="latitude" value="${stopObject.latitude}"/>
                                <input type="hidden" name="longitude" value="${stopObject.longitude}"/>
                                <input type="hidden" name="tripId" value="${stopObject.trip.id}"/>
                                <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
                            </div>
                        </div>
                    </form>
                </c:when>
                    <c:when test="${tripObject == null}">
                        <jsp:forward page="/error/emptyobject"/>
                    </c:when>
                </c:choose>
            </div>
        </section>
    </div>
</div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
<script src="/js/functions.js"></script>
<script>
    $(function(){
        //google.maps.event.addDomListener(window, 'load', initializeGMaps);
        initializeGMaps();
        placeStopMarker(parseInt(${stopObject.latitude}), parseInt(${stopObject.longitude}));
        //placeStopMarker(51.221212,4.399166);
    })
</script>
</body>
</html>