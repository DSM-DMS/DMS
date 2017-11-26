$('#btn-signup').on('click', function() {
    $.ajax({
        url: "/admin/auth",
        type: "POST",
        data: {
            "id": $('#login-username').val(),
            "pw": $('#login-password').val()
        },
        success: function(data) {
            setCookie("JWT", data.access_token, 3);
            $.ajax({
                url: "/admin",
                type: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader ("Authorization", "JWT " + getCookie("JWT"));
                },          
                success: function(data) {
                },
                error: function(xhr) {
                },
            });
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

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}