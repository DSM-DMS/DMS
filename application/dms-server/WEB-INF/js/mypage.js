var cardsArr = $(".mypage-component-container");

for (var loop = 2; loop < cardsArr.length; loop++) {
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").data("loop", loop);
    $(cardsArr[loop]).children(".card").children(".card-block").children(".moreinfo-span").click(function() {
        if ($(this).data("clicked")) {
            $(this).text("클릭하여 자세히보기");

            $(this).siblings(".simple-container").show(500);
            $(this).siblings(".detail-container").hide(500);
            $(this).data("clicked", false);
        } else {
            $(this).text("클릭하여 간략히보기");

            $(this).siblings(".simple-container").hide(500);
            $(this).siblings(".detail-container").show(500);
            $(this).data("clicked", true);
        }
    })
}



$(".modify-password-btn").click(function() {
    if ($("#modify-password").val() === $("#modify-password-re").val()) {
        console.log($("#modify-password").val() + ", " + $("#modify-password-re").val())
        $.ajax({
            url: "/account/password/student",
            type: "PATCH",
            data: {
                password: $("modify-password")
            }
        })
    } else {
        $("#modify-password-re").css({
            "background-color": "#F06868"
        });
        $(".modify-password-btn").text("비밀번호를 확인하세요!");
        $(".modify-password-btn").css({
            "background-color": "#F06868"
        });
        setTimeout(function() {
            console.log("tet")
            $(".modify-password-btn").text("변경");
            $(".modify-password-btn").css({
                "background-color": "#31b0d5"
            });
        }, 2000)
    }
})

$(".modify-profile-picture-btn").click(function() {
    console.log($(".preview-picture").attr("src"));
    if ($(".preview-picture").attr("src") !== null) {
        $.ajax({
            url: "/account/profile-image",
            type: "PATCH",
            data: {
                "profile_image": $(".preview-picture").attr("src")
            },
            success: function (data) {
                alert("변경이 완료되었습니다.")
            },
            error: function (data) {
                alert("변경에 실패하였습니다.")
            }
        })
    }
})


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('.preview-picture')
                .attr('src', e.target.result)
                .width(150)
                .height(150);
        };
        reader.readAsDataURL(input.files[0]);
    }
}
