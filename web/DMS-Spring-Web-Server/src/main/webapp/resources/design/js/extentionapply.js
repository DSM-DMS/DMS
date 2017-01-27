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
    var canvasElement = $("#canvas");

    //컨테이너div 사이즈의 픽셀값을 얻기위해 computedStyle객체 선언
    var seat = document.querySelector(".seat");
    var computedContainer = getComputedStyle(seat, null);

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

    var interval = 10;
    var seatWidth = ((canvasElement.width - interval) / 5);
    var seatHeight = ((canvasElement.height - interval) / 4);
    ctx.fillStyle = "white";
    for (var loop = 0; loop < 4; loop++) {
        for (var innerLoop = 0; innerLoop < 5; innerLoop++) {
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
