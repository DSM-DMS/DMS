// $(".mypage-afterschool .moreinfo-span").click(function() {
//     console.log("aa")
//     // $("mypage-afterschool").attr('class', 'class_name');
//     $(".mypage-afterschool").removeClass("col-lg-6");
//     $(".mypage-afterschool").addClass("col-lg-12");
// })

var cardsArr = $(".mypage-component-container");

for (var loop = 2; loop < cardsArr.length; loop++) {
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").data("loop", loop);
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").click(function() {
        if ($(this).data("clicked")) {
            $(this).text("클릭하여 자세히보기");
            // $(this).siblings(".simple-container").css({
            //    display: "block"
            // });
            // $(this).siblings(".detail-container").css({
            //    display: "none"
            // });

            $(this).siblings(".simple-container").show(500);
            $(this).siblings(".detail-container").hide(500);

            var cardHeight = $(cardsArr[$(this).data("loop")]).children(".card").outerHeight();

            // $(cardsArr[$(this).data("loop")]).removeClass("col-lg-12");
            // $(cardsArr[$(this).data("loop")]).addClass("col-lg-6");
            $(cardsArr[$(this).data("loop")]).children(".card").css({
                // "height": cardHeight
            })
            $(this).data("clicked", false);
        } else {
            $(this).text("클릭하여 간략히보기");
            // $(this).siblings(".simple-container").css({
            //     display: "none"
            // });
            // $(this).siblings(".detail-container").css({
            //     display: "block"
            // });

            $(this).siblings(".simple-container").hide(500);
            $(this).siblings(".detail-container").show(500);
            // $(cardsArr[$(this).data("loop")]).removeClass("col-lg-6");
            // $(cardsArr[$(this).data("loop")]).addClass("col-lg-12");
            var cardHeight = $(cardsArr[$(this).data("loop")]).children(".card").outerHeight();
            $(cardsArr[$(this).data("loop")]).children(".card").css({
                // height: "15rem"
                // height: "20rem"
                // "height": cardHeight
            })

            $(this).data("clicked", true);
        }

    })
}
