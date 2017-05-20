CKEDITOR.replace('editor1');

(function() {
    $("#article-write").on("click", function() {
        $.ajax({
            url: "/post/" + localStorage.category,
            type: "PETCH",
            data: {
                title: $(".clear-input").val(),
                content: CKEDITOR.instances.editor1.getData(),
                no: getAllUrlParams(document.URL).no
            },
            success: function() {
                redirect("post/admin?category=" + localStorage.category);
            },
            error: function() {
                alert(errorMessage);
            }
        });
    });
})()
