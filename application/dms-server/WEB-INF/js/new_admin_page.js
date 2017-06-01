var $stayDownload = $("#stay-download");
var $extensionDownload = $("#extension-download");
var $goingOutDownload = $("#going-out-download");
var $facilityDownload = $("#facility-download");
var $noticeManagement = $("#notice-management");
var $dormRuleManagement = $("#dorm-rule-management");
var $faqManagement = $("#faq-management");
var $qnaManagement = $("#qna-management");
var $deleteAccount = $("#delete-account");
var $createAdminAccount = $("#create-admin-account");
var $closeModal = $(".btn-close");
var $modalButton = $(".modal-button");
var $closeModal = $(".btn-close");


$createAdminAccount.on("click", function() {
    $(".new-admin-modal-wrapper").toggleClass("open");
});


/// modal
$closeModal.on("click", function() {
    $(this).parents().parents().parents().parents(".modal-wrapper").toggleClass('open');
    return false;
});

//달의 말일 구하기
function numOfDays(year, month) {
    var daysOfMonth;

    if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
        daysOfMonth = 30;
    } else {
        daysOfMonth = 31;
        if (month == 2) {
            if (year / 4 - parseInt(year / 4) != 0) {
                daysOfMonth = 28;
            } else {
                if (year / 100 - parseInt(year / 100) != 0) {
                    daysOfMonth = 29;
                } else {
                    if (year / 400 - parseInt(year / 400) != 0) {
                        daysOfMonth = 28;
                    } else {
                        daysOfMonth = 29;
                    }
                }
            }
        }
    }
    return daysOfMonth;
}

function getWeek(date) {
    var tempDate = new Date(date.getFullYear(), date.getMonth(), 1);
    var daysOfMonth = numOfDays(tempDate.getFullYear(), date.getMonth());
    var week = parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;



    if (week == 5) {
        if (daysOfMonth == 31 && (tempDate.getDay() == 4 || tempDate.getDay() == 5 || tempDate.getDay() == 6)) {
            return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
        } else if (daysOfMonth == 30 && (tempDate.getDay() == 5 || tempDate.getDay() == 6)) {
            return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
        } else if (daysOfMonth == 29 && tempDate.getDay() == 6) {
            return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
        } else {
            return 0;
        }
    }
    return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
}

$stayDownload.on('click', function() {
    var today = new Date();
    var week = getWeek(today);
    if (week == 0) {
        today.setMonth(today.getMonth() + 1);
        week = 1;
    }
    var year = today.getFullYear();
    var month = today.getMonth() + 1;

    location.href = 'http://dsm2015.cafe24.com/stay/download?year=' + year + '&month=' + month + '&week=' + week;
});

$extensionDownload.on('click', function() {
    location.href = 'http://dsm2015.cafe24.com/extension/download';
});

$goingOutDownload.on('click', function() {
    var today = new Date();
    var week = getWeek(today);
    if (week == 0) {
        today.setMonth(today.getMonth() + 1);
        week = 1;
    }
    var year = today.getFullYear();
    var month = today.getMonth() + 1;

    location.href = 'http://dsm2015.cafe24.com/goingout/download?year=' + year + '&month=' + month + '&week=' + week;
});

$facilityDownload.on('click', function() {
    location.href = 'http://dsm2015.cafe24.com/report/download';
});

$noticeManagement.on('click', function() {
    location.href = 'http://dsm2015.cafe24.com/post/admin?category=notice';
});

$dormRuleManagement.on('click', function() {
    location.href = 'http://dsm2015.cafe24.com/post/admin?category=rule';
})

$qnaManagement.on('click', function() {
    location.href = 'http://dsm2015.cafe24.com/post/admin?category=qna';
})

$faqManagement.on('click', function() {
    location.href = 'http://dsm2015.cafe24.com/post/admin?category=faq'
})

$deleteAccount.on('click', function() {
    $('.delete-account-modal-wrapper').toggleClass('open');
    console.log("click")
});
$(".account-reset").on("click", function() {
    $.ajax({
        url: "/account/initialize",
        type: "POST",
        data: {
            number: $("#uid").val()
        },
        complete: function(e, xhr, settings) {
            if (e.status === 200) {
                alert("삭제가 완료되었습니다.");
            } else {
                alert("삭제에 실패했습니다.");

            }
        }
    })
});

$(".new-admin-button").on("click", function() {
    var lists = $('#create-admin-form').find($('input'));
    for (var index = 0; index < lists.length; index++) {
        if (lists[index].value.length == 0) {
            alert('정보를 모두 입력해 주세요!');
            return;
        }
    }
    $.ajax({
        url: "/account/register/admin",
        type: "POST",
        data: $("#create-admin-form").serialize(),
        complete: function(xhr, textStatus) {
            var statusCode = xhr.status;
            if (statusCode == 200) {
                alert('계정생성이 완료되었습니다.');
            } else {
                alert('계정생성에 실패했습니다.');
            }
        }
    })
});

// modal common ----------------------
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
