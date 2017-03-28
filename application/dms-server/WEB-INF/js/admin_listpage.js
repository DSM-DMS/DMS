$('.edit').on('click', function () {
  var postNum = $(this).parent().first().text().split(' ')[0];
  redirect("post/notice/modify?no=" + postNum);
});

$('.delete').on('click', function() {
  var postNum = $(this).parent().first().text().split(' ')[0];
})
