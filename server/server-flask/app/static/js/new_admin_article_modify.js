CKEDITOR.replace('editor1');

(function() {
    $.ajax({
        url: "http://dsm2015.cafe24.com" + "/post/" + "notice",
        data: {
            no: 37
        },
        type: 'GET',
        success: function(data) {
            var parsedData = JSON.parse(data);
            $(".clear-input").val(parsedData.title);
            CKEDITOR.instances['editor1'].setData(parsedData.content);
            // $("#editor1").val(parsedData.content);
        },
        error: function(data) {
            alert("글을 불러오지 못했어요.");
        }
    });

    $("#article-modify").on("click", function() {
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
})();

function redirect(page) {
    location.href = '/' + page;
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
