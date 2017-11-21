$(".write-btn").click(function() {
    // $.ajax({
    //     url: "post/" + getAllUrlParams(document.URL).category + "write",
    //     type: "GET",
    //     data: {
    //         category: getAllUrlParams(document.URL).category,
    //         type: "write"
    //     },
    //     success: function (data) {
    //         redirect(data);
    //     },
    //     error: function () {
    //         alert("글을 작성할 수 없어요 TT");
    //     }
    // })
    if (getAllUrlParams(document.URL).category == "qna") {
        localStorage.setItem('category', getAllUrlParams(document.URL).category);
        localStorage.setItem('type', 'write');
        redirect("post/" + "question" + "/write");
        return;
    }
    localStorage.setItem('category', getAllUrlParams(document.URL).category);
    localStorage.setItem('type', 'write');
    console.log("post/" + getAllUrlParams(document.URL).category + "/write");
    redirect("post/" + getAllUrlParams(document.URL).category + "/write");
});

function setListEvent() {
    $("table tr").click(function() {
        console.log($(this).children("td").eq(0).text());
        redirect("post/content?category=" + getAllUrlParams(document.URL).category + "&no=" + $(this).children("td").eq(0).text());
    });
}
setListEvent();

$(".edit").on('click', function(e) {
    e.stopPropagation();
    var no = $(this).parent().parent().children(0).first().text();
    if (getAllUrlParams(document.URL).category == "qna") {
        localStorage.setItem('category', getAllUrlParams(document.URL).category);
        localStorage.setItem('type', 'modify');
        localStorage.setItem('no', getAllUrlParams(document.URL).no);
        redirect("post/" + "question" + "/modify?no=" + getAllUrlParams(document.URL).no);
        return;
    }
    localStorage.setItem('category', getAllUrlParams(document.URL).category);
    localStorage.setItem('type', 'modify');
    localStorage.setItem('no', no);

    console.log("post/" + getAllUrlParams(document.URL).category + "/modify");
    redirect("post/" + getAllUrlParams(document.URL).category + "/modify?no=" + no);
});

$(".delete").on('click', function(e) {
    e.stopPropagation();
    var no = $(this).parent().parent().children().first().text();
    $.ajax({
        url: "/post/" + getAllUrlParams(document.URL).category,
        data: {
            no: Number(no)
        },
        type: 'DELETE',
        success: function(data) {
            redirect("post/admin?category=" + getAllUrlParams(document.URL).category);
        },
        error: function(data) {
            alert("삭제에 실패했어요. TT");
        }
    });
});

$(".preview").on('click', function(e) {
    e.stopPropagation();
    var no = $(this).parent().parent().children().first().text();
    $.ajax({
        url: "/post/notice/preview",
        data: {
            no: Number(no)
        },
        type: 'POST',
        success: function(data) {
            alert("프리뷰로 설정되었습니다.");
        },
        error: function(data) {
            alert("프리뷰 설정을 실패했습니다.");
        }
    });
});

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

function redirect(page) {
    location.href = '/' + page;
}
