<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<script type="text/javascript">
$(function(){
	nextPage(0);
});
function nextPage(page){
	var total_page="${total_page}";
	if(total_page==page){
		return;
	}
	var url="<%=cp%>/club/${clubSeq}/free/list2";
	var pageNo=page;
	
	$.post(url, {pageNo:pageNo}, function(data){
		$("#listFree${page}").append(data);
	});
}
</script>

  <section id="blog" class="padding-bottom" style="text-align: center">
  <div style="float: right; clear:both">
        		    	<button type="button" class="btn btn-lg btn-info" onclick="javascript:location.href='<%=cp%>/club/${clubSeq}/free/created';"><span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기</button>
        			</div>
                    <div class="timeline-date text-center" style="clear:both">
                        <a href="#" class="btn btn-common uppercase" style="border-color: #0099AE; background-color: #FFFFFF">June 2016</a>
                    </div>
             		
                <div class="timeline-date text-center">
                       	<div id="listFree${page}"></div>
    
                <div  id="seeMore${page}"><a onclick="nextPage();" class='btn btn-common' style="border-color: #0099AE; background-color: #FFFFFF">See More</a></div>
                </div>
    </section>
