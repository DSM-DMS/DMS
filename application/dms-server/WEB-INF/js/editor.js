// Replace the <textarea id="editor1"> with a CKEditor
// instance, using default configuration.
CKEDITOR.replace('editor1');

var url;
var type;
var callback;
var errorMessage;
var data;

function checkCategotyAndType() {
    if ($("hide-type").text() == "write") {
        errorMessage = "작성에 실패했어요 TT"
    } else if ($("hide-type").text() == "modify") {
        errorMessage = "수정에 실패했어요 TT"
    }

    if ($(".hide-categoty").text() == "notice" && $("hide-type").text() == "write") {
        // 공지 작성
        url = "/post/notice";
        type = "POST";
        data = {
            category: "notice",
            type: "write"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=notice");
        };
    } else if ($(".hide-categoty").text() == "notice" && $("hide-type").text() == "modify") {
        // 공지 수정
        url = "/post/notice";
        type = "PATCH";
        data = {
            category: "notice",
            type: "modify"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=notice");
        };
    } else if ($(".hide-categoty").text() == "faq" && $("hide-type").text() == "write") {
        // faq 작성
        url = "/post/faq";
        type = "POST";
        data = {
            category: "faq",
            type: "write"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=faq");
        };
    } else if ($(".hide-categoty").text() == "faq" && $("hide-type").text() == "modify") {
        // faq 수정
        url = "/post/faq";
        type = "PATCH";
        data = {
            category: "faq",
            type: "modify"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=faq");
        };
    } else if ($(".hide-categoty").text() == "report" && $("hide-type").text() == "write") {
        // 시설고장 작성
        url = "/post/report";
        type = "POST";
        data = {
            category: "report",
            type: "write"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=report");
        };
    } else if ($(".hide-categoty").text() == "report" && $("hide-type").text() == "modify") {
        // 시설고장 수정
        url = "/post/report";
        type = "PATCH";
        data = {
            category: "report",
            type: "modify"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=report");
        };
    } else if ($(".hide-categoty").text() == "reportResult" && $("hide-type").text() == "write") {
        // 시설고장답 작성
        url = "/post/report";
        type = "PUT";
        data = {
            category: "reportResult",
            type: "write"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=report");
        };
    } else if ($(".hide-categoty").text() == "rule" && $("hide-type").text() == "write") {
        // 규칙 작성
        url = "/post/rule";
        type = "POST";
        data = {
            category: "rule",
            type: "write"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=rule");
        };
    } else if ($(".hide-categoty").text() == "rule" && $("hide-type").text() == "modify") {
        // 규칙 수정
        url = "/post/rule";
        type = "PATCH";
        data = {
            category: "rule",
            type: "modify"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=rule");
        };
    } else if ($(".hide-categoty").text() == "qnaQuestion" && $("hide-type").text() == "write") {
        // qna질문 작성
        url = "/post/qna/question";
        type = "POST";
        data = {
            category: "qnaQuestion",
            type: "write"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=qna");
        };
    } else if ($(".hide-categoty").text() == "qnaQuestion" && $("hide-type").text() == "modify") {
        // qna질문 수정
        url = "/post/qna/question";
        type = "PATCH";
        data = {
            category: "qnaQuestion",
            type: "modify"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=qna");
        };
    } else if ($(".hide-categoty").text() == "qnaAnswer" && $("hide-type").text() == "write") {
        // qna답 작성
        url = "/post/qna/answer";
        type = "PUT";
        data = {
            category: "qnaAnswer",
            type: "write"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=qna");
        };
    }
}


function setWriteBtnEvent() {
    $(".editor-button").click(function() {
        // ajax 데이터 설정
        checkCategotyAndType();
        $.ajax({
            url: url,
            type: type,
            data: data,
            success: callback();
            error: function () {
                alert(errorMessage);
            }
        })
    })
}
