$(".modify-btn").click(function() {
    if (getAllUrlParams(document.URL).category == "qna") {
        localStorage.setItem('category', getAllUrlParams(document.URL).category);
        localStorage.setItem('type', 'modify');
        localStorage.setItem('no', getAllUrlParams(document.URL).no);
        redirect("post/" + "question" + "/modify?no=" + getAllUrlParams(document.URL).no);
        return;
    }

    localStorage.setItem('category', getAllUrlParams(document.URL).category);
    localStorage.setItem('type', 'modify');
    localStorage.setItem('no', getAllUrlParams(document.URL).no);

    console.log("post/" + getAllUrlParams(document.URL).category + "/modify");
    redirect("post/" + getAllUrlParams(document.URL).category + "/modify?no=" + getAllUrlParams(document.URL).no);
});

$(".delete-btn").click(function() {
  $.ajax({
      url: "/post/"+localStorage.getItem('category'),
      data: {no: Number(localStorage.getItem('no'))},
      type: 'DELETE',
      success: function(data) {
          alert("삭제가 완료되었어요.");
      },
      error: function(data) {
          alert("삭제에 실패했어요. TT");
      }
  });
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
