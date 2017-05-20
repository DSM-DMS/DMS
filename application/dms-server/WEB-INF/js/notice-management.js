var $lastRow = $("#notice-table > tbody:last-child");

$.ajax({
    url: "http://dsm2015.cafe24.com/post/notice/list",
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



{/*<tr>
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
</tr>*/}