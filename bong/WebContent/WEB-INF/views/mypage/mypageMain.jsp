<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>

	<div id="tab-container">
                    
                        <ul id="tab1" class="nav nav-tabs">
                            <li class="active"><a href="#tab1-item1" data-toggle="tab">나의 봉사 신청현황</a></li>
                            <li><a href="#tab1-item2" data-toggle="tab">정보 수정</a></li>
                            <li><a href="#tab1-item3" data-toggle="tab">나의 점수현황</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="tab1-item1">
                               <div id="deApproval">신청현황</div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item2">
                                <div id=deEval">수정</div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item3">
                                <div id="deBookmark">나의 점수현황</div>
                            </div>
                        </div>
                </div>
            
	