var classVal = $("#btn-register").attr('class');
$("#register-warn").hide();
$("#login-warn").hide();

$('.menu-controller').click(function () {
    var top = $('.slider-menu-container');
    if (top.hasClass("hides")) {
        top.removeClass('hides');
    }
    else {
        top.addClass('hides');
    }
});

function redirect(page) {
    if (!isLogin) {
        $('#myModal').modal('show');
    } else {
        location.href = '/'+page;
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
        error: function (xhr) {
          if(xhr.status == 404) {
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

function viewPost(category, no){
    location.href= '/post/content?category='+category+'&no='+no;
}

function login(params) {
    $.ajax({
        url: "/account/login/student",
        type: "POST",
        data: params,
        success: function(data, status) {
          location.reload();
        },
        error: function (xhr) {
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
            $("#register-warn").addClass('text-success');
            $("#register-warn").removeClass('text-warning');
            $("#register-warn").text('회원가입에 성공했습니다.');
            $("#register-warn").show();
        },
        error: function (xhr) {
            $("#register-warn").removeClass('text-success');
            $("#register-warn").addClass('text-warning');
            $("#register-warn").text('회원가입에 실패했습니다.');
            $("#register-warn").show();
        },
    });
}
