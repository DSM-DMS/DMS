/***
 * background
 */
var $backgroundImage = $("#backgroundWallpaper");

/**
 * Panel
 */
var $panel = $("#panel");

/**
 * Common button
 */
var $materialButton = $(".material-button");

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
var $menuWrapper = $menu.parent();
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
var goingOutDate = new Date();

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

var $mealNavigationButton = $(".meal-navigation-button");
var $mealCardContainer = $(".meal-card-container");

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
 * Domitory survey
 */
var $surveyBtn = $(".survey-btn");
var $closeSurveyButton = $("#survey-going-out-window");
var $surveyListWindow = $(".survey-window");

/**
 * aftershcooc-request
 */
var $aftsch = $(".aftsch-btn");
var $closeAftSchButton = $("#close-aft-window");
var $aftListWindow = $(".aft-window");

var $aftMon = $("#aft-mon-list ul");
var $aftTue = $("#aft-tue-list ul");
var $aftSat = $("#aft-weekend-list ul");

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
var $password = $("#pass");

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
var $noticeBox = $(".list-box");

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
    var height = screen.height - (window.outerHeight - window.innerHeight);
    
function setBrowserSize() {


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
    window.scrollTo(0,0); 

}


 // scroll lock (작업표시줄 유무에 따른 화면 크기문제 해결)
setBrowserSize();
$(window).on('resize', function() {
    setBrowserSize();
})


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
    $(this).parents().parents().parents("div").children("input").val("");
    $(this).parents().parents().parents("div").children("textarea").val("");
    $(this).parents().parents().parents().parents(".modal-wrapper").toggleClass('open');
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
    $(this).css({
        transition: "0.2s ease-in",
        backgroundColor: "rgba(255, 255, 255, .2)"
    });
    if ($(this).attr('id') === "extension-gaon") {
        selectedClass.css({
            transition: "0.2s ease-in",
            backgroundColor: "rgba(0, 0, 0, 0)"
        });
        getClassData(1);
        selectedClass = $(this);
    } else if ($(this).attr('id') === "extension-naon") {
        selectedClass.css({
            transition: "0.2s ease-in",
            backgroundColor: "rgba(0, 0, 0, 0)"
        });
        getClassData(2);
        selectedClass = $(this);
    } else if ($(this).attr('id') === "extension-daon") {
        selectedClass.css({
            transition: "0.2s ease-in",
            backgroundColor: "rgba(0, 0, 0, 0)"
        });
        getClassData(3);
        selectedClass = $(this);
    } else if ($(this).attr('id') === "extension-laon") {
        selectedClass.css({
            transition: "0.2s ease-in",
            backgroundColor: "rgba(0, 0, 0, 0)"
        });
        getClassData(4);
        selectedClass = $(this);
    } else if ($(this).attr('id') === "extension-three") {
        selectedClass.css({
            transition: "0.2s ease-in",
            backgroundColor: "rgba(0, 0, 0, 0)"
        });
        getClassData(5);
        selectedClass = $(this);
    } else if ($(this).attr('id') === "extension-four") {
        selectedClass.css({
            transition: "0.2s ease-in",
            backgroundColor: "rgba(0, 0, 0, 0)"
        });
        getClassData(6);
        selectedClass = $(this);
    } else if ($(this).attr('id') === "extension-five") {
        selectedClass.css({
            transition: "0.2s ease-in",
            backgroundColor: "rgba(0, 0, 0, 0)"
        });
        getClassData(7);
        selectedClass = $(this);
    } else if ($(this).attr('id') === "extension-cancel") {
        let $cancelButton = $(this);
        $.ajax({
            url: "/apply/extension",
            type: "DELETE",
            success: function() {
                alert("연장학습 신청이 취소되었습니다.");
                window.location.href = "/";
            },
            error: function() {
                $cancelButton.bind("transitionend webkitTransitionEnd oTransitionEnd MSTransitionEnd", function(event){ 
                    alert("연장학습 취소 중 에러가 발생하였습니다.");
                    window.location.href = "/";
                });
                $cancelButton.css({
                    transition: "0.2s ease-in",
                    backgroundColor: "rgba(0, 0, 0, 0)"
                });
            }
        });
    }
});

$openExtensionButton.on("click", function() {
    if($stayWindow.hasClass("fade-in")) {
        $panel.toggleClass("left-move");
        $stayWindow.toggleClass("fade-in");
        $menu.toggleClass("fade-out");
        $menuWrapper.toggleClass("fade-out");
        $menu2.toggleClass("fade-out");
        $menuPagenation.toggleClass("fade-out");
    }
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu2.toggleClass("fade-out");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$closeExtensionButton.on("click", function() {
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

/** ======================================================================================
 * Notice
========================================================================================== */
$noticeMoreBtn.on("click", function() {
    if (selectedCategory == "notice") {
        $noticeListWindow.toggleClass("fade-in");
        $panel.toggleClass("left-move");
        $menu.toggleClass("fade-out");
        $menu2.toggleClass("fade-out");
        $menuWrapper.toggleClass("fade-out");
        $menuPagenation.toggleClass("fade-out");
    } else if (selectedCategory == "rule") {
        $openStayButton.prop("disabled", true);
        $openExtensionButton.prop("disabled", true);
        $dormListWindow.toggleClass("fade-in");
        $panel.toggleClass("left-move");
        $menu.toggleClass("fade-out");
        $menuWrapper.toggleClass("fade-out");
        $menu2.toggleClass("fade-out");
        $menuPagenation.toggleClass("fade-out");
    } else if (selectedCategory == "faq") {
        $openStayButton.prop("disabled", false);
        $openExtensionButton.prop("disabled", false);
        $faqListWindow.toggleClass("fade-in");
        $panel.toggleClass("left-move");
        $menu.toggleClass("fade-out");
        $menuWrapper.toggleClass("fade-out");
        $menu2.toggleClass("fade-out");
        $menuPagenation.toggleClass("fade-out");
    }
});

$closeNoticeButton.on("click", function() {
    $noticeListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

function getNoticeList() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/list/notice",
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
        "class": "list-box-writer",
        text: "사감부"
    }));
    newCard.append($('<p/>', {
        "class": "list-box-title",
        text: data.title
    }));
    // newCard.append($('<p/>', {
    //     "class": "list-box-no-content",
    //     html: data.content
    // }));
    newCard.on('click', function() {
        if (!$(".list-box p").hasClass("list-box-no-content")) {
            $(this).css('width', '100%');
            $(this).css('height', 'auto');
            $(this).append($('<p/>', {
                "class": "list-box-no-content",
                html: data.content
            }));
            $(".list-box-no-content").css('opacity', '1');
        } else {
            $(this).css('width', '20vh');
            $(this).css('height', '20vh');
            $(this).children(".list-box-no-content").detach();
        }
    });
    target.append(newCard);
}

// function setNoticePreview() {
//     $.ajax({
//         url: "http://dsm2015.cafe24.com/post/notice/preview",
//         type: "GET",
//         statusCode: {
//             200: function(data) {
//                 var parsedData = JSON.parse(data);
//                 $("#notice-title").html(parsedData.title);
//                 $(".notice-content-container p").html((parsedData.content));
//             },
//             204: function(data) {
//                 $("#notice-title").text("");
//                 $(".notice-content-container p").text("글이 없습니다.");
//             }
//         },
//         error: function() {
//             console.log("error");
//         }
//     });
// }

function setNoticePreview() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/notice/preview",
        type: "GET",
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data);
                $("#notice-title").html(parsedData.title);
                $(".notice-content-container p").html((parsedData.content));
            },
            204: function() {
                $.ajax({
                    url: "http://dsm2015.cafe24.com/post/list/notice",
                    type: "GET",
                    data: {
                        page: 1,
                        limit: 1
                    },
                    statusCode: {
                        200: function(data) {
                            var parsedData = JSON.parse(data).result;
                            $("#notice-title").text(parsedData[0].title);
                            $(".notice-content-container p").html(parsedData[0].content)
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
    $openStayButton.prop("disabled", true);
    $openExtensionButton.prop("disabled", true);
    $mypageWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$closeMypageWindow.on("click", function() {
    $openStayButton.prop("disabled", false);
    $openExtensionButton.prop("disabled", false);
    $mypageWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});


$dormRule.on("click", function() {
    $openStayButton.prop("disabled", true);
    $openExtensionButton.prop("disabled", true);
    $dormListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$closeDormRuleButton.on("click", function() {
    $openStayButton.prop("disabled", false);
    $openExtensionButton.prop("disabled", false);
    $dormListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});
getRuleList();

function getRuleList() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/list/rule",
        type: "GET",
        success: function(data) {
            var parsedData = JSON.parse(data).result;
            parsedData.forEach(function(data) {
                fillListCard(data, $(".rule-window .list-box-container"));
            });
        },
        error: function() {
            console.log("error");
        }
    });
}

function setRulePreview() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/list/rule",
        type: "GET",
        data: {
            page: 1,
            limit: 1
        },
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data).result;
                $("#notice-title").text(parsedData[0].title);
                $(".notice-content-container p").html(parsedData[0].content);
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
 * mypage
========================================================================================== */

$passwordChangeBtn.on("click", function() {
    $('.password-change-modal-wrapper').toggleClass('open');
});

$passwordChangeReq.on("click", function() {
    if ($("#new-password").val() === $("#new-password2").val()) {
        $.ajax({
            url: "/account/password/student",
            type: "PATCH",
            data: {
                password: $("#new-password").val()
            },
            success: function() {
                alert("변경이 완료되었어요!");
            },
            error: function() {
                alert("변경에 실패했어요. ㅠㅠ");
            }
        });
    } else {
        alert("비밀번호를 다시 확인하세요.")
    }
});

getStudentInfo();

function getStudentInfo() {
    $.ajax({
        url: "/account/student",
        method: "get",
        success: function(data) {
            fillStudentData(JSON.parse(data));
        },
        error: function() {
            console.log("error");
        }
    });
}

function fillStudentData(data) {
    $(".profile-container .name").text(data.name);
    $(".point-container .merit").text("상점 : " + data.merit);
    $(".point-container .demerit").text("벌점 : " + data.demerit);
}



/** ======================================================================================
 * faq rule
========================================================================================== */
$closeFaqButton.on("click", function() {
    $openStayButton.prop("disabled", true);
    $openExtensionButton.prop("disabled", true);
    $faqListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$faqBtn.on("click", function() {
    $openStayButton.prop("disabled", false);
    $openExtensionButton.prop("disabled", false);
    $faqListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

getFaqList();

function getFaqList() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/list/faq",
        type: "GET",
        success: function(data) {
            var parsedData = JSON.parse(data).result;
            parsedData.forEach(function(data) {
                fillListCard(data, $(".faq-window .list-box-container"));
            });
        },
        error: function() {
            console.log("error");
        }
    });
}

function setFaqPreview() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/list/faq",
        type: "GET",
        data: {
            page: 1,
            limit: 1
        },
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data).result;
                $("#notice-title").text(parsedData[0].title);
                $(".notice-content-container p").html(parsedData[0].content);
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
 * survey
========================================================================================== */
$closeSurveyButton.on("click", function() {
    $openStayButton.prop("disabled", true);
    $openExtensionButton.prop("disabled", true);
    $surveyListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

$surveyBtn.on("click", function() {
    $openStayButton.prop("disabled", false);
    $openExtensionButton.prop("disabled", false);
    $surveyListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

function getSurveyList() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/post/list/faq",
        type: "GET",
        success: function(data) {
            var parsedData = JSON.parse(data).result;
            parsedData.forEach(function(data) {
                fillListCard(data, $(".faq-window .list-box-container"));
            });
        },
        error: function() {
            console.log("error");
        }
    });
}

/** ======================================================================================
 * Stay
========================================================================================== */
var setStayValue = function(thisDate) {
    var weekData = makeWeekFormat(thisDate)

    $.ajax({
        url: "/apply/stay",
        type: "GET",
        data: {
            "week": weekData
        },
        success: function(data) {
            try {
                switch (jQuery.parseJSON(data).value) {
                    case 1:
                        $('#stayValue').text('금요귀가');
                        $(":radio[name=1][value=1]").prop('checked', true);
                        $('#stayIcon').attr('src', './images/fri-out.svg');
                        break;
                    case 2:
                        $('#stayValue').text('토요귀가');
                        $(":radio[name=1][value=2]").prop('checked', true);
                        $('#stayIcon').attr('src', './images/sat-out.svg');
                        break;
                    case 3:
                        $('#stayValue').text('토요귀사');
                        $(":radio[name=1][value=3]").prop('checked', true);
                        $('#stayIcon').attr('src', './images/sat-in.svg');
                        break;
                    case 4:
                        $('#stayValue').text('잔류');
                        $(":radio[name=1][value=4]").prop('checked', true);
                        $('#stayIcon').attr('src', './images/stay.svg');
                        break;
                }
            } catch (err) {
                $('#stayValue').text('신청안됨');
                $('#stayIcon').attr('src', './images/stay-not-applied.svg');
            }
        },
        error: function(xhr) {
            console.log(xhr.status);
            $('#stayValue').text('불러오기 실패');
        }
    });
};

setStayValue(stayDate);

$openStayButton.click(function() {
    if($extensionWindow.hasClass("fade-in")) {
        $panel.toggleClass("left-move");
        $extensionWindow.toggleClass("fade-in");
        $menu.toggleClass("fade-out");
        $menu2.toggleClass("fade-out");
        $menuWrapper.toggleClass("fade-out");
        $menuPagenation.toggleClass("fade-out");
    }
    $stayWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
    setStayValue(stayDate);
});

$closeStayButton.on("click", function() {
    $panel.toggleClass("left-move");
    $stayWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
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

    var applySendDataValue = $(":radio[name=1]:checked").val();

    console.log(applySendDataValue);

    $.ajax({
        url: "/apply/stay",
        type: "PUT",
        async: false,
        data: {
            "value": applySendDataValue
        },
        statusCode: {
            200: function() {
                alert('신청되었습니다.');
                setStayValue(stayDate);
                $stayPaperplane.removeClass("send-paperplane");
            },
            204: function() {
                alert('신청 시간이 아닙니다.');
                $stayPaperplane.removeClass("send-paperplane");

            },
            500: function() {
                alert('신청 시간이 아닙니다.');
                $stayPaperplane.removeClass("send-paperplane");
            }
        },
        error: function(xhr, status, err) {
            alert('신청 시간이 아닙니다.');
            $stayPaperplane.removeClass("send-paperplane");
        }
    });
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
/**============================================================
 * afterschool-request
=============================================================== */
$aftsch.on("click", function() {
    $openStayButton.prop("disabled", true);
    $openExtensionButton.prop("disabled", true);
    $aftListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");

    
    $.ajax({
        url: "/afterschool/item/list",
        type: "GET",
        statusCode:{
            200:function(){
                var templ1 = '<li><input type="radio" name="';
                var templ2 = '" value="';
                var templ3 = '"><label>';
                var templ4 = '</label><div class="bullet"><div class="line zero"></div><div class="line one"></div><div class="line two"></div><div class="line three"></div><div class="line four"></div><div class="line five"></div><div class="line six"></div><div class="line seven"></div></div></li>';
                
                var mondayAft = document.createDocumentFragment();
                var tuesdayAft = document.createDocumentFragment();
                var saturdayAft = document.createDocumentFragment();

                var aftList = Jquery.parseJSON(data);
                for(var i=1;i<=aftList.length;i++){
                    if(aftList[i].on_monday == true){
                        var monList = templ1 + "2" + templ2 + aftList[i].no.toString() + templ3 + aftList[i].title + templ4;
                        mondayAft.appendChild(monList);
                    }
                    else if(aftList[i].on_tuesday == true){
                        var tueList = templ1 + "3" + templ2 + aftList[i].no.toString() + templ3 + aftList[i].title + templ4;
                        tuesdayAft.appendChild(tueList);
                    }
                    else if(aftList[i].on_saturday == true){
                        var satList = templ1 + "4" + templ2 + aftList[i].no.toString() + templ3 + aftList[i].title + templ4;
                        saturdayAft.appendChild(satList);
                    }
                    else{
                        console.log("123");
                    }
                }

                aftMon.appendChild(mondayAft);
                aftTue.appendChild(tuesdayAft);
                aftSat.appendChild(saturdayAft);

                aftMon.appendChild
            },  
            204:function(){
                console.log("aftsch_");
            }    
        }
    });
});
$closeAftSchButton.on("click", function() {
    $openStayButton.prop("disabled", false);
    $openExtensionButton.prop("disabled", false);
    $aftListWindow.toggleClass("fade-in");
    $panel.toggleClass("left-move");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});
$("#aft-date-mon span").click(function(){
    $("#aft-tue-list").hide();
    $("#aft-weekend-list").hide();
    $("#aft-mon-list").slideDown();
});
$("#aft-date-tue span").click(function(){
    $("#aft-mon-list").hide();
    $("#aft-weekend-list").hide();
    $("#aft-tue-list").slideDown();
});
$("#aft-date-weekend span").click(function(){
    $("#aft-mon-list").hide();
    $("#aft-tue-list").hide();
    $("#aft-weekend-list").slideDown();
});
$("#aft-apply-btn").click(function() {
    $stayPaperplane.addClass("send-paperplane");

});

/** ======================================================================================
 * Login
========================================================================================== */
$openLoginButton.on("click", function() {
    $.ajax({
        url: "/account/logout/student",
        type: "POST",
        success: function() {
            console.log("logout");
            setCookie('UserSession', '', '-1');
            setCookie('vertx-web.session', '', '-1');
            deleteCookie('UserSession');
            deleteCookie('vertx-web.session');
            window.location.reload();
        },
        error: function() {
            alert("로그아웃에 실패했어요 TT");
        }
    });
});


function setCookie(name, value, d) {
    document.cookie = name + '=' + escape(value) + '; path=/' + (d ? '; expires=' + (function(t) {
        t.setDate(t.getDate() + d);
        return t
    })(new Date).toGMTString() : '');
}

function deleteCookie(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

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
            $(".bug-modal input").val("");
            $(".bug-modal textarea").val("");
            $(".bug-modal .btn-close").click();
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
    $FacilityModal.toggleClass('open');
    // $panel.toggleClass('blur');
    // $menu.toggleClass('blur');
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
var setGoingOutValue = function(thisDate) {
    var weekData = makeWeekFormat(thisDate)

    $.ajax({
        url: "/apply/goingout",
        type: "GET",
        data: {
            "week": weekData
        },
        success: function(data) {
            try {
                $saturdayContainer.toggleClass("select", jQuery.parseJSON(data).sat);
                $sundayContainer.toggleClass("select", jQuery.parseJSON(data).sun);
            } catch (err) {}
        },
        error: function(xhr) {
            console.log(xhr.status);
        }
    });
};

setGoingOutValue(goingOutDate);

$openGoingOutButton.on("click", function() {
    $openStayButton.prop("disabled", true);
    $openExtensionButton.prop("disabled", true);
    $panel.toggleClass("left-move");
    $goingOutWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
    setGoingOutValue(goingOutDate);
    return false;
});

$closeGoingOutButton.on("click", function() {
    $openStayButton.prop("disabled", false);
    $openExtensionButton.prop("disabled", false);
    $panel.toggleClass("left-move");
    $goingOutWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
    $menuWrapper.toggleClass("fade-out");
    $menu2.toggleClass("fade-out");
    $menuPagenation.toggleClass("fade-out");
});

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
            200: function() {
                alert("신청 완료되었습니다.");
                getClassData(classId);
            },
            204: function() {
                alert("신청가능한 시간이 아닙니다.");
                getClassData(classId);
            },
            500: function() {
                alert("신청중에 오류가 발생하였습니다.");
                getClassData(classId);
            }
        },
        error: function(request, status, error) {
            alert("신청중에 오류가 발생하였습니다.");
            getClassData(classId);
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

function getNextDay(date) {
    mealDate.setDate(mealDate.getDate() + 1);
    return mealDate;
}

function getPrevDay(date) {
    mealDate.setDate(mealDate.getDate() - 1);
    return mealDate;
}

function formatDate(date) {
    date = new Date(date);
    return [
        date.getFullYear(),
        ('0' + (date.getMonth() + 1)).slice(-2),
        ('0' + date.getDate()).slice(-2)
    ].join('-');
}

function formatDate2() {
    var days = ["일", "월", "화", "수", "목", "금", "토"];
    var y = mealDate.getFullYear();
    var m = mealDate.getMonth() + 1;
    var d = mealDate.getDate();
    var day = days[mealDate.getDay()];

    return y + "년 " + m + "월 " + d + "일 " + day + "요일";
}

function setDay() {
    $(".meal-date").text(formatDate2());
}

getSomedayMeal(mealDate, $(".today-meal"));

function getSomedayMeal(day, target) {
        $.ajax({
        url: "http://dsm2015.cafe24.com:3000/meal/" + formatDate(mealDate),
        statusCode: {
            200: function(data) {
                var parsedData = JSON.parse(data);
                var domArr = target.find(".meal-card p");
                $(domArr[0]).html(parsedData.breakfast.toString().replace(/,/gi, "<br>"));
                $(domArr[1]).html(parsedData.lunch.toString().replace(/,/gi, "<br>"));
                $(domArr[2]).html(parsedData.dinner.toString().replace(/,/gi, "<br>"));
            },
            error: function() {
                var domArr = $(".meal-content p");
                $(domArr[0]).text("급식이 없습니다.");
                $(domArr[1]).text("급식이 없습니다.");
                $(domArr[2]).text("급식이 없습니다.");
            }
        }
    });
}

//Sets the document when it is loaded
$(document).ready(function() {
    //check user agent
    if(navigator.userAgent.toLowerCase().indexOf("android") > -1){
        // var loadMobileApp = {
        //     host:"intent://default?version=1#Intent;",
        //     action:"action=android.intent.action.VIEW;",
        //     category:"category=android.intent.category.BROWSABLE;",
        //     package:"package=com.nhn.android.search;end"
        // }

        // var url;
        // for(var element in loadMobileApp){
        //     url += loadMobileApp[element];
        // }

        // window.location.href = url;
        alert("this is mobile");
        $("body").css({"transform":"rotate(90deg);"});
    }
    else if(navigator.userAgent.toLowerCase().indexOf("iphone") > -1){
        window.location.href = 'https://itunes.apple.com/kr/app/apple-store/id375380948?mt=8';
    }

    //set random background image
    //$backgroundImage.attr("src", ".\\images\\wallpaper" + (Math.floor(Math.random() * 9) + 1) + ".jpg");

    //연장신청 시간 보여줌
    var startTime = '05:30 PM';
    var endTime = '10:00 PM';

    var formatTime = (function() {
        function addZero(num) {
            return (num >= 0 && num < 10) ? "0" + num : num + "";
        }
        return function(dt) {
            var formatted = '';

            if (dt) {
                var hours24 = dt.getHours();
                var hours = ((hours24 + 11) % 12) + 1;
                formatted = [
                    [addZero(hours), addZero(dt.getMinutes())].join(":"), hours24 > 11 ? "PM" : "AM"
                ].join(" ");
            }
            return formatted;
        }
    })();

    var currentTime = formatTime(new Date());

    if (currentTime >= startTime && currentTime <= endTime) {
        $('#extensionValue').html("연장신청이 가능합니다.");
    } else {
        $('#extensionValue').html("연장신청시간이 아닙니다.");
    }

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

    var slideCount = $('#slider ul li').length;
	var slideWidth = $('#slider ul li').width();
	var slideHeight = $('#slider ul li').height();
	var sliderUlWidth = slideCount * slideWidth;

	$('#slider').css({ width: slideWidth, height: slideHeight });

	$('#slider ul').css({ width: sliderUlWidth, marginLeft: - slideWidth });

    $('#slider ul li:last-child').prependTo('#slider ul');

    function moveLeft() {
        $('#slider ul').animate({
            left: + slideWidth
        }, 200, function () {
            getSomedayMeal(getPrevDay(mealDate), $('#slider ul li:first-child'));
            $(".meal-date").text(formatDate2());
            $('#slider ul li:last-child').prependTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };

    function moveRight() {
        $('#slider ul').animate({
            left: - slideWidth
        }, 200, function () {
            getSomedayMeal(getNextDay(mealDate), $('#slider ul li:last-child'));
            $(".meal-date").text(formatDate2());
            $('#slider ul li:first-child').appendTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };

    $('.control_prev').click(function () {
        moveLeft();
    });

    $('.control_next').click(function () {
        moveRight();
    });
});

/** ======================================================================================
 * Week Format fuction
========================================================================================== */
function numOfDays(year, month) {
    var daysofmonth;
    if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
        daysofmonth = 30;
    } else {
        daysofmonth = 31;
        if (month == 2) {
            if (year / 4 - parseInt(year / 4) != 0) {
                daysofmonth = 28;
            } else {
                if (year / 100 - parseInt(year / 100) != 0) {
                    daysofmonth = 29;
                } else {
                    if (year / 400 - parseInt(year / 400) != 0) {
                        daysofmonth = 28;
                    } else {
                        daysofmonth = 29;
                    }
                }
            }
        }
    }
    return daysofmonth;
}

function leadingZeros(data, num) {
    var zero = '';
    data = data.toString();

    if (data.length < num) {
        for (i = 0; i < num - data.length; i++)
            zero += '0';
    }
    return zero + data;
};

function getWeek(thisDate) {
    var tempDate = new Date(thisDate.getFullYear(), thisDate.getMonth(), 1);
    var daysOfMonth = numOfDays(tempDate.getFullYear(), thisDate.getMonth());
    var week = parseInt(((thisDate.getDate() - 1) + tempDate.getDay()) / 7) + 1;

    if (week == 5) {
        if (daysOfMonth == 31 && (tempDate.getDay() == 4 || tempDate.getDay() == 5 || tempDate.getDay() == 6)) {
            return parseInt(((thisDate.getDate() - 1) + tempDate.getDay()) / 7) + 1;
        } else if (daysOfMonth == 30 && (tempDate.getDay() == 5 || tempDate.getDay() == 6)) {
            return parseInt(((thisDate.getDate() - 1) + tempDate.getDay()) / 7) + 1;
        } else if (daysOfMonth == 29 && tempDate.getDay() == 6) {
            return parseInt(((thisDate.getDate() - 1) + tempDate.getDay()) / 7) + 1;
        } else {
            return 0;
        }
    }
    return parseInt(((thisDate.getDate() - 1) + tempDate.getDay()) / 7) + 1;
};

function makeWeekFormat(thisDate) {
    var year = thisDate.getFullYear();
    var week = getWeek(thisDate);
    var month = thisDate.getMonth();
    if (week == 0) {
        if (month == 12) {
            year = year + 1;
            month = 1;
        } else {
            month = month + 1;
        }
        week = 1;
    }

    return year + "-" + leadingZeros(month + 1, 2) + "-" + leadingZeros(week, 2);
};


/** ======================================================================================
 * article preview
========================================================================================== */
noticePreviewBtn.on("click", function() {
    selectedCategory = "notice";
    setNoticePreview();
});

rulePreviewBtn.on("click", function() {
    selectedCategory = "rule";
    setRulePreview();
});

faqPreviewBtn.on("click", function() {
    selectedCategory = "faq";
    setFaqPreview();
});

/** ======================================================================================
 * alert
========================================================================================== */

function showAlert(message) {
    $("body").append('<div class="infoAlert">message</div>');
    setTimeout(function() {
        $(".infoAlert").remove();
    }, 2000);
}

$("body").mousemove(function(event) {
  var eye = $(".eye");
  var x = (eye.offset().left) + (eye.width() / 2);
  var y = (eye.offset().top) + (eye.height() / 2);
  var rad = Math.atan2(event.pageX - x, event.pageY - y);
  var rot = (rad * (180 / Math.PI) * -1) + 150;
  eye.css({
    '-webkit-transform': 'rotate(' + rot + 'deg)',
    '-moz-transform': 'rotate(' + rot + 'deg)',
    '-ms-transform': 'rotate(' + rot + 'deg)',
    'transform': 'rotate(' + rot + 'deg)'
  });
});

$(".left-menu").on("click", function() {
    $(".left-selected-meneBar").offset({
        top: $(this).offset().top,
        left: 0
    });
});

