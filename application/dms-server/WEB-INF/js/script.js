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
    }
    else {
        window.href = "";
    }
}

function checkIdExists(id) {
    $.post("/account/", {
        id: idStr
    }, function (data, status) {
        if(status){
            window.location.href='/';
        }else{
            alert('로그인 실패');
        }
        alert("Data: " + data + "\nStatus: " + status);
    });
}

function register(params) {
    alert(params);
    $.post("/account/register/student", params, function (data, status) {
        alert(status+'  '+data);
        if(status==200){
            alert('회원가입 성공');
            window.location.href='/';
        }else{
            alert('회원가입 실패');
        }
    });
}
