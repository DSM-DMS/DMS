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

$('#sat_out').on('click', function () {
  $('#sat_out').css('background-color', 'rgb(255, 122, 249)');
  $('#sun_out').css('background-color', 'rgb(230, 230, 230)');
  $('#out_day').val('sat');
});

$('#sun_out').on('click', function () {
  $('#sun_out').css('background-color', 'rgb(255, 122, 249)');
  $('#sat_out').css('background-color', 'rgb(230, 230, 230)');
  $('#out_day').val('sun');
});
