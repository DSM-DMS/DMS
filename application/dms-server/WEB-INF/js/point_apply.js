$('#recommend').hide();
$('#point_apply').css('background-color', 'rgb(91, 192, 222)');
$('#point_reco').css('background-color', 'rgb(230, 230, 230)');
$('#sat_out').css('background-color', 'rgb(255, 122, 249)');
$('#sun_out').css('background-color', 'rgb(230, 230, 230)');

$('#point_apply').on('click', function () {
  $('#point_apply').css('background-color', 'rgb(91, 192, 222)');
  $('#point_reco').css('background-color', 'rgb(230, 230, 230)');
  $('#individual').show();
  $('#recommend').hide();
});

$('#point_reco').on('click', function () {
  $('#point_reco').css('background-color', 'rgb(91, 192, 222)');
  $('#point_apply').css('background-color', 'rgb(230, 230, 230)');
  $('#recommend').show();
  $('#individual').hide();
});


$("#individual_btn").click(function() {
    var reason = $("#individual_reason").val();
    $.ajax({
        url: "http://dsm2015.cafe24.com:8089/apply/merit",
        type: "POST",
        data: {
            "id": id,
            "content": reason
        },
        success: function() {
            alert("신청되었습니다.");
        }
    });
});

// 추천탭 추천버튼 추천 이벤트
$("recommend_btn").click(function() {
    var reason = $("#recommend_reason").val();
    var person = $("#recommend_name").val();
    $.ajax({
        url: "http://dsm2015.cafe24.com:8089/apply/merit",
        type: "GET",
        data: {
            "id": id,
            "content": reason,
            "target": person
        },
        success: function() {
            alert('신청되었습니다.');
        }
    });
});
