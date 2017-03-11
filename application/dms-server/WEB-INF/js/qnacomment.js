function getQnaCommentList(callback) {
    $.ajax({
        url: "http://dsm2015.cafe24.com:8088/post/qna/comment",
        type: "GET",
        data: JSON.stringify({
            "no": no
        }),
        success: function(data) {
            // alert("취소 완료.");
            // 화면을 리로드하는 코드
            // seatcontainer의 html을 백지화
            $("div.seatcontainer").html("");
            getSeatData(function(data) {
                for (var loop = 0; loop < JSON.parse(data).result.length; loop++) {
                    callback(JSON.parse(data).result[loop]);
                }
                drawSeat(data, "7.5px")
            });
        }
    });
}



function addTr(data) {
    var newComment = $('<div/>', {
        class: "comment-box"
    }).data("no", data.no);

    var newProfile = $('<img/>', {
        class: "profile comment-box-component",
        text: data.writer;
    }).appendTo(newComment);

    var newWriter = $('<span/>', {
        class: "comment-name comment-box-component",
        text: data.writer;
    }).appendTo(newComment);

    var newDate = $('<span/>', {
        class: "comment-date comment-box-component",
        text: data.comment_date;
    }).appendTo(newComment);

    var newContent = $('<p/>', {
        class: "comment-content comment-box-component",
        text: data.comment_date;
    }).appendTo(newComment);

    var newModify = $('<span/>', {
        class: "comment-modify comment-button comment-box-component",
        text: "수정",
        click: function() {
            // 수정창 로드
            $(newContent).hide(500);
            var newModifyCommentInput = $('<textarea/>', {
                class: "comment-modify-input comment-input-text",
            }).appendTo(newComment);
            newModifyComment.val($(newContent).val());

            var newModifyCommentBtn = $('<button/>', {
                class: "comment-box-component comment-input-button",
                click: function() {
                    $.ajax({
                        url: "http://dsm2015.cafe24.com:8088/post/qna/comment",
                        type: "PATCH",
                        data: {
                            "no": data.no,
                            "content": newModifyComment.val()
                        },
                        success: function(data) {
                            // 다시 qna로드
                        }
                    });
                }
            })
        }
    }).appendTo(newComment);

    var newDelete = $('<span/>', {
        class: "comment-delete comment-button comment-box-component",
        text: "삭제",
        click: function() {
            $.ajax({
                url: "http://dsm2015.cafe24.com:8088/post/qna/comment",
                type: "DELETE",
                data: {
                    "no": data.no
                },
                success: function(data) {
                    // 다시 qna로드
                }
            });
        }
    }).appendTo(newComment);

    newComment.appendTo("div.comment");
}

function setQnestionModifyEvent() {
    $(".question .content-modify").click(function () {
        // 수정페이지 요청
    })
}

function setQnestionDleteEvent() {
    $(".question .content-delete").click(function () {
        // 수정페이지 요청
    })
}

function setAnswerModifyEvent() {
    $(".answer .content-modify").click(function () {
        // 수정페이지 요청
    })
}

function setAnswerModifyEvent() {
    $(".answer .content-modify").click(function () {
        // 수정페이지 요청
    })
}
