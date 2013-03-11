/*Google Map Functions*/
/*http://jsfiddle.net/fatihacet/CKegk/*/
var map;
var myOptions = {
    zoom: 7,
    center: new google.maps.LatLng(51.221212,4.399166),
    mapTypeId: 'roadmap'
};
var marker = new google.maps.Marker({});
var info = new google.maps.InfoWindow({});
var yMarker = 'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png';
var gMarker = 'http://maps.google.com/mapfiles/ms/icons/green-dot.png';
var bMarker = 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png';
var pMarker = 'http://maps.google.com/mapfiles/ms/icons/pink-dot.png';

function initializeGMaps()
{
    map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
    google.maps.event.addListener(map, 'click', function(e) {
        var lat = e.latLng.lat(); // lat of clicked point
        var lng = e.latLng.lng(); // lng of clicked point
        marker.setMap(null);
        marker = new google.maps.Marker({
            position: getLatLng(lat, lng),
            map: map,
            draggable:true
        });
        setInputText(lat, "latitude");
        setInputText(lng, "longitude");
        bindMarkerEvents(marker); // bind right click event to marker
    });
}
var getLatLng = function(lat, lng) {
    return new google.maps.LatLng(lat, lng);
};
var bindMarkerEvents = function(marker) {
    /*google.maps.event.addListener(marker, "click", function (point) {
     });*/
    google.maps.event.addListener(marker, "rightclick", function (point) {
        /*window.close()
         window.setPosition(point.latLng);
         window.setContent(point.latLng.toString());
         window.open(map, marker);*/
        removeMarker(marker); // remove it
    });
    google.maps.event.addListener(marker, "dragend", function (point){
        setInputText(point.latLng.lat(), "latitude");
        setInputText(point.latLng.lat(), "longitude");
    });

};
var removeMarker = function(marker) {
    marker.setMap(null); // set markers setMap to null to remove it from map
};
function setInputText(text, controlId){
    $('[name="' + controlId + '"]').val(text);
}

function placeStopMarker(latitude, longitude){
    marker = new google.maps.Marker({
        position: new google.maps.LatLng(latitude,longitude),
        map: map,
        draggable:true,
        icon:gMarker
    });
    bindMarkerEvents(marker);
}
