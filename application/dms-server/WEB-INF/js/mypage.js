// $(".mypage-afterschool .moreinfo-span").click(function() {
//     console.log("aa")
//     // $("mypage-afterschool").attr('class', 'class_name');
//     $(".mypage-afterschool").removeClass("col-lg-6");
//     $(".mypage-afterschool").addClass("col-lg-12");
// })

var cardsArr = $(".mypage-component-container");

for (var loop = 2; loop < cardsArr.length; loop++) {
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").data("loop", loop)
    if ($(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").data("clicked")) {

    }
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").click(function() {
        if ($(this).data("clicked")) {
            // $(cardsArr[$(this).data("loop")]).removeClass("col-lg-12");
            // $(cardsArr[$(this).data("loop")]).addClass("col-lg-6");
            $(cardsArr[$(this).data("loop")]).children(".card").css({
                height: "12rem"
            })
            $(this).text("클릭하여 자세히보기");
            $(this).siblings(".simple-container").css({
                display: "block"
            });
            $(this).siblings(".detail-container").css({
                display: "none"
            })
            $(this).data("clicked", false);
        } else {
            // $(cardsArr[$(this).data("loop")]).removeClass("col-lg-6");
            // $(cardsArr[$(this).data("loop")]).addClass("col-lg-12");
            $(cardsArr[$(this).data("loop")]).children(".card").css({
                height: "15rem"
            })
            $(this).text("클릭하여 간략히보기");
            $(this).siblings(".simple-container").css({
                display: "none"
            });
            $(this).siblings(".detail-container").css({
                display: "block"
            })
            $(this).data("clicked", true);
        }

    })
}
