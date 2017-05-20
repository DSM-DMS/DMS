var $lastRow = $("#notice-table > tbody:last-child");
var $articleDelete = $("#article-delete");
var $articleWrite = $("#article-write");
var category = getAllUrlParams(document.URL).category;
var category = "notice";

$articleDelete.on("click", function() {
    var deleteArticleList = [];
    $(".md-checkbox input:checked").each(function() {
        // deleteArticleList.push($(this).arrt("id").slice(10));
        $.ajax({
            url: "/post/" + category,
            data: {
                no: $(this).attr("id").slice(10)
            },
            type: 'DELETE',
            success: function(data) {
                location.reload();
            },
            error: function(data) {
                alert("삭제에 실패했어요. TT");
            }
        });
    });
});

$.ajax({
    url: "http://dsm2015.cafe24.com/post/" + category + "/list",
    type: "GET",
    success: function(data) {
        var parsedData = JSON.parse(data).result;
        parsedData.forEach(function(data) {
            $lastRow.append(
                $('<tr>')
                .append(
                    $('<td>')
                    .append(
                        $('<div>')
                        .addClass('md-checkbox')
                        .append(
                            $('<input>')
                            .attr('id', "notice-id-" + data.no)
                            .attr('type', 'checkbox')
                        )
                        .append(
                            $('<label>')
                            .attr('for', "notice-id-" + data.no)
                        )
                    )
                )
                .append(
                    $('<td>')
                    .html(data.no)
                )
                .append(
                    $('<td>')
                    .append(
                        $('<a>')
                        .html(data.title)
                        .attr('href', '#')
                    )
                )
                .append(
                    $('<td>')
                    .html('사감부')
                )
            )
        });
    },
    error: function() {
        console.log("failed to get posts");
    }
});

$("#notice-table").on("click", "tr", function() {
    redirect("post/content?category=" + getAllUrlParams(document.URL).category + "&no=" + $(this).find("input").attr("id").slice(10));
});

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



{
    /*<tr>
        <td>
            <div class="md-checkbox">
            <input id="notice-id-2" type="checkbox">
            <label for="notice-id-2"></label>
            </div>
        </td>
        <td>
            1
        </td>
        <td>
            기숙사 규정 안내
        </td>
        <td>
            사감부
        </td>
    </tr>*/
}
