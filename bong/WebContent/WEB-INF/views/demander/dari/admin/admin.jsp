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
                            <li class="active"><a href="#tab1-item1" data-toggle="tab">요청승인</a></li>
                            <li><a href="#tab1-item2" data-toggle="tab">동아리 평가</a></li>
                            <li><a href="#tab1-item3" data-toggle="tab">관심등록 동아리</a></li>
                            <li><a href="#tab1-item4" data-toggle="tab">봉사내역</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="tab1-item1">
                               <div id="deApproval">요청승인</div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item2">
                                <div id=deEval">동아리 평가</div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item3">
                                <div id="deBookmark">관심등록 동아리</div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item4">
                                <div id="deVolList">봉사내역</div>
                            </div>
                        </div>
                   

                   
                </div>
            
