var clickable = true;

$("#day_go").click(function(){
  if(clickable) {
    $("#day_go").animate({background: '#90EE90'});
    clickable = false;
  } else {
    $("#day_go").animate({background: '#FFB6C1'});
    clickable = true;
  }
});
