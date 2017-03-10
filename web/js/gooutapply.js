clickable = true;

$('#selection').on("click", function () {
  if(clickable) {
    $("#select_table td:eq(1), td:eq(2)").show();
    clickable = false;

    $("#sat").on("click", function () {
      $('#selection').text('토요일')
      $('#day').attr('value', '토요일');
      $("#select_table td:eq(1), td:eq(2)").hide();
      clickable = true;
    })

    $('#sun').on("click", function () {
      $('#selection').text('일요일');
      $('#day').attr('value', '일요일');
      $("#select_table td:eq(1), td:eq(2)").hide();
      clickable = true;
    })

  } else {
    $("#select_table td:eq(1), td:eq(2)").hide();
    clickable = true;
  }
})

$('#submit_button').on('click', function () {
  $('#apply_form').submit();
})
