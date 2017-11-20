getQnaCommentList(addTr);
setCommentBtnEvent();
setQnestionModifyEvent();

function getProfilePicture(callback) {
    $.ajax({
        url: "/account/student",
        type: "GET",
        data: {},
        success: function(data) {
            callback(data.profile_image);
        }
    });
}

function getQnaCommentList(callback) {
    $.ajax({
        url: "/post/qna/comment",
        type: "GET",
        data: {
            "no": getAllUrlParams(document.URL).no
        },
        success: function(data) {
            callback(data);
        }
    });
}

function addTr(data) {
    var commentArr = JSON.parse(data).result;

    for (var i = 0; i < commentArr.length; i++) {
        (function(i) {
            var newComment = $('<div/>', {
                class: "comment-box"
            }).data("no", commentArr[i].no);
            var newProfile = $('<img/>', {
                class: "profile comment-box-component",
                text: commentArr[i].writer
            }).appendTo(newComment);

            var newWriter = $('<span/>', {
                class: "comment-name comment-box-component",
                text: commentArr[i].writer
            }).appendTo(newComment);

            var newDate = $('<span/>', {
                class: "comment-date comment-box-component",
                text: commentArr[i].comment_date
            }).appendTo(newComment);

            var newContent = $('<p/>', {
                class: "comment-content comment-box-component",
                text: commentArr[i].content
            }).appendTo(newComment);

            var newModify = $('<span/>', {
                class: "comment-modify comment-button comment-box-component",
                text: "수정",
                click: function() {
                    // 수정창 로드
                    console.log("수정");
                    $(newContent).hide(500);
                    var newModifyCommentInput = $('<textarea/>', {
                        class: "comment-modify-input comment-input-text",
                    }).appendTo(newComment);
                    newModifyCommentInput.val($(newContent).val());

                    var newModifyCommentBtn = $('<button/>', {
                        class: "comment-box-component comment-input-button",
                        click: function() {
                            $.ajax({
                                url: "/post/qna/comment",
                                type: "PATCH",
                                data: {
                                    "no": commentArr[i].no,
                                    "content": newModifyCommentInput.val()
                                },
                                success: function(data) {
                                    // 다시 qna로드
                                }
                            });
                        }
                    }).appendTo(newComment);
                }
            }).appendTo(newComment);
            var newDelete = $('<span/>', {
                class: "comment-delete comment-button comment-box-component",
                text: "삭제",
                click: function() {
                    $.ajax({
                        url: "/post/qna/comment",
                        type: "DELETE",
                        data: {
                            "no": commentArr[i].no
                        },
                        success: function(data) {
                            // 다시 qna로드
                        }
                    });
                }
            }).appendTo(newComment);
            newComment.appendTo("div.comment");
        })(i);
    }

}

function setQnestionModifyEvent() {
    $(".question .content-modify").click(function() {
        // 수정페이지 요청
        $.ajax({
            url: "???",
            type: "???",
            data: {
                category: getAllUrlParams(document.URL).category,
                type: "modify"
            },
            success: function(data) {
                redirect(data);
            },
            error: function() {
                alert("접근할 수 없어요 TT");
            }
        });
    });
}

function setCommentBtnEvent() {
    $(".comment-input-button").click(function() {
        $.ajax({
            url: "/post/qna/comment",
            type: "POST",
            data: {
                no: getAllUrlParams(document.URL).no,
                content: $(".comment-input-text").val()
              //  writer: "writer"
            },
            success: function(data) {
                getQnaCommentList(addTr);
            },
            error: function() {
                alert("접근할 수 없어요 TT");
            }
        });
    });
}

function setQnestionDleteEvent() {
    $(".question .content-delete").click(function() {
        // 수정페이지 요청
    });
}

function setAnswerModifyEvent() {
    $(".answer .content-modify").click(function() {
        // 수정페이지 요청
    });
}

function setAnswerModifyEvent() {
    $(".answer .content-modify").click(function() {
        // 수정페이지 요청
    });
}

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
