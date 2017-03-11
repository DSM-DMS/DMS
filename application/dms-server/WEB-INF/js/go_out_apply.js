$("#sat_go").flip();
$("#sun_go").flip();

$("#sat_go .front").on('click', function () {
  $("#sat_val").text('외출 X');
});

$("#sat_go .back").on('click', function () {
  $("#sat_val").text('외출 O');
});

$("#sun_go .front").on('click', function () {
  $("#sun_val").text('외출 X');
});

$("#sun_go .back").on('click', function () {
  $("#sun_val").text('외출 O');
});
