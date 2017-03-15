// 할것 추천, 신청 ajax

$("form.point-apply-form")

function pointApplySubmit () {
    console.log("신청 서밋")
    $.ajax({
        url: "/apply/merit",
        type: "POST",
        data: {
            target: null,
            content: $(".point-apply-form point-apply-content").val()
        },
        success: function () {
            alert("신청이 완료되었어요.");
        },
        error: function (xhr) {
            alert("신청에 실패했어요. TT");
        }
    })
}

function pointRecommandSubmit () {
    console.log("추천 서밋")
    $.ajax({
        url: "/apply/merit",
        type: "POST",
        data: {
            target: null,
            content: $(".point-apply-form point-apply-content").val()
        },
        success: function () {
            alert("신청이 완료되었어요.");
        },
        error: function (xhr) {
            alert("신청에 실패했어요. TT");
        }
    })
}
