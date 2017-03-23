function getWeek(date) {
  var weekNum = new Date(date.getFullYear(), date.getMonth(), 1);
  return parseInt(((date.getDate() - 1) + weekNum.getDay()) / 7) + 1;
}

$('#stay_download').on('click', function () {
  var today = new Date();
  var year = today.getFullYear();
  var month = today.getMonth() + 1;
  var week = getWeek(today);
  location.href = 'http://dsm2015.cafe24.com/stay/download?year=' + year + '&month=' + month + '&week=' + week;
});

$('#goOut_download').on('click', function () {
  location.href = 'http://dsm2015.cafe24.com/goingout/download';
})

$('#extension_download').on('click', function () {
  location.href = 'http://dsm2015.cafe24.com/extension/download';
})
