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
        <h2><spring:message code="stop.add"/></h2>
        <section>
            <div class="half big">
                <div class="gmap" id="map_canvas">
                </div>
            </div>
        </section>
        <section>
            <div class="half">
                <form method="post" action="/stop/create" class="mainstyle tooltips validate">
                    <div class="row">
                        <span><spring:message code='trip.trip'/></span> <c:out value="${tripObject.title}"/>
                    </div>
                    <div id="stopDetails">
                        <div class="row">
                            <span><spring:message code='text.name'/></span>
                            <input type="text" name="name"/>
                        </div>
                        <div class="row">
                            <span><spring:message code='stop.type'/></span>
                            <select name="type">
                                <option value="0"><spring:message code='stop.interactive'/></option>
                                <option value="1"><spring:message code='stop.informative'/></option>
                            </select>
                        </div>
                        <div class="row">
                            <span><spring:message code='text.rending'/></span>
                            <select name="displayMode">
                                <option value="0"><spring:message code='stop.normal'/></option>
                                <option value="1"><spring:message code='stop.augmentedreality'/></option>
                            </select>
                        </div>
                        <div class="row">
                            <input type="hidden" name="latitude"/>
                            <input type="hidden" name="longitude"/>
                            <input type="hidden" name="tripId" value="${tripObject.id.toString()}"/>
                            <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
<script src="/js/functions.js"></script>
<script src="/js/gmapfunctions.js"></script>
<script>
    $(function () {
        google.maps.event.addDomListener(window, 'load', initializeGMaps);
    })
</script>
</body>
</html>