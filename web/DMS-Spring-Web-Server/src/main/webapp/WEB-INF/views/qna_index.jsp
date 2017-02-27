<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="frame left articlecontainer question">
            <div class="frametitle">
                <img class="back_arrow" src="../image/arrow2.png" alt="" onclick="back()">
                <h2>Q : ${title}</h2>
                <p>${date}</p>
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
        <div class="frame extention articlecontainer answer">
            <div class="frametitle">
                <h2>Answer</h2>
                <p>${resultDate}</p>
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
                  <!-- 여기에 번호 넣어주면 css오 숨기고 내가 번호는 가져갈게 -->
                  <td class="hide-no">${comment.no}</td>
                  <td><h3>${comment.writer}</h3></td>
                  <td><p>${comment.content}</p></td>
                  <td><p class="date">${comment.date}</p></td>
                  <!-- 댓글 수정, 삭제 기능 -->
                  <td><span class="comment-modify">수정</span></td>
                  <td><span class="comment-delete">삭제</span></td>
              </tr>
            </c:forEach>
          </table>
        </div>
