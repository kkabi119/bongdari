<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>

<style type="text/css">

/* 수정삭제는 관리자만 보이도록한다 
 */
.bbs-reply {
	border-top: #3897f0 solid 2px;
	border-bottom: #3897f0 solid 2px;
	padding: 15px;
	margin-bottom: 70px;
}

.bbs-reply-write {
	border-bottom: #ddd solid 2px;
	padding: 10px;
	min-height: 50px;
}

.table>thead>tr>th {
	font-size: 20px;
	text-align: center;
	padding: 14px;
	border-bottom: 2px solid gray;
}

.icon-wrapper:hover {
	background-color: #4FCCCD;
}

.table>tbody>tr>td {
	padding-top: 13px;
}
</style>


<div class="row" style="margin-left: 15px;">
	<div class="col-md-12 col-sm-12">


		<div class="col-sm-10_2"
			style="float: none; margin-left: auto; margin-right: auto;">

			<div class="body-title">
				<h3 style="font-size: 30px;">수요처 간단 프로필</h3>

			</div>

		</div>
		<hr
			style="margin-bottom: 10px; margin-top: 0px; border: 1px solid gray;">


		<div class="table-responsive" style="clear: both;">
			<div>
				<table class="table">
					<thead>
						<tr height="50">
							<th style="color: #555;" colspan="7" class="bbs-subject">
								희망 복지관
							</th>
						</tr>
					<thead>
					<tbody>
						<tr>
							<td bgcolor="#DFE6E8"
								style="color: black;; border-top: none; text-align: left; width: 14%; height: 45px;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
								수요처
							</td>
							<td style="text-align: left; width: 35%; height: 45px;">희망 복지관</td>

							<td bgcolor="#DFE6E8"
								style="color: black;; border-top: none; text-align: left; width: 14%; height: 45px;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;주소
							</td>
							<td style="text-align: left; width: 45%; height: 45px;" colspan="4" >
								서울특별시 노원구 삼양동 종합복지센터 장암역 1번출구  <a href="#"> [지도]</a>
							</td>
						</tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="color: black;; border-top: none; border-top: none; text-align: left; height: 45px;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이메일
							</td>
							<td style="text-align: left; height: 45px;">heemang@naver.com</td>

							<td bgcolor="#DFE6E8"
								style="color: black; border-top: none; text-align: left; width: 12%; height: 45px;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;핸드폰
							</td>
							<td style="text-align: left; width: 200px; height: 45px;" colspan="4">
								010-1111-2222</td>
						</tr>

						<tr>
							<td bgcolor="#DFE6E8"
								style="color: black;; border-top: none; border-top: none; text-align: left; height: 45px;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사분야
							</td>
							<td style="text-align: left; height: 45px;">문화체육 > 행사보조</td>

							<td bgcolor="#DFE6E8"
								style="color: black;; border-top: none; text-align: left; width: 12%; height: 45px;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사자
								유형
							</td>
							<td style="text-align: left; width: 200px; height: 45px;" colspan="4">
								청소년/성인</td>



						</tr>
					

						<tr>
							<td colspan="7"
								style="border-top: 2px solid gray; padding: 50px 20px 50px 20px; line-height: 20pt;">
								희망복지관에서는 따뜻한 봄을 맞이하여 '봄맞이 어르신 봄나들이' 프로그램을 진행 할 예정입니다. <br>
								인솔을 함께할 자원봉사자를 모집합니다. <br> 어르신을 공경하고, 서비스 마인드를 가지신 분들의 신청을
								기다립니다^^ <br>
							
							</td>
						</tr>



					</tbody>
					<tfoot>
						<tr>
							<td colspan="6" align="left"></td>
							<td align="right" colspan="2">
								<button type="button" class="btn btn-default"
								onclick="javascript:location.href='<%=cp%>/main/demander';">목록으로</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>



		</div>
	</div>
</div>

