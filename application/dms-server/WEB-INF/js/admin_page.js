//달의 말일 구하기
function numOfDays(year, month) {
    var daysOfMonth;

    if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
        daysOfMonth = 30;
    } else {
        daysOfMonth = 31;
        if (month == 2) {
            if (year / 4 - parseInt(year / 4) != 0) {
                daysOfMonth = 28;
            } else {
                if (year / 100 - parseInt(year / 100) != 0) {
                    daysOfMonth = 29;
                } else {
                    if (year / 400 - parseInt(year / 400) != 0) {
                        daysOfMonth = 28;
                    } else {
                        daysOfMonth = 29;
                    }
                }
            }
        }
    }
    return daysOfMonth;
}

function getWeek(date) {
  var tempDate = new Date(date.getFullYear(), date.getMonth(), 1);
  var daysOfMonth = numOfDays(tempDate.getFullYear(), date.getMonth());
  var week = parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;



  if(week == 5) {
    if (daysOfMonth == 31 && (tempDate.getDay() == 4 || tempDate.getDay() == 5 || tempDate.getDay() == 6)) {
      return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
    } else if(daysOfMonth == 30 && (tempDate.getDay() == 5 || tempDate.getDay() == 6)) {
      return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
    } else if(daysOfMonth == 29 && tempDate.getDay() == 6) {
      return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
    } else {
      return 0;
    }
  }
  return parseInt(((date.getDate() - 1) + tempDate.getDay()) / 7) + 1;
}

$('#stay_download').on('click', function () {
  var today = new Date();
  var week = getWeek(today);
  if(week == 0) {
    today.setMonth(today.getMonth() + 1);
    week = 1;
  }
  var year = today.getFullYear();
  var month = today.getMonth() + 1;

  location.href = 'http://dsm2015.cafe24.com/stay/download?year=' + year + '&month=' + month + '&week=' + week;
});

$('#goOut_download').on('click', function () {
  var today = new Date();
  var week = getWeek(today);
  if(week == 0) {
    today.setMonth(today.getMonth() + 1);
    week = 1;
  }
  var year = today.getFullYear();
  var month = today.getMonth() + 1;

  location.href = 'http://dsm2015.cafe24.com/goingout/download?year=' + year + '&month=' + month + '&week=' + week;
})

$('#extension_download').on('click', function () {
  location.href = 'http://dsm2015.cafe24.com/extension/download';
})

$("#account-refresh-btn").click(function() {
    $.ajax({
        url: "/account/initialize",
        type: "POST",
        data: {
            number: $("#refresh-student-number").val()
        },
        statusCode: {
            204: function() {
                alert('초기화에 실패했습니다. 학번을 확인해 주세요');
            },
            200: function() {
                alert('초기화에 성공했습니다.');
            }
        },
        error: function(e) {
            alert('계정생성에 실패했습니다.');
        }
    })
})

$("#create-admin-btn").click(function() {
  var lists = $('#create-admin-form').find($('input'));
  for(var index=0;index<lists.length; index++){
    if(lists[index].value.length==0){
      alert('정보를 모두 입력해 주세요!');
      return;
    }
  }
    $.ajax({
        url: "/account/register/admin",
        type: "POST",
        data: $("#create-admin-form").serialize(),
        statusCode: {
            204: function() {
                alert('계정생성에 실패했습니다.');
            },
            200: function() {
                alert('계정생성이 완료되었습니다.');
            }
        },
        error: function(e) {
            alert('계정생성에 실패했습니다.');
        }
    })
})
