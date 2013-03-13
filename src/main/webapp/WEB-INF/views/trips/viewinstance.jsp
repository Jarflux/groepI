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
    <link href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" rel="stylesheet"/>
</head>
<body>
<div id="wrapper">
    <div id="topmenu" class="column dark">
        <jsp:include page="/topmenu"/>
    </div>
    <div id="content" class="column light">
        <h2><spring:message code='text.viewtripinformation'/></h2>
        <section class="fill">
            <h3>
                ${tripInstanceObject.id}
                ${tripInstanceObject.title}
            </h3>
            ${tripInstanceObject.description}
            <br/>
            <spring:message code='text.triporganiser'/>:
            <a href="/profile/view/${tripInstanceObject.organiser.id}"
               class="active">${tripInstanceObject.organiser.name}</a> <br/>
            <spring:message code='text.date'/>: ${date} <br/>
            <spring:message code='text.time'/>: ${startTimeString} - ${endTimeString}<br/>
            <%--<spring:message code='text.to'/>:  <br/>--%>

            <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                <a href="/trips/editinstance/${tripInstanceObject.id}" class="button"><spring:message
                        code='text.edittrip'/></a>           <p id="invitefriends" class="button" data-instance='${tripInstanceObject.id}' data-naam='${tripInstanceObject.title}'><spring:message code="text.invitefriendsfb"/></p> <p id="invitefriendsmail" class="button"><spring:message code="invite.bymail"/> </p>
            </c:if>



        </section>
        <div class="quarter">

                <section>
                    <h4><spring:message code='text.participants'/></h4>
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
                            <spring:message code='text.noparticipants'/>
                        </c:otherwise>
                    </c:choose>



                    <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                        <div id="assignRequirementToParticipant" title="<spring:message code="text.assignrequirementtoparticipant"/>">
                            <p>

                            <form method="POST" action="/trips/assignrequirementtouser">
                                <input type="hidden" name="requirementinstanceid"/>
                                <select name="responsibleuser">
                                    <option value="0">Iedereen</option>
                                    <c:forEach var="participant" items="${tripInstanceObject.participants}">
                                        <option value="${participant.id}">${participant.name}</option>
                                    </c:forEach>
                                    <input type="submit" class="button" value="<spring:message code="text.save"/>"/>
                                </select>
                            </form>
                            </p>
                        </div>
                    </c:if>
                </section>

            <section>
                <h4><spring:message code='text.stops'/></h4>
                <%--<form method="get" action="/trips/addstop/${tripObject.id}">
                    <input type="submit" class="button" value="<spring:message code='text.add'/>"/>
                </form>--%>
                <%--<a href="/trips/addstop/${tripInstanceObject.id}" class="active"><spring:message code='text.add'/></a>--%>
                <c:choose>
                    <c:when test="${!empty tripInstanceObject.trip.stops}">
                        <ul class='sortable'>
                            <c:forEach var="stop" items="${tripInstanceObject.trip.stops}">
                                <li id="stop-${stop.id}">${stop.stopnumber}:
                                    <a href="/trips/editStop/${stop.id}" class="active"><c:out value="${stop.name}"/></a>
                                </li>
                                <%--<li>view die stop jongeuh</li>--%>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <spring:message code='text.nostopsfound'/>
                    </c:otherwise>
                </c:choose>
            </section>


            <section>
                <h4><spring:message code='text.requirements'/></h4>


                <c:choose>
                    <c:when test="${!empty tripInstanceObject.requirementInstances}">
                        <table>
                            <c:forEach var="requirementInstance" items="${tripInstanceObject.requirementInstances}">
                                <tr>
                                    <td>
                                        <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                                            <a href="/trips/editinstancerequirement/${requirementInstance.id}" class="active">
                                                <c:out value="${requirementInstance.name}"/>
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
                                            <td><spring:message code="text.requirementinstancebrings"/>:
                                                <spring:message code='text.requirementinstanceforallusers'/></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                                        <td class='addrequirement' inid='${requirementInstance.id}'>
                                            Click to assign to participant
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <br/>
                        <spring:message code='text.norequirementsfound'/>
                    </c:otherwise>
                </c:choose>
                <c:if test="${tripInstanceObject.organiser.id == userObject.id}">            <br>
                    <a href="/trips/addinstancerequirement/${tripInstanceObject.id}" class="button">
                        <spring:message code='text.add'/>
                    </a>
                </c:if>
            </section>
        <section>
            <h4><spring:message code='text.costs'/></h4>

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
                                        <form method="post" action="/trips/deletecost">
                                            <input type="hidden" value="${cost.id}" name="costId"/>
                                            <input type="hidden" value="${tripInstanceObject.id}"
                                                   name="tripInstanceId"/>
                                            <input type="submit" class="button"
                                                   value="<spring:message code='text.deletecost'/>"/>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <spring:message code='text.nocostsfound'/>
                </c:otherwise>
            </c:choose>
            <br>            <a href="/trips/addcost/${tripInstanceObject.id}" class="button"><spring:message code='text.add'/></a>

        </section>
                  </div>

        <div class="three-quarter">
            <section>
                <h4><spring:message code='text.messages'/></h4>

                <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                    <a href="/trips/addmessage/${tripInstanceObject.id}" class="active">
                        <spring:message code='text.add'/>
                    </a>
                </c:if>
                <c:choose>
                    <c:when test="${!empty tripInstanceObject.messages}">
                        <table>
                            <c:forEach var="message" items="${tripInstanceObject.messages}">
                                <tr>
                                    <td>${message.user.name}</td>

                                    <c:forEach var="messageDate" items="${messageDates}">
                                        <c:choose>
                                            <c:when test="${messageDate.key == tripInstanceObject.id}">
                                                <td>${messageDate.value}</td>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${tripInstanceObject.organiser.id == userObject.id}">
                                        <td>
                                            <form method="post" action="/trips/deletemessage">
                                                <input type="hidden" value="${message.id}" name="messageId"/>
                                                <input type="hidden" value="${tripInstanceObject.id}"
                                                       name="tripInstanceId"/>

                                                <input type="submit" class="button"
                                                       value="<spring:message code='text.deletemessage'/>"/>
                                            </form>
                                        </td>
                                    </c:if>
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
                <br>
                <form method="post" action="/trips/doaddmessage" class="mainstyle tooltips">
                    <input type="hidden" name="tripInstanceId" title="tripInstanceId" value="${tripInstanceObject.id}"/>

                    <div class="row">
                        <span><spring:message code='text.messagecontent'/></span>
                        <input name="content" title="<spring:message code='text.messagecontent'/>"/>
                    </div>

                    <input type="submit" class="button" value="<spring:message code='text.save'/>"/>
                </form>
                <a href="/trips/addmessage/${tripInstanceObject.id}" class="button"><spring:message
                        code='text.add'/></a>
            </section>
        </div>


    </div>
</div>



<div id="editCost" title="<spring:message code="text.editcost"/>">
    <p>

    <form method="POST" action="/trips/editcost">
        <input type="hidden" id="costid" name="costid"/>
        <input type="text" name="description" id="descval"
               title="<spring:message code="text.costdescription"/>"/>
        <input type="text" name="amount" id="amval"
               title="<spring:message code="text.costamount"/>"/>
        <input type="submit" class="button" value="<spring:message code="text.save"/>"/>
    </form>
    </p>

</div>
<div id="invitemail" title="<spring:message code="invite.bymail" />">
    <form action="/trips/invitebymail" method="POST" class="mainstyle">
        <input type="hidden" name="instanceid" value="${tripInstanceObject.id}"/>
        <input type="hidden" name="instancename" value="${tripInstanceObject.title}"/>
      <div class="row"><span><spring:message code="invite.receipients"/></span><textarea name="receipients"></textarea> </div>
        <div class="row"><span><spring:message code="invite.personalmessage"/></span><textarea name="message"></textarea> </div>

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
