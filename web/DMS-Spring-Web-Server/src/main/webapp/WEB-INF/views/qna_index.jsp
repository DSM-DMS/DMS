<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="frame left articlecontainer">
            <div class="frametitle">
                <img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">
                <h2>Q : ${title}</h2>
                <p>${date}</p>
                <div class="underline puple">
                </div>
            </div>
            <div class="article">
                ${content}
            </div>
            <hr>
        </div>
        <div class="frame extention articlecontainer">
            <div class="frametitle">
                <h2>Answer</h2>
                <p>${resultDate}</p>
                <div class="underline">
                </div>
            </div>
            <div class="article">
                ${result}
            </div>
            <hr>
            <div class="commentinput">
              <table>
                  <tr>
                      <td> <input type="text" name="comment" value="" placeholder="여기에 댓글을 입력해 주세요">
                      </td>
                      <td> <button type="button" name="button">슝!</button>
                      </td>
                  </tr>
              </table>
            </div>
            <div class="comment">
              <table>
                  <c:forEach items="${comments}" var="comment">
                  <tr>
                      <td><h3>${comment.writer}</h3></td>
                      <td><p>${comment.content}</p></td>
                      <td><p class="date">${comment.date}</p></td>
                  </tr>
	              </c:forEach>
              </table>
            </div>
        </div>