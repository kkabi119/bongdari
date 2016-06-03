<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="<%=cp%>/admin"><i class="fa fa-gear fa-fw"></i> 관리자 메인페이지</a>
                        </li>
                        <li>
                            <a href="<%=cp%>/admin/member"><i class="fa fa-user fa-fw"></i> 회원 목록 보기</a>
                        </li>
                        <li>
                            <a href="<%=cp%>/admin/demander?switching=1"><i class="fa fa-flag fa-fw"></i> 수요처 목록 보기</a>
                        </li>
                        <li>
                            <a href="<%=cp%>/admin/club"><i class="fa fa-users fa-fw"></i> 동아리 목록 보기</a>
                        </li>
                        <li>
                            <a href="<%=cp%>/admin/demander?switching=0"><i class="fa fa-check fa-fw"></i> 수요처 승인하기</a>
                        </li>
                        <li>
                            <a href="<%=cp%>/admin/cal"><i class="fa fa-calendar fa-fw"></i> 전체 일정 보기</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>