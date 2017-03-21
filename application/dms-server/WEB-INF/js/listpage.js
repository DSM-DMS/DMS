$(".write-btn").click(function () {
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
    localStorage.setItem('category', getAllUrlParams(document.URL).category);
    localStorage.setItem('type', 'write');
    console.log("post/" + getAllUrlParams(document.URL).category + "/write");
    redirect("post/" + getAllUrlParams(document.URL).category + "/write");
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

    for (var i=0; i<arr.length; i++) {
      // separate the keys and the values
      var a = arr[i].split('=');

      // in case params look like: list[]=thing1&list[]=thing2
      var paramNum = undefined;
      var paramName = a[0].replace(/\[\d*\]/, function(v) {
        paramNum = v.slice(1,-1);
        return '';
      });

      // set parameter value (use 'true' if empty)
      var paramValue = typeof(a[1])==='undefined' ? true : a[1];

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
