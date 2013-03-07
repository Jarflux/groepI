<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 9/02/13
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code='text.viewtripinformation'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code='text.viewtripinformation'/></h2>
        <section>
            <h2>
                ${tripInstanceObject.id}
                ${tripInstanceObject.title}
            </h2>
            ${tripInstanceObject.description}
            <br/>
            <spring:message code='text.tripcreator'/>:
            <a href="/profile/view/${tripInstanceObject.organiser.id}"
               class="active">${tripInstanceObject.organiser.name}</a>
            <br/>

            <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                <a href="/trips/editinstance/${tripInstanceObject.id}" class="active"><spring:message
                        code='text.edittrip'/></a>
            </c:if>
            <c:if test="${tripInstanceObject.organiser.id != userObject.id}">
                Zo, jij wil deze trip aanpassen? MAG NIET!
            </c:if>


        </section>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.stops'/></h3>
                <%--<form method="get" action="/trips/addstop/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <%--<a href="/trips/addstop/${tripInstanceObject.id}" class="active"><spring:message code='text.add'/></a>--%>
                <c:choose>
                    <c:when test="${!empty tripInstanceObject.trip.stops}">
                        <ul class='sortable'>
                            <c:forEach var="stop" items="${tripInstanceObject.trip.stops}">
                                <%--<li id="stop-${stop.id}">${stop.stopnumber}: <a href="/trips/editStop/${stop.id}" class="active"><c:out value="${stop.name}"/></a></li>--%>
                                <li>view die stop jongeuh</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.nostopsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.requirements'/></h3>
                <%--<form method="get" action="/trips/addRequirement/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <a href="/trips/addinstancerequirement/${tripInstanceObject.id}" class="active"><spring:message
                        code='text.add'/></a>
                <c:choose>
                    <c:when test="${!empty tripInstanceObject.requirementInstances}">
                        <table>
                            <c:forEach var="requirementInstance" items="${tripInstanceObject.requirementInstances}">
                                <tr>
                                    <td><a href="/trips/editinstancerequirement/${requirementInstance.id}"
                                           class="active">${requirementInstance.name}</a></td>
                                    <td><spring:message code="text.amount"/>: ${requirementInstance.amount}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">${requirementInstance.description}</td>
                                </tr>
                                <tr>
                                    <c:choose>
                                        <c:when test="${!empty requirementInstance.user}">
                                            <td>${requirementInstance.user.name}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><spring:message code="text.requirementinstancebrings"/>: <spring:message
                                                    code='text.requirementinstanceforallusers'/></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>assign user to reqInstance (anchor of javascript? idk)</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <br/>
                        <spring:message code='text.norequirementsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.messages'/></h3>
                <%--<form method="post" action="/trips/addinstance/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <a href="/trips/addmessage/${tripInstanceObject.id}" class="active"><spring:message
                        code='text.add'/></a>
                <c:choose>
                    <c:when test="${!empty tripInstanceObject.messages}">
                        <table>
                            <c:forEach var="message" items="${tripInstanceObject.messages}">
                                <tr>
                                    <td>${message.user.name}</td>
                                    <td>${message.date}</td>
                                </tr>
                                <tr>
                                    <td rowspan="2">${message.content}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.nomessagesfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.costs'/></h3>
                <%--<form method="post" action="/trips/addinstance/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <a href="/trips/addcost/${tripInstanceObject.id}" class="active"><spring:message code='text.add'/></a>
                <c:choose>
                    <c:when test="${!empty tripInstanceObject.costs}">
                        <table>
                            <c:forEach var="cost" items="${tripInstanceObject.costs}">
                                <tr>
                                    <td>${cost.user.name}</td>
                                </tr>
                                <tr>
                                    <td>${cost.amount}</td>
                                    <td>${cost.description}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.nocostsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
        <div class="quarter">
            <section>
                <h3><spring:message code='text.participants'/></h3>
                <%--<form method="post" action="/trips/addinstance/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <%--<a href="/trips/addcost/${tripInstanceObject.id}" class="active"><spring:message code='text.add'/></a>--%>
                <c:choose>
                    <c:when test="${!empty tripInstanceObject.participants}">
                        <table>
                            <c:forEach var="user" items="${tripInstanceObject.participants}">
                                <tr>
                                    <td>${user.name}</td>
                                </tr>
                                <%--
                                                                <tr>
                                                                    <td>${user.}</td>                      TODO: cost/message aanmaken
                                                                    <td>${cost.description}</td>
                                                                </tr>--%>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.noparticipants'/>
                    </c:otherwise>
                </c:choose>
            </section>
        </div>
    </div>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<script src="/js/functions.js"></script>
<script>makesortable();
</script>
</body>
</html>
