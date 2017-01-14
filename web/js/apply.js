//신청탭 over 이벤트
$(".remote .category").children("a").eq(0).mouseover(() => {
    //신청 text를 white로
    $(".remote .category").children("a").eq(0).animate({
        fontSize: "1.5em",
        color: "white"
    })

    //remote background-color를 신청색으로
    $(".remote").animate({
        backgroundColor: "rgb(52, 152, 219)",
        backgroundImage: "linear-gradient( to right top, transparent 33%, gray 33%, gray 66%, transparent 66%)"

    });

    //DMS hide
    $(".remote h1").slideUp();

    //신청탭 하위메뉴 show
    $(".remote .category .application").show();

    //기숙사, 마이페이지탭 hide
    $(".remote .category").children("a").eq(1).hide();
    $(".remote .category").children("a").eq(2).hide();

})

//기숙사탭 over 이벤트
$(".remote .category").children("a").eq(1).mouseover(() => {
    //기숙사 text를 white로
    $(".remote .category").children("a").eq(1).animate({
        fontSize: "1.5em",
        color: "white"
    })

    //remote background-color를 신청색으로
    $(".remote").animate({
        backgroundColor: "rgb(142, 68, 173)"
    }, 500);

    //DMS hide
    $(".remote h1").slideUp();

    //기숙사탭 하위메뉴 show
    $(".remote .category .dom").show();

    //공지사항, 마이페이지탭 hide
    $(".remote .category").children("a").eq(0).hide();
    $(".remote .category").children("a").eq(2).hide();
})

$(".remote .category").children("a").eq(2).mouseover(() => {
    //remote background-color를 마이페이지색으로
    $(".remote").animate({
        backgroundColor: "rgb(192, 57, 43)"
    });

    //마이페이지 text를 white로
    $(".remote .category").children("a").eq(2).animate({
        fontSize: "1em",
        color: "white"
    })

    //DMS hide
    $(".remote h1").slideUp();

    //공지사항, 기숙사 hide
    $(".remote .category").children("a").eq(0).hide();
    $(".remote .category").children("a").eq(1).hide();
})

//리모트 leave 이벤트
$(".remote").mouseleave(() => {
    //모든 탭 글자색을 윈대래로
    $(".remote .category").children("a").eq(0).animate({
        color: "rgb(52, 152, 219)",
        fontSize: "1em"
    })

    $(".remote .category").children("a").eq(1).animate({
        color: "rgb(142, 68, 173)",
        fontSize: "1em"

    },500)

    $(".remote .category").children("a").eq(2).animate({
        color: "color: rgb(192, 57, 43)",
        fontSize: "1em"
    })

    //remote backgroundColor를 원래대로
    $(".remote").animate({
        backgroundColor: "white"
    });

    // DMS복구
    $(".remote h1").slideDown();

    //모든 탭 show
    $(".remote .category").children("a").eq(0).show()
    $(".remote .category").children("a").eq(1).show()
    $(".remote .category").children("a").eq(2).show();

    //탭 하부메뉴 hide
    $(".remote .category .application").hide(0);
    $(".remote .category .dom").hide(0);
})

//***********************달력*********************
var date = new Date();
var currentYear = date.getFullYear();
var currentMonth = date.getMonth()+1;
var currentDate = date.getDate();
var lastDay = noofdays(currentMonth, currentYear);
var newDate = new Date(currentYear, currentMonth - 1, 1);

$('#month').text(currentYear+'.'+currentMonth); //달력 년, 월 표시
drawCalendar(newDate, lastDay);

//이전 달
$('#prev_month').click(() => {
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
$('#next_month').click(() => {
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

$('#first_week').click(() => {
  $('#calendar tbody tr').css("background-color", "white");
  $('#first_week').css("background-color", "yellow");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'first_week');
});

$('#second_week').click(() =>{
  $('#calendar tbody tr').css("background-color", "white");
  $('#second_week').css("background-color", "yellow");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'second_week');
});

$('#third_week').click(() => {
  $('#calendar tbody tr').css("background-color", "white");
  $('#third_week').css("background-color", "yellow");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'third_week');
});

$('#fourth_week').click(() => {
  $('#calendar tbody tr').css("background-color", "white");
  $('#fourth_week').css("background-color", "yellow");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'fourth_week');
});

$('#fifth_week').click(() => {
  $('#calendar tbody tr').css("background-color", "white");
  $('#fifth_week').css("background-color", "yellow");
  $('#date').val(currentYear+'.'+currentMonth+'.'+'fifth_week');
});
