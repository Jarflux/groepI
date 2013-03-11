
window.fbAsyncInit = function() {
    // init the FB JS SDK
    FB.init({
        appId      : '349005061872374', // App ID from the App Dashboard
        channelUrl : '/fbloginpage', // Channel File for x-domain communication
        status     : true, // check the login status upon init?
        cookie     : true, // set sessions cookies to allow your server to access the session?
        xfbml      : true  // parse XFBML tags on this page?
    });

    // Additional initialization code such as adding Event Listeners goes here

};

// Load the SDK's source Asynchronously
// Note that the debug version is being actively developed and might
// contain some type checks that are overly strict.
// Please report such bugs using the bugs tool.
(function(d, debug){
    var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement('script'); js.id = id; js.async = true;
    js.src = "//connect.facebook.net/en_US/all" + (debug ? "/debug" : "") + ".js";
    ref.parentNode.insertBefore(js, ref);
}(document, /*debug*/ false));


var formfout=false;

$(function()
{
    preparetooltips();
    validateform();

    addhandlers();





})
function addhandlers()
{
    $("#fblogin").on("click",function()
    {
        fblogin();
    })

    $("#invitefriends").on("click",function()
    {
        invitefriendsdialog();
    })
}
function preparetooltips()
{
}

function preparemodal() {
    $("#assignRequirementToParticipant").dialog({
        modal: true,
        autoOpen: false

    });

    $('.addrequirement').on("click", function () {
        $("#assignRequirementToParticipant").dialog('open');
        var instid = $(this).attr("inid");
        $("#requirementinstanceid").val(instid);

    });

    $("#editCost").dialog({
        modal: true,
        autoOpen: false
    });

    $('.editcost').on("click", function () {
        $("#editCost").dialog("open");
        var instcostid = $(this).attr("incostid");
        var instdesc = $(this).attr("indesc");
        var instam = $(this).attr("inam");
        $("#costid").val(instcostid);
        $("#descval").val(instdesc);
        $("#amval").val(instam);
    });
}

function preparetooltips() {
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
function validateform() {
    $(".dateregister").dateinput({format: 'dd-mm-yyyy', yearRange: [-80, -12], firstDay: 1, selectors: true, value: "Today"  });
    $(".date").dateinput({format: 'dd-mm-yyyy', yearRange: [0, 1], firstDay: 1, selectors: true, value: "Today"  });
    $("form.validate input, form.validate textarea").each(function () {
        $(this).after("<span class='deerror'></span>");
        /* Required validatie */
        if ($(this).hasClass("required")) {
            $(this).prev("span").append("<span style='color: red;width:20px;margin:0;paddin'>*</span>");
            $(this).on("blur", function () {
                lengte = $(this).val().length;
                console.log("Lengte is " + lengte);
                if (lengte == 0) {
                    $(this).next(".deerror").addClass("inputerror").text("Dit veld is verplicht.");
                }
                else {
                    $(this).next(".deerror").removeClass("inputerror").text("");
                }
            })
        }
        /* Equalsto validatie */
        if ($(this).hasClass("equalsto")) {
            andere = $(this).attr("equalsto");
            $(this).on("blur", function () {
                dezewaarde = $(this).val();
                anderewaarde = $("input[name=" + andere + "]").val();
                if (dezewaarde != anderewaarde) {
                    $(this).next(".deerror").addClass("inputerror").text("Veld is niet gelijk");
                }
                else {
                    $(this).next(".deerror").removeClass("inputerror").text("")
                }
            })

            }
        })
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
function invitefriendsdialog()
{

        FB.ui({method: 'apprequests',
            message: 'Invite the friends yes.'
        }, function(requestid,toids)
        {
            console.log("Friends waren: "+requestid.to);
        });

}

