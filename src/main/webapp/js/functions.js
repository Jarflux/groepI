var formfout=false;
/*$("#stopType option[stoptype=1]").attr({"selected":"selected"})*/

$(function()
{
    preparetooltips();
    validateform();

    $("#fblogin").on("click",function()
    {
        fblogin();
    })
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
        $(".dateregister").dateinput({format: 'dd-mm-yyyy',yearRange: [-80, -12],firstDay: 1,selectors: true,value: "Today"  });
        $(".date").dateinput({format: 'dd-mm-yyyy',yearRange: [0, 1],firstDay: 1,selectors: true,value: "Today"  });
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
function maketripsortable()
{
    $( ".sortable" ).sortable({
        update: function(event, ui) {
            var deorde = $(this).sortable('toArray').toString();
console.log("Order is : "+deorde)        }});
    $( ".sortable" ).disableSelection();
}

function login() {
    FB.login(function(response) {
        if (response.authResponse) {
            // User heeft toestemming gegeven aan de app, controleer of user bestaat met userid in DB en anders aanmaken.
            performLogin()
        } else {

            // User heeft geen toestemming gegeven, helaas:(
            //TODO: Geef foutboodschap dat er geanulleerd is.
        }    // extra rechten nodig om aan e-mailadres te kunnen en
    },{scope:'email,publish_stream,user_birthday'});
}

function fblogin()
{
    FB.getLoginStatus(function(response) {
        if (response.status === 'connected') {
            //user is ingelogd op FB en heeft reeds toestemming gegeven voor de app, check dus of user bestaat met userid in DB anders aanmaken
            console.log("gebruiker is ingelogd al");
            var uid = response.authResponse.userID;
            var accessToken = response.authResponse.accessToken;
            console.log("uid: "+uid+" en token: "+accessToken);
            performLogin();
        } else if (response.status === 'not_authorized') {
           //User is ingelogd op FB maar heeft (nog) geen toestemming gegeven voor de app, toon permissie-dialoog
            login();
        } else {
            // User is zelfs niet ingelogd op FB, toon dialoog om in te loggen (en dan toestemming te geven)
            login();
        }
    });
}
function performLogin() {


    FB.api('/me', function(response) {
    var gegevens= JSON.stringify(response);
               console.log(response);
        $.post("/profile/fblogin",response,function(resultaat)
        {
            window.location ="/profile/myprofile"
        })

    });
}

