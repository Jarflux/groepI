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
            <section class="full">
                <div class="half big">
                    <div class="gmap" id="map_canvas">
                    </div>
                </div>
                <div class="half">
                    <c:choose>
                        <c:when test="${stopObject != null}">
                        <form method="post" action="/trips/updateStop" class="mainstyle tooltips validate">
                            <div id="stopDetails">
                                <div class="row">
                                    <span><spring:message code='text.trip'/></span><c:out value="${stopObject.trip.title}"/>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.name'/></span>
                                    <input type="text" name="name" value="${stopObject.name}"/>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.type'/></span>
                                    <select name="type">
                                        <option value="0"><spring:message code='text.informative'/></option>
                                        <option value="1"><spring:message code='text.interactive'/></option>
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
                                    <span><spring:message code='text.question'/></span>
                                    <textarea name="stopText"><c:out value="${stopObject.stopText}"/></textarea>
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
            <section class="full">
                <div class="half" id="divAndwers">
                    <div class="row">
                        <span><c:out value="${stopObject.stopText}"/></span>
                    </div>
                    <div>
                        <c:choose>
                            <c:when test="${!empty stopObject.answers}">
                                <ul>
                                    <c:forEach var="answer" items="${stopObject.answers}" varStatus="status">
                                            <c:choose>
                                                <c:when test="${answer.isCorrect}">
                                                    <li class="active"><span><input type="radio" name="group1" checked="checked" onclick="setCorrectAnswer()"/><c:out value="${answer.answerText}"/></span></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="active"><span><input type="radio" name="group1" onclick="setCorrectAnswer()" /><c:out value="${answer.answerText}"/></span></li>
                                                </c:otherwise>
                                            </c:choose>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <span><spring:message code='text.noaswersfound'/></span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="row">
                        <form method="post" action="/trips/createAnswer" class="mainstyle tooltips validate">
                            <input type="text" name="answer"/>
                            <input type="hidden" name="stopId" value="${stopObject.id}"/>
                            <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                        </form>
                    </div>
                </div>
                <div class="half" id="divPictureAR">
                      <%--AR picture upload control--%>
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
        //google.maps.event.addDomListener(window, 'load', initializeGMaps);
        initializeGMaps();
        placeStopMarker(parseInt(${stopObject.latitude}), parseInt(${stopObject.longitude}));
        $('[name="type"]').change(stopVisibility());
        $('[name="displayMode"]').change(stopVisibility());
        stopVisibility()
    })
    function setCorrectAnswer(){

    }
    function newAnswerAjax()
    {
        $.ajax({
            type: "POST",
            url: "trips/addAnswer",
            data: "question=" + $("newAnswer").value + "&stopId=" + ${stopObject.id}
        });
    }
    function stopVisibility()
    {
        if ($('[name="type"]').value == 0)
        {
            $("#divAndwers").css("visibility", "hidden");
        }
        else if ($('[name="type"]').value == 1)
        {
            $("#divAndwers").css("visibility", "visible");
        }
        if ($('[name="displayMode"]').value == 0)
        {
            $("#divPictureAR").css("visibility", "hidden");
        }
        else if ($('[name="displayMode"]').value == 1)
        {
            $("#divPictureAR").css("visibility", "hidden");
        }
    }
</script>
</body>
</html>