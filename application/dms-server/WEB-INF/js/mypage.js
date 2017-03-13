var cardsArr = $(".mypage-component-container");

for (var loop = 2; loop < cardsArr.length; loop++) {
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").data("loop", loop);
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").click(function() {
        if ($(this).data("clicked")) {
            $(this).text("클릭하여 자세히보기");

            $(this).siblings(".simple-container").show(500);
            $(this).siblings(".detail-container").hide(500);
            $(this).data("clicked", false);
        } else {
            $(this).text("클릭하여 간략히보기");

            $(this).siblings(".simple-container").hide(500);
            $(this).siblings(".detail-container").show(500);
            $(this).data("clicked", true);
        }
    })
}

$(".modify-password").click(function () {

})
