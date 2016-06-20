<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<style>
.btn-common{
 	font-size: 18px;
    color: white;
   border-radius:0px;
    font-weight: bold;
    padding: 20px 40px;
    background-color: #32BEF3; border:none;

}
 .btn-common:hover{
  background-color: #37DABA;
 	font-size: 19px;  border-radius:0px; border:none; color:white;
 }
 
 
  .padding{
    padding: 30px 0;
  }

</style>
    <section id="home-slider">
        <div class="container">
            <div class="row">
                <div class="main-slider">
                    <div class="slide-text" style="margin-top:11%"><!-- 
                     <p  style="font-size:25px;">봉사에 친목을 더하다 따뜻함을 더하다 </p> -->
                    
                      	<img Id="listBtn"style="margin-left:-10px; width:300px; height:150px; background-size:cover; "src="<%=cp%>/res/images/mainslide/maintext.png" alt="">
                      
           <!--         <h1 style="font-size:40px; font-weight:bolder; margin-bottom:5px;">우리를 이어주는 다리 </h1>
                        <h1 style="margin-top:3px;font-size:60px; font-weight:900; color:white;">봉 다 리 </h1> -->
                        <p  style="font-size:20px;margin-top:20px; margin-bottom:-10px ;font-weight:600; line-height:25px;">
                        작은 실천과 만남으로 따뜻한 일상을 만들어 보세요<br>
                        </p>
                        <div><a href="#" style="margin-top:30px; "class="btn btn-common">가입하기</a></div>
                    </div>
                    <div>
          	          
                    </div>
                 <%--    <img src="<%=cp%>/res/images/home/slider/hill.png" class="slider-hill" alt="slider image">
                    <img src="<%=cp%>/res/images/home/slider/house.png" class="slider-house" alt="slider image">
                    <img src="<%=cp%>/res/images/home/slider/sun.png" class="slider-sun" alt="slider image">
                    <img src="<%=cp%>/res/images/home/slider/birds1.png" class="slider-birds1" alt="slider image">
                    <img src="<%=cp%>/res/images/home/slider/birds2.png" class="slider-birds2" alt="slider image"> --%>
                </div>
            </div>
        </div>
        <div class="preloader"><i class="fa fa-sun-o fa-spin"></i></div>
    </section>
    <!--/#home-slider-->

    <section id="services" style="margin-bottom:30px;">
        <div class="container">
            <div class="row">
                <div class="col-sm-3 text-center padding wow fadeIn" data-wow-duration="1000ms" data-wow-delay="300ms">
                    <div class="single-service" style="color:#555; ">
                        <div class="wow scaleIn" data-wow-duration="500ms" data-wow-delay="300ms">
                          <a href="#">  <img style="border-radius:100%; width:95px; height:95px;"src="<%=cp%>/res/images/happy.png" alt=""></a>
                        </div>
                        <h2 style="font-size:22px;font-size:22px; font-weight:600;">봉다리 메인</h2>
                       <!--  <p style="font-weight:400">이번 달의 포토와 후기를 칭찬해주세요 </p> -->
                    </div>
                </div>
                <div class="col-sm-3 text-center padding wow fadeIn" data-wow-duration="1000ms" data-wow-delay="600ms">
                    <div class="single-service">
                        <div class="wow scaleIn" data-wow-duration="500ms" data-wow-delay="600ms">
                              <a href="#">  <img style="border-radius:100%; width:105px; height:95px;"src="<%=cp%>/res/images/search1.png" alt=""></a>
                        </div>
                        <h2 style="font-size:22px;font-weight:600;">동아리 찾기</h2>
                      <!--   <p> 우리 지역 봉다리에 참여하세요 </p> -->
                    </div>
                </div>
                <div class="col-sm-3 text-center padding wow fadeIn" data-wow-duration="1000ms" data-wow-delay="900ms">
                    <div class="single-service">
                        <div class="wow scaleIn" data-wow-duration="500ms" data-wow-delay="900ms">
                             <a href="#">  <img style="border-radius:100%; width:105px; height:95px;"src="<%=cp%>/res/images/search2.png" alt=""></a>
                        </div>
                          <h2 style="font-size:22px;font-weight:600;">수요처 찾기</h2>
          <!--               <p> 지금 관심있는 수요처를 찾아보세요</p> -->
                       
                    </div>
                </div>
                <div class="col-sm-3 text-center padding wow fadeIn" data-wow-duration="1000ms" data-wow-delay="900ms">
                    <div class="single-service">
                        <div class="wow scaleIn" data-wow-duration="500ms" data-wow-delay="900ms">
                              <a href="#">  <img style=" width:105px; height:95px;"src="<%=cp%>/res/images/calendar1.png" alt=""></a>
                        </div>
                          <h2 style="font-size:22px;font-weight:600; "> 봉사 달력</h2>
                      <!--   <p> 나에게 맞는 봉사활동을 찾을 수 있어요 </p> -->
                       
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--/#services-->
<%-- 
    <section id="action" class="responsive">
        <div class="vertical-center">
             <div class="container">
                <div class="row">
                    <div class="action take-tour">
                        <div class="col-sm-7 wow fadeInLeft" data-wow-duration="500ms" data-wow-delay="300ms">
                            <h1 class="title">Triangle Corporate Template</h1>
                            <p>A responsive, retina-ready &amp; wide multipurpose template.</p>
                        </div>
                        <div class="col-sm-5 text-center wow fadeInRight" data-wow-duration="500ms" data-wow-delay="300ms">
                            <div class="tour-button">
                                <a href="#" class="btn btn-common">TAKE THE TOUR</a>
                             </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </section>
    <!--/#action-->

    <section id="features">
        <div class="container">
            <div class="row">
                <div class="single-features">
                    <div class="col-sm-5 wow fadeInLeft" data-wow-duration="500ms" data-wow-delay="300ms">
                        <img src="<%=cp%>/res/images/home/image1.png" class="img-responsive" alt="">
                    </div>
                    <div class="col-sm-6 wow fadeInRight" data-wow-duration="500ms" data-wow-delay="300ms">
                        <h2>Experienced and Enthusiastic</h2>
                        <P>Pork belly leberkas cow short ribs capicola pork loin. Doner fatback frankfurter jerky meatball pastrami bacon tail sausage. Turkey fatback ball tip, tri-tip tenderloin drumstick salami strip steak.</P>
                    </div>
                </div>
                <div class="single-features">
                    <div class="col-sm-6 col-sm-offset-1 align-right wow fadeInLeft" data-wow-duration="500ms" data-wow-delay="300ms">
                        <h2>Built for the Responsive Web</h2>
                        <P>Mollit eiusmod id chuck turducken laboris meatloaf pork loin tenderloin swine. Pancetta excepteur fugiat strip steak tri-tip. Swine salami eiusmod sint, ex id venison non. Fugiat ea jowl cillum meatloaf.</P>
                    </div>
                    <div class="col-sm-5 wow fadeInRight" data-wow-duration="500ms" data-wow-delay="300ms">
                        <img src="<%=cp%>/res/images/home/image2.png" class="img-responsive" alt="">
                    </div>
                </div>
                <div class="single-features">
                    <div class="col-sm-5 wow fadeInLeft" data-wow-duration="500ms" data-wow-delay="300ms">
                        <img src="<%=cp%>/res/images/home/image3.png" class="img-responsive" alt="">
                    </div>
                    <div class="col-sm-6 wow fadeInRight" data-wow-duration="500ms" data-wow-delay="300ms">
                        <h2>Experienced and Enthusiastic</h2>
                        <P>Ut officia cupidatat anim excepteur fugiat cillum ea occaecat rump pork chop tempor. Ut tenderloin veniam commodo. Shankle aliquip short ribs, chicken eiusmod exercitation shank landjaeger spare ribs corned beef.</P>
                    </div>
                </div>
            </div>
        </div>
    </section>
     <!--/#features-->

    <section id="clients">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="clients text-center wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">
                        <p><img src="<%=cp%>/res/images/home/clients.png" class="img-responsive" alt=""></p>
                        <h1 class="title">Happy Clients</h1>
                        <p>Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. <br> Ut enim ad minim veniam, quis nostrud </p>
                    </div>
                    <div class="clients-logo wow fadeIn" data-wow-duration="1000ms" data-wow-delay="600ms">
                        <div class="col-xs-3 col-sm-2">
                            <a href="#"><img src="<%=cp%>/res/images/home/client1.png" class="img-responsive" alt=""></a>
                        </div>
                        <div class="col-xs-3 col-sm-2">
                            <a href="#"><img src="<%=cp%>/res/images/home/client2.png" class="img-responsive" alt=""></a>
                        </div>
                         <div class="col-xs-3 col-sm-2">
                            <a href="#"><img src="<%=cp%>/res/images/home/client3.png" class="img-responsive" alt=""></a>
                        </div>
                         <div class="col-xs-3 col-sm-2">
                            <a href="#"><img src="<%=cp%>/res/images/home/client4.png" class="img-responsive" alt=""></a>
                        </div>
                         <div class="col-xs-3 col-sm-2">
                            <a href="#"><img src="<%=cp%>/res/images/home/client5.png" class="img-responsive" alt=""></a>
                        </div>
                         <div class="col-xs-3 col-sm-2">
                            <a href="#"><img src="<%=cp%>/res/images/home/client6.png" class="img-responsive" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
     </section> --%>
    <!--/#clients-->
