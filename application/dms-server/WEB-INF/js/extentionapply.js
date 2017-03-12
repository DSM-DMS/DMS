// 정석 시작
// set cancleEvent -> setSelecterOptions -> setSelecterEvent -> drawSeat
var id = "test";
var name = "momo";

setCancleEvent();
setSelecterOptions();
setSelecterEvent();
getSeatData(function(data) {
    drawSeat(data, "7.5px")
})

// 취소 이벤트 등록
function setCancleEvent() {
    $(".extentionapply div.selecter-container input.apply-cancle").click(function() {
        // 신청취소 ajax
        $.ajax({
            url: "http://dsm2015.cafe24.com:8089/apply/extension",
            type: "DELETE",
            data: {
                "id": id
            },
            success: function(data) {
                // alert("취소 완료.");
                // 화면을 리로드하는 코드
                // seatcontainer의 html을 백지화
                $("div.seatcontainer").html("");
                getSeatData(function(data) {
                    drawSeat(data, "7.5px")
                });
            }
        });
    })
}

function setSelecterOptions() {
    var mapData;
    // $.ajax({
    //     url: "http://dsm2015.cafe24.com:8088/apply/extension/class",
    //     type: "GET",
    //     data: JSON.stringify({
    //         "class": $(".extentionapply div.class-selecter-div select#class-select").children("option:selected").val()
    //     }),
    //     success: function(data) {
    //         setOptions(JSON.parse(data).result)
    //         mapData = JSON.parse(data);
    //     }
    // });
    function setOptions(optionData) {
        // class 목록을 받아오는 코드
        for (var loop = 0; loop < optionData.length; loop++) {
            var newOption = $('<option/>', {
                value: optionData[loop].no,
                text: optionData[loop].name
            }).appendTo("#class-select");
        }
    }

    var data = {

        result: [{
                no: 1,
                name: "가온실"
            },
            {
                no: 2,
                name: "나온실"
            },
            {
                no: 3,
                name: "다온실"
            },
            {
                no: 4,
                name: "라온실"
            },
            {
                no: 5,
                name: "3층 독서실"
            },
            {
                no: 6,
                name: "4층 독서실"
            },
            {
                no: 7,
                name: "5층 자유구역"
            }
        ]
    };
    setOptions(data);
    //return data;
}



// selecter 이벤트 등록
function setSelecterEvent() {
    $(".extentionapply div.class-selecter-div select#class-select").change(function() {
        //change event;
        var select_name = $(this).children("option:selected").text();
        $(this).siblings("label").text(select_name);
        this.class = $(this).children("option:selected").val();
        // 리로드하늠ㄴ 함수 만들어야댐 망
        $("div.seatcontainer").html("");
        getSeatData(function(data) {
            drawSeat(data, "7.5px")
        });
    })
}

function getBorderSize() {
    var windowWidth = $(window).width();
    console.log(windowWidth)
    if (windowWidth > 992) {
        return "15px";
    } else if (windowWidth > 768) {
        return "12.5px";
    } else {
        return "7.5px";
    }
}

var borderSize = getBorderSize();
console.log(borderSize);

function getSeatData(callback) {
    // var result;
    $.ajax({
        url: "http://dsm2015.cafe24.com:8089/apply/extension/class",
        type: "GET",
        data: {
            "option": "map",
            "class": $(".extentionapply div.class-selecter-div select#class-select").children("option:selected").val()
        },
        success: function(data) {
            // result = JSON.parse(data);
            callback(JSON.parse(data).map)
        }
    });
}

function drawSeat(seatArr, borderSize) {
    var selected;
    for (var loop = 0; loop < seatArr.length; loop++) {
        for (var innerLoop = 0; innerLoop < seatArr[0].length; innerLoop++) {
            console.log(innerLoop);
            // draw circle
            // 자리가 벽이 아니면
            if (seatArr[loop][innerLoop] != 0) {
                // 자리가 이미 선택됐으면
                if (typeof(seatArr[loop][innerLoop]) == "string") {
                    $('<div/>', {
                        "class": "seat",
                        css: {
                            "background": "rgb(134,193,233)"
                        },
                        text: seatArr[loop][innerLoop]
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
                    newSeat.data("seat", seatArr[loop][innerLoop]);
                    // console.log("seat test = "+newSeat.data("seat"))
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
                            console.log("test = " + seatArr[loop - 1][innerLoop - 1] + "test2 = " + $(".extentionapply div.class-selecter-div select#class-select").children("option:selected").val());
                            // 신청 ajax
                            $.ajax({
                                url: "http://dsm2015.cafe24.com:8089/apply/extension",
                                type: "PUT",
                                data: JSON.stringify({
                                    "id": "test",
                                    "class": $(".extentionapply div.class-selecter-div select#class-select").children("option:selected").val(),
                                    "seat": 0,
                                    "name": "momo"
                                }),
                                success: function(data) {
                                    $("div.seatcontainer").html("");
                                    drawSeat(getSeatData(), "7.5px");
                                }
                            });
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


// // 정석 시작
// // set cancleEvent -> setSelecterOptions -> setSelecterEvent -> drawSeat
//
// setCancleEvent();
// setSelecterOptions();
// setSelecterEvent();
// getSeatData(function(data) {
//     drawSeat(data, "7.5px")
// })
//
// // 취소 이벤트 등록
// function setCancleEvent() {
//     $(".extentionapply div.selecter-container input.apply-cancle").click(function() {
//         // 신청취소 ajax
//         $.ajax({
//             url: "http://dsm2015.cafe24.com:8089/apply/extension",
//             type: "DELETE",
//             data: JSON.stringify({
//                 "id": id
//             }),
//             success: function(data) {
//                 // alert("취소 완료.");
//                 // 화면을 리로드하는 코드
//                 // seatcontainer의 html을 백지화
//                 $("div.seatcontainer").html("");
//                 getSeatData(function(data) {
//                     drawSeat(data, "7.5px")
//                 });
//             }
//         });
//     })
// }
//
// function setSelecterOptions() {
//     var mapData;
//     // $.ajax({
//     //     url: "http://dsm2015.cafe24.com:8088/apply/extension/class",
//     //     type: "GET",
//     //     data: JSON.stringify({
//     //         "class": $(".extentionapply div.class-selecter-div select#class-select").children("option:selected").val()
//     //     }),
//     //     success: function(data) {
//     //         setOptions(JSON.parse(data).result)
//     //         mapData = JSON.parse(data);
//     //     }
//     // });
//     function setOptions(optionData) {
//         // class 목록을 받아오는 코드
//         var optionData = getSelecterOptions().result;
//         for (var loop = 0; loop < optionData.length; loop++) {
//             var newOption = $('<option/>', {
//                 value: optionData[loop].no,
//                 text: optionData[loop].name
//             }).appendTo("#class-select");
//         }
//     }
//     var data = {
//
//         result: [{
//                 no: 1,
//                 name: "가온실"
//             },
//             {
//                 no: 2,
//                 name: "나온실"
//             },
//             {
//                 no: 3,
//                 name: "다온실"
//             },
//             {
//                 no: 4,
//                 name: "라온실"
//             },
//             {
//                 no: 5,
//                 name: "3층 독서실"
//             },
//             {
//                 no: 6,
//                 name: "4층 독서실"
//             },
//             {
//                 no: 7,
//                 name: "5층 자유구역"
//             }
//         ]
//     };
//     setOptions(data);
//     //return data;
// }
//
//
//
// // selecter 이벤트 등록
// function setSelecterEvent() {
//     $(".extentionapply div.class-selecter-div select#class-select").change(function() {
//         //change event;
//         var select_name = $(this).children("option:selected").text();
//         $(this).siblings("label").text(select_name);
//         this.class = $(this).children("option:selected").val();
//         // 리로드하늠ㄴ 함수 만들어야댐 망
//         $("div.seatcontainer").html("");
//         getSeatData(function(data) {
//             drawSeat(data, "7.5px")
//         });
//     })
// }
//
// function getBorderSize() {
//     var windowWidth = $(window).width();
//     console.log(windowWidth)
//     if (windowWidth > 992) {
//         return "15px";
//     } else if (windowWidth > 768) {
//         return "12.5px";
//     } else {
//         return "7.5px";
//     }
// }
//
// var borderSize = getBorderSize();
// console.log(borderSize);
//
// function getSeatData(callback) {
//     // var result;
//     $.ajax({
//         url: "http://dsm2015.cafe24.com:8089/apply/extension/class",
//         type: "PUT",
//         data: JSON.stringify({
//             "option": "map",
//             "class": $(".extentionapply div.class-selecter-div select#class-select").children("option:selected").val()
//         }),
//         success: function(data) {
//             // result = JSON.parse(data);
//             callback(JSON.parse(data))
//         }
//     });
// }
//
// function drawSeat(seatArr, borderSize) {
//     var selected;
//     for (var loop = 0; loop < seatArr.length; loop++) {
//         for (var innerLoop = 0; innerLoop < seatArr[0].length; innerLoop++) {
//             // draw circle
//             // 자리가 벽이 아니면
//             if (seatArr[loop][innerLoop] != 0) {
//                 // 자리가 이미 선택됐으면
//                 if (typeof(seatArr[loop][innerLoop]) == "string") {
//                     $('<div/>', {
//                         "class": "seat",
//                         css: {
//                             "background": "rgb(134,193,233)"
//                         },
//                         text: seatArr[loop][innerLoop]
//                     }).appendTo(".seatcontainer");
//                 }
//                 // 자리가 선택가능하면
//                 else {
//                     var newSeat = $('<div/>', {
//                         "class": "seat",
//                         css: {
//                             "border": borderSize + " solid rgb(134,193,233)"
//                         },
//                     });
//                     newSeat.appendTo(".seatcontainer");
//                     newSeat.click(function() {
//                         if (selected !== undefined) {
//                             console.log(selected);
//                             selected.css({
//                                 "background": "white",
//                                 "border": borderSize + " solid rgb(134,193,233)"
//                             });
//                             selected.text("");
//                         }
//                         console.log(selected);
//                         console.log($(this).get(0));
//                         if (selected !== undefined && selected.get(0) == this) {
//                             console.log("ASD");
//                             selected.css({
//                                 "border": "0 solid black",
//                                 "background": "rgb(231,160,153)"
//                             })
//                             selected.text("신청됨");
//                             selected = $(this);
//                             // 신청하는 ajax
//                             $.ajax({
//                                 url: "http://dsm2015.cafe24.com:8089/apply/extension",
//                                 type: "PUT",
//                                 data: JSON.stringify({
//                                     "id": id,
//                                     "class": $(this).children("option:selected").val(),
//                                     "seat": seatArr[loop][innerLoop]
//                                 }),
//                                 success: function(data) {
//                                     $("div.seatcontainer").html("");
//                                     drawSeat(getSeatData(), "7.5px");
//                                 }
//                             });
//                         } else {
//                             console.log("wht");
//                             selected = $(this);
//                             $(this).css({
//                                 "border": "0 solid black",
//                                 "background": "#FFD180"
//                             });
//                             $(this).text("신청?");
//                         }
//                     })
//                 }
//             } else {
//                 $('<div/>', {
//                     "class": "seat",
//                     css: {
//                         // "z-index": "100",
//                         // "background": "#f6f6f6",
//                         "border": borderSize + " solid #757575"
//                     }
//                 }).appendTo(".seatcontainer");
//             }
//         }
//         $('</br>').appendTo(".seatcontainer");
//     }
// }
// //
// // var gaon = [
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, 0, 1, 1, 0],
// //     [0, 1, 1, 0, 1, 1, 0],
// //     [0, 1, 1, 0, 1, 1, 0],
// //     [0, 1, 1, 0, 1, 1, 0],
// //     [0, 1, 1, 0, 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0]
// // ];
// //
// // var gaon2 = [
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, "임나연", "조성빈", 0, "사나", "권장호", 0],
// //     [0, "모모", "김지수", 0, "박지효", "박영준", 0],
// //     [0, "미나", "구승완", 0, 1, 1, 0],
// //     [0, 1, 1, 0, 2, 1, 0],
// //     [0, 1, 1, 0, 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0]
// // ];
// //
// // var naon = [
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, 1, 1, 1, 0],
// //     [0, 1, 0, 1, 0, 1, 0],
// //     [0, 1, 0, 1, 0, 1, 0],
// //     [0, 1, 0, 1, 0, 1, 0],
// //     [0, 1, 0, 1, 0, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0]
// // ];
// //
// // var naon2 = [
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, "루시우", "디바", "메르시", "트레이서", "정크렛", 0],
// //     [0, "솔저", 0, "라인하르트", 0, "메이", 0],
// //     [0, "맥크리", 0, "리퍼", 0, "겐지", 0],
// //     [0, "아나", 0, "솜브라", 0, "젠야타", 0],
// //     [0, "바스티온", 0, "한조", 0, "파라", 0],
// //     [0, 0, 0, 0, 0, 0, 0]
// // ];
// //
// // var daon = [
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, 1, 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, 1, 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, 1, 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, 1, 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, 1, 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0]
// // ];
// //
// // var daon2 = [
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, "호랑이", "사자", "사슴", "노루", "거북이", 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, "코끼리", "기린", 1, "코뿔소", 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, "하마", 1, "개미", 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, "펭귄", 1, "여우", 1, "늑대", 0],
// //     [0, 0, 0, 0, 0, 0, 0],
// //     [0, 1, 1, "수달", 1, 1, 0],
// //     [0, 0, 0, 0, 0, 0, 0]
// // ];
// //
// // drawSeat(gaon2, "7.5px");
// // $(".extentionapply div.class-selecter-div select#class-select").change(function() {
// //     //change event;
// //     var select_name = $(this).children("option:selected").text();
// //     $(this).siblings("label").text(select_name);
// //     console.log($(this).children("option:selected").val());
// // })
// //
// // function Class() {
// //     trancedSeatData;
// //     this.map = [
// //         [0, 0, 0, 0, 0, 0, 0],
// //         [0, this.seatData[0], this.seatData[1], 0, this.seatData[2], this.seatData[3], 0],
// //         [0, this.seatData[4], this.seatData[5], 0, this.seatData[6], this.seatData[7], 0],
// //         [0, this.seatData[8], this.seatData[9], 0, this.seatData[10], this.seatData[11], 0],
// //         [0, this.seatData[12], this.seatData[13], 0, this.seatData[14], this.seatData[15], 0],
// //         [0, this.seatData[16], this.seatData[17], 0, this.seatData[18], this.seatData[19], 0],
// //         [0, 0, 0, 0, 0, 0, 0]
// //     ];
// //
// //     this.pushData = function(seatData) {
// //         this.seatData = seatData;
// //         var trancedSeatData = [];
// //         // 받은 데이터를 그리기위한 배열로 만들기위한 전 단계
// //         for (var loop = 0; loop < seatData.length; loop++) {
// //
// //             if (seatData.name !== null) {
// //                 trancedSeatData.push(seatData.name);
// //             } else {
// //                 trancedSeatData.push(seatData.seat);
// //             }
// //         }
// //         return [
// //             [0, 0, 0, 0, 0, 0, 0],
// //             [0, trancedSeatData[0], trancedSeatData[1], 0, trancedSeatData[2], trancedSeatData[3], 0],
// //             [0, trancedSeatData[4], trancedSeatData[5], 0, trancedSeatData[6], trancedSeatData[7], 0],
// //             [0, trancedSeatData[8], trancedSeatData[9], 0, trancedSeatData[10], trancedSeatData[11], 0],
// //             [0, trancedSeatData[12], trancedSeatData[13], 0, trancedSeatData[14], trancedSeatData[15], 0],
// //             [0, trancedSeatData[16], trancedSeatData[17], 0, trancedSeatData[18], this.seatData[19], 0],
// //             [0, 0, 0, 0, 0, 0, 0]
// //         ];
// //     }
// //
// //     this.classNum = 1;
// //     this.seats = [];
// //     for (var loop = 1; loop <= 20; loop++) {
// //         seats.push({
// //             "seat": loop
// //         })
// //     }
// //
// // }
//
//
// // ====가온실====
// // □□□□□□□
// // □●●□●●□
// // □●●□●●□
// // □●●□●●□
// // □●●□●●□
// // □●●□●●□
// // □□□□□□□
// //
// // ====나온실====
// // □□□□□□□
// // □●●●●●□
// // □●□●□●□
// // □●□●□●□
// // □●□●□●□
// // □●□●□●□
// // □□□□□□□
// //
// // ====다온실====
// // □□□□□□□
// // □●●●●●□
// // □□□□□□□
// // □●●●●●□
// // □□□□□□□
// // □●●●●●□
// // □□□□□□□
// // □●●●●●□
// // □□□□□□□
// // □●●●●●□
// // □□□□□□□
