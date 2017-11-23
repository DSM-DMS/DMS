$('#btn-signup').on('click', function() {
    $.ajax({
        url: "/account/login/admin",
        type: "POST",
        data: {
            "id": $('#login-username').val(),
            "pw": $('#login-password').val()
        },
        success: function(data) {
            let parsedData = JSON.parse(data);
            setCookie("JWT", parsedData.access_token, 3);
            location.href = '/admin';
        },
        error: function(xhr) {
            $("#login-warn").show();
        },
    });
});

function setCookie(cname,cvalue,exdays) {
    let d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}