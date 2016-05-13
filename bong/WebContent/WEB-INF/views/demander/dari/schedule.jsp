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
                            <li class="active"><a href="#tab1-item1" data-toggle="tab">달력으로 보기</a></li>
                            <li><a href="#tab1-item2" data-toggle="tab">게시판으로 보기</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="tab1-item1">
                               <div id="deCalendar">달력으로 보기</div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item2">
                                <div id=deBoard">게시판으로 보기</div>
                            </div>
                           
                        </div>
                   

                   
                </div>
            
