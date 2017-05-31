// Replace the <textarea id="editor1"> with a CKEditor
// instance, using default configuration.
CKEDITOR.replace('editor1');

var url;
var type;
var callback;
var errorMessage;
var data;

function checkCategotyAndType() {
    if (localStorage.type == "write") {
        errorMessage = "작성에 실패했어요 TT";
    } else if (localStorage.type == "modify") {
        errorMessage = "수정에 실패했어요 TT";
    }

    if (localStorage.category == "notice" && localStorage.type == "write") {
        // 공지 작성
        url = "/post/notice";
        type = "POST";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData()
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=notice");
        };
    } else if (localStorage.category == "notice" && localStorage.type == "modify") {
        // 공지 수정
        url = "/post/notice";
        type = "PATCH";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData(),
            no: Number(localStorage.getItem('no'))
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=notice");
        };
    } else if (localStorage.category == "faq" && localStorage.type == "write") {
        // faq 작성
        url = "/post/faq";
        type = "POST";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData()
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=faq");
        };
    } else if (localStorage.category == "faq" && localStorage.type == "modify") {
        // faq 수정
        url = "/post/faq";
        type = "PATCH";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData(),
            no: Number(localStorage.getItem('no'))
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=faq");
        };
    } else if (localStorage.category == "report" && localStorage.type == "write") {
        // 시설고장 작성
        url = "/post/report";
        type = "POST";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData(),
            room: $(".room").val()
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=report");
        };
    } else if (localStorage.category == "report" && localStorage.type == "modify") {
        // 시설고장 수정
        url = "/post/report";
        type = "PATCH";
        data = {
            category: "report",
            type: "modify",
            room: $(".room").val(),
            no: Number(localStorage.getItem('no'))
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=report");
        };
    } else if (localStorage.category == "reportResult" && localStorage.type == "write") {
        // 시설고장답 작성
        url = "/post/report";
        type = "PUT";
        data = {
            content: CKEDITOR.instances.editor1.getData()
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=report");
        };
    } else if (localStorage.category == "rule" && localStorage.type == "write") {
        // 규칙 작성
        url = "/post/rule";
        type = "POST";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData()
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=rule");
        };
    } else if (localStorage.category == "rule" && localStorage.type == "modify") {
        // 규칙 수정
        url = "/post/rule";
        type = "PATCH";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData(),
            no: Number(localStorage.getItem('no'))
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=rule");
        };
    } else if (localStorage.category == "qna" && localStorage.type == "write") {
        // qna질문 작성
        url = "/post/qna/question";
        type = "POST";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData(),
            writer: "writer"
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=qna");
        };
    } else if (localStorage.category == "qna" && localStorage.type == "modify") {
        // qna질문 수정
        url = "/post/qna/question";
        type = "PATCH";
        data = {
            title: $(".clear-input").val(),
            content: CKEDITOR.instances.editor1.getData(),
            no: Number(localStorage.getItem('no'))
        };
        callback = function() {
            // /post/?category=notice
            redirect("/post/?category=qna");
        };
    } else if (localStorage.category == "qnaAnswer" && localStorage.type == "write") {
        // qna답 작성
        url = "/post/qna/answer";
        type = "PUT";
        data = {
            content: CKEDITOR.instances.editor1.getData(),
            no: getAllUrlParams(document.URL).no
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
            success: callback(),
            error: function () {
                alert(errorMessage);
            }
        });
    });
}

function redirect(page) {
    location.href = page;
}

setWriteBtnEvent();

function getAllUrlParams(url) {

    // get query string from url (optional) or window
    var queryString = url ? url.split('?')[1] : window.location.search.slice(1);

    // we'll store the parameters here
    var obj = {};

    // if query string exists
    if (queryString) {

        // stuff after # is not part of query string, so get rid of it
        queryString = queryString.split('#')[0];

        // split our query string into its component parts
        var arr = queryString.split('&');

        for (var i = 0; i < arr.length; i++) {
            // separate the keys and the values
            var a = arr[i].split('=');

            // in case params look like: list[]=thing1&list[]=thing2
            var paramNum = undefined;
            var paramName = a[0].replace(/\[\d*\]/, function(v) {
                paramNum = v.slice(1, -1);
                return '';
            });

            // set parameter value (use 'true' if empty)
            var paramValue = typeof(a[1]) === 'undefined' ? true : a[1];

            // (optional) keep case consistent
            paramName = paramName.toLowerCase();
            paramValue = paramValue.toLowerCase();

            // if parameter name already exists
            if (obj[paramName]) {
                // convert value to array (if still string)
                if (typeof obj[paramName] === 'string') {
                    obj[paramName] = [obj[paramName]];
                }
                // if no array index number specified...
                if (typeof paramNum === 'undefined') {
                    // put the value on the end of the array
                    obj[paramName].push(paramValue);
                }
                // if array index number specified...
                else {
                    // put the value at that index number
                    obj[paramName][paramNum] = paramValue;
                }
            }
            // if param name doesn't exist yet, set it
            else {
                obj[paramName] = paramValue;
            }
        }
    }

    return obj;
}
