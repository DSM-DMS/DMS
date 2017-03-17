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

    if ($("#modify-password").val() === $("#modify-password-re").val() && $("#modify-password").val() !== "") {
        $.ajax({
            url: "/account/password/student",
            type: "PATCH",
            data: {
                password: $("modify-password").val()
            },
            success: function () {
                alert("변경이 완료되었어요!")
            },
            error: function () {
                alert("변경에 실패했어요. ㅠㅠ")
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
            $(".modify-password-btn").text("변경");
            $(".modify-password-btn").css({
                "background-color": "#31b0d5"
            });
            $("#modify-password-re").css({
                "background-color": "white"
            });
        }, 2000)
    }
})



$(".modify-profile-picture-btn").click(function() {
    console.log($(".preview-picture").attr("src"));
    if ($(".preview-picture").attr("src") !== null) {
        var formData = new FormData();
        formData.append("profile_image", $("#modify-profile-picture")[0].files[0]);
        $.ajax({
<<<<<<< HEAD
            url: "/upload/image/",
=======
            url: "/upload/image",
>>>>>>> d1e3ffb27823fd2a9475559a393febbd8682bc49
            data: formData,
            processData: false,
            contentType: false,
            type: 'POST',
            success: function(data) {
                alert("변경이 완료되었어요.")
            },
            error: function(data) {
                alert("변경에 실패했어요. TT")
            }
        })
    } else {
        alert("사진을 업로드 해주세요.")
    }
})

function fileToImg(file, result) {
    //var result = $("<img/>");
    var fileReader = new FileReader();
    fileReader.onload = function (e) {
        result.attr("src", e.target.result);
    }
    fileReader.readAsDataURL(file);
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('.preview-picture')
                .attr('src', e.target.result)
                .width(150)
                .height(150);
        };
        console.log("file test = "+input.files[0]);
        reader.readAsDataURL(input.files[0]);
    }
}
