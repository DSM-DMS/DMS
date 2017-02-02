clickable = true;

$('#selection').on("click", function () {
  if(clickable) {
    $('#select_table').append('<tr><td id="sat" style="border-radius: 0 0 10% 10%">토요일</td></tr><tr><td id="sun" style="border-radius: 0 0 10% 10%">일요일</tr></td>');
    clickable = false;

    $("#sat").on("click", function () {
      $('#selection').text('토요일')
      $('#day').attr('value', '토요일');
      $("#select_table tr:eq(1), tr:eq(2)").remove();
      clickable = true;
    })

    $('#sun').on("click", function () {
      $('#selection').text('일요일');
      $('#day').attr('value', '일요일');
      $("#select_table tr:eq(1), tr:eq(2)").remove();
      clickable = true;
    })

  } else {
    $("#select_table tr:eq(1), tr:eq(2)").remove();
    clickable = true;
  }
})
