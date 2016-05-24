<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">
<c:if test="${mode=='update'}">
$(function(){
	themeList();
});
</c:if>

function themeList() {
	var groupNum=$("#groupNum").val();
	if(groupNum=="") {
		$("#themeNum option").each(function() {
			$("#themeNum option:eq(0)").remove();
		});

		$("#themeNum").append("<option value=''>:: 중분류 ::</option>");
		return false;
	}
	
	var url="<%=cp%>/club/themeList";
	var params="groupNum="+groupNum;
	
	$.ajax({
		type:"post"
		,url:url
		,data:params
		,dataType:"json"
		,success:function(data){
			$("#themeNum option").each(function() {
				$("#themeNum option:eq(0)").remove();
			});

			 $("#themeNum").append("<option value=''>:: 중분류 ::</option>");
			 
			 var cn="${dto.themeNum}";
			 var s;
			 
			 for(var idx=0; idx<data.listTheme.length; idx++) {
				 s="";
				 if(cn==data.listTheme[idx].themeNum)
					 s=" selected='selected'";
				 $("#themeNum").append("<option value='"+data.listTheme[idx].themeNum+"' " + s +">"+data.listTheme[idx].subject+"</option>");
			 }
		}
	    ,error:function(e) {
	    	alert(e.responseText);
	    }
	});
}

  function sendClub() {
        var f = document.clubForm;

    	var str = f.groupNum.value;
        if(!str) {
            alert("동아리 주제를 선택 하세요. ");
            f.groupNum.focus();
            return;
        }
        
    	str = f.themeNum.value;
        if(!str) {
            alert("동아리 주제를 선택 하세요. ");
            f.themeNum.focus();
            return;
        }

    	str = f.clubname.value;
        if(!str || str.length>50) {
            alert("50자 이내의 동아리 제목을 입력 하세요. ");
            f.clubname.focus();
            return;
        }
        
    	str = f.introduce.value;
        if(!str || str.length>150) {
            alert("150자 이내의 동아리 소개를 입력 하세요. ");
            f.introduce.focus();
            return;
        }
        
    	str = f.nickName.value;
        if(!str || str.length>10) {
            alert("10자 이내의 닉네임을 입력 하세요. ");
            f.nickName.focus();
            return;
        }

    	str = f.city.value;
        if(!str) {
            alert("지역를 선택 하세요. ");
            f.city.focus();
            return;
        }

    	if(f.upload.value!="") {
    		if(! /(\.gif|\.jpg|\.png|\.jpeg)$/i.test(f.upload.value)) {
    			alert("사진은 이미지 파일만 가능합니다. ");
    			f.upload.focus();
    			return false;
    		}
    	}        
    	str = f.prologue.value;
        if(!str) {
            alert("프롤로그를 입력하세요. ");
            f.prologue.focus();
            return false;
        }

        var mode="${mode}";
        if(mode=="created")
            f.action = "<%=cp%>/club/created";
        else if(mode=="update")
            f.action = "<%=cp%>/club/update";
            
       f.submit();
  }
</script>

<div class="bodyFrame3">
    <div style="width:600px; padding-top:35px; clear: both; margin: 0px auto;">
        <div class="body-clubname">
              <h3> 동아리 ${mode=="created"?"생성":"수정" }</h3>
        </div>
     </div>
    		
    <div style="width:600px; padding-top:25px; clear: both; margin: 0px auto;">
    		<form name="clubForm" method="post" enctype="multipart/form-data">
			  <table style="width: 600px; margin: 0px auto; border-spacing: 0px;">
			  <tr><td colspan="3" height="2" bgcolor="#212121"></td></tr>

			  <tr align="left" height="40"> 
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">동아리주제</td>
			      <td width="500" colspan="2" style="padding-left:10px;"> 
			          <select name="groupNum" id="groupNum" class="selectField"
			                      onchange="themeList();">
			               <option value="">:: 대분류 ::</option>
			               <c:forEach var="vo" items="${listGroup}">
			                   <option value="${vo.themeNum}" ${vo.themeNum==dto.groupNum ? "selected='selected'" : ""}>${vo.subject}</option>
			               </c:forEach>
			          </select>
			          <select name="themeNum" id="themeNum" class="selectField">
			               <option value="">:: 중분류 ::</option>
			          </select>
			      </td>
			  </tr>
			  <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>
			
			  <tr align="left" height="40"> 
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">제목</td>
			      <td width="500" colspan="2" style="padding-left:10px;"> 
			        <input type="text" name="clubname" size="75" maxlength="100" class="boxTF" value="${dto.clubname}">
			      </td>
			  </tr>
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>
			
			  <tr align="left"> 
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center; padding-top:5px;" valign="top">동아리 소개<br>(150자 이내)</td>
			      <td width="500" colspan="2" valign="top" style="padding:5px 0px 5px 10px;"> 
			        <textarea name="introduce" cols="75" rows="5" class="boxTA" style="height: 50px;">${dto.introduce}</textarea>
			      </td>
			  </tr>
			  <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>
			   
			  <tr align="left" height="40">
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">이름</td>
			      <td width="250" style="padding-left:10px;"> 
						<input type="text" name="userName" maxlength="10" class="boxTF" value="${sessionScope.member.userName}"
						            readonly="readonly" style="width: 210px; border: none;">
				  </td>
                  <td style="padding-left:10px;"> 				  
						 <input type="checkbox" name="isUserName" value="1" ${dto.isUserName=="1"?"checked='checked'":"" }> <span>공개</span>
			      </td>
			  </tr>
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>

			  <tr align="left" height="40">
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">닉네임</td>
			      <td width="500" colspan="2" style="padding-left:10px;"> 
						<input type="text" name="nickName" style="width: 210px;" maxlength="10" class="boxTF" value="${dto.nickName}">
			      </td>
			  </tr>
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>
			
			  <tr align="left" height="40">
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">성별</td>
			      <td width="250" style="padding-left:10px;"> 
			            <select name="gender" class="selectField" style="width: 222px;">
			                <option value="남자" ${dto.gender=="남자" ? "selected='selected'" : ""}>남자</option>
			                <option value="여자" ${dto.gender=="여자" ? "selected='selected'" : ""}>여자</option>
			            </select>
				  </td>
                  <td style="padding-left:10px;"> 				  
						 <input type="checkbox" name="isGender" value="1" ${dto.isGender=="1"?"checked='checked'":"" }> <span>공개</span>
			      </td>
			  </tr>
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>
			
			  <tr align="left" height="40">
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">지역</td>
			      <td width="250" style="padding-left:10px;"> 
			            <select name="city" class="selectField" style="width: 222px;">
			                <option value="">:: 선택 ::</option>
			                <option value="서울" ${dto.city=="서울" ? "selected='selected'" : ""}>서울</option>
			                <option value="부산" ${dto.city=="부산" ? "selected='selected'" : ""}>부산</option>
			                <option value="대구" ${dto.city=="대구" ? "selected='selected'" : ""}>대구</option>
			                <option value="인천" ${dto.city=="인천" ? "selected='selected'" : ""}>인천</option>
			                <option value="광주" ${dto.city=="광주" ? "selected='selected'" : ""}>광주</option>
			                <option value="대전" ${dto.city=="대전" ? "selected='selected'" : ""}>대전</option>
			                <option value="울산" ${dto.city=="울산" ? "selected='selected'" : ""}>울산</option>
			                <option value="세종" ${dto.city=="세종" ? "selected='selected'" : ""}>세종</option>
			                <option value="강원" ${dto.city=="강원" ? "selected='selected'" : ""}>강원</option>
			                <option value="경기" ${dto.city=="경기" ? "selected='selected'" : ""}>경기</option>
			                <option value="충남" ${dto.city=="충남" ? "selected='selected'" : ""}>충남</option>
			                <option value="충북" ${dto.city=="충북" ? "selected='selected'" : ""}>충북</option>
			                <option value="전남" ${dto.city=="전남" ? "selected='selected'" : ""}>전남</option>
			                <option value="전북" ${dto.city=="전북" ? "selected='selected'" : ""}>전북</option>
			                <option value="경남" ${dto.city=="경남" ? "selected='selected'" : ""}>경남</option>
			                <option value="경북" ${dto.city=="경북" ? "selected='selected'" : ""}>경북</option>
			                <option value="제주" ${dto.city=="제주" ? "selected='selected'" : ""}>제주</option>
			            </select>
				  </td>
                  <td style="padding-left:10px;"> 				  
						 <input type="checkbox" name="isCity" value="1" ${dto.isCity=="1"?"checked='checked'":"" }> <span>공개</span>
			      </td>
			  </tr>
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>

			  <tr align="left" height="40">
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">취미</td>
			      <td width="250" style="padding-left:10px;"> 
						<input type="text" name="hobby" maxlength="10" class="boxTF" value="${dto.hobby}"  style="width: 210px;">
				  </td>
                  <td style="padding-left:10px;"> 				  
						 <input type="checkbox" name="isHobby" value="1" ${dto.isHobby=="1"?"checked='checked'":"" }> <span>공개</span>
			      </td>
			  </tr>
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>

			  <tr align="left" height="40" >
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">사진</td>
			      <td width="500" colspan="2" style="padding-left:10px;"> 
                      <input type="file" name="upload" class="boxTF"  size="61"  style="height: 20px;">			           
			       </td>
			  </tr> 
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>

			  <tr align="left" height="40">
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center;">동아리공개</td>
			      <td width="500" colspan="2"  style="padding-left:10px;"> 
			            <input type="radio" value="0" name="closed" ${dto.closed=="0"?"checked='checked'":"" }> 공개&nbsp;&nbsp;&nbsp;
			            <input type="radio" value="1" name="closed" ${dto.closed=="1"?"checked='checked'":"" }> 비공개
			      </td>
			  </tr>
			   <tr><td colspan="3" height="1" bgcolor="#C3C3C3"></td></tr>

			  <tr align="left"> 
			      <td width="100" bgcolor="#EEEEEE" style="text-align: center; padding-top:5px;" valign="top">프롤로그</td>
			      <td width="500" colspan="2" valign="top" style="padding:5px 0px 5px 10px;"> 
			        <textarea name="prologue" cols="75" rows="7" class="boxTA" style="height: 100px;">${dto.prologue}</textarea>
			      </td>
			  </tr>
			
			  <tr><td colspan="3" height="2" bgcolor="#212121"></td></tr>
			  </table>
			
			  <table style="width: 600px; margin: 0px auto; border-spacing: 0px;">
			     <tr height="45"> 
			      <td align="center" >
					   <button type="button" class="btn1" onclick="sendClub();"> ${mode=="created"?"등록완료":"수정완료"} </button>
                       <button type="button" class="btn1" onclick="javascript:history.back();"> ${mode=="created"?"등록취소":"수정취소"} </button>
                       <c:if test="${mode=='update'}">
                           <input type="hidden" name="clubSeq" value="${dto.clubSeq}">
                           <input type="hidden" name="photoFilename" value="${dto.photoFilename}">
                       </c:if>
			      </td>
			    </tr>
			  </table>
			</form>
		
		</div>
</div>