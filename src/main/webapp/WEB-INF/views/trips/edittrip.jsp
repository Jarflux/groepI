<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code='text.edittrip'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="topmenu" class="column dark">
    <jsp:include page="/topmenu"/>
</div>
<div id="content" class="column light">
    <h2><spring:message code="text.createtrip"/></h2>
    <c:choose>
        <c:when test="${tripObject != null}">
            <form method="post" action="/trips/updateTrip" class="mainstyle tooltips">
                <div class="row">
                    <span><spring:message code='text.title'/></span>
                    <input type="text" class="" name="title" placeholder="" value="${tripObject.title}"
                           title="<spring:message code='text.titletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='text.available'/></span>
                    <input type="checkbox" id="chkAvailable" name="available" value="true" title="<spring:message code='text.availabletooltip'/>"/>
                </div>
                <div class="row">
                    <span><spring:message code='text.description'/></span>
                    <textarea name="description"
                              title="<spring:message code='text.descriptiontooltip'/>">${tripObject.description}</textarea>
                </div>
                <div class="row">
                    <span><spring:message code='text.repeatabletrip'/></span>
                    <input type="checkbox" id="chkRepeatable" name="repeatable" value="true" title="<spring:message code='trips.repeatabletooltip'/>" />
                </div>
                <input type="hidden" name="Id" value="${tripObject.id}"/>
                <%--<input type="hidden" name="organizerId" value="${tripObject.organiser.id}"/>--%>
                <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
            </form>
        </c:when>
        <c:when test="${tripObject == null}">
            <jsp:forward page="/error/emptyobject"/>
        </c:when>
    </c:choose>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="/js/functions.js"></script>
<script>
    $(function () {
        console.log("Start");
        /*Page data init*/
        if (${tripObject.available})
        {
            $("#chkAvailable").val(0);
            console.log("chkAvailable.true");
        }
        else
        {
            $("#chkAvailable").val(1);
            console.log("chkAvailable.false");
        }
        if (${tripObject.repeatable})
        {
            $("#chkRepeatable").val(0);
            console.log("chkRepeatable.true");
        }
        else
        {
            $("#chkRepeatable").val(1);
            console.log("chkRepeatable.false");
        }

        /*Event handlers*/
        $("#chkAvailable").change(function () {
            console.log("Selected:" + $(this).val());

        });
        $("#chkRepeatable").change(function () {
            console.log("Selected:" + $(this).val());

        });
    });
</script>
</body>
</html>