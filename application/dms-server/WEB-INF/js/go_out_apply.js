<<<<<<< HEAD
$('#sat_go').flip();
$('#sun_go').flip();
=======
$("#sat_go").flip();
$("#sun_go").flip();
$("#sat_go").flip(satBool);
$("#sun_go").flip(sunBool);
$("#sat_go .back").removeAttr('hidden');
$("#sun_go .back").removeAttr('hidden');
>>>>>>> 46c7e1bcbd1fe3465ec3964c0d5760d37544783c

var fillSat = function () {
  var val = ${status_1?c};
  if(val) {
    $("#sat_val").text('외출 X');
  } else {
    $("#sat_val").text('외출 O');
  }
};

var fillSun = function () {
  var val = ${status_2?c};
  if(val) {
    $("#sun_val").text('외출 X');
  } else {
    $("#sun_val").text('외출 O');
  }
};

$('#sat_go').flip(${status_1?c});
$('#sun_go').flip(${status_2?c});

fillSat();
fillSun();

$('#sat_go .back').removeAttr('hidden');
$('#sun_go .back').removeAttr('hidden');