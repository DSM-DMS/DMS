//***********************달력*********************
var date = new Date();
var currentYear = date.getFullYear();
var currentMonth = date.getMonth()+1;
var currentDate = date.getDate();
var lastDay = noofdays(currentMonth, currentYear);
var newDate = new Date(currentYear, currentMonth - 1, 1);

$('#month').text(currentYear+'.'+currentMonth); //달력 년, 월 표시

drawCalendar(newDate, lastDay); //달력 날짜 표시

//이전 달
$('#prev_month').click(function() {
  if(currentMonth == 1) {
    currentYear--;
    currentMonth = 12;
  } else {
    currentMonth--;
  }
  $('#month').text(currentYear+'.'+currentMonth);
  newDate = new Date(currentYear, currentMonth - 1, 1);
  lastDay = noofdays(currentMonth, currentYear);
  clearCalendar();
  drawCalendar(newDate, lastDay);
});

//다음 달
$('#next_month').click(function() {
  if(currentMonth == 12) {
    currentYear++;
    currentMonth = 1;
  } else {
    currentMonth++;
  }
  $('#month').text(currentYear+'.'+currentMonth);
  newDate = new Date(currentYear, currentMonth - 1, 1);
  lastDay = noofdays(currentMonth, currentYear);
  clearCalendar();
  drawCalendar(newDate, lastDay);
});

//달의 말일 구하기
function noofdays(mm, yyyy)	{
	var daysofmonth;

	if((mm == 4) || (mm ==6) || (mm ==9) || (mm == 11)) {
		daysofmonth = 30;
	}
	else {
		daysofmonth = 31;
		if(mm == 2) {
			if (yyyy/4 - parseInt(yyyy/4) != 0) {
				daysofmonth = 28;
			}	else {
				if (yyyy/100 - parseInt(yyyy/100) != 0) {
					daysofmonth = 29;
				} else {
					if(yyyy/400 -  parseInt(yyyy/400) != 0) {
						daysofmonth = 28;
					} else {
						daysofmonth =29;
					}
				}
			}
		}
	}
	return daysofmonth;
}

//달력 그리기
function drawCalendar(date, lastDay) {
  $.post("http://", {year:date.getFullYear(), month:date.getMonth()}, function(date) {
    drawPrev(date);
  })
  switch(date.getDay()) {
    case 0:
      for(var i = 0; i < lastDay; i++) {
        var idx = 10 + i;
        $('#calendar td:eq('+idx+')').text(i+1);
      }
      break;
    case 1:
      for(var i = 0; i < lastDay; i++) {
        var idx = 11 + i;
        $('#calendar td:eq('+idx+')').text(i+1);
      }
      break;
    case 2:
      for(var i = 0; i < lastDay; i++) {
        var idx = 12 + i;
        $('#calendar td:eq('+idx+')').text(i+1);
      }
      break;
    case 3:
      for(var i = 0; i < lastDay; i++) {
        var idx = 13 + i;
        $('#calendar td:eq('+idx+')').text(i+1);
      }
      break;
    case 4:
      for(var i = 0; i < lastDay; i++) {
        var idx = 14 + i;
        $('#calendar td:eq('+idx+')').text(i+1);
      }
      break;
    case 5:
      for(var i = 0; i < lastDay; i++) {
        var idx = 15 + i;
        $('#calendar td:eq('+idx+')').text(i+1);
      }
      break;
    case 6:
      for(var i = 0; i < lastDay; i++) {
        var idx = 16 + i;
        $('#calendar td:eq('+idx+')').text(i+1);
      }
      break;
  }
}

//달력 초기화
function clearCalendar() {
  for(var i = 0; i < 35; i++) {
    $('#calendar td:eq('+(i+10)+')').text("");
    $('#calendar tbody tr').css("background-color", "white");
  }
}

$('#first_week').click(function() {
  $('#calendar tbody tr').css("background-color", "white");
  $('#first_week').css("background-color", "rgb(197, 197, 197)");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'first_week');
});

$('#second_week').click(function(){
  $('#calendar tbody tr').css("background-color", "white");
  $('#second_week').css("background-color", "rgb(197, 197, 197)");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'second_week');
});

$('#third_week').click(function() {
  $('#calendar tbody tr').css("background-color", "white");
  $('#third_week').css("background-color", "rgb(197, 197, 197)");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'third_week');
});

$('#fourth_week').click(function() {
  $('#calendar tbody tr').css("background-color", "white");
  $('#fourth_week').css("background-color", "rgb(197, 197, 197)");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'fourth_week');
});

$('#fifth_week').click(function() {
  $('#calendar tbody tr').css("background-color", "white");
  $('#fifth_week').css("background-color", "rgb(197, 197, 197)");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'fifth_week');
});

//********************이전 데이터 표시*********************
function prevDate(week, sel) {
  this.week = week;
  this.sel = sel; // 0.잔류 1.금요귀가 2.토요귀가 3.토요귀사
}

function drawPrev(date) {
  for(i = 0; i < date.length; i++) {
    if(date.sel == 0) {
    } else if(date.sel == 1) {
      $('tr:eq('+(date.week+1)+') .fri').attr('class', 'fri go_home');
      $('tr:eq('+(date.week+2)+') .sun').attr('class', 'sun go_dom');
    } else if(date.sel == 2) {
      $('tr:eq('+(date.week+1)+') .sat').attr('class', 'sat go_home');
      $('tr:eq('+(date.week+2)+') .sun').attr('class', 'sun go_dom');
    } else if(date.sel == 3) {
      $('tr:eq('+(date.week+1)+') .fri').attr('class', 'fri go_home');
      $('tr:eq('+(date.week+1)+') .sat').attr('class', 'sat go_dom');
    }
  }
}

//***********************신청*********************
$('#apply_form input[type="radio"]').checkboxradio();

$("#date").keydown(function(e){
        e.preventDefault();
});
