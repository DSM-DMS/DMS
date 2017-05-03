var $foldingButton = $("#extension-apply");
var $panel = $("#panel");
var $extensionWindow = $("#extension-apply-window");

$foldingButton.on("click", function() {
    $panel.toggleClass("left-move");
    console.log("asdf");
    $extensionWindow.toggleClass("fade-in");
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
