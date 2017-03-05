$('.menu-controller').click(function() {
    var top = $('.slider-menu-container');
  if(top.hasClass( "hide" )){
      top.removeClass('hide');
  }else{
      top.addClass('hide');
  }
});