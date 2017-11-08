var surveyCnt = 0;
var choice = '<input style="margin-bottom: 1.25rem" type="text" name="" value="" placeholder="답안을 입력해 주세요">';

function getMutipleChoice() {
    return '<div class=""><input survey-number="survey' + surveyCnt + '" id="survey' + surveyCnt + '"style="margin-bottom: 1.25rem; font-size:1.5em" class="clear-input" type="text" name="" value="" placeholder="항목의 제목을 쓰세요"><input id="finish' + surveyCnt + '" style="float:right; margin: 0;" class="editor-button btn btn-default" type="button" name="" value="추가" onclick=addChoice()></div>' +
        '<div choies-number="choices' + surveyCnt + '" id="choices' + surveyCnt + '"></div>';
}

function getShortAnswerQuestion() {
    return '<div class=""><input survey-number="survey' + surveyCnt + '" id="survey' + surveyCnt + '" style="font-size:1.5em" class="clear-input" type="text" name="" value="" placeholder="항목의 제목을 쓰세요"></div>';
}

function getChoice(select) {
    var i = 0;
    while (1) {
        if (!!$("div[choies-number='choices" + select + "'] input[choice-number='choice" + i + "']")[0]) i++;
        else return '<input choice-number="choice' + i + '" style="margin-bottom: 1.25rem" type="text" name="" value="" placeholder="답안을 입력해 주세요">';
    }
}

function addShortAnswerQuestion() {
    let div = document.createElement('div');
    div.setAttribute('class', 'border-blue');
    div.setAttribute('style', 'border-bottom-style: solid; border-bottom-width: 2px; padding-bottom: 3px;');
    div.innerHTML = getShortAnswerQuestion();
    surveyCnt++;
    document.getElementById('survey-contents').append(div);
}

function addMultipleChoice() {
    let div = document.createElement('div');
    div.setAttribute('class', 'border-blue');
    div.setAttribute('style', 'border-bottom-style: solid; border-bottom-width: 2px; padding-bottom: 3px;');
    div.innerHTML = getMutipleChoice();
    surveyCnt++;
    document.getElementById('survey-contents').append(div);
}

function addChoice() {
    let div = document.createElement('div');
    var select = event.target.id.replace(/[^0-9]/g, '');
    div.innerHTML = getChoice(select);
    document.getElementById('choices' + select).append(div);
}

function kind() {
    document.getElementById('choice-modal').style.display = "inline-block";
}
$(document).ready(function () {
    $(".finish-btn").click(function () {
        let title = $("#survey-title")[0].value;
        let startDate = $(".start-date")[0].value + '-' + $(".start-date")[1].value + '-' + $(".start-date")[2].value;
        let finishDate = $(".finish-date")[0].value + '-' + $(".finish-date")[1].value + '-' + $(".finish-date")[2].value;
        let grade = $("#grade")[0].value;
        let surveys = {};
        for (var i = 0; i < surveyCnt; i++) {
            let choices = [];
            let servey;

            let currentSurvey = $("input[survey-number='survey" + i + "']")[0];
            surveys[i] = currentSurvey.value;

            if (!!$("div[choies-number='choices" + i + "'] input[choice-number=choice0]")[0]) {
                var j = 0;
                while (1) {
                    if (!!$("div[choies-number='choices" + i + "'] input[choice-number=choice" + j + "]")[0]) {
                        let choice = $("div[choies-number='choices" + i + "'] input[choice-number=choice" + j + "]")[0].value
                        choices.push(choice);
                        j++;
                    } else break;
                }
                survey = {
                    title: currentSurvey.value,
                    choices: choices
                }
            } else survey = currentSurvey.value;
            surveys[i] = survey;
        }
        console.log(title);
        console.log(startDate);
        console.log(finishDate);
        console.log(grade);
        console.log(surveys);
        $.ajax({
            url: "/admin/survey",
            data: {
                title: title,
                start_date: startDate,
                end_date: finishDate,
                target: grade
            },
            type: 'post',
            statusCode: {
                201: function () {
                }
            },
            error: function (e) {
                alert('설문조사 추가에 실패했습니다.');
            }
        });

        $.ajax({
            url: "/survey",
            type: 'GET',
            statusCode: {
                200: function (data) {
                    console.log(data);
                    for (var i = 0; i < data.length; i++) {
                        let id = data[i].id;
                        for (var j = 0; i < surveys.length; i++) {
                            let title=surveys[i];
                            let is_objective=false;
                            let choice_paper=null;

                            if(surveys[i].choices){
                                title=surveys[i].title;
                                choice_paper=surveys[i].choices;
                                is_objective=true;
                            }

                            $.ajax({
                                url: "/admin/survey/question",
                                data: {
                                    id: id,
                                    title: title,
                                    is_objective: is_objective,
                                    choice_paper: choice_paper
                                },
                                type: 'post',
                                statusCode: {
                                    201: function () {
                                        alert('설문조사 추가 완료');
                                    }
                                },
                                error: function (e) {
                                    alert('설문조사 추가에 실패했습니다.');
                                }
                            });
                        }

                    }
                }
            },
            error: function (e) {}
        });
    });
});