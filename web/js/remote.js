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
    loadExntetionApplyData();
});

$(".remote .inner .category .children #point").click(function() {
    loadPointApplyPage();
});

$(".remote .inner .category .children #after").click(function() {
    loadExntetionApplyData();
});

$(".remote .inner .category .children #notice").click(function() {

});

$(".remote .inner .category .children #faq").click(function() {
    loadFAQList();
});

$(".remote .inner .category .children #rule").click(function() {
    loadRuleList();
});

$(".remote .inner .category .children #qna").click(function() {
    loadQNAList();
});

$(".remote .inner .category .a#mypage").click(function() {
    loadMyPage();
});


// 연장학습-----------------------------------------------------------------------

function loadExntetionApplyData() {
    console.log("clicked");
    //data로 id 전송해야함
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "POST",
        data: {},
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "115");
        },
        success: function(data) {
            createExtentionPage(data);
        }
    });
}

function createExtentionPage(data) {
    $(".main").html('\
    <div class=\"frame left extentionapply\">\
    <div class=\"frametitle\">\
    <h1>연장신청</h1>\
    <div class=\"underline blue\"></div></div>\
    <div class=\"seatcontainer\"></div></div>');

    var seatArr = JSON.parse(data).result;
    drawSeat(seatArr, "7.5px");
}

function drawSeat(seatArr, borderSize) {
    var selected;
    for (var loop = 0; loop < seatArr.length; loop++) {
        for (var innerLoop = 0; innerLoop < seatArr[0].length; innerLoop++) {
            //draw circle
            if (seatArr[loop][innerLoop] != 0) {
                if (seatArr[loop][innerLoop] != 1) {
                    $('<div/>', {
                        "class": "seat",
                        css: {
                            "background": "rgb(134,193,233)"
                        },
                        text: seatArr[loop][innerLoop]
                    }).appendTo(".seatcontainer");
                } else {
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

                            //자리 선택했다고 ajax보내는 함수
                            //id, class, seat 보내야함
                            // postExtention(class, seat);
                            //reload하는 함수

                            selected.text("신청됨");
                            selected = $(this);
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
                        // "z-index": "100",
                        // "background": "#f6f6f6",
                        "border": borderSize + " solid #757575"
                    }
                }).appendTo(".seatcontainer");
            }
        }
        $('</br>').appendTo(".seatcontainer");
    }
}

// function postExtention(class, seat) {
//     $.ajax({
//         url: "dsm2015.cafe24.com",
//         type: "POST",
//         data: {class: class, seat: seat, id: id},
//         beforeSend: function(xhr) {
//             xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
//             xhr.setRequestHeader("command", "141");
//         }
//         // success: function(data) {
//         //     createExtentionPage(data);
//         // }
//     })
// }

//외출 신청은 그냥 html받어오면 됨

//귀가 신청은 현재 자신의 정보 받아와야 할듯

//상점 신청-----------------------------------------------------------------------

function loadPointApplyPage() {
    $(".main").html(
        '<div class="frame left pointapply">' +
        '<div class="frametitle">' +
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
        '</div>'
    );
    addPointEvent();
}

function addPointEvent() {
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

    $(".pointapply form:nth-child(3) button").click(function() {
        var reason = $(".pointapply .individual input").val();
        postIndividual(reason);
    });

    $(".pointapply form:nth-child(4) button").click(function() {
        var reason = $(".pointapply .group input:nth-child(1)").val();
        var person = $(".pointapply .group input:nth-child(2)").val();
        postGroup(reason, person);
    });

    function postIndividual(reason) {
        $.ajax({
            url: "dsm2015.cafe24.com",
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
                //모달로 수정
                alert("신청되었습니다.");
            }
        });
    }

    function postGroup(reason, person) {
        $.ajax({
            url: "dsm2015.cafe24.com",
            type: "POST",
            data: {
                "id": id,
                "content": reason,
                "targer": person
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", "144");
            },
            success: function() {
                //모달로 수정
                alert("신청되었습니다.");
            }
        });
    }
}

// 방과후-----------------------------------------------------------------------

function loadAfterList() {
    var trHeight = 53;
    var listNum = getListNum(trHeight);
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "GET",
        data: {},
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "416");
        },
        success: function(data) {
            AfterMakeNewTable(data, listNum);
        }
    });

}

function AfterMakeNewTable(data, listNum) {
    var objDataArr = JSON.parse(data).result;
    for (var loop = 0; loop < listNum; loop++) {
        AfterMakeNewLine(objDataArr[loop]);
    }
}

function AfterMakeNewLine(data) {
    var newTr = $('<tr/>', {
        click: function(e) {
            // tr 클릭이벤트
            loadAfterArticle(data);
        }
    });
    var appendString = "<td>" + data.target + "</td>";
    appendString += "<td>" + data.title + "</td>";
    appendString += "<td>" + data.instuctor + "</td>";
    appendString += "<td>";
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
    //수강인원은 추가해야함
    // for (var loop in data) {
    //     appendString += "<td>" + data[loop] + "</td>";
    // }
    newTr.append(appendString);
    $(".articlelist table").append(newTr);
}

//현재 view height 기준으로 몇개의 tr이 들어가는지 구하는 함수
function getListNum(trHeight) {
    // main의 header등의 height를 제외한 공간의 높이
    console.log($(".main").height());
    var useableSpace = $(".main").height() - $(".articlelist table .tableheader").position().top;
    console.log(useableSpace);
    return (useableSpace / trHeight) - 2;
}


// 방과후 본문 -------------------------------------------------------------------
// 받아오는걸로 할지 아닐지는 정해야 할듯
function loadAfterArticle(data) {
    var myAfter = getAfterStatus();
    var formString = '<form class="afterform" action="index.html" method="post">' +
        '<button id = "apply" type="button" name="button">YES</button>' +
        '</form>';
    for (var loop = 0; loop < myAfter.length; loop++) {
        if (myAfter[loop].no == data.no) {
            formString = '<form class="afterform" action="index.html" method="post">' +
                '<button id = "cancle" type="button" name="button">NO</button>' +
                '</form>';
            break;
        }
    }
    $(".main").html(
        '<div class="frame left afterapplypage">' +
        '<div class="frametitle">' +
        '<img src="../image/arrow2.png" alt="">' +
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
        '<h3>' + data.title + '</h3>' +
        '<p>' + data.instuctor + '</p>' +
        '<p class="grade" id="first">1학년</p>' +
        '<p class="grade" id="second">2학년</p>' +
        '<p class="grade" id="third">3학년</p>' +
        '<p id="class">' + data.place + '</p>' +
        '<p id="member">' + data.asdfasdfasdf + '0</p>' +
        '<div class="bar">' +
        '<div class="in bar">' +
        '</div>' +
        '</div>' +
        formString +
        '</div>' +
        '<br>' +
        '</div>' +
        '</div>');
    //target의 향식을 알아야 함
    if (data.on_monday) {
        $(".afterapplypage .afterinfo p#firsr").css({
            "background-color": "rgb(134, 193, 233)"
        })
    }

    addAfterApplyEvent(data.no);
}

function getAfterStatus() {
    var result;
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "POST",
        data: {
            "id": id
        },
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

function addAfterApplyEvent(no) {
    $(".afterapplypage form button#apply").click(function() {
        // 신청 메시지 보내야함
        $.ajax({
            url: "dsm2015.cafe24.com",
            type: "POST",
            data: {
                "id": id,
                "no": no
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", "145");
            },
            success: function(data) {
                createExtentionPage(data);
            }
        });
        alert("YES");
    });
    $(".afterapplypage form button#cancle").click(function() {
        // 신청취소 메시지 보내야함
        alert("NO");
    })
}

// 공지사항 -------------------------------------------------------------------
// 서버가 아직 구현 안됨

// FAQ -------------------------------------------------------------------
function loadFAQList() {
    var trHeight = 53;
    var listNum = getListNum(trHeight);
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "POST",
        data: {},
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "427");
        },
        success: function(data) {
            FAQMakeNewTable(data, listNum);
        }
    });

}

function FAQMakeNewTable(data, listNum) {
    $(".main").html(
        '<div class="frame left articlelist">' +
        '<div class="frametitle">' +
        '<h1>FAQ</h1>' +
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table>' +
        '<tr class="tableheader">' +
        '<th>번호</th>' +
        '<th>제목</th>' +
        '<th>날짜</th>' +
        '</tr>' +
        '</table>' +
        '</div>'
    );
    var objDataArr = JSON.parse(data).result;
    for (var loop = 0; loop < listNum; loop++) {
        FAQMakeNewLine(objDataArr[loop]);
    }
}

function FAQMakeNewLine(data) {
    var newTr = $('<tr/>', {
        click: function(e) {
            // tr 클릭이벤트
            loadFAQArticle(data);
        }
    });
    var appendString = "<td>" + data.no + "</td>";
    appendString += "<td>" + data.title + "</td>";
    appendString += "<td>" + data.date + "</td>";
    newTr.append(appendString);
    $(".articlelist table").append(newTr);
}

// FAQ Article -------------------------------------------------------------------
function loadFAQArticle(data) {
    $(".main").html(
        '<div class="frame left articlecontainer">' +
        '<div class="frametitle">' +
        '<h2> ' + data.title + '</h2>' +
        '<p>' + data.date + '</p>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<div class="article">' +
        '<p>' + data.content + '</p>' +
        '</div>' +
        '<hr>' +
        '</div>'
    );
}

// RULE -------------------------------------------------------------------
function loadRuleList() {
    var trHeight = 53;
    var listNum = getListNum(trHeight);
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "POST",
        data: {},
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "424");
        },
        success: function(data) {
            RuleMakeNewTable(data, listNum);
        }
    });

}

function RuleMakeNewTable(data, listNum) {
    $(".main").html(
        '<div class="frame left articlelist">' +
        '<div class="frametitle">' +
        '<h1>FAQ</h1>' +
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table>' +
        '<tr class="tableheader">' +
        '<th>번호</th>' +
        '<th>제목</th>' +
        '<th>날짜</th>' +
        '</tr>' +
        '</table>' +
        '</div>'
    );
    var objDataArr = JSON.parse(data).result;
    for (var loop = 0; loop < listNum; loop++) {
        RuleMakeNewLine(objDataArr[loop]);
    }
}

function RuleMakeNewLine(data) {
    var newTr = $('<tr/>', {
        click: function(e) {
            // tr 클릭이벤트
            loadRuleArticle(data);
        }
    });
    var appendString = "<td>" + data.no + "</td>";
    appendString += "<td>" + data.title + "</td>";
    appendString += "<td>" + data.date + "</td>";
    newTr.append(appendString);
    $(".articlelist table").append(newTr);
}

// RULE Article -------------------------------------------------------------------
function loadRuleArticle(data) {
    $(".main").html(
        '<div class="frame left articlecontainer">' +
        '<div class="frametitle">' +
        '<h2> ' + data.title + '</h2>' +
        '<p>' + data.date + '</p>' +
        '<div class="underline puple">' +
        '</div>' +
        '</div>' +
        '<div class="article">' +
        '<p>' + data.content + '</p>' +
        '</div>' +
        '<hr>' +
        '</div>'
    );
}


// QNA -------------------------------------------------------------------
function loadQNAList() {
    var trHeight = 53;
    var listNum = getListNum(trHeight);
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "POST",
        data: {},
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "414");
        },
        success: function(data) {
            QNAMakeNewTable(data, listNum);
        }
    });

}

function QNAMakeNewTable(data, listNum) {
    $(".main").html(
        '<div class="frame left articlelist qna">' +
        '<div class="frametitle">' +
        '<h1>QNA</h1>' +
        '<div class="underline puple"></div>' +
        '</div>' +
        '<table>' +
        '<tr class="tableheader">' +
        '<th>번호</th>' +
        '<th>제목</th>' +
        '<th>날짜</th>' +
        '</tr>' +
        '</table>' +
        '</div>'
    );
    var objDataArr = JSON.parse(data).result;
    for (var loop = 0; loop < listNum; loop++) {
        FAQMakeNewLine(objDataArr[loop]);
    }
}

function FAQMakeNewLine(data) {
    var newTr = $('<tr/>', {
        click: function(e) {
            // tr 클릭이벤트
            loadFAQArticle(data);
        }
    });
    var appendString = "<td>" + data.no + "</td>";
    appendString += "<td>" + data.title + "</td>";
    appendString += "<td>" + data.date + "</td>";
    newTr.append(appendString);
    $(".articlelist table").append(newTr);
}

// QNA Atricle -------------------------------------------------------------------
function loadQNAArticle(data) {
    // 뭔 요청
}

function addQNAEvent() {
    //댓글 전송 이벤트
    // no, content, writer필요
    $(".extention .commentinput table button").click(function() {
        $.ajax({
            url: "dsm2015.cafe24.com",
            type: "POST",
            data: {
              "no": no,
              "content": content,
              "writer": writer
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
                xhr.setRequestHeader("command", "144");
            },
            success: function(data) {
                loadQNAArticle();
            }
        });
    })
}

// QNA Atricle -------------------------------------------------------------------
function loadMyPage() {
    // mypage로드 ajax
}
