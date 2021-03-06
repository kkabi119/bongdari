<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>

<style type="text/css">
.col-md-9 {
	margin-top: -25px;
}
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
tr:last-of-type{

	border-bottom: 2px solid #807F7F; 
}

tr:first-of-type{

	border-top: 2px solid #807F7F; 
	
}

</style>
<link rel="stylesheet" href="<%=cp%>/res/css/fileinput.css"
	type="text/css">

<script type="text/javascript" src="<%=cp%>/res/js/util.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/fileinput.js"></script>

<div class="row" style="margin-left: 15px;">
	<div class="col-md-12 col-sm-12">

		<div class="table-responsive"
			style="clear: both; width: 100%; align: center; margin: auto;">
			<div>
				<table class="table">
					<tbody>
						<tr>
							<td colspan="3" 
								style="text-align: center; font-size: 15px; background-color: #68cabb; color: white;
								font-weight: 550; font-size: 17px;">
								내 정보		
							</td>
						</tr>
						<tr>
							<td bgcolor="#DFE6E8" 
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555; 
								">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true" ></span>&nbsp;
								아이디
							</td>                 
							<td style="text-align: left; width: 55%; height: 45px;">${dto.userId}</td>
							<td rowspan="4"
								style="text-align: left; width: 25%; height: 45px;"><img
								src="<%=cp%>/uploads/memImg/${dto.memImg}"
								style="width: 250px; height: 200px;"></td>
						</tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이름
							</td>
							<td style="text-align: left; width: 5%; height: 45px;">${dto.userName}</td>
						</tr>

						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;생년월일
							</td>
							<td style="text-align: left; width: 5%; height: 45px;">${dto.userBirth}</td>

						</tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;성별
							</td>
							<td style="text-align: left; width: 5%; height: 45px;">${dto.userGender}</td>
						</tr>
                        <tr>
						<td bgcolor="#DFE6E8"
							style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;직업
						</td>
						<td colspan="3"
							style="text-align: left; width: 200px; height: 45px;">
							${dto.userJob}</td>
					    </tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;전화번호
							</td>
							<td style="text-align: left; width: 5%; height: 45px;">${dto.userTel}</td>
                        </tr>
                        <tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이메일
							</td>
							<td colspan="3"
								style="text-align: left; width: 200px; height: 45px;">
								${dto.userEmail}</td>
						</tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;주소
							</td>
							<td colspan="3"
								style="text-align: left; width: 5%; height: 45px;">${dto.userAddr}</td>
						</tr>
						<tr>
						    <td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;총
								봉사시간
							</td>
							<td colspan="2" style="text-align: left; height: 45px;">20시간</td>
						</tr>
						<tr>
						<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;참석률
							</td>
							<td colspan="2" style="text-align: left; height: 45px;">90%</td>
						</tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;참석률
							</td>
							<td colspan="2" style="text-align: left; height: 45px;">90%</td>
						</tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;획득
								점수
							</td>
							<td colspan="2" style="text-align: left; height: 45px;">300점</td>
						</tr>
						<tr>
							<td bgcolor="#DFE6E8"
								style="width:20%; font-size:15px; background-color:#edf7f5; color:#555;">
								<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉다리
								레벨
							</td>
							<td colspan="2" style="text-align: left; height: 45px;">
								${dto.userLevel}</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</div>

