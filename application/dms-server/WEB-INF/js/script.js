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

function checkIdExists(inputObj) {
    var idStr = inputObj.value();
    $.post("/account/idcheck/student/", {
        id: idStr
    }, function (data, status) {
        if(status==201){
            alert('asasv');
        }else{
            
        }
    });
}

function register(params) {
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
