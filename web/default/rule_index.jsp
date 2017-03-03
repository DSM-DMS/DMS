<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="frame left articlecontainer question">
            <div class="frametitle">
                <img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">
                <h1>${title}</h1>
                <%-- 관리자 에게만 보여야함 --%>
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
