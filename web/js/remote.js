//신청탭 over 이벤트
$(".remote .category").children("a").eq(0).mouseover(function(){
    //신청 text를 white로
    $(".remote .category").children("a").eq(0).animate({
        fontSize: "1.5em",
        color: "white"
    })

    //remote background-color를 신청색으로
    $(".remote").animate({
        backgroundColor: "rgb(52, 152, 219)",
        backgroundImage: "linear-gradient( to right top, transparent 33%, gray 33%, gray 66%, transparent 66%)"

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
$(".remote .category").children("a").eq(1).mouseover(function(){
    //기숙사 text를 white로
    $(".remote .category").children("a").eq(1).animate({
        fontSize: "1.5em",
        color: "white"
    })

    //remote background-color를 신청색으로
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

$(".remote .category").children("a").eq(2).mouseover(function(){
    //remote background-color를 마이페이지색으로
    $(".remote").animate({
        backgroundColor: "rgb(192, 57, 43)"
    });

    //마이페이지 text를 white로
    $(".remote .category").children("a").eq(2).animate({
        fontSize: "1em",
        color: "white"
    })

    //DMS hide
    $(".remote h1").slideUp();

    //공지사항, 기숙사 hide
    $(".remote .category").children("a").eq(0).hide();
    $(".remote .category").children("a").eq(1).hide();
})

//리모트 leave 이벤트
$(".remote").mouseleave(function(){
    //모든 탭 글자색을 윈대래로
    $(".remote .category").children("a").eq(0).animate({
        color: "rgb(52, 152, 219)",
        fontSize: "1em"
    })

    $(".remote .category").children("a").eq(1).animate({
        color: "rgb(142, 68, 173)",
        fontSize: "1em"

    },500)

    $(".remote .category").children("a").eq(2).animate({
        color: "color: rgb(192, 57, 43)",
        fontSize: "1em"
    })

    //remote backgroundColor를 원래대로
    $(".remote").animate({
        backgroundColor: "white"
    });

    // DMS복구
    $(".remote h1").slideDown();

    //모든 탭 show
    $(".remote .category").children("a").eq(0).show()
    $(".remote .category").children("a").eq(1).show()
    $(".remote .category").children("a").eq(2).show();

    //탭 하부메뉴 hide
    $(".remote .category .application").hide(0);
    $(".remote .category .dom").hide(0);
})
