// Replace the <textarea id="editor1"> with a CKEditor
// instance, using default configuration.
CKEDITOR.replace('editor1');
setWriteBtnEvent();
// 작성 버튼
function setWriteBtnEvent() {
    $(".editor-button").click(function () {
        $.ajax({
            url: "/post/qna/question",
            type: "PATCH",
            data: {
                content: CKEDITOR.instances.editor1.getData(),
                title: $(".title").val(),
                no
            },
            success: function () {
                // /post/?category=notice
                redirect("/post/?category=qna");
            },
            error: function () {
                alert("수정에 실패했어요. TT");
            }
        })
    })
}
