<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
        <div class="frame left mypage">
            <div class="frametitle">
                <h1>마이페이지</h1>
                <div class="underline red"></div>
            </div>
            <div class="info applyinfo">
                <h3>신청정보</h3>
                <table>
                    <tr>
                        <td>귀가</td>
                        <td>${stay}</td>
                    </tr>
                    <tr>
                        <td>연장</td>
                        <td>${extention}</td>
                    </tr>
                    <tr>
                        <td>방과후</td>
                        <td>${afterschool}</td>
                    </tr>
                </table>
            </div>
            <div class="info pointinfo">
                <h3>상벌점정보</h3>
                <table>
                    <tr>
                        <td>상점</td>
                        <td>#{good_score}</td>
                    </tr>
                    <tr>
                        <td>벌점</td>
                        <td>${bad_score}</td>
                    </tr>
                    <tr>
                        <td>총점</td>
                        <td>${total_score}</td>
                    </tr>
                </table>
            </div>
        </div>