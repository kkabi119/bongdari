<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
	String cp = request.getContextPath();
	// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>

<link rel="stylesheet" href="<%=cp%>/res/css/style.css" type="text/css">

<script type="text/javascript"
	src="<%=cp%>/res/jquery/js/jquery-1.12.3.min.js"></script>
	
<style type="text/css">
.bbs-article .table {
    margin-top: 15px;
  
}
.bbs-article .table thead tr,  .bbs-article .table tbody tr {
    height: 30px;
}
.bbs-article .table thead tr th, .bbs-article .table tbody tr td {
    font-weight: normal;
    border-top: none;
    border-bottom: none;
}
.bbs-article .table thead tr {
    border-top: #d5d5d5 solid 1px;
    border-bottom: #dfdfdf solid 1px;
} 
.bbs-article .table tbody tr {
    border-bottom: #dfdfdf solid 1px;
}
.bbs-article .table i {
    background: #424951;
    display: inline-block;
    margin: 0 7px 0 7px;
    position: relative;
    top: 2px;
    width: 1px;
    height: 13px;    
}

.bbs-reply {
    font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", 돋움, sans-serif;
}

.bbs-reply-write {
    border: #d5d5d5 solid 1px;
    padding: 10px;
    min-height: 50px;
}

</style>


<div class="table-responsive" style="width: 1200px;">
	<div class="bbs-article">
		<table class="table">
			<thead>
				<tr>
					<th colspan="2" style="text-align: center;"><a
						href="<%=cp%>/demander/index/main">수요처이름</a></th>
				</tr>
			<thead>
			<tbody>
				<tr>
					<td style="text-align: left;">전화번호 : 010-1111-2222</td>
					<td style="text-align: right;">주소 : 경기도 고양시 덕양구</td>
				</tr>

				<tr>
					<td style="text-align: left;">메일주소 : demander21@naver.com</td>
					<td style="text-align: right;">시설유형 : 보육</td>
				</tr>
				<tr>
					<td style="text-align: left;"></td>
					<td style="text-align: right;">담당자 : 홍길동</td>
				</tr>
				<tr>
					<td colspan="2" style="height: 230px;">${dto.content}</td>
				</tr>

			</tbody>
			<tfoot >
				<tr>
					<td align="center" colspan="2">
						<button type="button" class="btn btn-default btn-sm wbtn"
							onclick="javascript:location.href='<%=cp%>/main/demander';">
							목록으로</button>
					</td>
					<c:if test="${mode=='update'}">
						<input type="hidden" name="num" value="${dto.num}">
						<input type="hidden" name="saveFilename"
							value="${dto.saveFilename}">
						<input type="hidden" name="originalFilename"
							value="${dto.originalFilename}">
						<input type="hidden" name="page" value="${page}">
					</c:if>
				</tr>
			</tfoot>
		</table>
	</div>
</div>



