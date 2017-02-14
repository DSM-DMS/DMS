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

$(".pointapply form:nth-child(3) button").click(function() {
    var reason = $(".pointapply .individual input").val();
    postIndividual(reason);
});

$(".pointapply form:nth-child(4) button").click(function() {
    var reason = $(".pointapply .group input:nth-child(1)").val();
    var person = $(".pointapply .group input:nth-child(2)").val();
    postGroup(reason, person);
});

function postIndividual(reason) {
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "POST",
        data: {
            "id": id,
            "content": reason
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "144");
        },
        success: function() {
            //모달로 수정
            alert("신청되었습니다.");
        }
    });
}

function postGroup(reason, person) {
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "POST",
        data: {
            "id": id,
            "content": reason,
            "targer": person
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "144");
        },
        success: function() {
            //모달로 수정
            alert("신청되었습니다.");
        }
    });
}

function reload() {
    $.ajax({
        url: "demo_test.txt",
        type: "GET",
        data: {
            type: "reloadPoint"
        },
        success: function(data) {
            changeMain(data)
        }
    });
}

function changeMain(result) {
    var html = JSON.parse(result).data;
    $(".main").html(html);
}
