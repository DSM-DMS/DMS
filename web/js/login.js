$('#login').on('click', function () {
  $.ajax({
      url: "dsm2015.cafe24.com:10419",
      type: "POST",
      data: this.sendData,
      beforeSend: function(xhr) {
          xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
          xhr.setRequestHeader("command", this.command);
      },
      success: function(data) {
          this.ajaxData = JSON.parse(data);
      }
  });
});
