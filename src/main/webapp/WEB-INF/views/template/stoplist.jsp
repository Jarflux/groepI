<%--
  Created by IntelliJ IDEA.
  User: Gregory
  Date: 28/03/13
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="full">
    <div class="gmap" id="map_canvas">
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
        //placeStopMarker(parseInt(${stopObject.latitude}), parseInt(${stopObject.longitude}));
    });
</script>
</body>
</html>