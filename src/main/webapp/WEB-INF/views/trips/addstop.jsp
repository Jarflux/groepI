<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mitch Va Daele
  Date: 26-2-13
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Stop</title>
        <link href="/css/blue.css" rel="stylesheet"/>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
        <%--<script>
            var map;
            function initialize() {
            var mapOptions = {
            zoom: 8,
            center: new google.maps.LatLng(-34.397, 150.644),
            mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById('map_canvas'),
            mapOptions);
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>--%>
        <%--<script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAPqDCjf9lJDNy4kgA6CMphIibJHG5-OFw&sensor=false">
        </script>--%>
        <script>
            var map;
            var myCenter=new google.maps.LatLng(51.508742,-0.120850);
            function initialize()
            {
                var mapProp = {
                    center: myCenter,
                    zoom:5,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                map = new google.maps.Map(document.getElementById("map_canvas"),mapProp);
                var marker = new google.maps.Marker({
                    position: myCenter,
                    title:'Click to zoom'
                });
                marker.setMap(map);
                // Zoom to 9 when clicking on marker
                google.maps.event.addListener(marker,'click',function() {
                    map.setZoom(9);
                    map.setCenter(marker.getPosition());
                });
                google.maps.event.addListener(map, 'click', function(event) {
                    placeMarker(event.latLng);
                });
            }

            function placeMarker(location) {
                var marker = new google.maps.Marker({
                    position: location,
                    map:map
                });
                var infowindow = new google.maps.InfoWindow({
                    content: 'Latitude: ' + location.lat() +
                            '<br>Longitude: ' + location.lng()
                });
                infowindow.open(map,marker);
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="topmenu" class="column dark">
                <jsp:include page="/topmenu"/>
            </div>
            <div id="content" class="column light">
                <h2><spring:message code="text.addstop"/></h2>



                <form method="post" action="" class="mainstyle tooltips validate">
                    <div class="half big">
                        <div class="gmap" id="map_canvas">
                        </div>
                    </div>
                    <div class="half">
                        <div class="row">
                            <span><spring:message code='text.name'/></span>
                            <input type="text" name="name"/>
                        </div>
                        <div class="row">
                            <span><spring:message code='text.type'/></span>
                            <select>
                                <option value="1"><spring:message code='text.interactive'/></option>
                                <option value="2"><spring:message code='text.informative'/></option>
                            </select>
                        </div>
                        <div class="row">
                            <span><spring:message code='text.rending'/></span>
                            <select>
                                <option value="1"><spring:message code='text.normal'/></option>
                                <option value="2">Augmented Reality</option>
                            </select>
                        </div>

                        <div class="row">
                            <span><spring:message code='text.description'/></span>
                            <input type="text" name="stopText"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>