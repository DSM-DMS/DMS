var classVal = $("#btn-register").attr('class');
$("#register-warn").hide();
$("#login-warn").hide();
$(".allergy").hide();
$(".allergy-arrow").data("clicked", false);

$('.menu-controller').click(function() {
    var top = $('.slider-menu-container');
    if (top.hasClass("hides")) {
        top.removeClass('hides');
    } else {
        top.addClass('hides');
    }
});

function redirect(page) {
    if (!isLogin) {
        $('#myModal').modal('show');
    } else {
        location.href = '/' + page;
    }
}

function checkIdExists() {
    var idStr = $('#register-id').val();
    $.ajax({
        url: "/account/idcheck/student",
        type: "POST",
        data: {
            "id": idStr
        },
        success: function(data, status) {
            $("#btn-register").attr('class', classVal);
            $("#btn-register").removeAttr('disabled');
            $("#register-warn").hide();
        },
        error: function(xhr) {
            if (xhr.status == 404) {
                $("#btn-register").attr('class', (classVal + ' disabled'));
                $("#btn-register").attr('disabled', 'disalbed');
                $("#register-warn").removeClass('text-success');
                $("#register-warn").addClass('text-warning');
                $("#register-warn").text('이미 존재하는 아이디입니다.');
                $("#register-warn").show();
            }
        },
    });
}

function viewPost(category, no) {
    location.href = '/post/content?category=' + category + '&no=' + no;
}

function login(params) {
    $.ajax({
        url: "/account/login/student",
        type: "POST",
        data: params,
        success: function(data, status) {
            location.reload();
        },
        error: function(xhr) {
            $("#login-warn").show();
        },
    });
}

function register(params) {
    $.ajax({
        url: "/account/register/student",
        type: "POST",
        data: params,
        success: function(data, status) {
            $("#register-warn").removeClass('text-warning');
            alert("회원가입에 성공했습니다.")
            $('#signupbox').hide();
            $('#loginbox').show()
        },
        error: function(xhr) {
            $("#register-warn").addClass('text-warning');
            $("#register-warn").text('회원가입에 실패했습니다.');
            $("#register-warn").show();
        },
    });
}

function showAllergy(dom) {
    // 알러지 정보를 숨김
    if ($(dom).data("clicked")) {
        $(dom).siblings(".info").children(".menu").show(100);
        $(dom).siblings(".info").children(".allergy").hide(100);
        $(dom).data("clicked", false);
        $(dom).attr("src", "./images/arrow.png")
    } else {
        // 알러지 정보를 보임
        $(dom).siblings(".info").children(".menu").hide(100);
        $(dom).siblings(".info").children(".allergy").show(100);
        $(dom).data("clicked", true);
        $(dom).attr("src", "./images/arrow2.png")
    }
}
