var selected;
var selectableSeat = [];

function drawSeat(seatArr) {
    for (var loop = 0; loop < seatArr.length; loop++) {
        for (var innerLoop = 0; innerLoop < seatArr[0].length; innerLoop++) {
            //draw circle
            if (seatArr[loop][innerLoop] != 0) {
                if (seatArr[loop][innerLoop] != 1) {
                    $('<div/>', {
                        "class": "seat",
                        css: {
                            // "border": "15px solid rgb(191,148,208)"
                            "background": "rgb(134,193,233)"
                        },
                        text: seatArr[loop][innerLoop]
                    }).appendTo(".seatcontainer");
                } else {
                    var newSeat = $('<div/>', {
                        "class": "seat",
                        css: {
                            "border": "15px solid rgb(134,193,233)"
                        },
                    });
                    newSeat.appendTo(".seatcontainer");
                    newSeat.click(function() {
                        if (selected !== undefined) {
                            console.log(selected);
                            selected.css({
                                "background": "white",
                                "border": "15px solid rgb(134,193,233)"
                            });
                            selected.text("");
                        }
                        console.log(selected);
                        console.log($(this).get(0));
                        if (selected !== undefined && selected.get(0) == this) {
                            console.log("ASD");
                            selected.css({
                                "background": "rgb(231,160,153)",
                                "border": "0 solid #FFD180"
                            })
                           selected.text("신청됨");
                           selected = $(this);
                        } else {
                        console.log("wht");
                        selected = $(this);
                        $(this).css({
                            "background": "#FFD180",
                            "border": "0 solid #FFD180"
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
                        "border": "15px solid #757575"
                    }
                }).appendTo(".seatcontainer");
            }
        }
        $('</br>').appendTo(".seatcontainer");
    }
}

var gaon = [
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 0, 1, 1, 0],
    [0, 1, 1, 0, 1, 1, 0],
    [0, 1, 1, 0, 1, 1, 0],
    [0, 1, 1, 0, 1, 1, 0],
    [0, 1, 1, 0, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0]
];

var gaon2 = [
    [0, 0, 0, 0, 0, 0, 0],
    [0, "임나연", "조성빈", 0, "사나", "권장호", 0],
    [0, "모모", "김지수", 0, "박지효", "박영준", 0],
    [0, "미나", "구승완", 0, 1, 1, 0],
    [0, 1, 1, 0, 1, 1, 0],
    [0, 1, 1, 0, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0]
];

var naon = [
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 1, 1, 0],
    [0, 1, 0, 1, 0, 1, 0],
    [0, 1, 0, 1, 0, 1, 0],
    [0, 1, 0, 1, 0, 1, 0],
    [0, 1, 0, 1, 0, 1, 0],
    [0, 0, 0, 0, 0, 0, 0]
];

var naon2 = [
    [0, 0, 0, 0, 0, 0, 0],
    [0, "루시우", "디바", "메르시", "트레이서", "정크렛", 0],
    [0, "솔저", 0, "라인하르트", 0, "메이", 0],
    [0, "맥크리", 0, "리퍼", 0, "겐지", 0],
    [0, "아나", 0, "솜브라", 0, "젠야타", 0],
    [0, "바스티온", 0, "한조", 0, "파라", 0],
    [0, 0, 0, 0, 0, 0, 0]
];

var daon = [
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0]
];

var daon2 = [
    [0, 0, 0, 0, 0, 0, 0],
    [0, "호랑이", "사자", "사슴", "노루", "거북이", 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, "코끼리", "기린", 1, "코뿔소", 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, "하마", 1, "개미", 1, 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, "펭귄", 1, "여우", 1, "늑대", 0],
    [0, 0, 0, 0, 0, 0, 0],
    [0, 1, 1, "수달", 1, 1, 0],
    [0, 0, 0, 0, 0, 0, 0]
];

drawSeat(gaon2);


// ====가온실====
// □□□□□□□
// □●●□●●□
// □●●□●●□
// □●●□●●□
// □●●□●●□
// □●●□●●□
// □□□□□□□
//
// ====나온실====
// □□□□□□□
// □●●●●●□
// □●□●□●□
// □●□●□●□
// □●□●□●□
// □●□●□●□
// □□□□□□□
//
// ====다온실====
// □□□□□□□
// □●●●●●□
// □□□□□□□
// □●●●●●□
// □□□□□□□
// □●●●●●□
// □□□□□□□
// □●●●●●□
// □□□□□□□
// □●●●●●□
// □□□□□□□
