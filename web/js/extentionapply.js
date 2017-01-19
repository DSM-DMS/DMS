//신청탭 over 이벤트
$(".remote .category").children("a").eq(0).mouseover(() => {
    //신청 text를 white로
    $(".remote .category").children("a").eq(0).animate({
        fontSize: "1.5em",
        color: "white"
    })

    //remote background-color를 신청색으로
    $(".remote").animate({
        backgroundColor: "rgb(52, 152, 219)",
        backgroundImage: "linear-gradient( to right top, transparent 33%, gray 33%, gray 66%, transparent 66%)"

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
$(".remote .category").children("a").eq(1).mouseover(() => {
    //기숙사 text를 white로
    $(".remote .category").children("a").eq(1).animate({
        fontSize: "1.5em",
        color: "white"
    })

    //remote background-color를 신청색으로
    $(".remote").animate({
        backgroundColor: "rgb(142, 68, 173)"
    }, 500);

    //DMS hide
    $(".remote h1").slideUp();

    //기숙사탭 하위메뉴 show
    $(".remote .category .dom").show();

    //공지사항, 마이페이지탭 hide
    $(".remote .category").children("a").eq(0).hide();
    $(".remote .category").children("a").eq(2).hide();
})

$(".remote .category").children("a").eq(2).mouseover(() => {
    //remote background-color를 마이페이지색으로
    $(".remote").animate({
        backgroundColor: "rgb(192, 57, 43)"
    });

    //마이페이지 text를 white로
    $(".remote .category").children("a").eq(2).animate({
        fontSize: "1em",
        color: "white"
    })

    //DMS hide
    $(".remote h1").slideUp();

    //공지사항, 기숙사 hide
    $(".remote .category").children("a").eq(0).hide();
    $(".remote .category").children("a").eq(1).hide();
})

//리모트 leave 이벤트
$(".remote").mouseleave(() => {
    //모든 탭 글자색을 윈대래로
    $(".remote .category").children("a").eq(0).animate({
        color: "rgb(52, 152, 219)",
        fontSize: "1em"
    })

    $(".remote .category").children("a").eq(1).animate({
        color: "rgb(142, 68, 173)",
        fontSize: "1em"

    }, 500)

    $(".remote .category").children("a").eq(2).animate({
        color: "color: rgb(192, 57, 43)",
        fontSize: "1em"
    })

    //remote backgroundColor를 원래대로
    $(".remote").animate({
        backgroundColor: "white"
    });

    // DMS복구
    $(".remote h1").slideDown();

    //모든 탭 show
    $(".remote .category").children("a").eq(0).show()
    $(".remote .category").children("a").eq(1).show()
    $(".remote .category").children("a").eq(2).show();

    //탭 하부메뉴 hide
    $(".remote .category .application").hide(0);
    $(".remote .category .dom").hide(0);
})

// ------------------------------- canvas

var seatData = [
    [null, null, "test", "조쯔위", "김사나"],
    ["미사모", "손채영", "복숭아", null, "미나리"],
    ["와이스", null, "급식단", null, "김다현"],
    ["모모링", "박지효", "임나연", null, "룸메즈"]
];


function seat(owner, x, y) {
    this.owner = owner;
    this.x = x;
    this.y = y;
}

//var objSeatArr = seatArrInit(seatData);

function seatArrInit(seatArr) {
    var objSeatArr = [];
    for (var loop = 0; loop < seatArr.length; loop++) {
        for (var innerLoop = 0; innerLoop < seatArr[0].length; innerLoop++) {
            objSeatArr[loop][innerLoop] = new seat(seatArr[loop][innerLoop], innerLoop, loop);
        }
    }

    return objSeatArr;
}

canvasInit();

function canvasInit() {

    //canvas의 사이즈를 컨테이너div에 맞게 조정함.
    let canvasElement = $("#canvas");

    //컨테이너div 사이즈의 픽셀값을 얻기위해 computedStyle객체 선언
    let seat = document.querySelector(".seat");
    let computedContainer = getComputedStyle(seat, null);

    //컨테이너div의 computedContainer에 접근하여 얻은 픽셀 값을 이용하여 canvas 초기화
    canvas.width = parseInt(computedContainer.getPropertyValue('width'), 10);
    canvas.height = parseInt(computedContainer.getPropertyValue('height'), 10);
}


var canvasElement = document.getElementById("canvas");
var ctx = canvasElement.getContext("2d");

drawSeat(seatData);

function drawSeat(seatArr) {
    ctx.fillStyle = "white";
    ctx.fillRect(0, 0, canvasElement.width, canvasElement.height);

    let interval = 10;
    let seatWidth = ((canvasElement.width - interval) / 5);
    let seatHeight = ((canvasElement.height - interval) / 4);
    ctx.fillStyle = "white";
    for (let loop = 0; loop < 4; loop++) {
        for (let innerLoop = 0; innerLoop < 5; innerLoop++) {
            if (seatArr[loop][innerLoop] === null) {
                ctx.fillStyle = "gray";
                drawRoundBorderRect(innerLoop * seatWidth + interval, loop * seatHeight + interval, seatWidth - interval, seatHeight - interval, 20, "gray");
            } else {
                ctx.font = "15px Arial";
                ctx.textBaseline = "middle";
                ctx.fillStyle = "red";
                drawRoundBorderRect(innerLoop * seatWidth + interval, loop * seatHeight + interval, seatWidth - interval, seatHeight - interval, 20, "red");
                ctx.fillStyle = "white";
                console.log(ctx.measureText(seatArr[loop][innerLoop]).width);
                console.log(seatWidth);
                ctx.fillText(seatArr[loop][innerLoop], innerLoop * seatWidth + (seatWidth + interval - ctx.measureText(seatArr[loop][innerLoop]).width) / 2, loop * seatHeight + interval + (seatHeight - interval) / 2);
            }
        }
    }
}

function drawRoundBorderRect(rectX, rectY, rectWidth, rectHeight, cornerRadius, color) {
    ctx.save();
    ctx.lineJoin = "round";
    ctx.lineWidth = cornerRadius;
    ctx.fillStyle = color;
    ctx.strokeStyle = color;
    ctx.strokeRect(rectX + (cornerRadius / 2), rectY + (cornerRadius / 2), rectWidth - cornerRadius, rectHeight - cornerRadius);
    ctx.fillRect(rectX + (cornerRadius / 2), rectY + (cornerRadius / 2), rectWidth - cornerRadius, rectHeight - cornerRadius);
    ctx.restore();
}
