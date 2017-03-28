$('#myModal').modal('show');

function adminLogin(params) {
    $.ajax({
        url: "/account/login/admin",
        type: "POST",
        data: params,
        success: function(data, status) {
            location.href = '/admin';
        },
        error: function(xhr) {
            $("#login-warn").show();
        },
    });
}
