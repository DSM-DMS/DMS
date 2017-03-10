var vh = getVH();
// tr의 높이를 구함 (margin, padding 포함)
var trHeight = 53;
// view Height에 맞는 tr의 갯수
var listNum = getListNum(trHeight);

makeNewTable(1, listNum);

// 1VH의 px값을 구함
function getVH() {
    var windowHeight = $(window).height();
    return (windowHeight / 100);
}

//현재 view height 기준으로 몇개의 tr이 들어가는지 구하는 함수
function getListNum(trHeight) {
    // main의 header등의 height를 제외한 공간의 높이
    console.log($(".main").height());
    var useableSpace = $(".main").height() - $(".articlelist table .tableheader").position().top;
    console.log(useableSpace);
    return (useableSpace / trHeight) - 2;
}

//table에 들어갈 정보와 tr의 갯수를 가지고 테이블 tr생성
function makeNewTable(data, listNum) {
    for (var loop = 0; loop < listNum; loop++) {
        makeNewLine("1", "행복하자", "2017.02.07");
    }
}

//tr추가하는 함수
function makeNewLine(number, title, date) {
    $(".articlelist table").append(
        "<tr>" +
        "<td>" + number + "</td>" +
        "<td>" + title + "</td>" +
        "<td>" + date + "</td>" +
        "</tr>"
    );
}

function PROTOmakeNewTable(data, listNum) {
    var objDataArr = JSON.parse(data).result;
    for (var loop = 0; loop < objDataArr.length; loop++) {
        makeNewLine(objDataArr[loop]);
    }
}

function PROTOmakeNewLine(data) {
    var jsonLength = Object.keys(data).length;
    var newTr = $('<tr/>', {
        click: function(e) {
            // tr 클릭이벤트
        }
    });
    var appendString = "";
    for (var loop in data) {
        appendString += "<td>" + data[loop] + "</td>";
    }
    newTr.append(appendString);
    $(".articlelist table").append(newTr);
}

function ajaxList() {
    $.ajax({
        url: "dsm2015.cafe24.com",
        type: "GET",
        data: {},
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
            xhr.setRequestHeader("command", "115");
        },
        success: function(data) {
            makeNewTable(data, listNum);
        }
    });
}

$(".articlelist table tr").click(function() {
    console.log("test");
    alert("tet");
})


// faq 성공적인 통신

// var  xhr = new XMLHttpRequest();
//
// xhr.open('POST', 'http://dsm2015.cafe24.com:10419');
// xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8;');
// xhr.setRequestHeader('command', '438');
//
// xhr.onload = function() {
//     if (xhr.status === 200) {
//         var userInfo = JSON.parse(xhr.responseText);
//         alert(xhr.responseText);
//     }
//     else if (xhr.status !== 200) {
//         alert('Request failed.  Returned status of ' + xhr.status);
//     }
// };
// xhr.send(JSON.stringify({"year":2017, "month":02, "day": 10}));


// faq response

// {
//   "result": [
//     {
//       "no": 3,
//       "title": "Third FAQ",
//       "content": "This is third FAQ."
//     },
//     {
//       "no": 3,
//       "title": "Third FAQ",
//       "content": "This is third FAQ."
//     },
//     {
//       "no": 3,
//       "title": "Third FAQ",
//       "content": "This is third FAQ."
//     }
//   ]
// }
