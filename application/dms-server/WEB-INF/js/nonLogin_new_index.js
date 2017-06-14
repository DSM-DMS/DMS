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
var $openMyPageButton = $(".mypage-btn");
var $mypageWindow = $(".mypage-window");
var $closeMypageWindow = $("#close-mypage-window");
var $passwordChangeBtn = $(".edit-password-container");
var $passwordChangeReq = $(".password-change button");

/**
 * Stay
 */
var $openStayButton = $("#open-stay-apply")
var $stayWindow = $(".stay-window");
var $stayApplyButton = $("#stay-apply-btn");
var $stayPaperplane = $("#stay-apply-btn i");
var $closeStayButton = $("#close-stay-window");
var stayDate = new Date();
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
var $closeDormRuleButton = $("#close-rule-window");
var $dormListWindow = $(".rule-window");

/**
 * Domitory faq
 */
var $faqBtn = $(".faq-btn");
var $closeFaqButton = $("#faq-going-out-window");
var $faqListWindow = $(".faq-window");

/**
 * Facility
 */
var $facilityBtn = $(".facility-btn");
var $FacilityModal = $(".facility-modal-wrapper");

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
var $closeNoticeButton = $("#close-notice-window");
var $noticeListWindow = $(".notice-window");

/**
 * Current state(stay)
 */
var $stayCurrentState = $('#Layer_1');

/**
 * Current state(extension)
 */
var $extensionCurrentState = $('#Layer_2');

/**
 * Article preview
 */

var noticePreviewBtn = $(".notice-preview-btn");
var rulePreviewBtn = $(".rule-preview-btn");
var faqPreviewBtn = $(".faq-preview-btn");
var selectedCategory = "notice";


/**
 * register
 */
var registerBtn = $(".register-btn");

/**
 * remove html tag
 */
var protos = document.body.constructor === window.HTMLBodyElement;
validHTMLTags = /^(?:a|abbr|acronym|address|applet|area|article|aside|audio|b|base|basefont|bdi|bdo|bgsound|big|blink|blockquote|body|br|button|canvas|caption|center|cite|code|col|colgroup|data|datalist|dd|del|details|dfn|dir|div|dl|dt|em|embed|fieldset|figcaption|figure|font|footer|form|frame|frameset|h1|h2|h3|h4|h5|h6|head|header|hgroup|hr|html|i|iframe|img|input|ins|isindex|kbd|keygen|label|legend|li|link|listing|main|map|mark|marquee|menu|menuitem|meta|meter|nav|nobr|noframes|noscript|object|ol|optgroup|option|output|p|param|plaintext|pre|progress|q|rp|rt|ruby|s|samp|script|section|select|small|source|spacer|span|strike|strong|style|sub|summary|sup|table|tbody|td|textarea|tfoot|th|thead|time|title|tr|track|tt|u|ul|var|video|wbr|xmp)$/i;

function sanitize(txt) {
    var // This regex normalises anything between quotes
        normaliseQuotes = /=(["'])(?=[^\1]*[<>])[^\1]*\1/g,
        normaliseFn = function($0, q, sym) {
            return $0.replace(/</g, '&lt;').replace(/>/g, '&gt;');
        },
        replaceInvalid = function($0, tag, off, txt) {
            var
            // Is it a valid tag?
                invalidTag = protos &&
                document.createElement(tag) instanceof HTMLUnknownElement ||
                !validHTMLTags.test(tag),

                // Is the tag complete?
                isComplete = txt.slice(off + 1).search(/^[^<]+>/) > -1;

            return invalidTag || !isComplete ? '&lt;' + tag : $0;
        };

    txt = txt.replace(normaliseQuotes, normaliseFn)
        .replace(/<(\w+)/g, replaceInvalid);

    var tmp = document.createElement("DIV");
    tmp.innerHTML = txt;

    return "textContent" in tmp ? tmp.textContent : tmp.innerHTML;
}

/** ======================================================================================
 * browser size
========================================================================================== */


var width = screen.width;
  var fullHeight = window.innerHeight + window.screenTop;
var height = screen.height - (window.outerHeight -  window.innerHeight - window.screenTop || window.screenY);

var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);

if (window.innerWidth == width && window.innerHeight == fullHeight) {
    $("body").css({
        minWidth: width + "px",
        minHeight: fullHeight + "px",
        overflow: "hidden"
    });
} else {
    $("body").css({
        minWidth: width + "px",
        minHeight: height + "px",
        overflow: "auto"
    });
}

if (isMobile) {
    $("body").css({
        minWidth: "1707px",
        minHeight: "855px",
        overflow: "auto"
    });
} else {
    $(window).resize(function() {
        if (window.innerWidth == width && window.innerHeight == fullHeight) {
            $("body").css({
                minWidth: width + "px",
                minHeight: fullHeight + "px",
                overflow: "hidden"
            });
        } else {
            $("body").css({
                minWidth: width + "px",
                minHeight: height + "px",
                overflow: "auto"
            });
        }

    });
}


/** ======================================================================================
 * Common window
========================================================================================== */
// $windowClose.on("click", function() {
//     $(this).parents(".window").toggleClass("fade-in");
//     $panel.toggleClass("left-move");
//     $menu.toggleClass("fade-out");
//     $menuPagenation.toggleClass("fade-out");
// });

/** ======================================================================================
 * Common modal
========================================================================================== */
$closeModal.on("click", function() {
    $(this).parents().parents().parents().parents(".modal-wrapper").toggleClass('open');
    // $panel.toggleClass('blur');
    // $menu.toggleClass('blur');
    return false;
});

function showLoginModal() {
    $('.login-modal-wrapper').toggleClass('open');
    return false;
}

/** ======================================================================================
 * Extension
========================================================================================== */

$openExtensionButton.on("click", function() {
    showLoginModal();
});

/** ======================================================================================
 * Notice
========================================================================================== */
$noticeMoreBtn.on("click", function() {
    showLoginModal();
});

function getNoticeList() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/notice/list",
        type: "GET",
        success: function(data) {
            var parsedData = JSON.parse(data).result;
            parsedData.forEach(function(data) {
                fillListCard(data, $(".notice-window .list-box-container"));
            });
        },
        error: function() {
            console.log("error");
        }
    });
}
setNoticePreview();
getNoticeList();

function fillListCard(data, target) {
    var newCard = $('<div/>', {
        "class": "list-box",
    });
    newCard.append($('<p/>', {
        "class": "list-box-no",
        text: data.no
    }));
    newCard.append($('<p/>', {
        "class": "list-box-no-title",
        text: data.title
    }));
    // newCard.append($('<p/>', {
    //     "class": "list-box-writer",
    //     text: "사감부"
    // }));

    target.append(newCard);
}

function setNoticePreview() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/notice/list",
        type: "GET",
        data: {
            page: 1,
            limit: 1
        },
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data).result;
                $("#notice-title").text(parsedData[0].title);
                $(".notice-content-container p").text(sanitize(parsedData[0].content));
            },
            204: function(data) {
                $("#notice-title").text("");
                $(".notice-content-container p").text("글이 없습니다.");
            }
        },
        error: function() {
            console.log("error");
        }
    });
}


/** ======================================================================================
 * Dormitory rule
========================================================================================== */

/** ======================================================================================
 * My page
========================================================================================== */
$openMyPageButton.on("click", function() {
    showLoginModal();
});

$dormRule.on("click", function() {
    showLoginModal();
});

/** ======================================================================================
 * faq rule
========================================================================================== */

$faqBtn.on("click", function() {
    showLoginModal();
});

/** ======================================================================================
 * Stay
========================================================================================== */

$openStayButton.click(function() {
    showLoginModal();
});


/** ======================================================================================
 * Login
========================================================================================== */
$openLoginButton.on("click", function() {
    $('.login-modal-wrapper').toggleClass('open');
    // $panel.toggleClass('blur');
    // $menu.toggleClass('blur');
    return false;
});

$loginSendBtn.on("click", function() {
    $.ajax({
        url: "/account/login/student",
        type: "POST",
        data: {
            id: $(".login-input #name").val(),
            password: $(".login-input #pass").val(),
            remember: $(".login-check input:checked").val()
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
    // $panel.toggleClass('blur');
    // $menu.toggleClass('blur');
    return false;
});

$(".report-bug").on("click", function() {
    $.ajax({
        url: "/post/bug",
        type: "POST",
        data: {
            title: $("#bug-title").val(),
            content: $("#bug-content").val()
        },
        success: function() {
            alert("버그를 제보해 주셔서 고맙습니다!");
            $("#bugModal button:nth-child(2)").click();
        },
        error: function() {
            alert("버그신고에 실패했어요 TT");
        }
    });
});

/** ======================================================================================
 * Facility modal
========================================================================================== */
$facilityBtn.on("click", function() {
    showLoginModal();
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
    showLoginModal();
});

/** ======================================================================================
 * Current state (stay)
========================================================================================== */
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
 * Current state (extension)
========================================================================================== */
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

(function() {
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
})();
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
    return [
        mealDate.getFullYear(),
        ('0' + (mealDate.getMonth() + 1)).slice(-2),
        ('0' + mealDate.getDate()).slice(-2)
    ].join('-');
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
    //$backgroundImage.attr("src", ".\\images\\wallpaper" + (Math.floor(Math.random() * 9) + 1) + ".jpg");

    var agent = navigator.userAgent.toLowerCase();

    // if (agent.indexOf("chrome") != -1) {
    //     alert("크롬 브라우저입니다.");
    // }
    // if (agent.indexOf("safari") != -1) {
    //     alert("사파리 브라우저입니다.");
    // }
    // if (agent.indexOf("firefox") != -1) {
    //     alert("파이어폭스 브라우저입니다.");
    // }

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

/** ======================================================================================
 * article preview
========================================================================================== */
function setRulePreview() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/rule",
        type: "GET",
        data: {
            page: 1,
            limit: 1
        },
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data).result;
                $("#notice-title").text(parsedData[0].title);
                $(".notice-content-container p").text(sanitize(parsedData[0].content));
            },
            204: function(data) {
                $("#notice-title").text("");
                $(".notice-content-container p").text("글이 없습니다.");
            }
        },
        error: function() {
            console.log("error");
        }
    });
}

function setFaqPreview() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/faq/list",
        type: "GET",
        data: {
            page: 1,
            limit: 1
        },
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data).result;
                $("#notice-title").text(parsedData[0].title);
                $(".notice-content-container p").text(sanitize(parsedData[0].content));
            },
            204: function(data) {
                $("#notice-title").text("");
                $(".notice-content-container p").text("글이 없습니다.");
            }
        },
        error: function() {
            console.log("error");
        }
    });
}

noticePreviewBtn.on("click", function() {
    $(".speech-bubble-tail").remove();
    $(this).after('<div class="speech-bubble-tail"></div>');
    setNoticePreview();
});

rulePreviewBtn.on("click", function() {
    $(".speech-bubble-tail").remove();
    $(this).after('<div class="speech-bubble-tail"></div>');
    setRulePreview();
});

faqPreviewBtn.on("click", function() {
    $(".speech-bubble-tail").remove();
    $(this).after('<div class="speech-bubble-tail"></div>');
    setFaqPreview();
});

/** ======================================================================================
 * register
========================================================================================== */
registerBtn.on("click", function() {
    $(".login-content").toggle("slide");
    $(".register-content").toggle("slide");
});

$(".register-close").on("click", function() {
    $(".login-content").toggle("slide");
    $(".register-content").toggle("slide");
});

$("#register-apply-btn").on("click", function() {
    $.ajax({
        url: "/account/register/student",
        type: "POST",
        data: {
            id: $("#register-id").val(),
            password: $("#register-pass").val(),
            uid: $("#register-code").val()
        },
        success: function(data, status) {
            alert("회원가입에 성공했어요");
            $(".login-content").toggle("slide");
        },
        error: function(xhr) {
            alert("회원가입에 실패했어요");
            $(".login-content").toggle("slide");
            $(".register-content").toggle("slide");        },
    });
});
