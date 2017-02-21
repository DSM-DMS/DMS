var selectableSeat = [];

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

function drawSeat(seatArr, borderSize) {
    var selected;
    for (var loop = 0; loop < seatArr.length; loop++) {
        for (var innerLoop = 0; innerLoop < seatArr[0].length; innerLoop++) {
            // draw circle
            // 자리가 벽이 아니면
            if (seatArr[loop][innerLoop] != 0) {
                // 자리가 이미 선택됐으면
                if (seatArr[loop][innerLoop] != 1) {
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

drawSeat(gaon2, "7.5px");


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
