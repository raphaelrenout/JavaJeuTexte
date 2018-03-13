
(function () {
    "use strict";

    var $  = jQuery.noConflict();


    $(document).ready(function() {  

        createBrowserInfo();
        
    });


    $(window).resize(function() {
        
        updateBrowserInfoWidth();
        updateBrowserInfoOrientation();
    
    });


    // FUNCTIONS

    function createBrowserInfo () {

        // ajoute .browser-infos au body
        $('body').append('<div class="browser-infos">Width: <span class="browser-infos__width">xxx</span>px<br>Orientation: <span class="browser-infos__orientation">xxx</span></div>');

        // style .browser-infos
        $('.browser-infos').css({
            'position': 'fixed',
            'top': 0,
            'right': 0,
            'padding': '5px',
            'border-left': '1px solid #BCC655',
            'border-bottom': '1px solid #BCC655',
            'font-size': '12px',
            'color': '#BCC655',
            'text-align': 'right',
            'font-family': 'Arial, sans-serif',
            'background-color': '#1B2228'
        });


        updateBrowserInfoWidth();
        updateBrowserInfoOrientation();
    }

    function updateBrowserInfoWidth () {

        $('.browser-infos__width').html($(window).width());

    }

    function updateBrowserInfoOrientation () {
    
        var windowHeight = $(window).height();
        var windowWidth = $(window).width();

        if ( windowHeight > windowWidth) {
            $('.browser-infos__orientation').html('portait');
        
        } else {
            $('.browser-infos__orientation').html('lanscape');

        }
    }


}(jQuery));