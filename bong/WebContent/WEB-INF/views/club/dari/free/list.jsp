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
	var url="<%=cp%>/club/${clubSeq}/free/list2";
	var pageNo=page;
	alert(pageNo);
	$.post(url, {pageNo:pageNo}, function(data){
		$("#listFree${page}").append(data);
	});
}
</script>

  <section id="blog" class="padding-bottom" style="text-align: center">
                    <div class="timeline-date text-center" style="clear:both">
                        <a href="#" class="btn btn-common uppercase">May 2016</a>
                    </div>
             
                <div class="timeline-date text-center">
                       	<div id="listFree${page}">
                        
                       	</div>
                    </div>
    </section>
