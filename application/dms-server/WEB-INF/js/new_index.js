var $foldingButton = $("#extension-apply");
var $panel = $("#panel");

$foldingButton.on("click", function() {
    $panel.toggleClass("left-move");
})
