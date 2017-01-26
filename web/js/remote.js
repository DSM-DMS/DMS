//신청탭 over 이벤트
$(".remote .category").children("a").eq(0).click(function() {
    //신청 text를 white로
    $(".remote .category").children("a").eq(0).animate({
        fontSize: "1.5em",
        color: "white",
    })

    $(".remote .category").children("a").eq(0).children("p").animate({
        padding: "0"
    })

    //remote background-color를 신청색으로
    $(".remote").animate({
        backgroundColor: "rgb(52, 152, 219)",
    });

    //DMS hide
    $(".remote h1").slideUp();

    //신청탭 하위메뉴 show
    $(".remote .category .application").show();

    //기숙사, 마이페이지탭 hide
    $(".remote .category").children("a").eq(1).hide();
    $(".remote .category").children("a").eq(2).hide();

})

//기숙사탭 over 이벤트
$(".remote .category").children("a").eq(1).click(function() {
    //기숙사 text를 white로
    $(".remote .category").children("a").eq(1).animate({
        fontSize: "1.5em",
        color: "white"
    })

    $(".remote .category").children("a").eq(1).children("p").animate({
        padding: "0"
    })

    //remote background-color를 기숙사색으로
    $(".remote").animate({
        backgroundColor: "rgb(142, 68, 173)"
    }, 500);

    //DMS hide
    $(".remote h1").slideUp();

    //기숙사탭 하위메뉴 show
    $(".remote .category .dom").show();

    //공지사항, 마이페이지탭 hide
    $(".remote .category").children("a").eq(0).hide();
    $(".remote .category").children("a").eq(2).hide();
})

$(".remote .category").children("a").eq(2).click(function() {
    //remote background-color를 마이페이지색으로
    $(".remote").animate({
        backgroundColor: "rgb(192, 57, 43)"
    });

    //마이페이지 text를 white로
    $(".remote .category").children("a").eq(2).animate({
        fontSize: "1em",
        color: "white"
    })

    $(".remote .category").children("a").eq(2).children("p").animate({
        padding: "0"
    })

    //DMS hide
    $(".remote h1").slideUp();

    //공지사항, 기숙사 hide
    $(".remote .category").children("a").eq(0).hide();
    $(".remote .category").children("a").eq(1).hide();
})

//리모트 leave 이벤트
$(".remote").mouseleave(function() {
    //모든 탭 글자색을 윈대래로

    $(".remote .category").children("a").eq(0).animate({
        color: "rgb(52, 152, 219)",
        fontSize: "1em"
    })

    $(".remote .category").children("a").eq(1).animate({
        color: "rgb(142, 68, 173)",
        fontSize: "1em"
    })

    $(".remote .category").children("a").eq(2).animate({
        color: "color: rgb(192, 57, 43)",
        fontSize: "1em"
    })

    $(".remote .category").children("a").eq(0).children("p").animate({
        padding: "13% 0%"
    })

    $(".remote .category").children("a").eq(1).children("p").animate({
        padding: "13% 0%"
    })

    $(".remote .category").children("a").eq(2).children("p").animate({
        padding: "13% 0%"
    })

    //remote backgroundColor를 원래대로
    $(".remote").animate({
        backgroundColor: "white"
    });

    // DMS복구
    $(".remote h1").slideDown();

    //모든 탭 show
    $(".remote .category").children("a").eq(0).show();
    $(".remote .category").children("a").eq(1).show();
    $(".remote .category").children("a").eq(2).show();

    //탭 하부메뉴 hide
    $(".remote .category .application").hide(0);
    $(".remote .category .dom").hide(0);
})

$(".remote .category .children a").click(function(e) {
    e.preventDefault(); // prevent default anchor behavior
    var goTo = this.getAttribute("href"); // store anchor href

    // do something while timeOut ticks ...
    $('<div/>', {
        "class": "bg",
        css: {
            // "z-index": "100",
            "position": "absolute",
            "top": $(this).offset().top - 1500 + 30 + "px",
            "left": $(this).offset().left - 1500 + 30 + "px",
            "background":  $(".remote").css("background-color"),
            "width": "3000px",
            "height": "3000px",
            "border-radius": "50%",
            "border": "2px solid rgb(52, 152, 219)"
        }
    }).appendTo("body");

    $('<div/>', {
        "class": "bg",
        css: {
            // "z-index": "100",
            "position": "relative",
            "top": $(this).offset().top - $(".remote").offset().top - 1500 + "px",
            "left": - 1500 + "px",
            "background":  "white",
            "width": "3000px",
            "height": "3000px",
            "border-radius": "50%",
            "border": "2px solid white"
        }
    }).appendTo(".remote");
    console.log($(this).offset().top - $(".remote").offset().top);
    console.log($(this).offset().top);
    console.log($(".remote").offset().top);





    setTimeout(function() {
        window.location = goTo;
    }, 1500);


})
