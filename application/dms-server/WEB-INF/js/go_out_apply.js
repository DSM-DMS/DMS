$("#sat_go").on('flip:done',function(){
  var val = $("#sat_go").data("flip-model")
  if(val.isFlipped) {
    $("#sat_val").text('외출 X');
  } else {
    $("#sat_val").text('외출 O');
  }

$("#sun_go").on('flip:done',function(){
  var val = $("#sun_go").data("flip-model")
  if(val.isFlipped) {
    $("#sun_val").text('외출 X');
  } else {
    $("#sun_val").text('외출 O');
  }
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
