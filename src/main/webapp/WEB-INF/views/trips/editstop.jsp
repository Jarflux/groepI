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
                                    <span><spring:message code='text.trip'/></span><c:out
                                        value="${stopObject.trip.title}"/>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.name'/></span>
                                    <input type="text" name="name" value="${stopObject.name}"/>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.type'/></span>
                                    <select name="type" id="stopType">
                                        <option value="0"><spring:message code='text.informative'/></option>
                                        <option value="1"><spring:message code='text.interactive'/></option>
                                    </select>
                                </div>
                                <div class="row">
                                    <span><spring:message code='text.rending'/></span>
                                    <select name="displayMode" id="stopDisplayMode">
                                        <option value="0"><spring:message code='text.normal'/></option>
                                        <option value="1"><spring:message code='text.ar'/></option>
                                    </select>
                                </div>
                                <div class="row">
                                    <span><spring:message code='stops.radius'/></span>
                                    <textarea name="radius"><c:out value="${stopObject.radius}"/></textarea>
                                </div>
                                <div class="row">
                                    <span><spring:message code='stops.question'/></span>
                                    <input type="text" name="stopText" value="${stopObject.stopText}"/>
                                </div>
                                <div class="row">
                                    <input type="hidden" name="latitude" value="${stopObject.latitude}"/>
                                    <input type="hidden" name="longitude" value="${stopObject.longitude}"/>
                                    <input type="hidden" name="tripId" value="${stopObject.trip.id}"/>
                                    <input type="hidden" name="Id" value="${stopObject.id}"/>
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
            <div class="half" id="divAnswers">
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
                                            <li class="active" id="answer_${answer.id}"><span><input type="radio" name="group1" checked="checked"
                                                                            onclick="setCorrectAnswer(${answer.id})"/><c:out value="${answer.answerText}"/><p class="removeButton" onclick="deleteAnswer(${answer.id})">X</p></span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="active" id="answer_${answer.id}"><span><input type="radio" name="group1"
                                                                            onclick="setCorrectAnswer(${answer.id})"/><c:out value="${answer.answerText}"/><p class="removeButton" onclick="deleteAnswer(${answer.id})">X</p></span></li>
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
                <form action="/trips/addAR" enctype="multipart/form-data" method="POST">
                    <input type="hidden" name="stopid" value="${stopObject.id}"/>
                    <input type="file" name="photo"/>
                    <input type="submit" value="<spring:message code="text.save" />"/>
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
        console.log("Start");
        /*GMap functions*/
        initializeGMaps();
        placeStopMarker(parseInt(${stopObject.latitude}), parseInt(${stopObject.longitude}));

        /*Eventhandlers*/
        $("#stopType").change(function () {
            console.log("Selected:" + $(this).val());
            var selectedValue = $(this).val();
            if (selectedValue == 0) {
                $("#divAnswers").hide();
                console.log("divAnswers.hide");
            }
            else {
                $("#divAnswers").show();
                console.log("divAnswers.show");
            }
        });
        $("#stopDisplayType").change(function () {
            console.log("Selected:" + $(this).val());
            var selectedValue = $(this).val();
            if (selectedValue == 0) {
                $("#divPictureAR").hide();
                console.log("divPictureAR.hide");
            }
            else {
                $("#divPictureAR").show();
                console.log("divPictureAR.show");
            }
        });

        /*Page data init*/
        if (parseInt(${stopObject.type}) == 0)
        {
           $("#stopType").val(0);
           $("#divAnswers").hide();
        }
        else if (parseInt(${stopObject.type}) == 1)
        {
            $("#stopType").val(1);
            $("#divAnswers").show();
        }

        if (parseInt(${stopObject.displayMode}) == 0)
        {
            $("#stopDisplayType").val(0);
            $("#divPictureAR").hide();
        }
        else if (parseInt(${stopObject.displayMode}) == 1)
        {
            $("#stopDisplayType").val(1);
            $("#divPictureAR").show();
        }
        console.log("End--");
    });

    function setCorrectAnswer(id) {
        console.log("setCorrectAnswer");
        var feedback = $.post("/trips/setStopIsCorrect", { answerId: id})
    }

    function deleteAnswer(id) {
        console.log("deleteAnswer");
        var feedback = $.post("/trips/deleteAnswer", { answerId: id})
        $("#answer_" + id).remove();
    }
</script>
</body>
</html>