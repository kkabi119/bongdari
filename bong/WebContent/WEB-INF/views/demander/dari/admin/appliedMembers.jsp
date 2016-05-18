<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<style type="text/css">
#chatMsgContainer{
   clear:both;
   border: 1px solid #ccc;
   height: 277px;
   overflow-y: scroll;
   padding: 3px;
   width: 100%;
}
#chatMsgContainer p{
   padding-bottom: 0px;
   margin-bottom: 0px;
}
#chatConnectList{
	clear:both;
	width: 100%;
	height: 315px;
	text-align:left;
	padding:5px 5px 5px 5px;
	overflow-y:scroll;
    border: 1px solid #ccc;
}
</style>
<div class="bodyFrame2">
    <div class="body-title">
          <h3><span class="glyphicon glyphicon-send"></span> 봉사신청자 명단 </h3>
    </div>
 
    <div style="clear: both;">
    <table>
 <tr><td>
        <div style="float: left; width: 170px;">
             <div style="clear: both; padding-bottom: 5px;">
                 <span class="glyphicon glyphicon-menu-right"></span>
                 <span style="font-weight:bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">신청자 리스트</span>
             </div>
             <div id="chatConnectList"></div>
        </div>
        </td>
        <td>
        					<div style="width: 20%; min-width: 85px; text-align: right; padding-bottom: 50px">
        		    			<button type="button" class="btn btn-info" onclick="javascript:location.href='<%=cp%>/club/index/notice/created';"><span class="fa fa-arrow-right"></span></button>
        					</div>
        					<div style=" width: 20%; min-width: 85px; text-align: right;padding-right: 15px">
        		    			<button type="button" class="btn btn-info" onclick="javascript:location.href='<%=cp%>/club/index/notice/created';"><span class="fa fa-arrow-left"></span></button>
        					</div>
        </td>
        <td>
        
        <div style="float: left; width: 170px;">
             <div style="clear: both; padding-bottom: 5px;">
                 <span class="glyphicon glyphicon-menu-right"></span>
                 <span style="font-weight:bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">확정자 리스트</span>
             </div>
             <div id="chatConnectList"></div>
        </div>
        </td>
      </tr>
  </table>  
    </div>
</div>