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

$("#go_submit").on('click', function () {
  var satVal = false;
  var sunVal = false;
  if($("#sat_val").text() == '외출 O') {
    satVal = true;
  }
  if($("#sun_val").text() == '외출 X') {
    sunVal = true;
  }
  $.ajax({
    url: "/apply/goingout",
    type: "PUT",
    data: {
      "sat": satVal,
      "sun": sunVal
    },
    success: function() {
      alert('신청되었습니다.');
    }
  });
});
