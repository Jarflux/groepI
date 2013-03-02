var formfout=false;
/*$("#stopType option[stoptype=1]").attr({"selected":"selected"})*/

$(function()
{
    preparetooltips();
    validateform();
})
function preparetooltips()
{
    $("form.tooltips input").tooltip({
        // place tooltip on the right edge
        position: "center right",
        // a little tweaking of the position
        offset: [2, 10],
        // use the built-in fadeIn/fadeOut effect
        effect: "fade",
        // custom opacity setting
        opacity: 0.7

    });
}
function validateform()
{
        $(".dateregister").dateinput({format: 'yyyy-mm-dd',yearRange: [-80, -12],firstDay: 1,selectors: true,value: "Today"  });
        $(".date").dateinput({format: 'yyyy-mm-dd',yearRange: [0, 1],firstDay: 1,selectors: true,value: "Today"  });
        $("form.validate input, form.validate textarea").each(function()
        {
           $(this).after("<span class='deerror'></span>");
            /* Required validatie */
            if ($(this).hasClass("required"))
            {
                $(this).prev("span").append("<span style='color: red;width:20px;margin:0;paddin'>*</span>");
                $(this).on("blur",function()
                {
                    lengte=$(this).val().length;
                    console.log("Lengte is "+lengte);
                    if (lengte==0)
                    {
                        $(this).next(".deerror").addClass("inputerror").text("Dit veld is verplicht.");
                    }
                    else
                    {
                        $(this).next(".deerror").removeClass("inputerror").text("");
                    }
                })
            }
             /* Equalsto validatie */
            if ($(this).hasClass("equalsto"))
            {
                andere=$(this).attr("equalsto");
                $(this).on("blur",function()
                {
                    dezewaarde=$(this).val();
                    anderewaarde=$("input[name="+andere+"]").val();
                    if (dezewaarde!=anderewaarde)
                    {
                        $(this).next(".deerror").addClass("inputerror").text("Veld is niet gelijk");
                    }
                    else
                    {
                        $(this).next(".deerror").removeClass("inputerror").text("")
                    }
                })

            }
        })
    }

/*Google Map Functions*/
/*http://jsfiddle.net/fatihacet/CKegk/*/
var map;
var myOptions = {
    zoom: 7,
    center: new google.maps.LatLng(51.221212,4.399166),
    mapTypeId: 'roadmap'
};
var markers = {};

function initializeGMaps()
{
    map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
    google.maps.event.addListener(map, 'click', function(e) {
        var lat = e.latLng.lat(); // lat of clicked point
        var lng = e.latLng.lng(); // lng of clicked point
        var markerId = getMarkerUniqueId(lat, lng); // an that will be used to cache this marker in markers object.
        var marker = new google.maps.Marker({
            position: getLatLng(lat, lng),
            map: map,
            draggable:true,
            id: 'marker_' + markerId
        });
        markers[markerId] = marker; // cache marker in markers object
        bindMarkerEvents(marker); // bind right click event to marker
    });
}
var getMarkerUniqueId= function(lat, lng) {
    return lat + '_' + lng;
}
var getLatLng = function(lat, lng) {
    return new google.maps.LatLng(lat, lng);
};
var bindMarkerEvents = function(marker) {
    google.maps.event.addListener(marker, "click", function (point) {
        var markerId = getMarkerUniqueId(point.latLng.lat(), point.latLng.lng()); // get marker id by using clicked point's coordinate
        var marker = markers[markerId]; // find marker
        removeMarker(marker, markerId); // remove it
    });
    google.maps.event.addListener(marker, "rightclick", function (point) {
        var window = new google.maps.InfoWindow({});
        var markerId = getMarkerUniqueId(point.latLng.lat(), point.latLng.lng()); // get marker id by using clicked point's coordinate
        var marker = markers[markerId];
        window.setPosition(point.latLng);
        window.setContent(point.latLng.toString());
        window.open(map, marker);
    });
};
var removeMarker = function(marker, markerId) {
    marker.setMap(null); // set markers setMap to null to remove it from map
    delete markers[markerId]; // delete marker instance from markers object
};