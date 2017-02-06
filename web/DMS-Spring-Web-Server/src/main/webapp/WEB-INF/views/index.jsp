<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DMS</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <header>
        <div class="logo">
            <p>DSM</p>
        </div>
    </header>
    <div class="remote">
        <h1>DMS</h1>
        <div class="category">
            <a href="#">
                <p>신청</p>
            </a>
            <div class="application children">
                <a href="extention_apply.html">연장신청</a>
                <a href="go_home_apply.html">귀가신청</a>
                <a href="#">외출신청</a>
                <a href="#">상점신청</a>
                <a href="#">방과후신청</a>
            </div>
            <a href="#">
                <p>기숙사</p>
            </a>
            <div class="dom children">
                <a href="dorm_main.html">공지사항</a>
                <a href="dorm_life.html">FAQ</a>
                <a href="#">기숙사규칙</a>
                <a href="dorm_QNA.html">문의하기</a>
            </div>
            <a href="#">
                <p>마이페이지</p>
            </a>
        </div>
    </div>
    <div class="main">
        <div class="frame notice">
            <div class="frametitle">
                <h1>공지사항</h1>
                <div class="underline">
                </div>
            </div>
            <ul>
                <li>
                    <p class="title">${title1}</p>
                    <p class="date">2016.12.31</p>
                </li>
                <li>
                    <p class="title">${title2}</p>
                    <p class="date">2016.12.31</p>
                </li>
                <li>
                    <p class="title">${title3}</p>
                    <p class="date">2016.12.31</p>
                </li>
                <li>
                    <p class="title">${title4}</p>
                    <p class="date">2016.12.31</p>
                </li>
                <li>
                    <p class="title">${title5}</p>
                    <p class="date">2016.12.31</p>
                </li>
            </ul>
        </div>
        <div class="frame food">
            <div class="frametitle">
                <h1>급식</h1>
                <div class="underline">
                </div>
            </div>
            <div class="menues">
                <div class="mornig meal">
                    <div class="info">
                        <h2>아침</h2>
                        <p>${breakfast}</p>
                    </div>
                    <img src="image/arrow.png" alt="arrow image to see allergy">
                </div>
                <div class="lunch meal">
                    <div class="info">
                        <h2>점심</h2>
                        <p>${lunch}</p>
                    </div>
                    <img src="image/arrow.png" alt="arrow image to see allergy">
                </div>
                <div class="dinner meal">
                    <div class="info">
                        <h2>저녁</h2>
                        <p>${dinner}</p>
                    </div>
                    <img src="image/arrow.png" alt="arrow image to see allergy">

                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="js/remote.js"></script>
</body>
</html>