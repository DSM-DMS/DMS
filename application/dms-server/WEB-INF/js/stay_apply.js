var valueArray = new Array();
var currentDate = new Date();
var currentYear = currentDate.getFullYear();
var currentMonth = currentDate.getMonth() + 1;
var prevMonth = new Date(currentYear, currentMonth - 1, 1);
var nextMonth = new Date(currentYear, currentYear - 1, 1);
var lastDay = noofdays(currentYear, currentMonth);
var newDate = new Date(currentYear, currentMonth - 1, 1);
var five_week = false;
prevMonth.setMonth(newDate.getMonth() - 1);
nextMonth.setMonth(newDate.getMonth() + 1);

$('#month').text(currentYear + '.' + currentMonth); //달력 년, 월 표시

/*--------------- ajax ---------------*/

var loadSendDataWeek;
var applySendDataWeek;
var applySendDataValue;
var getDataValue;
var defaultValue;
var defaultSelector = new Array();

$.ajax({
  url: "/apply/stay/default",
  type: "GET",
  async: false,
  success: function(data) {
    defaultValue = data.value;
  }
});

var getData = function () {
  $.ajax({
    url: "/apply/stay",
    type: "GET",
    async: false,
    data: {
      "week": loadSendDataWeek,
    },
    success: function(data) {
      valueArray.push(data.value);
      defaultSelector.push(false);
    },
    error: function(xhr){
      console.log('['+ loadSendDataWeek + '] 404 error : push defaultValue (' + defaultValue + ')');
      valueArray.push(defaultValue);
      defaultSelector.push(true);
    }
  });
};

var applyData = function () {
  $.ajax({
    url: "/apply/stay",
    type: "PUT",
    data: {
      "week": applySendDataWeek,
      "value": applySendDataValue
    },
    success: function() {
      alert('신청되었습니다.');
    }
  });
};

function loadPrev() { //valueArray에 해당 달의 신청 상태 저장
  if(five_week) {
    for (var i = 1; i <= 5; i++) {
        loadSendDataWeek = dateToString(i);
        getData();
    }
  } else {
    for (var i = 1; i <= 4; i++) {
        loadSendDataWeek = dateToString(i);
        getData();
    }
  }
}

function drawPrev() {
    loadPrev();
    for (i = 1; i <= valueArray.length; i++) {
        if(defaultSelector[i - 1]) {
            $('tr:eq(' + (i + 1) + ')').css('border', '1px solid red')
        }
        if (valueArray[i - 1] == 4) { //잔류
        } else if (valueArray[i - 1] == 1) {
            $('tr:eq(' + (i + 1) + ') .fri').attr('class', 'fri go_home');
            $('tr:eq(' + (i + 2) + ') .sun').attr('class', 'sun go_dom');
        } else if (valueArray[i - 1] == 2) {
            $('tr:eq(' + (i + 1) + ') .sat').attr('class', 'sat go_home');
            $('tr:eq(' + (i + 2) + ') .sun').attr('class', 'sun go_dom');
        } else if (valueArray[i - 1] == 3) {
            $('tr:eq(' + (i + 1) + ') .fri').attr('class', 'fri go_home');
            $('tr:eq(' + (i + 1) + ') .sat').attr('class', 'sat go_dom');
        }
    }
}

/*-------------------그리기---------------*/

drawCalendar(newDate, lastDay); //처음 달력 날짜 표시

//이전 달
$('#prev_month').click(function() {
    valueArray = new Array();
    if (currentMonth == 1) {
        currentYear--;
        currentMonth = 12;
    } else {
        currentMonth--;
    }
    $('#month').text(currentYear + '.' + currentMonth);
    newDate = new Date(currentYear, currentMonth - 1, 1);
    prevMonth.setMonth(newDate.getMonth() - 1);
    nextMonth.setMonth(newDate.getMonth() + 1);
    lastDay = noofdays(currentYear, currentMonth);
    clearCalendar();
    drawCalendar(newDate, lastDay);
});

//다음 달
$('#next_month').click(function() {
    valueArray = new Array();
    if (currentMonth == 12) {
        currentYear++;
        currentMonth = 1;
    } else {
        currentMonth++;
    }
    $('#month').text(currentYear + '.' + currentMonth);
    newDate = new Date(currentYear, currentMonth - 1, 1);
    prevMonth.setMonth(newDate.getMonth() - 1);
    nextMonth.setMonth(newDate.getMonth() + 1);
    lastDay = noofdays(currentYear, currentMonth);
    clearCalendar();
    drawCalendar(newDate, lastDay);
});

//달의 말일 구하기
function noofdays(year, month) {
    var daysofmonth;

    if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
        daysofmonth = 30;
    } else {
        daysofmonth = 31;
        if (month == 2) {
            if (year / 4 - parseInt(year / 4) != 0) {
                daysofmonth = 28;
            } else {
                if (year / 100 - parseInt(year / 100) != 0) {
                    daysofmonth = 29;
                } else {
                    if (year / 400 - parseInt(year / 400) != 0) {
                        daysofmonth = 28;
                    } else {
                        daysofmonth = 29;
                    }
                }
            }
        }
    }
    return daysofmonth;
}

//달력 그리기
function drawCalendar(date, lastDay) {
    $('#sixth_week').hide();
    switch (date.getDay()) {
        case 0:
            for (var i = 0; i <= lastDay; i++) {
                var idx = 10 + i; //일요일
                $('#calendar td:eq(' + idx + ')').text(i + 1);
            }
            for(var j = 0; idx <= 51; idx++) {
              $('#calendar td:eq(' + idx + ')').text(++j);
              $('#calendar td:eq(' + idx + ')').attr('class', 'other_month');
            }
            break;
        case 1:
            for (var i = 0; i <= lastDay; i++) {
                var idx = 11 + i; //월요일
                $('#calendar td:eq(' + idx + ')').text(i + 1);
            }
            var prevMonthNum = noofdays(prevMonth.getFullYear(), prevMonth.getMonth() + 1);
            for(var k = 9 + date.getDay(); k >= 10; k--) {
              $('#calendar td:eq(' + k + ')').text(prevMonthNum--);
              $('#calendar td:eq(' + k + ')').attr('class', 'other_month');
            }
            for(var j = 0; idx <= 51; idx++) {
              $('#calendar td:eq(' + idx + ')').text(++j);
              $('#calendar td:eq(' + idx + ')').attr('class', 'other_month');
            }
            break;
        case 2:
            for (var i = 0; i <= lastDay; i++) {
                var idx = 12 + i; //화요일
                $('#calendar td:eq(' + idx + ')').text(i + 1);
            }
            var prevMonthNum = noofdays(prevMonth.getFullYear(), prevMonth.getMonth() + 1);
            for(var k = 9 + date.getDay(); k >= 10; k--) {
              $('#calendar td:eq(' + k + ')').text(prevMonthNum--);
              $('#calendar td:eq(' + k + ')').attr('class', 'other_month');
            }
            for(var j = 0; idx <= 51; idx++) {
              $('#calendar td:eq(' + idx + ')').text(++j);
              $('#calendar td:eq(' + idx + ')').attr('class', 'other_month');
            }
            break;
        case 3:
            for (var i = 0; i <= lastDay; i++) {
                var idx = 13 + i; //수요일
                $('#calendar td:eq(' + idx + ')').text(i + 1);
            }
            var prevMonthNum = noofdays(prevMonth.getFullYear(), prevMonth.getMonth() + 1);
            for(var k = 9 + date.getDay(); k >= 10; k--) {
              $('#calendar td:eq(' + k + ')').text(prevMonthNum--);
              $('#calendar td:eq(' + k + ')').attr('class', 'other_month');
            }
            for(var j = 0; idx <= 51; idx++) {
              $('#calendar td:eq(' + idx + ')').text(++j);
              $('#calendar td:eq(' + idx + ')').attr('class', 'other_month');
            }
            break;
        case 4:
            for (var i = 0; i <= lastDay; i++) {
                var idx = 14 + i; //목요일
                $('#calendar td:eq(' + idx + ')').text(i + 1);
            }
            var prevMonthNum = noofdays(prevMonth.getFullYear(), prevMonth.getMonth() + 1);
            for(var k = 9 + date.getDay(); k >= 10; k--) {
              $('#calendar td:eq(' + k + ')').text(prevMonthNum--);
              $('#calendar td:eq(' + k + ')').attr('class', 'other_month');
            }
            for(var j = 0; idx <= 51; idx++) {
              $('#calendar td:eq(' + idx + ')').text(++j);
              $('#calendar td:eq(' + idx + ')').attr('class', 'other_month');
            }
            break;
        case 5:
            if(lastDay == 31) {
              $('#sixth_week').toggle();
              five_week = true;
            }
            for (var i = 0; i <= lastDay; i++) {
                var idx = 15 + i; //금요일
                $('#calendar td:eq(' + idx + ')').text(i + 1);
            }
            var prevMonthNum = noofdays(prevMonth.getFullYear(), prevMonth.getMonth() + 1);
            for(var k = 9 + date.getDay(); k >= 10; k--) {
              $('#calendar td:eq(' + k + ')').text(prevMonthNum--);
              $('#calendar td:eq(' + k + ')').attr('class', 'other_month');
            }
            for(var j = 0; idx <= 51; idx++) {
              $('#calendar td:eq(' + idx + ')').text(++j);
              $('#calendar td:eq(' + idx + ')').attr('class', 'other_month');
            }
            break;
        case 6:
            if(lastDay == 31 || lastDay == 30) {
              $('#sixth_week').toggle();
              five_week = true;
            }
            for (var i = 0; i <= lastDay; i++) {
                var idx = 16 + i; //토요일
                $('#calendar td:eq(' + idx + ')').text(i + 1);
            }
            var prevMonthNum = noofdays(prevMonth.getFullYear(), prevMonth.getMonth() + 1);
            for(var k = 9 + date.getDay(); k >= 10; k--) {
              $('#calendar td:eq(' + k + ')').text(prevMonthNum--);
              $('#calendar td:eq(' + k + ')').attr('class', 'other_month');
            }
            for(var j = 0; idx <= 51; idx++) {
              $('#calendar td:eq(' + idx + ')').text(++j);
              $('#calendar td:eq(' + idx + ')').attr('class', 'other_month');
            }
            break;
    }
    drawPrev();
}

//달력 초기화
function clearCalendar() {
    for (var i = 0; i < 42; i++) {
      var idx = 10 + i;
        switch (i % 7) {
          case 0:
            $('#calendar td:eq(' + idx + ')').attr('class', 'sun');
            break;
          case 1:
            $('#calendar td:eq(' + idx + ')').attr('class', 'mon');
            break;
          case 2:
            $('#calendar td:eq(' + idx + ')').attr('class', 'tue');
            break;
          case 3:
            $('#calendar td:eq(' + idx + ')').attr('class', 'wed');
            break;
          case 4:
            $('#calendar td:eq(' + idx + ')').attr('class', 'thu');
            break;
          case 5:
            $('#calendar td:eq(' + idx + ')').attr('class', 'fri');
            break;
          case 6:
            $('#calendar td:eq(' + idx + ')').attr('class', 'sat');
            break;
        }
        $('#calendar td:eq(' + idx + ')').text("");
    }
    $('#calendar tbody tr').css("background-color", "white");
    $('#first_week').css('border', '0px');
    $('#second_week').css('border', '0px');
    $('#third_week').css('border', '0px');
    $('#fourth_week').css('border', '0px');
    $('#fifth_week').css('border', '0px');
    $('#sixth_week').toggle();
    five_week = false;
}

$('#first_week').click(function() {
    $('stay_select').val(valueArray[0]);
    $('#calendar tbody tr').css("background-color", "white");
    $('#first_week').css("background-color", "#f1f1f1");
    if (currentMonth < 10) {
      $('#date').val(currentYear + '-0' + currentMonth + '-' + '01');
    } else {
      $('#date').val(currentYear + '-' + currentMonth + '-' + '01');
    }
});

$('#second_week').click(function() {
    $('stay_select').val(valueArray[1]);
    $('#calendar tbody tr').css("background-color", "white");
    $('#second_week').css("background-color", "#f1f1f1");
    if (currentMonth < 10) {
      $('#date').val(currentYear + '-0' + currentMonth + '-' + '02');
    } else {
      $('#date').val(currentYear + '-' + currentMonth + '-' + '02');
    }
});

$('#third_week').click(function() {
    $('stay_select').val(valueArray[2]);
    $('#calendar tbody tr').css("background-color", "white");
    $('#third_week').css("background-color", "#f1f1f1");
    if (currentMonth < 10) {
      $('#date').val(currentYear + '-0' + currentMonth + '-' + '03');
    } else {
      $('#date').val(currentYear + '-' + currentMonth + '-' + '03');
    }
});

$('#fourth_week').click(function() {
    $('stay_select').val(valueArray[3]);
    $('#calendar tbody tr').css("background-color", "white");
    $('#fourth_week').css("background-color", "#f1f1f1");
    if (currentMonth < 10) {
      $('#date').val(currentYear + '-0' + currentMonth + '-' + '04');
    } else {
      $('#date').val(currentYear + '-' + currentMonth + '-' + '04');
    }
});

$('#fifth_week').click(function() {
  if (five_week) {
    $('stay_select').val(valueArray[4]);
    $('#calendar tbody tr').css("background-color", "white");
    $('#fifth_week').css("background-color", "#f1f1f1");
    if (currentMonth < 10) {
      $('#date').val(currentYear + '-0' + currentMonth + '-' + '05');
    } else {
      $('#date').val(currentYear + '-' + currentMonth + '-' + '05');
    }
  } else {
    alert("다음 달 1주로 신청해주세요.");
  }
});


$('#sixth_week').click(function() {
    alert("다음 달 1주로 신청해주세요.");
});

//***********************신청*********************

function dateToString(week) {
  if(newDate.getMonth() + 1 < 10) {
    return newDate.getFullYear().toString() + "-0" + (newDate.getMonth() + 1).toString() + "-0" + week.toString();
  } else {
    return newDate.getFullYear().toString() + "-" + (newDate.getMonth() + 1).toString() + "-0" + week.toString();
  }
};

$('#date').keydown(function(e) {
    e.preventDefault();
});

$('#stay_submit').on('click', function() {
    applySendDataWeek = $('#date').val();
    applySendDataValue = $("#stay_select").val();
    applyData();
});
