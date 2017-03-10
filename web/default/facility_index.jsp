<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="frame left articlecontainer question">
            <div class="frametitle">
                <img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">
                <h1>${title}</h1>
                <p>${write_date} ${room}호실 ${writer}</p>
                <%-- 이건 학생이어도 안숨겨도 돼 --%>
                <div class="input-container">
                    <input class="modify" type="button" name="" value="수정">
                    <input class="delete" type="button" name="" value="삭제">
                </div>
                <div class="underline puple">
                </div>
            </div>
            <div class="article">
                ${content}
            </div>
            <hr>
        </div>

        <%-- 결과가 없으면 아래의 answer div는 숨겨야함 --%>
        <div class="frame extention articlecontainer answer">
            <div class="frametitle">
                <h1>결과</h1>
                <p>${result_date}</p>
                <%-- 관리자가 아니면, 이거를 숨겨줘 --%>
                <div class="input-container">
                    <input class="modify" type="button" name="" value="수정">
                    <input class="delete" type="button" name="" value="삭제">
                </div>
                <div class="underline">
                </div>
            </div>
            <div class="article">
                ${result}
            </div>
            <hr>
        </div>
