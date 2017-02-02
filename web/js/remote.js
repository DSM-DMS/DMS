//신청탭 over 이벤트
$(".remote .category").children("a").eq(0).click(function() {
    // 신청 text를 white로
    $(".remote .category").children("a").eq(0).animate({
        fontSize: "1.5em",
        color: "white"
    })

    $(".remote .category").children("a").eq(0).css({})

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


//모바일 기기 판단
var isMobile = false; //initiate as false
// device detection
if (/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|ipad|iris|kindle|Android|Silk|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(navigator.userAgent) ||
    /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0, 4))) isMobile = true;


if (!isMobile) {
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

        if ($(window).width() > 992) {
            $(".remote .category").children("a").eq(0).children("p").animate({
                padding: "13% 0%"
            })

            $(".remote .category").children("a").eq(1).children("p").animate({
                padding: "13% 0%"
            })

            $(".remote .category").children("a").eq(2).children("p").animate({
                padding: "13% 0%"
            })
        }


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
} else if (isMobile) {
    //리모트 leave 이벤트
    $(".remote").mouseup(function() {
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

        if ($(window).width() > 992) {
            $(".remote .category").children("a").eq(0).children("p").animate({
                padding: "13% 0%"
            })

            $(".remote .category").children("a").eq(1).children("p").animate({
                padding: "13% 0%"
            })

            $(".remote .category").children("a").eq(2).children("p").animate({
                padding: "13% 0%"
            })
        }


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


}

$(window).scroll(function() {
    var vh = $(window).height()/100;
    var vw = $(window).width()/100;

    console.log("scroll " + $(window).scrollTop() + ", "+ 17*vh);
    if ($(window).scrollTop() > 17*vh) {
        $(".remote").css({
            "background-color": $("body").css("background-color"),
            "border-radius": "0",
            "top": "0",
            "width": "100vw",
            "left": "0"
        });
    } else if ($(window).scrollTop() <= 6*vh) {
      $(".remote").css({
          "background-color": "white",
          "border-radius": "5px",
          "top": "1%",
          "left": "5%",
          "width": "90%"
      });
    }
})


$(".remote .category .children a").click(function(e) {
    e.preventDefault(); // prevent default anchor behavior
    var goTo = this.getAttribute("href"); // store anchor href
    var rollBackColor = $(".remote").css("background-color");

    //백그라운드 애니메이션을 위한 DOM을 생성함
    $('<div/>', {
        "class": "bg",
        css: {
            // "z-index": "100",
            "position": "absolute",
            "top": $(this).offset().top - 1500 + 30 + "px",
            "left": $(this).offset().left - 1500 + 30 + "px",
            "background": $(".remote").css("background-color"),
            "width": "3000px",
            "height": "3000px",
            "border-radius": "50%",
            "border": "2px solid rgb(52, 152, 219)"
        }
    }).appendTo("body");


    //리모컨 백그라운드 애니메이션을 위한 DOM을 생성함
    $('<div/>', {
        "class": "bg",
        css: {
            // "z-index": "100",
            "position": "relative",
            "top": $(this).offset().top - $(".remote").offset().top - 1500 + "px",
            "left": -1500 + "px",
            "background": "white",
            "width": "3000px",
            "height": "3000px",
            "border-radius": "50%",
            "border": "2px solid white"
        }
    }).appendTo(".remote");

    setTimeout(function() {
        //window.location = goTo;
        console.log($(".remote").css("background-color"));
        $("body").css({
            'background-color': rollBackColor
        });
        //백그라운드 애니메이션을 위한 DOM을 삭제함
        console.log("asdfasdfasdf");
        var bgArr = $(".bg");
        for (var i = 0; i < bgArr.length; i++) {
            $(bgArr[i]).remove();
        }
    }, 1500);
})
