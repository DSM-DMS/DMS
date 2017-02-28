id = "test";
name = "momo";

// 로그인 페이지
$('#login').on('click', function() {
    $.ajax({
        url: "http://dsm2015.cafe24.com/user/login",
        type: "POST",
        data: JSON.stringify({
            id: $('#login_id').val(),
            password: $('#login_pw').val(),
            recaptcha: recaptcha,
            autoLogin: autoLogin
        }),
        statusCode: {
            500: function(xhr) {
                if (window.console) console.log(xhr.responseText);
            }
        }
    });
});

// 회원가입 페이지
$('#code_submit').on('click', function() {
    $.ajax({
        url: "dsm2015.cafe24.com/user/login",
        type: "POST",
        data: {
            code: $('#register_code').val()
        },
        success: function(data) {
            var studentData = JSON.parse(data);
            $('register_hidden').val($('#register_code').val());
            $('#student_data').html('<td colspan="3" text-align="center">' + studentData.name + '</td>');
        }
    });
});

$('register').on('click', function() {
    $.ajax({
        url: "dsm2015.cafe24.com/user/login",
        type: "POST",
        data: {
            code: $('#register_hidden').val(),
            id: $('#register_id').val(),
            password: $('#register_id').val()
        },
        success: function() {
            alert('회원가입이 완료되었습니다.')
        }
    });
});

//신청탭 over 이벤트

$(".remote .category").children("a").eq(0).click(function() {
    // 신청 text를 white로
    if ($(window).width() > 480) {
        $(".remote .category").children("a").eq(0).animate({
            fontSize: "1.5em",
            color: "white"
        })
    } else {
        $(".remote .category").children("a").eq(0).animate({
            fontSize: "0.9em",
            color: "white"
        })
        $(".children a").animate({
            fontSize: "1.1em"
        })
    }

    $(".remote .category").children("a").eq(0).css({})

    $(".remote .category").children("a").eq(0).children("p").animate({
        padding: "0"
    })

    //remote background-color를 신청색으로
    $(".remote").animate({
        backgroundColor: "rgb(134,193,233)",
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
    if ($(window).width() > 480) {
        $(".remote .category").children("a").eq(1).animate({
            fontSize: "1.5em",
            color: "white"
        })
    } else {
        $(".remote .category").children("a").eq(2).animate({
            fontSize: "0.9em",
            color: "white"
        })
        $(".children a").animate({
            fontSize: "1.1em"
        })
    }

    $(".remote .category").children("a").eq(1).children("p").animate({
        padding: "0"
    })

    //remote background-color를 기숙사색으로
    $(".remote").animate({
        backgroundColor: "rgb(191,148,208)"
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
        backgroundColor: "rgb(231,160,153)"
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

function remoteRestore() {
    //모바일 기기 판단
    var isMobile = false; //initiate as false
    // device detection
    if (/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|ipad|iris|kindle|Android|Silk|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(navigator.userAgent) ||
        /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0, 4))) isMobile = true;

    if (!isMobile) {
        $(".remote .category").children("a").eq(0).animate({
            color: "rgb(134,193,233)",
            fontSize: "1em"
        })

        $(".remote .category").children("a").eq(1).animate({
            color: "rgb(191,148,208)",
            fontSize: "1em"
        })

        $(".remote .category").children("a").eq(2).animate({
            color: "color: rgb(231,160,153)",
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
    } else if (isMobile) {
        //리모트 leave 이벤트
        //모든 탭 글자색을 윈대래로

        $(".remote .category").children("a").eq(0).animate({
            color: "rgb(134,193,233)",
            fontSize: "1em"
        })

        $(".remote .category").children("a").eq(1).animate({
            color: "rgb(191,148,208)",
            fontSize: "1em"
        })

        $(".remote .category").children("a").eq(2).animate({
            color: "color: rgb(231,160,153)",
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
    }

}

if (!isMobile) {
    //리모트 leave 이벤트
    $(".remote").mouseleave(function() {
        remoteRestore();
    })
} else if (isMobile) {
    //리모트 leave 이벤트
    $(".remote").mouseup(function() {
        remoteRestore();
    });

    $(window).scroll(function() {
        if ($(window).scrollTop() > 2) {
            $('<div/>', {
                "class": "mbremotebg",
                css: {
                    "background": $("body").css("background-color"),
                }
            }).appendTo("body");
        } else if ($(window).scrollTop() <= 3) {
            var mbbgArr = $(".mbremotebg");
            for (var i = 0; i < mbbgArr.length; i++) {
                $(mbbgArr[i]).remove();
            }
        }
    })


}

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
            "border": "2px solid rgb(134,193,233)"
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
    remoteRestore();

    setTimeout(function() {
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
        //window.location = goTo;

    }, 1500);
})

// 이벤트 등록-----------------------------------------------------------------------
$(".remote .inner .category .children #extention").click(function() {
    new ExtentionApplyPage();
});

$(".remote .inner .category .children #point").click(function() {
    new PointApplyPage();
});

$(".remote .inner .category .children #after").click(function() {
    new AfterSchoolListPage();
});

$(".remote .inner .category .children #notice").click(function() {
    new FaqListPage();
});

$(".remote .inner .category .children #facility").click(function() {
    new FacilityListPage();
});

$(".remote .inner .category .children #rule").click(function() {
    new RuleListPage();
});

$(".remote .inner .category .children #qna").click(function() {
    new QnaListPage();
});

$(".remote .inner .category .a#mypage").click(function() {
    loadMyPage();
});

$(".remote .inner .category .children #goout").click(function() {
    new GoOutApplyPage();
});

// 위는 모두 remote에 관한 코드임

// Page객체 저장하는 스택 -----------------------------------------------------------------
var pageStack = [];

// 페이지를 바꾸는 함수
function changePage(beforePage, AfterPage) {
    pageStack.push(beforePage);
    AfterPage.draw();
}

function prevPage() {
    pageStack.pop();
    console.log(pageStack[pageStack.length - 1].html.children());
    $(".main").html(pageStack[pageStack.length - 1].html.children())
    pageStack[pageStack.length - 1].saveDom();
    console.log(pageStack[pageStack.length - 1].html.children());
}

// 상속트리
// Page(추상)
// | -- ClientPage(추상)
// |   |
// |   | -- AjaxPage(추상)
// |   |    |
// |   |    | -- ArticleListPage(추상)
// |   |    |    |
// |   |    |    | -- FaqListPage
// |   |    |    |
// |   |    |    | -- QnaListPage
// |   |    |    |
// |   |    |    | -- AfterSchoolListPage
// |   |    |    |
// |   |    |    | -- NoticeListPage (사라짐 FAQ와 통합)
// |   |    |    |
// |   |    |    | -- RuleListPage
// |   |    |    |
// |   |    |    | -- FacilityListPage 구현 미완료
// |   |    |
// |   |    | -- AfterSchoolArticlePage
// |   |    |
// |   |    | -- ExtentionApplyPage
// |   |    |
// |   |    | -- GoHomeApplyPage
// |   |    |
// |   |    | -- Mypage 구현 중
// |   |    |
// |   |    | -- MainPage
// |   |
// |   | -- NonAjaxPage(추상)
// |        |
// |        | -- PointApplyPage
// |        |
// |        | -- GoOutApplyPage
// |        |
// |        | -- ArticleModifyPage -------------- 서버에서 준다고 함
// |        |
// |        | -- NoticeModifyPage 구현 미완료
// |        |
// |        | -- RuleModifyPage
// |        |
// |        | -- QnaAnswerModifyPage
// |        |
// |        | -- FacilityResultModifyPage
// |        |
// |        | -- NoticeWritePage
// |        |
// |        | -- RuleWritePage
// |        |
// |        | -- FacilityWritePage
// |        |
// |        | -- QnaAnswerWritePage
// |        |
// |        | -- FacilityResultWritePage
// |        |
// |        | -- QnaQuestionWritePage
// |        |
// |        | -- QnaQuestionModifyPage
// |        |
// |        | -- FacilityModifyPage
// |
// | -- ServerPage(추상) (Notice, Rule, Faq, Qna 해당)
//      |
//      | -- NoticeArticlePage
//      |
//      | -- RuleArticlePage
//      |
//      | -- FaqArticlePage 구현 미완료
//      |
//      | -- QnaArticlePage 구현 거의 완료



// Page객체 구현 -----------------------------------------------------------------------
// Page 최상위 객체
function Page() {
    // 이벤트 등록까지 완료된 DOM저장
    this.html;

    // 페이지를 그리기 시작하는 함수
    this.draw;

    //이벤트 등록
    this.setEvent;

    // 이벤트 설정된 DOM을 저장
    this.saveDom = function() {
        this.html = $(".main").clone(true, true);
    }
}

function ClientPage() {
    this.form;

    // 기본 form을 main에 설정
    this.setForm = function() {
        $(".main").html(this.form);
    }

    this.setEvent;
}

ClientPage.prototype = new Page();

// 서버에서 Page구성에 필요한 Data만을 받아와 동적으로 html을 구성하는 페이지
// 자식 객체는 setData(), setEvent() 구현해야함
// 자식 객체는 form, command, sendData 초기화 해야함
function AjaxPage() {
    this.form;
    this.command;
    this.ajaxData;
    this.sendData;
    this.draw = function() {
        this.setForm();
        this.getData();
        this.setData();
        this.setEvent();
        this.saveDom();
    }

    // 필요한 Data를 받아옴
    this.getData = function() {
        $.ajax({
            url: "http://dsm2015.cafe24.com:10419",
            type: "POST",
            data: this.sendData,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", this.command);
            },
            success: function(data) {
                this.ajaxData = JSON.parse(data);
            }
        });
    }

    // 받아온 Data를 html에 설정
    this.setData;

    // html에 이벤트 설정
    this.setEvent;
}

// Page를 상속
AjaxPage.prototype = new ClientPage();

// 서버측 정보 없이 클라이언트에서 동적으로 html을 구성하는 페이지
// 자식 객체는 setEvent() 구현해야함
// 자식 객체는 form 초기화 해야함
function NonAjaxPage() {
    this.draw = function() {
        this.setForm();
        this.setEvent();
        this.saveDom();
    }
}

NonAjaxPage.prototype = new ClientPage();


// 서버에서 html을 받아오는 Page
// faq, qna, rule, facility, notice 해당
// 자식 객체는 setEvent() 구현해야함
function ServerPage() {
    this.htmlData;
    this.sendData;
    this.type;
    this.no;
    this.action;
    this.draw = function() {
        this.getHtml();
        this.setHtml();
        this.setEvent();
        this.saveDom();
    }

    this.getHtml = function() {
        $.ajax({
            url: "http://dsm2015.cafe24.com" + "/" + this.action + "/" + this.type,
            type: "POST",
            data: this.sendData,
            success: function(data) {
                this.htmlData = data;
            }
        });
    }

    this.setHtml = function() {
        $(".main").html(this.htmlData);
    }

    this.setEvent;
}

// Page객체 상속
ServerPage.prototype = new Page();

// 메인페이지
// 공지사항 가져오고, 급식정보 가져와야함
// 자식객체는 setDate, setEvent구현해야 함
// 자식객체는 form, command, sendData 초기화 해야함
// MainPage는 약간 다른것이 요청해야하는 정보가 2개임 (공지사항, 급식)
// getData 재정의 해야함 (급식, 공지 다 받아오도록)
// 아직 알고 있는게 하나도 없어서 구현을 못하겠다.
function MainPage() {

    this.command = {
        noticeCommand: 427,
        mealCommand: 438
    };
    this.noticeCommand = 427;
    this.mealCommand = 438;

    this.form;
    this.ajaxData = {
        noticeData: {},
        mealData: {}
    }

    this.noticeData;
    this.mealData;
    this.date = new Date();

    // 공지 ajax + 급식 ajax
    this.getData = function() {
        // getNoticeData
        $.ajax({
            url: "http://dsm2015.cafe24.com:10419",
            type: "POST",
            data: JSON.stringify({
                "page": 0,
                "limit": 5
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", this.noticeCommand);
            },
            success: function(data) {
                this.noticeData = JSON.parse(data);
            }
        });

        // getMaelData
        $.ajax({
            url: "http://dsm2015.cafe24.com:10419",
            type: "POST",
            data: JSON.stringify({
                "year": this.date.getFullYear(),
                "month": this.date.getMonth() + 1,
                "day": this.date.getDate()
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", this.mealCommand);
            },
            success: function(data) {
                this.mealData = JSON.parse(data);
            }
        });

    }

    // 공지 셋팅 + 급식
    // 급식 reseponse형식을 아직 모르겠다.
    this.setData = function() {
        // 공지사항 채우기 and 이벤트 등록
        for (var loop = 0; loop < 5; loop++) {
            var newLi = $('<li/>', {
                click: function() {
                    pageStack.push(this);
                    new NoticePage("notice", this.noticeData.no);
                }
            }).appendTo("ul.notice");

            // li DOM에 no 저장
            newLi.data("no", this.noticeData.result.no);
            var appendString =
                '<p class="title">' + this.noticeData.result.title + '</p>';
            appendString +=
                '<p class="no">' + this.noticeData.result.no + '</p>';
            newLi.append(appendString);
        }

        // 메뉴 채우기
        var mealArr = $(".right div.menues div.meal div.info");
        for (var loop = 0; loop < mealArr.length; loop++) {
            var menuString = "";
            for (var innerLoop = 0; innerLoop < this.mealData.result.Meals[loop].Menu.length; innerLoop++) {
                menuString += this.mealData.result.Meals[loop].Menu[innerLoop];
                if (innerLoop != this.mealData.result.Meals[loop].Menu.length - 1) {
                    menuString += " / "
                }
            }
            $(mealArr[loop]).children().eq(1).text(menuString);
        }

        // 알러지 채우기
        var mealArr = $(".right div.menues div.meal div.info");
        for (var loop = 0; loop < mealArr.length; loop++) {
            var allergyString = "";
            for (var innerLoop = 0; innerLoop < this.mealData.result.Meals[loop].Allergy.length; innerLoop++) {
                allergyString += this.mealData.result.Meals[loop].Allergy[innerLoop];
                if (innerLoop != this.mealData.result.Meals[loop].Allergy.length - 1) {
                    allergyString += " / "
                }
            }
            $(mealArr[loop]).children().eq(2).children().eq(0).text(allergyString);
        }

    }

    // 공지 클릭시, notice글 받아오기 + 급식 알러지 정보
    this.setEvent = function() {
        // 공지사항 글로 넘어가는 이벤트
        var liArr = $(".left ul.notice li");
        for (var loop = 0; loop < liArr.length; loop++) {
            $(liArr[loop]).click(function() {
                new NoticePage("notice", $(liArr[loop]).data("no"));
                pageStack.push(this);
            })
        }

        // 알러지 보여주는 이벤트
        $(".right .mornig img").click(function() {
            // 알러지 정보를 가져와야함
            if ($(this).data("clicked") == false || $(this).data("clicked") === undefined) {
                $(this).data("clicked", true);
                $(".right .mornig .info .div-allergy").show(100)
            } else {
                // 알러지 정보를 집어넣어야함
                $(this).data("clicked", false);
                $(".right .mornig .info .div-allergy").hide(100)
            }
        })

        $(".right .lunch img").click(function() {
            // 알러지 정보를 가져와야함
            if ($(this).data("clicked") == false || $(this).data("clicked") === undefined) {
                $(this).data("clicked", true);
                $(".right .lunch .info .div-allergy").show(100)
            } else {
                // 알러지 정보를 집어넣어야함
                $(this).data("clicked", false);
                $(".right .lunch .info .div-allergy").hide(100)
            }
        })

        $(".right .dinner img").click(function() {
            // 알러지 정보를 가져와야함
            if ($(this).data("clicked") == false || $(this).data("clicked") === undefined) {
                $(this).data("clicked", true);
                $(".right .dinner .info .div-allergy").show(100)
            } else {
                // 알러지 정보를 집어넣어야함
                $(this).data("clicked", false);
                $(".right .dinner .info .div-allergy").hide(100)
            }
        })
    }

    pageStack.push(this);
    this.draw();

}

MainPage.prototype = new AjaxPage();


// 리스트 페이지
// faqlist, qnalist, afterschoollist, noticelist
// setEvent구현 완료
// 자식객체는 setDate구현해야 함
// 자식객체는 form, command, sendData 초기화 해야함
// articlelist는 setEvent()를 구현하지 않는다.
// 동적으로 Element를 삽입하는 과정이서 이벤트를 등록하는것이 더 효율적일듯
function ArticleListPage() {
    this.page = 1;
    // 한줄에 표시할 페이지 갯 수
    this.pageUnit = 5;
    this.type;
    // 최대 표시할 수 있는 '페이지' 수 = DB에 저장된 '리스트' 수 / 최대 리스트 표수 수
    this.maxPageLength;
    this.trHeight = 53;
    this.getListLength = function() {
        // main의 header등의 height를 제외한 공간의 높이
        console.log($(".main").height());
        var useableSpace = $(".main").height() - $(".articlelist table.list .tableheader").position().top;
        console.log(useableSpace);
        return (useableSpace / this.trHeight) - 2;
    };

    this.setEvent = function() {};

    // page를 넘겼을때 list를 다시 출력하는 함수
    this.reload = function() {
        // 데이터를 다시 받아오기 전에 sendData 다시 초기화
        this.sendData = JSON.stringify({
            "page": this.page,
            "limit": this.getListLength()
        });
        this.getData();
        // table초기화 전에 header를 저장해 둠
        var tableHeader = $(".articlelist table.list tr:nth-child(1)").get();
        // table초기화 & tableHeader생성
        $(".articlelist table.list").html(tableHeader);
        this.setDate();
        this.saveDom();
    };

    // page선택하는 html을 로드하는 함수
    this.setPageData = function() {
        // maxPageLength 초기화
        this.maxPageLength = Math.floor(this.ajaxData.num_of_post / this.getListLength());

        // 페이지 숫자 표시
        // 정상적인 경우 == 최대 표시가능한 페이지 수가 페이지 표시 단위보다 같거나 클때
        if (this.maxPageLength - Math.floor((this.page - 1) / this.pageUnit) * this.pageUnit + 1 >= this.pageUnit) {
            for (var loop = 0; loop < this.pageUnit; loop++) {
                var selected = "";
                // 선택된 번호의 경우
                if (Math.floor((this.page - 1) / this.pageUnit) * this.pageUnit + loop + 1 == this.page) {
                    selected = "selected";
                }

                var newNextPage = $('<td/>', {
                    "class": selected,
                    text: Math.floor((this.page - 1) / this.pageUnit) * this.pageUnit + loop + 1,
                    click: function(e) {
                        this.page = Math.floor((this.page - 1) / this.pageUnit) * this.pageUnit + loop + 1;
                        this.reload();
                    }
                });
            }
        }
        // 예외인 경우 == 최대 표시가능한 페이지 수가 페이지 표시 단위보다 작을때
        else if (this.maxPageLength - Math.floor((this.page - 1) / this.pageUnit) < this.pageUnit) {
            for (var loop = 0; loop < this.maxPageLength; loop++) {
                var selected = "";
                // 선택된 번호의 경우
                if (Math.floor((this.page - 1) / this.pageUnit) * this.pageUnit + loop + 1 == this.page) {
                    selected = "selected";
                }
                var newPrevPage = $('<td/>', {
                    "class": selected,
                    text: Math.floor((this.page - 1) / this.pageUnit) * this.pageUnit + loop + 1,
                    click: function(e) {
                        this.page = Math.floor((this.page - 1) / this.pageUnit) * this.pageUnit + loop + 1;
                        this.reload();
                    }
                });
            }
        }

        // 이전, 다음 표시
        // 현재 페이지가 1 ~ pageUnit일 경우
        if (this.page <= this.pageUnit) {
            var newPageTd = $('<td/>', {
                text: "다음",
                click: function(e) {
                    this.page = Math.floor((this.page - 1) / this.pageUnit + 1) * this.pageUnit + 1;
                    this.reload();
                }
            });
            $(".articlelist table.page").prepend(newPageTd);

        }

        // 현재 페이지가 pageUnit+1 이상일 경우
        if (this.page <= this.pageUnit) {
            var newPageTd = $('<td/>', {
                text: "이전",
                click: function(e) {
                    this.page = Math.floor((this.page - 1) / this.pageUnit + 1) * this.pageUnit - 5;
                    this.reload();
                }
            });
            $(".articlelist table.page").append(newPageTd);
        }
    };
}

ArticleListPage.prototype = new AjaxPage();

// form, command, sendData 초기화 해야함
function FaqListPage() {
    this.form =
        '<div class="frame left articlelist">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>공지사항</h1>' +
        '<div class="input-container">' +
        '<input type="button" name="" value="글 쓰기">' +
        '</div>' +
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table class="list">' +
        '<tr class="tableheader">' +
        '<th>번호</th>' +
        '<th>제목</th>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '<table class="page">' +
        '<tr>' +
        '</tr>' +
        '</table>';
    this.sendData = JSON.stringify({
        "page": this.page
    });
    this.type = "faq";
    this.command = "427";
    this.setData = function() {
        for (var loop = 0; loop < this.ajaxData.result.length; loop++) {
            var newTr = $('<tr/>', {
                click: function(e) {
                    // 클릭이벤트
                    new ServerPage(this.type, this.ajaxData.result.list[loop].no);
                    pageStack.push(this);
                }
            });
            var appendString = "<td>" + this.ajaxData.result[loop].no + "</td>";
            appendString += "<td>" + this.ajaxData.result[loop].title + "</td>";
            newTr.append(appendString);
            $(".articlelist table.list").append(newTr);
        }
        this.setPageData();
    }

    pageStack.push(this);
    this.draw();
}

FaqListPage.prototype = new ArticleListPage();


// form, command, sendData 초기화 해야함
function QnaListPage() {
    this.form =
        '<div class="frame left articlelist qna">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>Q&A</h1>' +
        '<div class="input-container">' +
        '<input type="button" name="" value="글 쓰기">' +
        '</div>' +
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table class="list">' +
        '<tr class="tableheader">' +
        '<th>번호</th>' +
        '<th>제목</th>' +
        '<th>날짜</th>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '<table class="page">' +
        '<tr>' +
        '</tr>' +
        '</table>';
    this.sendData = JSON.stringify({
        "page": this.page
    });
    this.type = "qna";
    this.command = "425";
    this.setData = function() {
        for (var loop = 0; loop < this.ajaxData.result.length; loop++) {
            var newTr = $('<tr/>', {
                click: function(e) {
                    // 클릭이벤트
                    new QnaArticlePage(this.ajaxData.result.list[loop].no);
                    pageStack.push(this);
                }
            });
            var appendString = "<td>" + this.ajaxData.result[loop].no + "</td>";
            appendString += "<td>" + this.ajaxData.result[loop].title;
            if (this.ajaxData.result[loop].privacy) {
                appendString += '<img src="../image/lock2.png" alt="lock image">';
            }
            appendString += "</td>"
            appendString += "<td>" + this.ajaxData.result[loop].question_date + "</td>";
            newTr.append(appendString);
            $(".articlelist table.list").append(newTr);
        }
        this.setPageData();
    }
    this.setEvent = function() {
        $(".articlelist div.input-container input").click(function() {
            // 글 쓰기 페이지 로드
        })
    }

    pageStack.push(this);
    this.draw();
}

QnaListPage.prototype = new ArticleListPage();

// form, command, sendData 초기화 해야함
function RuleListPage() {
    this.form =
        '<div class="frame left articlelist qna">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>기숙사규칙</h1>' +
        '<div class="input-container">' +
        '<input type="button" name="" value="글 쓰기">' +
        '</div>' +
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table class="list">' +
        '<tr class="tableheader">' +
        '<th>번호</th>' +
        '<th>제목</th>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '<table class="page">' +
        '<tr>' +
        '</tr>' +
        '</table>';
    this.sendData = JSON.stringify({
        "page": this.page
    });
    this.type = "rule";
    this.command = "424";
    this.setData = function() {
        for (var loop = 0; loop < this.ajaxData.result.length; loop++) {
            var newTr = $('<tr/>', {
                click: function(e) {
                    // 클릭이벤트
                    new RuleArticlePage(this.ajaxData.result.list[loop].no);
                    pageStack.push(this);
                }
            });
            var appendString = "<td>" + this.ajaxData.result[loop].no + "</td>";
            appendString += "<td>" + this.ajaxData.result[loop].title + "</td>";
            newTr.append(appendString);
            $(".articlelist table.list").append(newTr);
        }
        this.setPageData();
    }

    pageStack.push(this);
    this.draw();
}

RuleListPage.prototype = new ArticleListPage();

// // form, command, sendData 초기화 해야함
// function NoticeListPage() {
//     this.form =
//         '<div class="frame left articlelist afterapply">' +
//         '<div class="frametitle">' +
//         '<h1>공지사항</h1>' +
//         '<div class="underline puple"></div>' +
//         '</div>' +
//         '<table class="list">' +
//         '<tr class="tableheader">' +
//         '<th>번호</th>' +
//         '<th>제목</th>' +
//         '<th>날짜</th>' +
//         '</tr>' +
//         '</table>' +
//         '</div>' +
//         '<table class="page">' +
//         '<tr>' +
//         '</tr>' +
//         '</table>';
//     this.sendData = {
//         "page": this.page
//     };
//     this.type = "notice";
//     this.command = "416"; //command 몇인지 모름 !!!!!!!!!!!!!!!!!!!!
//     this.setData = function() {
//         for (var loop = 0; loop < this.ajaxData.result.length; loop++) {
//             var newTr = $('<tr/>', {
//                 click: function(e) {
//                     // 클릭이벤트
//                     new ServerPage(this.type, this.ajaxData.result.list[loop].no);
//                     pageStack.push(this);
//                 }
//             });
//             var appendString = "<td>" + this.ajaxData.result[loop].no + "</td>";
//             appendString += "<td>" + this.ajaxData.result[loop].title + "</td>";
//             // 아직 모름
//             appendString += "<td>" + this.ajaxData.result[loop].instructor + "</td>";
//             newTr.append(appendString);
//             $(".articlelist table.list").append(newTr);
//         }
//         this.setPageData();
//     }
// }
//
// NoticeListPage.prototype = new ArticleListPage();

// form, command, sendData 초기화 해야함
// 방과후 신청기간 가져와하 함 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
function AfterSchoolListPage() {
    this.form =
        '<div class="frame left articlelist afterapply">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>방과후 신청</h1>' +
        '<p>신청기간 2017.3.2 ~ 3.10</p>' + //방과후 신청기간 가져와야함!!!
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table class="list">' +
        '<tr class="tableheader">' +
        '<th>대상학년</th>' +
        '<th>과목</th>' +
        '<th>교사</td>' +
        '<th>날짜</th>' +
        '<th>인원</th>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '<table class="page">' +
        '<tr>' +
        '</tr>' +
        '</table>';
    this.sendData = JSON.stringify({
        "page": this.page
    });
    this.type = "afterschool";
    this.command = "416";
    this.setData = function() {
        for (var loop = 0; loop < this.ajaxData.result.length; loop++) {
            var newTr = $('<tr/>', {
                click: function(e) {
                    // 클릭이벤트
                    // 방과후 신청 페이지로 수정해야함 아마 ClientPage 일듯
                    new AfterSchoolArticlePage(this.ajaxData.result);
                    pageStack.push(this);
                }
            });
            var appendString = "<td>" + this.ajaxData.result[loop].target + "</td>";
            appendString += "<td>" + this.ajaxData.result[loop].title + "</td>";
            appendString += "<td>" + this.ajaxData.result[loop].instructor + "</td>";
            appendString += "<td>"
            var days = [];
            if (data.on_monday) {
                days.push("월");
            }
            if (data.on_tuesday) {
                days.push("화");
            }
            if (data.on_wednesday) {
                days.push("수");
            }
            if (data.on_saturday) {
                days.push("토");
            }
            for (var loop = 0; loop < days.length; loop++) {
                appendString += days[loop];
                if (loop != days.length) {
                    appendString += " / ";
                }
            }
            appendString += "</td>";
            // 방과후 최대 인원이 필요한데
            appendString += "<td>" + this.ajaxData.result[loop].personnel + "</td>";
            newTr.append(appendString);
            $(".articlelist table.list").append(newTr);
        }
        this.setPageData();
    }

    pageStack.push(this);
    this.draw();
}

AfterSchoolListPage.prototype = new ArticleListPage();

// form, command, sendData 초기화 해야함
function FacilityListPage() {
    this.form =
        '<div class="frame left articlelist">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>시설고장신고</h1>' +
        '<div class="input-container">' +
        '<input type="button" name="" value="글 쓰기">' +
        '</div>' +
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table class="list">' +
        '<tr class="tableheader">' +
        '<th>번호</th>' +
        '<th>제목</th>' +
        '<th>호실</th>' +
        '<th>작성일</th>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '<table class="page">' +
        '<tr>' +
        '</tr>' +
        '</table>';
    this.sendData = JSON.stringify({
        "page": this.page
    });
    this.type = "facility";
    this.command = "415";
    this.setData = function() {
        for (var loop = 0; loop < this.ajaxData.result.length; loop++) {
            var newTr = $('<tr/>', {
                click: function(e) {
                    // 클릭이벤트
                    // 새로운 AjaxPage 만들어서 생성해야 할듯
                    new FacilityArticlePage(this.ajaxData.result[loop].no);
                    pageStack.push(this);
                }
            });
            var appendString = "<td>" + this.ajaxData.result[loop].no + "</td>";
            appendString += "<td>" + this.ajaxData.result[loop].title;
            if (this.ajaxData[loop].has_result) {
                appendString += '<img src="../image/pupleCheck.png" alt="check image">';
            }
            appendString += +"</td>";
            appendString += "<td>" + this.ajaxData.result[loop].room + "</td>";
            appendString += "<td>" + this.ajaxData.result[loop].write_date + "</td>";

            newTr.append(appendString);
            $(".articlelist table.list").append(newTr);
        }
        this.setPageData();
    }

    pageStack.push(this);
    this.draw();
}

FacilityListPage.prototype = new ArticleListPage();

// 객체는 setData(), setEvent() 구현해야함
// 객체는 form, command, sendData 초기화 해야함
// function FacilityArticlePage(no) {
//     this.command = 428;
//     this.form =
//         '<div class="frame left articlecontainer question">' +
//         '<div class="frametitle">' +
//         '<h2></h2>' +
//         '<p class="date">date</p>' +
//         '<div class="underline puple">' +
//         '</div>' +
//         '</div>' +
//         '<div class="article">' +
//         '</div>' +
//         '<hr>' +
//         '</div>' +
//         '</div>';
//     this.sendData = {
//         "no": no
//     };
//
//     this.setData = function() {
//         // 답변이 있으면, 답변 form 추가
//         if (this.ajaxData.result.has_result) {
//             $("div.articlecontainer").append(
//                 '<div class="frame extention articlecontainer">' +
//                 '<div class="frametitle">' +
//                 '<h2>답변</h2>' +
//                 '<p class="date">' +
//                 this.ajaxData.result.result_date +
//                 '</p>' +
//                 '<div class="underline puple">' +
//                 '</div>' +
//                 '</div>' +
//                 '<div class="article">' +
//                 this.ajaxData.result.result +
//                 '</div>' +
//                 '<hr>'
//             );
//         }
//
//         // 질문 제목 셋팅
//         $("div.question div.frametitle h2").html = '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
//             this.ajaxData.result.title;
//
//         // 질문 내용 셋팅
//         $("div.question div.title").text(this.ajaxData.result.content);
//
//
//     }
//
//     // 이벤트가 없다
//     this.setEvent = function() {}
//
// }

// 객체는 setData(), setEvent() 구현해야함
// 객체는 form, command, sendData 초기화 해야함
// AfterSchoolArticlePage은 특별한 경우 같다. 어느 객체를 상속 받아야 할지 모르겠다. ㅠㅠ
// 우선, ajaxData속성이 필요할것 같으으로, AjaxPage를 상속받는데, getData()를 빈 함수로 초기화 해야겠다.
// 자신의 상태에 따라 신청, 취소 바꾸는 기능 구현해야함
function AfterSchoolArticlePage(data) {
    // AfterSchoolArticlePage만의 예외적인 경우다. 나중에 리팩토링 필요
    this.getData = function() {
        this.ajaxData = data;
    }
    this.form =
        '<div class="frame left afterapplypage">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>방과후 신청</h1>' +
        '<div class="underline blue"></div>' +
        '</div>' +
        '<div class="aftercontainer">' +
        '<div class="afterinfo">' +
        '<div class="header">' +
        '<p>강사</p>' +
        '<p>대상</p>' +
        '<p>장소</p>' +
        '<p>인원</p>' +
        '</div>' +
        '<h3 class="title">C를 우숩게 보지 마라</h3>' +
        '<p class="teacher">박정호</p>' +
        '<p class="grade" id="first">1학년</p>' +
        '<p class="grade" id="second">2학년</p>' +
        '<p class="grade" id="third">3학년</p>' +
        '<p id="class">2-2 교실</p>' +
        '<p id="member">5 / 20</p>' +
        '<div class="bar">' +
        '<div class="in bar">' +
        '</div>' +
        '</div>' +
        '<form class="afterform" action="index.html" method="post">' +
        '<button id = "apply" type="button" name="button">YES</button>' +
        // <button id = "cancle" type="button" name="button">NO</button>
        '</form>' +
        '</div>' +
        '<br>' +
        '</div>' +
        '</div>';

    this.setData = function() {
        // 해당 방과후를 이미 신청했는지 확인
        // 신청했으면 신청버튼을 없에고 신청취소버튼 생성
        for (var loop = 0; loop < myAfter.length; loop++) {
            if (myAfter[loop].no == data.no) {
                $(".afterapplypage .aftercontainer .afterinfo form.afterform").html(
                    '<form class="afterform" action="index.html" method="post">' +
                    '<button id = "cancle" type="button" name="button">NO</button>' +
                    '</form>'
                );
                break;
            }
        }

        // 교사
        $(".afterapplypage .aftercontainer .afterinfo p.teacher").text(this.ajaxData.instructor);
        // 장소
        $(".afterapplypage .aftercontainer .afterinfo p.class").text(this.ajaxData.place);
        // 신청자 수
        $(".afterapplypage .aftercontainer .afterinfo p.member").text(this.ajaxData.personnel);

        //  대상학년
        // 아직 형식을 몰라 구현불가
        // #first, #secoend, #third 에 각각 background-color 명시해주면 됨.
    }

    this.setEvent = function() {
        $(".afterapplypage form button#apply").click(function() {
            // 신청 메시지 보내야함
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "id": id,
                    "no": this.ajaxData.no
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "145");
                },
                success: function(data) {
                    alert("신청 되었습니다.");
                }
            });
            alert("YES");
        });
        $(".afterapplypage form button#cancle").click(function() {
            // 신청취소 메시지 보내야함
            alert("취소 되었습니다.");
        })
    }

    // 현재 방과후 신청상태확인
    this.stausCheck = function() {
        var result;
        $.ajax({
            url: "http://dsm2015.cafe24.com:10419",
            type: "POST",
            data: JSON.stringify({
                "id": id
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", "436");
            },
            success: function(data) {
                result = data;
            }
        });
        result = JSON.parse(result);
        return result.result;
    }

    pageStack.push(this);
    this.draw();
}

AfterSchoolListPage.prototype = new AjaxPage();

// 객체는 setData(), setEvent() 구현해야함
// 객체는 form, command, sendData 초기화 해야함
// 구현 미완료 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// 서버에서는 클래스 맵데이터를 가지고 있지 않음

function ExtentionApplyPage() {
    this.form =
        '<div class="frame left extentionapply">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>연장신청</h1>' +
        '<div class="underline blue"></div>' +
        '</div>' +
        '<div class="selecter-container">' +
        '<div class="class-selecter-div">' +
        '<label for="class-select">가온실</label>' +
        '<select id="class-select">' +
        '<option selected="selected" value="1">가온실</option>' +
        '<option value="2">나온실</option>' +
        '<option value="3">다온실</option>' +
        '<option value="4">라온실</option>' +
        '<option value="5">3층 독서실</option>' +
        '<option value="4">4층 독서실</option>' +
        '<option value="4">5층 자유구역</option>' +
        '</select>' +
        '</div>' +
        '</div>' +
        '<div class="seatcontainer">' +
        '</div>' +
        '</div>';
    this.command = "432";
    // 연장학습 장소의 고유값
    this.class = 1;
    this.sendData = JSON.stringify({
        "class": this.class
    });

    this.pushData = function() {
        var trancedSeatData = [];
        // 받은 데이터를 그리기위한 배열로 만들기위한 전 단계
        for (var loop = 0; loop < this.ajaxData.length; loop++) {

            if (this.ajaxData[loop].name !== null) {
                trancedSeatData.push(this.ajaxData[loop].name);
            } else {
                trancedSeatData.push(this.ajaxData[loop].seat);
            }
        }
        // 가온실이면
        if (this.class == 1) {
            return [
                [0, 0, 0, 0, 0, 0, 0],
                [0, trancedSeatData[0], trancedSeatData[1], 0, trancedSeatData[2], trancedSeatData[3], 0],
                [0, trancedSeatData[4], trancedSeatData[5], 0, trancedSeatData[6], trancedSeatData[7], 0],
                [0, trancedSeatData[8], trancedSeatData[9], 0, trancedSeatData[10], trancedSeatData[11], 0],
                [0, trancedSeatData[12], trancedSeatData[13], 0, trancedSeatData[14], trancedSeatData[15], 0],
                [0, trancedSeatData[16], trancedSeatData[17], 0, trancedSeatData[18], trancedSeatData[19], 0],
                [0, 0, 0, 0, 0, 0, 0]
            ];
        } else if (this.class == 2) { //나온실이면
            return [
                [0, 0, 0, 0, 0, 0, 0],
                [0, trancedSeatData[0], trancedSeatData[1], trancedSeatData[2], trancedSeatData[3], trancedSeatData[4], 0],
                [0, trancedSeatData[5], 0, trancedSeatData[6], 0, trancedSeatData[7], 0],
                [0, trancedSeatData[8], 0, trancedSeatData[9], 0, trancedSeatData[10], 0],
                [0, trancedSeatData[11], 0, trancedSeatData[12], 0, trancedSeatData[13], 0],
                [0, trancedSeatData[14], 0, trancedSeatData[15], 0, trancedSeatData[16], 0],
                [0, 0, 0, 0, 0, 0, 0]
            ]
        } else if (this.class == 3) { //다온실이면
            return [
                [0, 0, 0, 0, 0, 0, 0],
                [0, trancedSeatData[0], trancedSeatData[1], trancedSeatData[2], trancedSeatData[3], trancedSeatData[4], 0],
                [0, 0, 0, 0, 0, 0, 0],
                [0, trancedSeatData[5], trancedSeatData[6], trancedSeatData[7], trancedSeatData[8], trancedSeatData[9], 0],
                [0, 0, 0, 0, 0, 0, 0],
                [0, trancedSeatData[10], trancedSeatData[11], trancedSeatData[12], trancedSeatData[13], trancedSeatData[14], 0],
                [0, 0, 0, 0, 0, 0, 0],
                [0, trancedSeatData[15], trancedSeatData[16], trancedSeatData[17], trancedSeatData[18], trancedSeatData[19], 0],
                [0, 0, 0, 0, 0, 0, 0],
                [0, trancedSeatData[20], trancedSeatData[21], trancedSeatData[22], trancedSeatData[23], trancedSeatData[24], 0],
                [0, 0, 0, 0, 0, 0, 0]
            ]
        } else if (this.class == 4) { //라온실이면
            return []
        } else if (this.class == 5) { //3층 독서실이면
            return []
        } else if (this.class == 6) { //4층 독서실이면
            return []
        } else if (this.class == 7) { //5층 이면
            return []
        }
    }

    this.setData = function() {
        // seatcontainer 초기화
        $(".seatcontainer").html("");
        // 받은배열을 인자로 넘겨주고, 변환된 2차원 배열을 받음.
        var mapData = this.pushData(this.ajaxData);
        var selected;
        for (var loop = 0; loop < mapData.length; loop++) {
            for (var innerLoop = 0; innerLoop < mapData[0].length; innerLoop++) {
                // draw circle
                // 자리가 벽이 아니면
                if (mapData[loop][innerLoop] != 0) {
                    // 자리가 이미 선택됐으면
                    if (typeof(mapData[loop][innerLoop]) == "string") {
                        $('<div/>', {
                            "class": "seat",
                            css: {
                                "background": "rgb(134,193,233)"
                            },
                            text: mapData[loop][innerLoop]
                        }).appendTo(".seatcontainer");
                    }
                    // 자리가 선택가능하면
                    else {
                        var newSeat = $('<div/>', {
                            "class": "seat",
                            css: {
                                "border": borderSize + " solid rgb(134,193,233)"
                            },
                        });
                        newSeat.appendTo(".seatcontainer");
                        newSeat.click(function() {
                            if (selected !== undefined) {
                                console.log(selected);
                                selected.css({
                                    "background": "white",
                                    "border": borderSize + " solid rgb(134,193,233)"
                                });
                                selected.text("");
                            }
                            console.log(selected);
                            console.log($(this).get(0));
                            if (selected !== undefined && selected.get(0) == this) {
                                console.log("ASD");
                                selected.css({
                                    "border": "0 solid black",
                                    "background": "rgb(231,160,153)"
                                })
                                selected.text("신청됨");
                                selected = $(this);
                                // 신청하는 ajax작성 !~!~!@#$~@#$~@#~$~@#$~
                                // 연장신청
                                this.extentionapply(mapData[loop][innerLoop]);
                                this.reload();
                            } else {
                                console.log("wht");
                                selected = $(this);
                                $(this).css({
                                    "border": "0 solid black",
                                    "background": "#FFD180"
                                });
                                $(this).text("신청?");
                            }
                        })
                    }
                } else {
                    $('<div/>', {
                        "class": "seat",
                        css: {
                            "border": borderSize + " solid #757575"
                        }
                    }).appendTo(".seatcontainer");
                }
            }
            $('</br>').appendTo(".seatcontainer");
        }

        // 자신의 연장정보 받아오는 기능
        // 신청이 되어있으면 취소버튼 활성와 아니면 숨김
        var myInfo = this.getMyExtentionData();
        if (myInfo.class == null) {
            $(".extentionapply div.selecter-container input.apply-cancle").css({
                "display": "none"
            })
        }

    }

    this.extentionapply = function(seat) {
        $.ajax({
            url: "http://dsm2015.cafe24.com:10419",
            type: "POST",
            data: JSON.stringify({
                "id": id,
                "class": this.class,
                "seat": seat,
                "name": name
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", "510");
            },
            success: function(data) {
                result = JSON.parse(data).result;
            }
        });
    }

    this.getMyExtentionData = function() {
        var result;
        $.ajax({
            url: "http://dsm2015.cafe24.com:10419",
            type: "POST",
            data: JSON.stringify({
                "id": id,
                "no": this.ajaxData.no
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", "431");
            },
            success: function(data) {
                result = JSON.parse(data).result;
            }
        });
        return result;
    }

    this.setEvent = function() {
        $(".extentionapply div.selecter-container input.apply-cancle").click(function() {
            // 신청취소 ajax
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "id": id,
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "331");
                },
                success: function(data) {
                    alert("취소 완료.");
                }
            });
        })

        $(".extentionapply div.class-selecter-div select#class-select").change(function() {
            //change event;
            var select_name = $(this).children("option:selected").text();
            $(this).siblings("label").text(select_name);
            this.class = $(this).children("option:selected").val();
            // 리로드하늠ㄴ 함수 만들어야댐 망
        })
    }

    // 교실바꿔서 리로드하는 함수
    this.reload = function() {
        this.sendData = JSON.stringify({
            "class": this.class
        });
        this.getData();
        this.setData();

    }
    pageStack.push(this);
    this.draw();
}
ExtentionApplyPage.prototype = new AjaxPage();

function GoHomeApplyPage() {
    this.form =
        '<div class="frame calendar">' +
        '<h1>귀가 신청</h1>' +
        '<div class="underline"></div>' +
        '<table id="calendar" cellspacing="0">' +
        '<thead>' +
        '<tr>' +
        '<td id="prev_month"><i class="fa fa-toggle-left" style="font-size: 28px;"></i></td>' +
        '<td id="month" colspan="5" style="font-size: 28px; font-weight: bold;"></td>' +
        '<td id="next_month"><i class="fa fa-toggle-right" style="font-size: 28px;"></i></td>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        '<tr class="days">' +
        '<td>Sun</td>' +
        '<td>Mon</td>' +
        '<td>Tue</td>' +
        '<td>Wed</td>' +
        '<td>Thu</td>' +
        '<td>Fri</td>' +
        '<td>Sat</td>' +
        '</tr>' +
        '<tr id="first_week" class="weeks">' +
        '<td class="sun"></td>' +
        '<td class="mon"></td>' +
        '<td class="tue"></td>' +
        '<td class="wed"></td>' +
        '<td class="thu"></td>' +
        '<td class="fri go_home"></td>' +
        '<td class="sat"></td>' +
        '</tr>' +
        '<tr id="second_week" class="weeks">' +
        '<td class="sun go_dom"></td>' +
        '<td class="mon"></td>' +
        '<td class="tue"></td>' +
        '<td class="wed"></td>' +
        '<td class="thu"></td>' +
        '<td class="fri go_home"></td>' +
        '<td class="sat go_dom"></td>' +
        '</tr>' +
        '<tr id="third_week" class="weeks">' +
        '<td class="sun"></td>' +
        '<td class="mon"></td>' +
        '<td class="tue"></td>' +
        '<td class="wed"></td>' +
        '<td class="thu"></td>' +
        '<td class="fri"></td>' +
        '<td class="sat go_home"></td>' +
        '</tr>' +
        '<tr id="fourth_week" class="weeks">' +
        '<td class="sun go_dom"></td>' +
        '<td class="mon"></td>' +
        '<td class="tue"></td>' +
        '<td class="wed"></td>' +
        '<td class="thu"></td>' +
        '<td class="fri go_home_duty"></td>' +
        '<td class="sat"></td>' +
        '</tr>' +
        '<tr id="fifth_week" class="weeks">' +
        '<td class="sun go_dom"></td>' +
        '<td class="mon"></td>' +
        '<td class="tue"></td>' +
        '<td class="wed"></td>' +
        '<td class="thu"></td>' +
        '<td class="fri"></td>' +
        '<td class="sat"></td>' +
        '</tr>' +
        '</tbody>' +
        '</table>' +
        '</div>' +
        '<div class="frame go_home_apply">' +
        '<form id="apply_form">' +
        '<fieldset id="apply_field">' +
        '<legend>잔류신청</legend>' +
        '<table width="100%">' +
        '<tr>' +
        '<td width="45%">' +
        '<label for="stay-1">&nbsp&nbsp&nbsp잔&nbsp&nbsp류&nbsp&nbsp&nbsp</label>' +
        '<input type="radio" name="stay" id="stay-1" value="1" required><br><br>' +
        '<label for="stay-2">금요 귀가</label>' +
        '<input type="radio" name="stay" id="stay-2" value="2"><br><br>' +
        '<label for="stay-3">토요 귀가</label>' +
        '<input type="radio" name="stay" id="stay-3" value="3"><br><br>' +
        '<label for="stay-4">토요 귀사</label>' +
        '<input type="radio" name="stay" id="stay-4" value="4"><br><br>' +
        '</td>' +
        '<td>' +
        '<div style="margin: 20%; margin-left: 0;">' +
        '<span class="color" style="background-color: rgb(52, 219, 94);"></span>&nbsp&nbsp귀가' +
        '</div>' +
        '<div style="margin: 20%; margin-left: 0;">' +
        '<span class="color" style="background-color: rgb(255, 151, 49);"></span>&nbsp&nbsp귀사' +
        '</div>' +
        '<div style="margin: 20%; margin-left: 0;">' +
        '<span class="color" style="background-color: rgb(255, 238, 34);"></span>&nbsp&nbsp의무귀가' +
        '</div>' +
        '</td>' +
        '</tr>' +
        '<tr>' +
        '<td colspan="2"><input type="text" name="date" id="date" size="15"><div class="ui-button ui-widget ui-corner-all" id="apply_submit">신청</div></td>' +
        '</tr>' +
        '</table>' +
        '</fieldset>' +
        '</form>' +
        '</div>';

    this.command = {
        load: "433",
        apply: "502"
    }
    this.ajaxData = {
        "value": value
    };
    this.sendData = JSON.stringify({
        "id": id,
        "week": week,
        "value": value
    });

    // 필요한 Data를 받아옴
    this.getData = function() {
        $.ajax({
            url: "http://dsm2015.cafe24.com",
            type: "POST",
            data: JSON.stringify({
                "id": this.sendData.id,
                "week": this.sendData.week
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", this.command.load);
            },
            success: function(data) {
                this.ajaxData = JSON.parse(data);
            }
        });
    }

    // 신청
    this.applyData = function() {
        $.ajax({
            url: "http://dsm2015.cafe24.com",
            type: "POST",
            data: this.sendData,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", this.command.apply);
            },
            success: function(data) {
                alert("신청되었습니다.");
            }
        });
    }

    // 받아온 Data를 html에 설정
    this.setData;

    // html에 이벤트 설정
    this.setEvent = function() {
        var valueArray = new Array();
        var currentDate = new Date();
        var currentYear = currentDate.getFullYear();
        var currentMonth = currentDate.getMonth() + 1;
        var lastDay = noofdays(currentYear, currentMonth);
        var newDate = new Date(currentYear, currentMonth - 1, 1);

        $('#month').text(currentYear + '.' + currentMonth); //달력 년, 월 표시

        drawCalendar(newDate, lastDay); //처음 달력 날짜 표시

        //이전 달
        $('#prev_month').click(function() {
            if (currentMonth == 1) {
                currentYear--;
                currentMonth = 12;
            } else {
                currentMonth--;
            }
            $('#month').text(currentYear + '.' + currentMonth);
            newDate = new Date(currentYear, currentMonth - 1, 1);
            lastDay = noofdays(currentMonth, currentYear);
            clearCalendar();
            drawCalendar(newDate, lastDay);
        });

        //다음 달
        $('#next_month').click(function() {
            if (currentMonth == 12) {
                currentYear++;
                currentMonth = 1;
            } else {
                currentMonth++;
            }
            $('#month').text(currentYear + '.' + currentMonth);
            newDate = new Date(currentYear, currentMonth - 1, 1);
            lastDay = noofdays(currentMonth, currentYear);
            clearCalendar();
            drawCalendar(newDate, lastDay);
        });

        //달의 말일 구하기
        function noofdays(year, month) {
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

        //달력 그리기
        function drawCalendar(date, lastDay) {
            switch (date.getDay()) {
                case 0:
                    for (var i = 0; i < lastDay; i++) {
                        var idx = 10 + i; //일요일
                        $('#calendar td:eq(' + idx + ')').text(i + 1);
                    }
                    break;
                case 1:
                    for (var i = 0; i < lastDay; i++) {
                        var idx = 11 + i; //월요일
                        $('#calendar td:eq(' + idx + ')').text(i + 1);
                    }
                    break;
                case 2:
                    for (var i = 0; i < lastDay; i++) {
                        var idx = 12 + i; //화요일
                        $('#calendar td:eq(' + idx + ')').text(i + 1);
                    }
                    break;
                case 3:
                    for (var i = 0; i < lastDay; i++) {
                        var idx = 13 + i; //수요일
                        $('#calendar td:eq(' + idx + ')').text(i + 1);
                    }
                    break;
                case 4:
                    for (var i = 0; i < lastDay; i++) {
                        var idx = 14 + i; //목요일
                        $('#calendar td:eq(' + idx + ')').text(i + 1);
                    }
                    break;
                case 5:
                    for (var i = 0; i < lastDay; i++) {
                        var idx = 15 + i; //금요일
                        $('#calendar td:eq(' + idx + ')').text(i + 1);
                    }
                    break;
                case 6:
                    for (var i = 0; i < lastDay; i++) {
                        var idx = 16 + i; //토요일
                        $('#calendar td:eq(' + idx + ')').text(i + 1);
                    }
                    break;
            }
            drawPrev();
        }

        //달력 초기화
        function clearCalendar() {
            for (var i = 0; i < 35; i++) {
                $('#calendar td:eq(' + (i + 10) + ')').text("");
                $('#calendar tbody tr').css("background-color", "white");
            }
        }

        $('#first_week').click(function() {
            $('#calendar tbody tr').css("background-color", "white");
            $('#first_week').css("background-color", "rgb(197, 197, 197)");
            $('#date').val(currentYear + '-' + currentMonth + '-' + '01');
        });

        $('#second_week').click(function() {
            $('#calendar tbody tr').css("background-color", "white");
            $('#second_week').css("background-color", "rgb(197, 197, 197)");
            $('#date').val(currentYear + '-' + currentMonth + '-' + '02');
        });

        $('#third_week').click(function() {
            $('#calendar tbody tr').css("background-color", "white");
            $('#third_week').css("background-color", "rgb(197, 197, 197)");
            $('#date').val(currentYear + '-' + currentMonth + '-' + '03');
        });

        $('#fourth_week').click(function() {
            $('#calendar tbody tr').css("background-color", "white");
            $('#fourth_week').css("background-color", "rgb(197, 197, 197)");
            $('#date').val(currentYear + '-' + currentMonth + '-' + '04');
        });

        $('#fifth_week').click(function() {
            alert("다음 달 1주로 신청해주세요.");
        });

        //********************이전 데이터 표시*********************
        function dateToString(week) {
            return newDate.getFullYear().toString() + "-" + (newDate.getMonth + 1).toString() + "-0" + week.toString();
        }

        function loadPrev() { //valueArray에 해당 달의 신청 상태 저장
            for (var i = 1; i <= 4; i++) {
                this.sendData.week = dateToString(i);
                this.getData();
                valueArray.push(this.ajaxData);
            }
        }

        function drawPrev() {
            loadPrev();
            for (i = 1; i <= valueArray.length; i++) {
                if (valueArray[i] == 4) { //잔류
                } else if (valueArray[i] == 1) {
                    $('tr:eq(' + (i + 1) + ') .fri').attr('class', 'fri go_home');
                    $('tr:eq(' + (i + 2) + ') .sun').attr('class', 'sun go_dom');
                } else if (valueArray[i] == 2) {
                    $('tr:eq(' + (i + 1) + ') .sat').attr('class', 'sat go_home');
                    $('tr:eq(' + (i + 2) + ') .sun').attr('class', 'sun go_dom');
                } else if (valueArray[i] == 3) {
                    $('tr:eq(' + (i + 1) + ') .fri').attr('class', 'fri go_home');
                    $('tr:eq(' + (i + 1) + ') .sat').attr('class', 'sat go_dom');

                }
                valueArray = new Array();
            }

            //***********************신청*********************
            $('#apply_form input[type="radio"]').checkboxradio();

            $('#date').keydown(function(e) {
                e.preventDefault();
            });

            $('#apply_submit').on('click', function() {
                this.sendData.week = $('#date').val();
                this.sendData.value = $(':input:radio[name="stay"]:checked').val();
                this.applyData();
            });

        };
    }
}
GoHomeApplyPage.prototype = new AjaxPage();

// 자식 객체는 setEvent() 구현해야함
// 자식 객체는 form 초기화 해야함
function PointApplyPage() {
    this.form =
        '<div class="frame left pointapply">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>상점신청</h1>' +
        '<div class="underline blue"></div>' +
        '</div>' +
        '<div class="selecter">' +
        '<div class="selectmenu">' +
        '<p>상점신청</p>' +
        '</div>' +
        '<div class="selectmenu">' +
        '<p>상점추천</p>' +
        '</div>' +
        '</div>' +
        '<form class="individual" action="index.html" method="post">' +
        '<input type="text" name="reason" placeholder="이유를 입력해 주세요" value="">' +
        '<button type="button" name="button">피융!</button>' +
        '</form>' +
        '<form class="group individual" action="index.html" method="post">' +
        '<input type="text" name="reason" placeholder="이유를 입력해 주세요" value="">' +
        '<input type="text" name="person" placeholder="추천자를 입력해 주세요(피추천자)" value="">' +
        '<button type="button" name="button">피융!</button>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // 추천 -> 신청 전환
        $(".pointapply .selecter .selectmenu:nth-child(1)").click(function() {
                $(".pointapply .selecter .selectmenu:nth-child(1)").css({
                    backgroundColor: "rgb(134, 193, 233)",
                    border: "1 px solid rgb(134, 193, 233)",
                    borderRadius: "5px 0px 0px 5px",
                    color: "white"
                });
                $(".pointapply .selecter .selectmenu:nth-child(2)").css({
                    borderRadius: "0px 5px 5px 0px",
                    border: "1px solid rgb(134, 193, 233)",
                    color: "black",
                    backgroundColor: "white"
                });
                $(".pointapply .individual").css({
                    display: "block"
                });
                $(".pointapply .group").css({
                    display: "none"
                });
            })
            // 신청 -> 추천 전환
        $(".pointapply .selecter .selectmenu:nth-child(2)").click(function() {
            $(".pointapply .selecter .selectmenu:nth-child(2)").css({
                backgroundColor: "rgb(134, 193, 233)",
                border: "1 px solid rgb(134, 193, 233)",
                borderRadius: "0px 5px 5px 0px",
                color: "white"
            });
            $(".pointapply .selecter .selectmenu:nth-child(1)").css({
                borderRadius: "5px 0px 0px 5px",
                border: "1px solid rgb(134, 193, 233)",
                color: "black",
                backgroundColor: "white"
            });
            $(".pointapply .individual").css({
                display: "none"
            });
            $(".pointapply .group").css({
                display: "block"
            });
        })

        // 신청탭 신청버튼 신청 이벤트
        $(".pointapply form:nth-child(3) button").click(function() {
            var reason = $(".pointapply .individual input").val();
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: {
                    "id": id,
                    "content": reason
                },
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "144");
                },
                success: function() {
                    alert("신청되었습니다.");
                }
            });
        });

        // 추천탭 추천버튼 추천 이벤트
        $(".pointapply form:nth-child(4) button").click(function() {
            var reason = $(".pointapply .group input:nth-child(1)").val();
            var person = $(".pointapply .group input:nth-child(2)").val();
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "id": id,
                    "content": reason,
                    "targer": person
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "144");
                },
                success: function() {
                    alert("신청되었습니다.");
                }
            });
        });
    }
    this.draw();
    pageStack.push(this);
}
PointApplyPage.prototype = new NonAjaxPage();

// 자식 객체는 setEvent() 구현해야함
// 자식 객체는 form 초기화 해야함
function GoOutApplyPage() {
    this.sendData;
    this.form =
        '<div class="frame left articlelist">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>외출신청</h1>' +
        '<div class="underline"></div>' +
        '</div>' +
        '<form id="apply_form">' +
        '<input id="day" type="hidden" name="day">' +
        '<table id="select_table">' +
        '<tr>' +
        '<td id="selection">신청</td>' +
        '</tr>' +
        '<tr>' +
        '<td id="sat" hidden>토요일</td>' +
        '</tr>' +
        '<tr>' +
        '<td id="sun" hidden>일요일</td>' +
        '</tr>' +
        '</table>' +
        '</form>' +
        '<div id="submit_button">신청</div>' +
        '</div>';

    this.setEvent = function() {
        clickable = true;

        $('#selection').on("click", function() {
            if (clickable) {
                $("#select_table td:eq(1), td:eq(2)").show();
                clickable = false;

                $("#sat").on("click", function() {
                    $('#selection').text('토요일')
                    $('#day').attr('value', '토요일');
                    this.sendData = 'sat';
                    $("#select_table td:eq(1), td:eq(2)").hide();
                    clickable = true;
                });

                $('#sun').on("click", function() {
                    $('#selection').text('일요일');
                    $('#day').attr('value', '일요일');
                    $('#goout_data').text('sun');
                    this.sendData = 'sun';
                    $("#select_table td:eq(1), td:eq(2)").hide();
                    clickable = true;
                });

            } else {
                $("#select_table td:eq(1), td:eq(2)").hide();
                clickable = true;
            }
        });

        $('#submit_button').on('click', function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "id": id,
                    "day": this.sendData,
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", 503);
                },
                success: function() {
                    alert('신청되었습니다.');
                }
            });

        });

    }

    pageStack.push(this);
    this.draw();
}
GoOutApplyPage.prototype = new NonAjaxPage();

// 객체는 setData(), setEvent() 구현해야함
// 객체는 form, command, sendData 초기화 해야함
function Mypage() {
    this.form =
        '<div class="frame left mypage">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="prevPage()">' +
        '<h1>마이페이지</h1>' +
        '<div class="underline red"></div>' +
        '</div>' +
        '<div class="info applyinfo">' +
        '<h3>신청정보</h3>' +
        '<table>' +
        '<tr>' +
        '<td>귀가</td>' +
        '<td>금요귀가</td>' +
        '</tr>' +
        '<tr>' +
        '<td>연장</td>' +
        '<td>가온실 1, 1</td>' +
        '</tr>' +
        '<tr>' +
        '<td>방과후</td>' +
        '<td>월 : 컴활 / 화 : 컴활 / 수 : JS</td>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '<div class="info pointinfo">' +
        '<h3>상벌점정보</h3>' +
        '<table>' +
        '<tr>' +
        '<td>상점</td>' +
        '<td>100 점</td>' +
        '</tr>' +
        '<tr>' +
        '<td>벌점</td>' +
        '<td>110 점</td>' +
        '</tr>' +
        '<tr>' +
        '<td>총점</td>' +
        '<td>-10 점</td>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '</div>';
    this.command = "401";
    this.sendData = JSON.stringify({
        "id": id
    });
    this.setData = function() {
        // 아직 마이페이지항목이 제대로 정의되지 않음.
    };
    this.setEvent = function() {};

    pageStack.push(this);
    this.draw();
}
Mypage.prototype = new AjaxPage();

// qna, rule, facility, notice 해당
function NoticeArticlePage(no) {
    this.type = "notice";
    this.no = no;
    this.action = "view";
    this.sendData = JSON.stringify({
        "no": this.no
    });
    // 이벤트 등록할 것 없음
    this.setEvent = function() {
        // 수정 삭제 이벤트 등록
        // 수정 이벤트 발생시, NoticeModifyPage() 생성
        $(".frametitle .input-container input.modify").click(function() {
            new NoticeModifyPage(this.no);

        })

        // 삭제 이벤트 발생시 삭제 요청 보내기
        $(".frametitle .input-container input.delete").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/notice/delete",
                type: "POST",
                data: JSON.stringify({
                    "no": no
                }),
                success: function() {
                    alert('삭제되었습니다.');
                    prevPage();
                }
            });
        })


    }

    pageStack.push(this);
    this.draw();
}

NoticeArticlePage.prototype = new ServerPage();


function FacilityArticlePage(no) {
    this.type = "facility";
    this.no = no;
    this.action = "view";

    this.setEvent = function() {
        // 학생용 이벤트
        // 수정이벤트 등록
        $(".question .frametitle .input-container input.modify").click(function() {
            new FacilityModifyPage(this.no);

        })

        // 삭제 이벤트 등록
        $(".question .frametitle .input-container input.delete").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "no": this.no
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", 316);
                },
                success: function() {
                    alert('삭제되었습니다.');
                    new FacilityListPage();
                }
            });
        })

        // 관리자용 이벤트
        // 글 수정, 삭제
        $(".answer .frametitle .input-container input.modify").click(function() {
            new FacilityResultModifyPage();
        });

        $(".answer .frametitle .input-container input.delete").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/facility/delete",
                type: "POST",
                data: JSON.stringify({
                    "no": no
                }),
                success: function() {
                    alert('삭제되었습니다.');
                    prevPage();
                }
            });
        })


    }

    this.draw();
    pageStack.push(this);
}

FacilityArticlePage.prototype = new ServerPage();


function RuleArticlePage(no) {
    this.type = "rule"
    this.no = no;
    this.action = "view";
    // 수정삭제 이벤트 등록해야함
    this.setEvent = function() {
        // 수정이벤트 등록
        $(".frametitle .input-container input.modify").click(function() {
            new RuleModifyPage(this.no);
        })

        // 삭제 이벤트 등록
        $(".frametitle .input-container input.delete").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/rule/delete",
                type: "POST",
                data: JSON.stringify({
                    "no": no
                }),
                success: function() {
                    alert('삭제되었습니다.');
                    prevPage();
                }
            });
        })
    }

    this.draw();
    pageStack.push(this);
}

RuleArticlePage.prototype = new ServerPage();

function QnaArticlePage(no) {
    this.type = "qna";
    this.no = no;
    this.action = "view";
    // 이벤트 등록할 것 없음
    this.setEvent = function() {
        // 질문수정, 질문삭제 이벤트 등록
        $(".articlecontainer .frametitle div.input-container input.modify").click(function() {
            // 질문 수정 ajax
            // qna질문 수정 페이지 로드 후, 질문 수정 페이지에서, 수정 버튼을 누르면, ajax발사후, QnaArticlePage리로드
            // 취소버튼 누르면 그냥 QnaArticlePage리로드
            // title question_content writer
            new QnaQuestionModifyPage(this.no, $(".question div.frametitle h1").text(), $(".question div.article").html());
        });
        $(".articlecontainer .frametitle div.input-container input.delete").click(function() {
            // 질문 삭제 ajax
            // 질문 삭제 후 전 페이지로 돌아가기
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "no": no
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", 312);
                },
                success: function() {
                    alert('삭제되었습니다.');
                    prevPage();
                }
            });
        })

        // 관리자에서만 동작
        // 답변수정, 답변삭제 이벤트 등록
        $(".answer .frametitle div.input-container input.modify").click(function() {
            // 답변 수정 ajax
            new QnaAnswerModifyPage();
        });

        // 관리자에서만 동작
        $(".answer .frametitle div.input-container input.delete").click(function() {
            // 답변 삭제 ajax
            $.ajax({
                url: "http://dsm2015.cafe24.com/delete/qna",
                type: "POST",
                data: JSON.stringify({
                    "no": no
                }),
                success: function() {
                    alert('삭제되었습니다.');
                    this.draw();
                }
            });
        })

        // 댓글 전송 이벤트
        $(".commentinput table tr td input[type='button']").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "no": this.no,
                    "content": content,
                    "writer": name
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "114");
                },
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    this.draw();
                }
            });
        })

        var commentArr = $(".comment table tr");
        for (var loop = 0; loop < commentArr.length; loop++) {
            // 댓글 수정
            $(commentArr[loop]).children("td").children("span.comment-modify").click(function() {
                // ajax전에 댓글 수정 페이지를 보여줘야 함 ㅠㅠ
                // 댓글 수정창 load
                $(commentArr[loop]).children("td").eq(1).html(
                    '<input class="comment-modify-input" type="text" name="" value="' +
                    $(commentArr[loop]).children("td").eq(1).text() + '">');

                // 댓글 수정 완료버튼 load
                $(commentArr[loop]).children("td").eq(2).html(
                    '<input class="comment-modify-push" type="button" name="" value="퓨슝!">');

                // 수정 완료 ajax 이벤트 등록
                $(commentArr[loop]).children("td").eq(2).children("input").click(function() {
                    $.ajax({
                        url: "http://dsm2015.cafe24.com:10419",
                        type: "POST",
                        data: JSON.stringify({
                            "id": id,
                            "content": content
                        }),
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                            xhr.setRequestHeader("command", "214");
                        },
                        success: function() {
                            // 다시 qna 글 페이지를 로드해야 할듯
                            this.draw();
                        }
                    });
                })

            })

            // 댓글 삭제
            $(commentArr[loop]).children("td").children("span.comment-delete").click(function() {
                // 댓글 삭제
                $.ajax({
                    url: "http://dsm2015.cafe24.com:10419",
                    type: "POST",
                    data: JSON.stringify({
                        "no": $(commentArr[loop]).children("td.hide-no").text(),
                    }),
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                        xhr.setRequestHeader("command", "314");
                    },
                    success: function() {
                        // 다시 qna 글 페이지를 로드해야 할듯
                        this.draw();
                    }
                });
            })
        }
    }

    pageStack.push(this);
    this.draw();
}

QnaArticlePage.prototype = new ServerPage();




// title question_content writer
function QnaQuestionModifyPage(no, title, content) {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1><input class="border-less-input title-input" type="text" name="title" placeholder="제목 입력" /></h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea',
            init_instance_callback: function(editor) {
                console.log("Editor: " + editor.id + " is now initialized.");
                tinymce.activeEditor.setContent(content);
            }
        });

        // title input에 기존 title set
        console.log(title);
        $(".frametitle h1 input.title-input").val(title);

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "title": $(".frametitle h1 input.title-input").val(),
                    "question_content": tinymce.get('content').getContent(),
                    "writer": name
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "212");
                },
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new QnaArticlePage("qna", no);
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }

    this.draw();
    pageStack.push();
}

QnaQuestionModifyPage.prototype = new NonAjaxPage();



function QnaQuestionWritePage() {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1><input class="border-less-input title-input" type="text" name="title" placeholder="제목 입력" /></h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea'
        });

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "title": $(".frametitle h1 input.title-input").val(),
                    "question_content": tinymce.get('content').getContent(),
                    "writer": name
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "212");
                },
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new QnaArticlePage("qna", no);
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }

    this.draw();
    pageStack.push();
}

QnaQuestionWritePage.prototype = new NonAjaxPage();

function QnaAnswerWritePage(no) {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1>Answer</h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea'
        });

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/qna",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "content": tinymce.get('content').getContent(),
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new QnaArticlePage("qna", no);
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }
    this.draw();
    pageStack.push();
}

QnaAnswerWritePage.prototype = new NonAjaxPage();

// title content room writer
function FacilityQuestionWritePage() {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1><input class="border-less-input title-input" type="text" name="title" placeholder="제목 입력" />' +
        '<input class="border-less-input title-input-room" type="text" name="title" placeholder="호실 입력" /></h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea'
        });

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "title": $(".frametitle h1 input.title-input").val(),
                    "room": $(".frametitle h1 input.title-input-room").val(),
                    "content": tinymce.get('content').getContent(),
                    "writer": name
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "117");
                },
                success: function() {
                    // 다시 facility 리스트 페이지를 로드해야 할듯
                    new FacilityListPage();
                }
            });
        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }

    this.draw();
    pageStack.push();
}

FacilityQuestionWritePage.prototype = new NonAjaxPage();

function FacilityResultWritePage(no) {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1>Result</h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea'
        });

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/facility",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "content": tinymce.get('content').getContent(),
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new FacilityArticlePage("facility", no);
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }
    this.draw();
    pageStack.push();
}

FacilityResultWritePage.prototype = new NonAjaxPage();

function NoticeWritePage() {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1><input class="border-less-input title-input" type="text" name="title" placeholder="제목 입력" /></h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea'
        });

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/notice",
                type: "POST",
                data: JSON.stringify({
                    "title": $(".articlecontainer .frametitle input.title-input").val(),
                    "content": tinymce.get('content').getContent()
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new NoticeListPage();
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }
    this.draw();
    pageStack.push();
}

NoticeWritePage.prototype = new NonAjaxPage();

function RuleWritePage() {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1><input class="border-less-input title-input" type="text" name="title" placeholder="제목 입력" /></h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea'
        });

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/rule",
                type: "POST",
                data: JSON.stringify({
                    "title": $(".articlecontainer .frametitle input.title-input").val(),
                    "content": tinymce.get('content').getContent()
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new RuleListPage();
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }
    this.draw();
    pageStack.push();
}

RuleWritePage.prototype = new NonAjaxPage();



// n t c


function FacilityQuestionModifyPage(no, title, content) {
    this.form =
        '<div class="frame left articlecontainer">' +
        '<form action="/admin/writepost" method="post" id="form">' +
        '<div class="frametitle">' +
        '<img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">' +
        '<h1><input class="border-less-input title-input" type="text" name="title" placeholder="제목 입력" /></h1>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<textarea style="height: 300px; width: 80%;" name="content"> </textarea>' +
        '<div style="right: 0;" class = "margin-left-7">' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin push">작성</a>' +
        '<a class="btn blue btn-effect btn-hover-blue btn-margin cancle">취소</a>' +
        '</div>' +
        '</form>' +
        '</div>';

    this.setEvent = function() {
        // text editor 설정
        tinymce.init({
            selector: 'textarea',
            init_instance_callback: function(editor) {
                console.log("Editor: " + editor.id + " is now initialized.");
                tinymce.activeEditor.setContent(content);
            }
        });

        // title input에 기존 title set
        console.log(title);
        $(".frametitle h1 input.title-input").val(title);

        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:10419",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "title": $(".frametitle h1 input.title-input").val(),
                    "content": tinymce.get('content').getContent(),
                }),
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                    xhr.setRequestHeader("command", "216");
                },
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new FacilityArticlePage("facility", no);
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }

    this.draw();
    pageStack.push();
}
FacilityQuestionModifyPage.prototype = new NonAjaxPage();


function NoticeModifyPage(no) {
    this.type = "notice";
    this.action = "view";

    this.setEvent = function() {
        // 전송, 취소 이벤트
        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/notice",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "title": $(".frametitle h1 input.title-input").val(),
                    "content": tinymce.get('content').getContent()
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new NoticeArticlePage(no);
                }
            });

        });

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        });
    };
}

NoticeModifyPage.prototype = new ServerPage();

function RuleModifyPage(no) {
    this.type = "rule";
    this.action = "view";
    this.no = no;

    this.setEvent = function() {
        // 전송, 취소 이벤트
        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/rule",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "title": $(".frametitle h1 input.title-input").val(),
                    "content": tinymce.get('content').getContent()
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new RuleArticlePage(no);
                }
            });

        });

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        });
    }
}

RuleModifyPage.prototype = new ServerPage();


function QnaAnswerModifyPage(no) {
    this.type = "rule";
    this.action = "view";
    this.no = no;

    this.setEvent = function() {
        // 전송, 취소 이벤트
        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/qna",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "content": tinymce.get('content').getContent()
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new QnaArticlePage(no);
                }
            });

        });

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        });
    }
}
QnaAnswerModifyPage.prototype = new ServerPage();


function FacilityResultModifyPage(no) {
    this.type = "rule";
    this.action = "view";
    this.no = no;

    this.setEvent = function() {
        // 전송, 취소 이벤트
        // 전송버튼 이벤트
        $(".articlecontainer a.push").click(function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com/update/qna",
                type: "POST",
                data: JSON.stringify({
                    "no": no,
                    "content": tinymce.get('content').getContent()
                }),
                success: function() {
                    // 다시 qna 글 페이지를 로드해야 할듯
                    new QnaArticlePage(no);
                }
            });

        })

        // 취소버튼 이벤트
        $(".articlecontainer a.cancle").click(function() {
            alert("cancle");
            // 다시 전 글로 돌아가기
            prevPage();
        })
    }
    this.draw();
    pageStack.push();
}
FacilityResultModifyPage.prototype = new ServerPage();
