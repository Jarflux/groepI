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
    <title><spring:message code='trip.viewinformation'/></title>
    <link href="/css/blue.css" rel="stylesheet"/>
    <link href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
<div id="topmenu" class="column dark">
    <jsp:include page="/topmenu"/>
</div>
<div id="content" class="column light">
    <h2><spring:message code='trip.viewinformation'/></h2>
    <section class="fill">
        <h3>
            <%--${tripInstanceObject.id}--%>
            ${tripInstanceObject.title}
        </h3>
        ${tripInstanceObject.description}
        <br/>
        <spring:message code='trip.creator'/>:
        <a href="/profile/view/${tripInstanceObject.organiser.id}"
           class="active">${tripInstanceObject.organiser.name}</a> <br/>
        <spring:message code='text.date'/>: ${date} <br/>
        <spring:message code='text.time'/>: ${startTimeString} - ${endTimeString}<br/>
        <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
            <a href="/trip/edit/${tripInstanceObject.id}" class="button"><spring:message
                    code='trip.edit'/></a>

            <p id="invitefriends" class="button" data-instance='${tripInstanceObject.id}'
               data-naam='${tripInstanceObject.title}'><spring:message code="invite.invitefriendsfb"/></p>

            <p id="invitefriendsmail" class="button"><spring:message code="invite.bymail"/></p>

        </c:if>
        <c:if test="${!isUserParticipating}">
            <form method="post" action="/trip/join">
                <input type="hidden" value="${tripInstanceObject.id}" name="tripId"/>
                <input type="submit" class="button"
                       value="<spring:message code='tripinstance.join'/>"/>
            </form>
        </c:if>
    </section>
    <div class="quarter">
        <section>
            <h4><spring:message code='tripinstance.participants'/></h4>
            <c:choose>
                <c:when test="${!empty tripInstanceObject.participants}">
                    <table>
                        <c:forEach var="user" items="${tripInstanceObject.participants}">
                            <tr>
                                <td><a href="/profile/view/${user.id}">${user.name}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <spring:message code='tripinstance.noparticipants'/>
                </c:otherwise>
            </c:choose>
            <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                <div id="assignRequirementToParticipant"
                     title="<spring:message code="requirementinstance.assignresponsible"/>">
                    <form method="POST" action="/requirementInstance/assign">
                        <input type="hidden" name="requirementinstanceid" id="requirementinstanceid"/>
                        <select name="responsibleuser">
                            <option value="0">Iedereen</option>
                            <c:forEach var="participant" items="${tripInstanceObject.participants}">
                                <option value="${participant.id}">${participant.name}</option>
                            </c:forEach>
                            <input type="submit" class="button" value="<spring:message code="text.save"/>"/>
                        </select>
                    </form>
                </div>
            </c:if>
        </section>

        <section>
            <h4>
                <spring:message code='requirement.requirements'/>
                <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                    <img src="/images/add.jpg" class="addrequirementinstance" width="18"
                         instid="${tripInstanceObject.id}"/>
                </c:if>
            </h4>
            <c:choose>
                <c:when test="${!empty tripInstanceObject.requirementInstances}">
                    <table>
                        <c:forEach var="requirementInstance" items="${tripInstanceObject.requirementInstances}">
                            <tr>
                                <td>
                                    <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                                        <%--TODO make modal to edit requirement--%>
                                        <a href="/trips/editinstancerequirement/${requirementInstance.id}"
                                           class="active">
                                            <c:out value="${requirementInstance.name}"/> DIT WERKT NIET
                                        </a>
                                    </c:if>
                                    <c:if test="${tripInstanceObject.organiser.id != userObject.id}">
                                        <c:out value="${requirementInstance.name}"/>
                                    </c:if>
                                </td>
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
                                        <td><spring:message code="requirementinstance.responsible"/>:
                                            <spring:message code='requirement.allusers'/></td>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                                    <td class='assignrequirement' inid="${requirementInstance.id}">
                                        Click to assign to participant
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <br/>
                    <spring:message code='requirement.norequirementsfound'/>
                </c:otherwise>
            </c:choose>
        </section>
        <section>
            <h4>
                <spring:message code='cost.costs'/>
                <img class="addcost" src="/images/add.jpg" width="18"/>
            </h4>
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
                                <c:if test="${tripInstanceObject.organiser.id == userObject.id || cost.user.id == userObject.id}">
                                    <td class="editcost" inid="${tripInstanceObject.id}" incostid="${cost.id}"
                                        indesc="${cost.description}" inam="${cost.amount}">
                                            <spring:message code="text.edit"/>
                                    <td>
                                        <form method="post" action="/cost/delete">
                                            <input type="hidden" value="${cost.id}" name="costId"/>
                                            <input type="hidden" value="${tripInstanceObject.id}"
                                                   name="tripInstanceId"/>
                                            <input type="image" src="/images/remove.jpg" width="18" height="18"/>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <spring:message code='cost.nocostsfound'/>
                </c:otherwise>
            </c:choose>
            <br>
        </section>
    </div>
    <div class="three-quarter">
        <section>
            <h4>
                <spring:message code='message.messages'/>
                <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                    <img class="addmessage" src="/images/add.jpg" width="18"/>
                </c:if>
            </h4>
            <c:if test="${tripInstanceObject.organiser.id == userObject.id}">

            </c:if>

            <c:choose>
                <c:when test="${!empty tripInstanceObject.messages}">
                    <table>
                        <c:forEach var="message" items="${tripInstanceObject.messages}">
                            <th>${messageDates.get(message.id)}</th>
                            <tr>
                                <td>${message.content}</td>
                                <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                                    <td>
                                        <form method="post" action="/message/delete">
                                            <input type="hidden" value="${message.id}" name="messageId"/>
                                            <input type="hidden" value="${tripInstanceObject.id}"
                                                   name="tripInstanceId"/>
                                            <input type="image" src="/images/remove.jpg" width="18" height="18"/>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <spring:message code='message.nomessagesfound'/>
                </c:otherwise>
            </c:choose>
            <br>
        </section>
    </div>
</div>
</div>

<div id="addMessage" title="<spring:message code="message.add"/>">
    <form method="post" action="/message/add" class="mainstyle tooltips">
        <input type="hidden" name="tripInstanceId" title="tripInstanceId" value="${tripInstanceObject.id}"/>

        <div class="row">
            <span><spring:message code='message.content'/></span>
            <input name="content" id="addMessageContent"/>

        </div>
        <input type="submit" class="button" value="<spring:message code='text.add'/>"/>

    </form>
</div>

<div id="addRequirementInstance" title="<spring:message code="requirement.create"/>">
    <form method="post" action="/requirementInstance/add" class="mainstyle tooltips">
        <input type="hidden" name="tripInstanceId" id="addRequirementTripInstanceId"
               title="tripInstanceId" value="${tripInstanceId}"/>

        <div class="row">
            <span><spring:message code='text.name'/></span>
            <input name="name" id="addRequirementName" title="<spring:message code='requirement.name'/>"/>
        </div>
        <div class="row">
            <span><spring:message code='text.amount'/></span>
            <input name="amount" class="required" id="addRequirementAmount"
                   title="<spring:message code='requirement.amount'/>"/> <%--TODO: number-validation--%>
        </div>
        <div class="row">
            <span><spring:message code='text.description'/></span>
            <textarea name="description" id="addRequirementDescription"
                      title="<spring:message code='requirement.description'/>"></textarea>
        </div>
        <input title="<spring:message code='text.save'/>" type="submit" class="button"
               value="<spring:message code='text.save'/>"/>
    </form>
</div>

<div id="addCost" title="<spring:message code="cost.add"/>">
    <form method="POST" action="/cost/add">
        <input type="hidden" name="tripInstanceId" title="tripInstanceId" value="${tripInstanceId}"/>

        <div class="row">
            <span><spring:message code='text.description'/></span>
            <input name="description" id="addcostdesc" title="<spring:message code='cost.description'/>"/>
        </div>
        <div class="row">
            <span><spring:message code='cost.amount'/></span>
            <input name="amount" id="addcostam" title="<spring:message code='cost.amount'/>"/>
        </div>
        <input type="submit" class="button" value="<spring:message code="text.add"/>"/>
    </form>
</div>


<div id="editCost" title="<spring:message code="cost.edit"/>">
    <form method="POST" action="/cost/edit">
        <input type="hidden" id="costid" name="costid"/>
        <input type="text" name="description" id="descval"
               title="<spring:message code="cost.description"/>"/>
        <input type="text" name="amount" id="amval"
               title="<spring:message code="cost.amount"/>"/>
        <input type="submit" class="button" value="<spring:message code="text.save"/>"/>
    </form>
</div>

<div id="invitemail" title="<spring:message code="invite.bymail" />">
    <form action="/trip/invitebymail" method="POST" class="mainstyle">
        <input type="hidden" name="instanceid" value="${tripInstanceObject.id}"/>
        <input type="hidden" name="instancename" value="${tripInstanceObject.title}"/>

        <div class="row"><span><spring:message code="invite.receipients"/></span><textarea
                name="receipients"></textarea></div>
        <div class="row"><span><spring:message code="invite.personalmessage"/></span><textarea
                name="message"></textarea></div>
        <input type="submit" class="button" value="<spring:message code="invite.doinvite"/>"/>
    </form>
</div>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<script src="/js/functions.js"></script>
<script>maketripsortable();
preparemodal();
</script>
</body>
</html>
