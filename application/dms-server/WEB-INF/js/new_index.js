var $foldingButton = $("#extension-apply");
var $closeButton = $("#close-extension-window");
var $panel = $("#panel");
var $extensionWindow = $("#extension-apply-window");
var $loginBtn = $(".login");
var $trigger = $(".trigger");
var $menu = $("#menu");

$foldingButton.on("click", function() {
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
});

$closeButton.on("click", function() {
    $panel.toggleClass("left-move");
    $extensionWindow.toggleClass("fade-in");
    $menu.toggleClass("fade-out");
});

var speechBubble = $('#speech-bubble');

$("#stay-apply").click(function() {
    speechBubble.toggleClass('fade-in');
    speechBubble.focus();
});

$('#speech-bubble button').click(function() {
    speechBubble.toggleClass('fade-in');
});

$trigger.on("click", function() {
    $('.modal-wrapper').toggleClass('open');
    $panel.toggleClass('blur');
    $menu.toggleClass('blur');
    return false;
});





var mapData = [
    [1, 2, 0, 3, 4],
    [5, 6, 0, 7, 8],
    [9, 10, 0, 11, 12],
    [13, 14, 0, 15, 16],
    [17, 18, 0, 19, 20]
];

var mapData2 = [
    [1, 2, 3, 0, 4, 5, 6],
    [7, 8, 9, 0, 10, 11, 12],
    [13, 14, 15, 0, 16, 17, 18]
];

function drawSeats(mapData) {
    var newTable = $('<table/>', {
        "id": "extension-seat-table"
    });

    for (var loop = 0; loop < mapData.length; loop++) {
        var newTr = $('<tr/>', {
            "class": "extension-seat-table-tr"
        });

        for (var innerLoop = 0; innerLoop < mapData[loop].length; innerLoop++) {
            var tdOpacity = 1;
            if (mapData[loop][innerLoop] === 0) {
                tdOpacity = 0;
            }
            var newTd = $('<td/>', {
                css: {
                    opacity: tdOpacity
                }
            });
            var newSeat = $('<div/>', {
                "class": "extension-seat",
            })
            newSeat.appendTo(newTd);
            newTd.appendTo(newTr);
        }
        newTr.appendTo(newTable);
    }
    $(".extension-seat-table-container").html(newTable);
    console.log($("#extension-seat-table").width());
    $(".extension-board").css({
        width: $("#extension-seat-table").width()
    });
}

drawSeats(mapData);

function stayDoCheck() {

    TweenLite.set([stayCross1, stayCross2], { autoAlpha: 0 });
    TweenLite.set(stayTick, { drawSVG: "0%" });
    TweenLite.set(stayCircle, { drawSVG: "50% 50%", scale: ".01", transformOrigin: "50% 50%", fill: "#607D8B", autoAlpha: 0 });

    tl1 = new TimelineMax({ repeat: 0, repeatDelay: 1 });
    tl1
        .to(stayCircle, 1, { scale: 1, ease: Elastic.easeOut })
        .to([stayTick, stayCircle], .6, { autoAlpha: 1 }, .1)
        .to(stayCircle, .8, { drawSVG: "100% 0%", ease: Power4.easeOut }, .2)
        .to(stayTick, .8, { drawSVG: "0% 100%", ease: Expo.easeOut }, '-=.6')
        .to(stayCircle, .6, { fill: "#607D8B", ease: Power1.easeInOut }, '-=.4');

    tl1.timeScale(.8);

}

function extensionDoCheck() {
    TweenLite.set([extensionCross1, extensionCross2], { autoAlpha: 0 });
    TweenLite.set(extensionTick, { drawSVG: "0%" });
    TweenLite.set(extensionCircle, { drawSVG: "50% 50%", scale: ".01", transformOrigin: "50% 50%", fill: "#607D8B", autoAlpha: 0 });

    tl1 = new TimelineMax({ repeat: 0, repeatDelay: 1 });
    tl1
        .to(extensionCircle, 1, { scale: 1, ease: Elastic.easeOut })
        .to([extensionTick, extensionCircle], .6, { autoAlpha: 1 }, .1)
        .to(extensionCircle, .8, { drawSVG: "100% 0%", ease: Power4.easeOut }, .2)
        .to(extensionTick, .8, { drawSVG: "0% 100%", ease: Expo.easeOut }, '-=.6')
        .to(extensionCircle, .6, { fill: "#607D8B", ease: Power1.easeInOut }, '-=.4');

    tl1.timeScale(.8);

}

$(document).ready(function() {
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
});