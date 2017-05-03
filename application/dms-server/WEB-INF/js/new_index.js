var $foldingButton = $("#extension-apply");
var $panel = $("#panel");
var $extensionWindow = $("#extension-apply-window");

$foldingButton.on("click", function() {
    $panel.toggleClass("left-move");
    console.log("asdf");
    $extensionWindow.toggleClass("fade-in");
})