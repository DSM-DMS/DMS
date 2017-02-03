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
