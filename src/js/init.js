$(document).ready(function(){
   $window = $(window);
 
   $('div[parallax="true"]').each(function(){
     var $scroll = $(this);
      $(window).scroll(function() {                           
        var yPos = -($window.scrollTop() / 4);
        var coords = '50% '+ yPos + 'px';
        $scroll.css({ backgroundPosition: coords });   
      });
   });
});
