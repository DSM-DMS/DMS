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
        window.href = '/'+page;
    }
}

function checkIdExists() {
    var idStr = $('#register-id').val();
    $.ajax({
        url: "http://dsm2015.cafe24.com:8089/account/idcheck/student",
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
            $("#register-warn").show();
          }
        },
    });
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
              alert('회원가입 성공');
        },
        error: function (xhr) {
              alert('회원가입 실패');
        },
    });
}
