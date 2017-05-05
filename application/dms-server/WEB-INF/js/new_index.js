var $foldingButton = $("#extension-apply");
var $closeButton = $("#close-extension-window");
var $panel = $("#panel");
var $extensionWindow = $("#extension-apply-window");
var $loginBtn = $(".login-btn");
var $menu = $("#menu");
var $dormRule = $(".dorm-rule");
var $mypageWindow = $(".mypage-window");
var $closeMypageWindow = $("#close-mypage-window");
var $goingOutWindow = $("#going-out-apply-window");
var $closeGoingOutWindow = $("#close-going-out-window");
var $windows = $(".window");
var $goingOutBtn = $(".goingOut-btn");
var $pointBtn = $(".point-btn");
var $saturdayContainer = $(".saturday-container");
var $sundayContainer = $(".sunday-container");

var $closeModal = $(".btn-close");
var $prevMenuBtn = $("#previous-menu");
var $nextMenuBtn = $("#next-menu");
var mealDate = new Date();

$closeModal.on("click", function() {
    $(this).parents().parents().parents().parents(".modal-wrapper").toggleClass('open');
    $panel.toggleClass('blur');
    $menu.toggleClass('blur');
    return false;
});

$foldingButton.on("click", function() {
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
});

$closeButton.on("click", function() {
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
});

$dormRule.on("click", function() {
    $panel.toggleClass("left-move");
    $mypageWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
});

$closeMypageWindow.on("click", function() {
    $mypageWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
});

var speechBubble = $('#speech-bubble');

$("#stay-apply").click(function() {
    speechBubble.toggleClass('fade-in');
    speechBubble.focus();
});

$('#speech-bubble button').click(function() {
    speechBubble.toggleClass('fade-in');
});

$loginBtn.on("click", function() {
    $('.login-modal-wrapper').toggleClass('open');
    $panel.toggleClass('blur');
    $menu.toggleClass('blur');
    return false;
});

$pointBtn.on("click", function() {
    $('.bug-modal-wrapper').toggleClass('open');
    $panel.toggleClass('blur');
    $menu.toggleClass('blur');
    return false;
});

$goingOutBtn.on("click", function() {
    $panel.toggleClass("left-move");
    $goingOutWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    return false;
});

$closeGoingOutWindow.on("click", function() {
    $goingOutWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
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

function drawSeats(mapData) {
    var newTable = $('<table/>', {
        "id": "extension-seat-table"
    });

    for (var loop = 0; loop < mapData.length; loop++) {
        var newTr = $('<tr/>', {
            "class": "extension-seat-table-tr"
        });

        for (var innerLoop = 0; innerLoop < mapData[loop].length; innerLoop++) {
            var tdOpacity = 1;
            if (mapData[loop][innerLoop] === 0) {
                tdOpacity = 0;
            }
            var newTd = $('<td/>', {
                css: {
                    opacity: tdOpacity
                }
            });
            var newSeat = $('<div/>', {
                "class": "extension-seat",
            })
            newSeat.appendTo(newTd);
            newTd.appendTo(newTr);
        }
        newTr.appendTo(newTable);
    }
    $(".extension-seat-table-container").html(newTable);
    console.log($("#extension-seat-table").width());
    // $(".extension-board").css({
    //     width: $("#extension-seat-table").width()
    // });
}

drawSeats(mapData);

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

$(document).ready(function() {
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

    $('#Layer_1').click(function() {
        stayDoCheck();
    });
    $('#Layer_2').click(function() {
        extensionDoCheck();
    });

    var path = document.getElementsByTagName('path');

    var ids = ["#letter-a", "#letter-s", "#letter-t", "#letter-t2", "#letter-n", "#letter-u", "#letter-s2"];

    //test -> 위치 변경필요
    $saturdayContainer.hover(function() {
            path[0].style.strokeDasharray = path[0].getTotalLength();
            path[0].style.strokeDashoffset = path[0].getTotalLength();
            $(ids[0]).animate({ strokeDashoffset: '0' }, 600);

            path[1].style.strokeDasharray = path[1].getTotalLength();
            path[1].style.strokeDashoffset = path[1].getTotalLength();
            $(ids[1]).animate({ strokeDashoffset: '0' }, 600);

            path[2].style.strokeDasharray = path[2].getTotalLength();
            path[2].style.strokeDashoffset = path[2].getTotalLength();
            $(ids[2]).animate({ strokeDashoffset: '0' }, 600);

            path[3].style.strokeDasharray = path[3].getTotalLength();
            path[3].style.strokeDashoffset = path[3].getTotalLength();
            $(ids[3]).animate({ strokeDashoffset: '0' }, 600);
        },
        function() {

        });

    $sundayContainer.hover(function() {
            path[4].style.strokeDasharray = path[4].getTotalLength();
            path[4].style.strokeDashoffset = path[4].getTotalLength();
            $(ids[4]).animate({ strokeDashoffset: '0' }, 600);

            path[5].style.strokeDasharray = path[5].getTotalLength();
            path[5].style.strokeDashoffset = path[5].getTotalLength();
            $(ids[6]).animate({ strokeDashoffset: '0' }, 600);

            path[6].style.strokeDasharray = path[6].getTotalLength();
            path[6].style.strokeDashoffset = path[6].getTotalLength();
            $(ids[5]).animate({ strokeDashoffset: '0' }, 600);
        },
        function() {

        });

    $saturdayContainer.click(function() {
        $saturdayContainer.toggleClass("select");
    });

    $sundayContainer.click(function() {
        $sundayContainer.toggleClass("select");
    });
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

    $(".modal-button").click(function(e) {
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
        }, 600);
        $('.x-' + oX + '.y-' + oY + '').animate({
            "width": "0",
            "height": "0",
            "top": "-0",
            "left": "-0",
        }, 1200, function() {
            $(".click-efect").remove();
            console.log("remove")
        });
        $("button", this).addClass('active');
    });
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

setDay();
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

$prevMenuBtn.on("click", function() {
    prevDay();
    setDay();
});

$nextMenuBtn.on("click", function() {
    nextDay();
    setDay();
});
