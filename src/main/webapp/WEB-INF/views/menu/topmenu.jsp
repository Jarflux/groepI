<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="centermenu">


    <ul>
        <li><a href="/" class="active"><spring:message code='menu.home'/></a></li>
        <li><a href="/template/list" class="active"><spring:message code='menu.organisetrips'/></a></li>
        <li><a href="/trip/list" class="active"><spring:message code='menu.trips'/></a></li>
        <li><a href="/profile/myprofile" class="active"><spring:message code='menu.profile'/></a></li>
        <li><a href="j_spring_security_logout" class="active"><spring:message code='menu.logout'/></a></li>
    </ul>

    <ul id="theme">
        <li data-theme="blue.css">Blue</li>
        <li data-theme="funky.css">Funky</li>
    </ul>
</div>