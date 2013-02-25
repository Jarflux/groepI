var formfout=false;
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
