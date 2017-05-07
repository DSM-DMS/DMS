/***
 * background
 */
var $backgroundImage = $("#backgroundWallpaper");

/**
 * Panel
 */
var $panel = $("#panel");

/**
 * Common window
 */
var $windowClose = $(".window-close");

/**
 * Common modal
 */
var $modalButton = $(".modal-button");
var $closeModal = $(".btn-close");

/**
 * Menu
 */
var $menu = $("#menu");
var $menu2 = $("#menu2");
var $page1 = $("#page1");
var $page2 = $("#page2");
var $menuPagenation = $("#menu-pagenation");

/**
 * Extension
 */
var $openExtensionButton = $("#open-extension-apply");
var $closeExtensionButton = $("#close-extension-window");
var $extensionWindow = $("#extension-apply-window");
var $gaon = $("#extension-gaon");
var $naon = $("#extension-naon");
var $daon = $("#extension-daon");
var $laon = $("#extension-laon");
var $three = $("#extension-three");
var $four = $("#extension-four");
var $five = $("#extension-five");
var selectedClass = $("#extension-gaon");
var $classSelect = $(".extension-class-select");

/**
 * Going out
 */
var $openGoingOutButton = $(".goingOut-btn");
var $goingOutWindow = $("#going-out-apply-window");
var $closeGoingOutButton = $("#close-going-out-window");
var $goingOutApplyButton = $("#going-out-apply-btn");
var $goingOutPaperplane = $("#going-out-apply-btn i");
var $saturdayContainer = $(".saturday-container");
var $sundayContainer = $(".sunday-container");

/**
 * My page
 */
var $mypageWindow = $(".mypage-window");
var $closeMypageWindow = $("#close-mypage-window");

/**
 * Stay
 */
var $openStayButton = $("#open-stay-apply")
var $stayWindow = $(".stay-window");
var $stayApplyButton = $("#stay-apply-btn");
var $stayPaperplane = $("#stay-apply-btn i");
var $closeStayButton = $("#close-stay-window");

/**
 * Meal
 */
var mealDate = new Date();
var $prevMenuBtn = $("#previous-menu");
var $nextMenuBtn = $("#next-menu");

/**
 * Domitory rule
 */
var $dormRule = $(".dorm-rule");

/**
 * bug
 */
var $bugBtn = $(".bug-btn");

/**
 * Login
 */
var $openLoginButton = $(".login-btn");
var $loginSendBtn = $(".login-button");

/**
 * Point
 */
var $openPointButton = $(".point-btn");

/**
 * Notice
 */
var $noticeMoreBtn = $(".notice-more");
var $noticeListWindow = $(".notice-window");

/**
 * Current state(stay)
 */
var $stayCurrentState = $('#Layer_1');

/**
 * Current state(extension)
 */
var $extensionCurrentState = $('#Layer_2');

/** ======================================================================================
 * Common window
========================================================================================== */
// $windowClose.on("click", function() {
//     $(this).parents(".window").toggleClass("fade-in");
//     $panel.toggleClass("left-move");
//     $menu.toggleClass("fade-out");
// });

/** ======================================================================================
 * Common modal
========================================================================================== */
$closeModal.on("click", function() {
    $(this).parents().parents().parents().parents(".modal-wrapper").toggleClass('open');
    $panel.toggleClass('blur');
    $menu.toggleClass('blur');
    return false;
});

/** ======================================================================================
 * Extension
========================================================================================== */
selectedClass.css({
    transition: "0.2s ease-in",
    backgroundColor: "rgba(255, 255, 255, .2)"
});

function getClassData(classId) {
    $.ajax({
        url: "http://dsm2015.cafe24.com/apply/extension/class",
        type: "GET",
        data: {
            "option": "map",
            "class": classId
        },
        success: function(data) {
            drawSeats(JSON.parse(data).map, classId);
        }
    });
}

getClassData(1);

$classSelect.on("click", "td", function(e) {
    selectedClass.css({
        transition: "0.2s ease-in",
        backgroundColor: "rgba(0, 0, 0, 0)"
    });
    selectedClass = $(this);
    $(this).css({
        transition: "0.2s ease-in",
        backgroundColor: "rgba(255, 255, 255, .2)"
    });
    if ($(this).attr('id') === "extension-gaon") {
        getClassData(1);
    } else if ($(this).attr('id') === "extension-naon") {
        getClassData(2);
    } else if ($(this).attr('id') === "extension-daon") {
        getClassData(3);
    } else if ($(this).attr('id') === "extension-laon") {
        getClassData(4);
    } else if ($(this).attr('id') === "extension-three") {
        getClassData(5);
    } else if ($(this).attr('id') === "extension-four") {
        getClassData(6);
    } else if ($(this).attr('id') === "extension-five") {
        getClassData(7);
    }
});

$openExtensionButton.on("click", function() {
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$closeExtensionButton.on("click", function() {
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

/** ======================================================================================
 * Notice
========================================================================================== */
$noticeMoreBtn.on("click", function() {
    $noticeListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});


/** ======================================================================================
 * Dormitory rule
========================================================================================== */
$dormRule.on("click", function() {
    $panel.toggleClass("left-move");
    $mypageWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

/** ======================================================================================
 * My page
========================================================================================== */
$closeMypageWindow.on("click", function() {
    $mypageWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

/** ======================================================================================
 * Stay
========================================================================================== */
$openStayButton.click(function() {
    $stayWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$closeStayButton.on("click", function() {
    $panel.toggleClass("left-move");
    $stayWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$saturdayContainer.click(function() {
    $saturdayContainer.toggleClass("select");
});

$sundayContainer.click(function() {
    $sundayContainer.toggleClass("select");
});

$stayApplyButton.on("click", function() {
    $stayPaperplane.addClass("send-paperplane");
});

function stayDoCheck() {
    TweenLite.set([stayCross1, stayCross2], {
        autoAlpha: 0
    });
    TweenLite.set(stayTick, {
        drawSVG: "0%"
    });
    TweenLite.set(stayCircle, {
        drawSVG: "50% 50%",
        scale: ".01",
        transformOrigin: "50% 50%",
        fill: "#607D8B",
        autoAlpha: 0
    });

    tl1 = new TimelineMax({
        repeat: 0,
        repeatDelay: 1
    });
    tl1
        .to(stayCircle, 1, {
            scale: 1,
            ease: Elastic.easeOut
        })
        .to([stayTick, stayCircle], .6, {
            autoAlpha: 1
        }, .1)
        .to(stayCircle, .8, {
            drawSVG: "100% 0%",
            ease: Power4.easeOut
        }, .2)
        .to(stayTick, .8, {
            drawSVG: "0% 100%",
            ease: Expo.easeOut
        }, '-=.6')
        .to(stayCircle, .6, {
            fill: "#607D8B",
            ease: Power1.easeInOut
        }, '-=.4');

    tl1.timeScale(.8);

}

/** ======================================================================================
 * Login
========================================================================================== */
$openLoginButton.on("click", function() {
    $('.login-modal-wrapper').toggleClass('open');
    $panel.toggleClass('blur');
    $menu.toggleClass('blur');
    return false;
});

$loginSendBtn.on("click", function() {
    $.ajax({
        url: "/account/login/student",
        type: "POST",
        data: {
            id: $(".login-input #name").val(),
            password: $(".login-input #pass").val(),
            remember: $(".login-check input:checked").val(),
            "g-recaptcha-response": grecaptcha.getResponse()
        },
        success: function(data, status) {
            location.reload();
        },
        error: function(xhr) {
            alert("로그인에 실패했습니다.");
        },
    });
});

/** ======================================================================================
 * Bug modal
========================================================================================== */
$bugBtn.on("click", function() {
    $('.bug-modal-wrapper').toggleClass('open');
    $panel.toggleClass('blur');
    $menu.toggleClass('blur');
    return false;
});

/** ======================================================================================
 * Point
========================================================================================== */
$openPointButton.on("click", function() {

});

/** ======================================================================================
 * Going out
========================================================================================== */
$openGoingOutButton.on("click", function() {
    $panel.toggleClass("left-move");
    $goingOutWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
    return false;
});

$closeGoingOutButton.on("click", function() {
    $panel.toggleClass("left-move");
    $goingOutWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

// TODO : 신청완료 되면 클래스 초기화해주기
$goingOutApplyButton.on('click', function() {
    $goingOutPaperplane.addClass("send-paperplane");
    var satVal = false;
    var sunVal = false;

    if ($saturdayContainer.hasClass("select")) {
        satVal = true;
    }
    if ($sundayContainer.hasClass("select")) {
        sunVal = true;
    }

    console.log(satVal, sunVal);

    $.ajax({
        url: "/apply/goingout",
        type: "PUT",
        data: {
            "sat": satVal,
            "sun": sunVal
        },
        success: function() {
            alert('신청되었습니다.');
        }
    });
});

/** ======================================================================================
 * Current state (stay)
========================================================================================== */
$stayCurrentState.click(function() {
    stayDoCheck();
});

/** ======================================================================================
 * Current state (extension)
========================================================================================== */
$extensionCurrentState.click(function() {
    extensionDoCheck();
});

var mapData = [
    [1, 2, 0, 3, 4],
    [5, 6, 0, 7, 8],
    [9, 10, 0, 11, 12],
    [13, 14, 0, 15, 16],
    [17, 18, 0, 19, 20]
];

var mapData2 = [
    [1, 2, 3, 0, 4, 5, 6],
    [7, 8, 9, 0, 10, 11, 12],
    [13, 14, 15, 0, 16, 17, 18]
];

function drawSeats(mapData, classId) {
    var newTable = $('<table/>', {
        "id": "extension-seat-table"
    });

    for (var loop = 0; loop < mapData.length; loop++) {
        var newTr = $('<tr/>', {
            "class": "extension-seat-table-tr"
        });

        for (var innerLoop = 0; innerLoop < mapData[loop].length; innerLoop++) {
            var newTd = $('<td/>');
            var newSeat = $('<div/>', {
                text: mapData[loop][innerLoop],
                "class": "extension-seat",
            });
            if (mapData[loop][innerLoop] === 0) {
                // newTd.css({
                //     opacity: "0"
                // })
                newSeat.addClass("none-selectalbe-seat");
            }

            if (typeof mapData[loop][innerLoop] === "number" && mapData[loop][innerLoop] !== 0) {
                (function(id) {
                    newSeat.on("click", function() {
                        extentionApply(classId, id);
                    })
                })(mapData[loop][innerLoop]);
                newSeat.addClass("selectalbe-seat");
            }

            newSeat.appendTo(newTd);
            newTd.appendTo(newTr);
        }
        newTr.appendTo(newTable);
    }
    $(".extension-seat-table-container").html(newTable);
}

function extentionApply(classId, id) {
    $.ajax({
        url: "http://dsm2015.cafe24.com/apply/extension",
        type: "PUT",
        data: {
            "class": classId,
            "seat": id
        },
        statusCode: {
            204: function() {
                alert("신청가능한 시간이 아닙니다.");

            },
            500: function() {
                alert("신청중에 오류가 발생하였습니다.");
            }
        },
        success: function(data, xhr) {
            getSeatData(classId);
        },
        error: function(request, status, error) {
            console.log(status);
        }
    });
}

function extensionDoCheck() {
    TweenLite.set([extensionCross1, extensionCross2], {
        autoAlpha: 0
    });
    TweenLite.set(extensionTick, {
        drawSVG: "0%"
    });
    TweenLite.set(extensionCircle, {
        drawSVG: "50% 50%",
        scale: ".01",
        transformOrigin: "50% 50%",
        fill: "#607D8B",
        autoAlpha: 0
    });

    tl1 = new TimelineMax({
        repeat: 0,
        repeatDelay: 1
    });
    tl1
        .to(extensionCircle, 1, {
            scale: 1,
            ease: Elastic.easeOut
        })
        .to([extensionTick, extensionCircle], .6, {
            autoAlpha: 1
        }, .1)
        .to(extensionCircle, .8, {
            drawSVG: "100% 0%",
            ease: Power4.easeOut
        }, .2)
        .to(extensionTick, .8, {
            drawSVG: "0% 100%",
            ease: Expo.easeOut
        }, '-=.6')
        .to(extensionCircle, .6, {
            fill: "#607D8B",
            ease: Power1.easeInOut
        }, '-=.4');

    tl1.timeScale(.8);
}

/** ======================================================================================
 * menu
========================================================================================== */
$page1.click(function() {
    if ($page1.hasClass("current-index")) {} else {
        $page2.removeClass("current-index");
        $page1.addClass("current-index");

        $menu2.removeClass("show-page");
        $menu2.addClass("hide-page");

        $menu.removeClass("hide-page");
        $menu.addClass("show-page");
    }
});

$page2.click(function() {
    if ($page2.hasClass("current-index")) {} else {
        $page1.removeClass("current-index");
        $page2.addClass("current-index");

        $menu.removeClass("show-page");
        $menu.addClass("hide-page");

        $menu2.removeClass("hide-page");
        $menu2.addClass("show-page");
    }
});

/** ======================================================================================
 * modal
========================================================================================== */
$modalButton.click(function(e) {
    var pX = e.pageX,
        pY = e.pageY,
        oX = parseInt($(this).offset().left),
        oY = parseInt($(this).offset().top);

    $(this).append('<span class="click-efect x-' + oX + ' y-' + oY + '" style="margin-left:' + (pX - oX) + 'px;margin-top:' + (pY - oY) + 'px;"></span>')
    $('.x-' + oX + '.y-' + oY + '').animate({
        "width": "500px",
        "height": "500px",
        "top": "-250px",
        "left": "-250px",
    }, 200);
    $('.x-' + oX + '.y-' + oY + '').animate({
        "width": "0",
        "height": "0",
        "top": "-0",
        "left": "-0",
    }, 600, function() {
        $(".click-efect").remove();
        console.log("remove")
    });
    $("button", this).addClass('active');
});

$(function() {
    $(".login-input input").focus(function() {
        $(this).parent(".login-input").each(function() {
            $("label", this).css({
                "line-height": "18px",
                "font-size": "18px",
                "font-weight": "100",
                "top": "0px"
            })
            $(".spin", this).css({
                "width": "100%"
            })
        });
    }).blur(function() {
        $(".spin").css({
            "width": "0px"
        })
        if ($(this).val() == "") {
            $(this).parent(".login-input").each(function() {
                $("label", this).css({
                    "line-height": "60px",
                    "font-size": "24px",
                    "font-weight": "300",
                    "top": "10px"
                })
            });

        }
    });

    $(".bug-content textarea").focus(function() {
        $(this).parent(".bug-content").each(function() {
            $("label", this).css({
                "line-height": "18px",
                "font-size": "18px",
                "font-weight": "100",
                "top": "0px"
            })
            $(".spin", this).css({
                "width": "100%"
            })
        });
    }).blur(function() {
        $(".spin").css({
            "width": "0px"
        })
        if ($(this).val() == "") {
            $(this).parent(".login-input").each(function() {
                $("label", this).css({
                    "line-height": "60px",
                    "font-size": "24px",
                    "font-weight": "300",
                    "top": "10px"
                })
            });

        }
    });
});
/** ======================================================================================
 * meal
========================================================================================== */
$prevMenuBtn.on("click", function() {
    prevDay();
    setDay();
});

$nextMenuBtn.on("click", function() {
    nextDay();
    setDay();
});

function nextDay() {
    mealDate.setDate(mealDate.getDate() + 1);
    getMeal();
}

function prevDay() {
    mealDate.setDate(mealDate.getDate() - 1);
    getMeal();
}

function formatDate() {
    return mealDate.toISOString().slice(0, 10);
}

function formatDate2() {
    var days = ["일", "월", "화", "수", "목", "금", "토"];
    var y = mealDate.getFullYear();
    var m = mealDate.getMonth() + 1;
    var d = mealDate.getDate();
    var day = days[mealDate.getDay()];

    return y + "." + m + "." + d + " " + day + "요일";
}

function setDay() {
    $(".meal-date").text(formatDate2());
    getMeal();
}

function getMeal() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/meal",
        data: {
            date: formatDate()
        },
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data);
                var domArr = $(".meal-content p");
                $(domArr[0]).text(JSON.parse(parsedData.breakfast).toString());
                $(domArr[1]).text(JSON.parse(parsedData.lunch).toString());
                $(domArr[2]).text(JSON.parse(parsedData.dinner).toString());
            },
            error: function() {
                var domArr = $(".meal-content p");
                $(domArr[0]).text("급식이 없습니다.");
                $(domArr[1]).text("급식이 없습니다.");
                $(domArr[2]).text("급식이 없습니다.");
            }
        }
    })
}

//Sets the document when it is loaded
$(document).ready(function() {
    //set random background image
    $backgroundImage.attr("src", ".\\images\\wallpaper" + (Math.floor(Math.random() * 9) + 1) + ".jpg");

    //show current stay state and extension state
    stayTick = $('#stayTick');
    stayCircle = $('#stayCheckCircle');
    stayCross1 = $('#stayCross1');
    stayCross2 = $('#stayCross2');
    stayDoCheck();

    extensionTick = $('#extensionTick');
    extensionCircle = $('#extensionCheckcircle');
    extensionCross1 = $('#extensionCross1');
    extensionCross2 = $('#extensionCross2');
    extensionDoCheck();


    //saturday, sunday svg animations
    var ids = ["#letter-s", "#letter-a", "#letter-t", "#letter-t2", "#letter-s2", "#letter-u", "#letter-n"];
    var path = $("path");

    $saturdayContainer.hover(function() {
            path[0].style.strokeDasharray = path[0].getTotalLength();
            path[0].style.strokeDashoffset = path[0].getTotalLength();
            $(ids[0]).animate({
                strokeDashoffset: '0'
            }, 600);

            path[1].style.strokeDasharray = path[1].getTotalLength();
            path[1].style.strokeDashoffset = path[1].getTotalLength();
            $(ids[1]).animate({
                strokeDashoffset: '0'
            }, 600);

            path[2].style.strokeDasharray = path[2].getTotalLength();
            path[2].style.strokeDashoffset = path[2].getTotalLength();
            $(ids[2]).animate({
                strokeDashoffset: '0'
            }, 600);

            path[3].style.strokeDasharray = path[3].getTotalLength();
            path[3].style.strokeDashoffset = path[3].getTotalLength();
            $(ids[3]).animate({
                strokeDashoffset: '0'
            }, 600);
        },
        function() {

        });

    $sundayContainer.hover(function() {
            path[4].style.strokeDasharray = path[4].getTotalLength();
            path[4].style.strokeDashoffset = path[4].getTotalLength();
            $(ids[4]).animate({
                strokeDashoffset: '0'
            }, 600);

            path[5].style.strokeDasharray = path[5].getTotalLength();
            path[5].style.strokeDashoffset = path[5].getTotalLength();
            $(ids[5]).animate({
                strokeDashoffset: '0'
            }, 600);

            path[6].style.strokeDasharray = path[6].getTotalLength();
            path[6].style.strokeDashoffset = path[6].getTotalLength();
            $(ids[6]).animate({
                strokeDashoffset: '0'
            }, 600);
        },
        function() {

        });

    //setting for show meal
    setDay();
});
