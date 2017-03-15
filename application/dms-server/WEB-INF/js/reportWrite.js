// Replace the <textarea id="editor1"> with a CKEditor
// instance, using default configuration.
CKEDITOR.replace('editor1');
setWriteBtnEvent();
// 작성 버튼
function setWriteBtnEvent() {
    $(".editor-button").click(function () {
        $.ajax({
            url: "/post/report",
            type: "POST",
            data: {
                content: CKEDITOR.instances.editor1.getData(),
                title: $(".title").val(),
                room: $(".room").val()
            },
            success: function () {
                // /post/?category=notice
                redirect("/post/?category=report");
            },
            error: function () {
                alert("작성에 실패했어요. TT");
            }
        })
    })
}
