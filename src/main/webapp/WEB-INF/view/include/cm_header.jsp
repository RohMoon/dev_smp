<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Moon
  Date: 2021-12-29
  Time: 오후 6:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="header">
    <div class="header-wrap">
        <div class="header-logo">
            <h1><a href="/main/main.do"><em>AP </em>GPS 경영목표시스템<span>Goal-setting Process System</span></a></h1>
        </div>

        <c:if test="${s_memId ne '' and s_memId ne null}">
            <nav>
                <h2 class="hidden">메뉴 바로가기</h2>
                <ul class="clearfix">
                    <c:if test="${s_maxRole eq '99'}">
                        <c:if test="${s_mem_Id eq 'AC924794' }">
                            <li class="nav-list">
                                <a href="#" class="bi-update">UPDATE</a>
                            </li>
                            <li class="nav-list">
                                <a href="#" class="gdy-update">GDY-UPDATE</a>
                            </li>
                            <li class="nav-list">
                                <a href="#" class="bi-master-update">BI-MASTER-UPDATE</a>
                            </li>
                        </c:if>

                        <c:if test="${s_isSectionManage eq 'Y'}">
                            <li class="nav-list">
                                <a href="/section/section_list.do"><spring:message code="section.sectionInformation"/></a>
                                <ul class="nav-sublist clearfix">
                                    <li><a href="/section/section_list.do"><spring:message code="section.sectionManage" /></a></li>
                                    <li><a href="/section/section_status.do"><spring:message code="section.sectionClassifcation"/></a></li>
                                </ul>
                            </li>

                        </c:if>
                        <li class="nav-list">
                            <a href="/guide/gide_list.do"><spring:message code="navi.distributeGuide"/></a>
                            <ul class="nav-sublist clearfix">
                                <li><a href="/guide/guide_reg.do"><spring:message code="navi.register"/></a></li>
                                <li><a href="/guide/guide_list.do"><spring:message code="navi.distribute"/></a></li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="nav-list">
                        <a href="/gps/gps_annual_list.do"><spring:message code="navi.registerGoal"/></a>
                        <ul class="nav-sublist clearfix">
                            <li><a href="/gps/gps_annual_list.do"><spring:message code="navi.registerAnnualGoal"/></a></li>
                            <li><a href="/gps/gps_list.do"><spring:message code="navi.annualGoalHistory"/></a></li>
                            <li><a href="/gps/mo/gps_mo_list.do"><spring:message code="navi.registerMonthlyGoal"/></a></li>
                            <li><a href="/gps/mo/gps_mo_history_list.do"><spring:message code="navi.mothlyGoalHistory"/></a></li>
                        </ul>
                    </li>
                    <li class="nav-list">
                        <a href="/roport/gps_view.do"><spring:message code="navi.report"/></a>
                        <ul class="nav-sublist clearfix">
                            <li><a href="/report/gps_view.do"><spring:message code="navi.registarationStatus"/></a></li>
                            <li><a href="/report/group_goal.do"><spring:message code="navi.comparisonGroupGoal"/></a></li>
                            <c:if test="${s_isReportViewable eq 'Y'}">
                                <li><a href="/report/group_gps_goal_summary.do"><spring:message code="navi.grpGoalSummary"/></a></li>
                                <li><a href="/report/group_gps_result_summary.do"><spring:message code="navi.grpResultSummary"/></a></li>
                                <li><a href="/report/digital_quantum_result_summary.do"><spring:message code="navi.digitalQuantum"/></a></li>
                                <li><a href="/report/one_china_result_summary.do"><spring:message code="navi.oneChina"/></a></li>
                                <li><a href="/report/group_brand.do"><spring:message code="navi.grpBrandXChannel"/></a></li>
                            </c:if>
                        </ul>
                    </li>
                    <li class="nav-list">
                        <a href="/board/board_list.do"><spring:message code="navi.communicationBoard"/></a>
                        <ul class="nav-sublist clearfix">
                            <li><a href="/board/borad_list.do"><spring:message code="navi.board" /></a></li>
                            <li><a href="/faq/faq_list.do"><spring:message code="navi.faq" /></a></li>
                        </ul>
                    </li>
                    <c:if test="${s_maxRole eq '99'}">
                        <li class="nav-list">
                            <a href="/auth/us/user_stting.do"><spring:message code="navi.setting"/></a>
                            <ul class="nav-sublist clearfix">
                                <li><a href="/auth/us/user_reg.do"><spring:message code="navi.userRegistration"/></a></li>
                                <li><a href="/auth/us/user_setting.do"><spring:message code="navi.authorityManagement"/></a></li>
                                <li><a href="/section/section_sync_config.do"><spring:message code="navi.syncConfig"/></a></li>
                                <li><a href="/guide/guide_deadline_setting_list.do"><spring:message code="guideDeadline.title"/></a></li>
                                <li><a href="/report/calender_result_type_setting.do"><spring:message code="bread.calenderResultTypeSetting"/></a></li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="nav-list" id="nav-nolist">
                        <a href="/exchange/exchange.do"><spring:message code="navi.exchangeInfomation"/></a>
                    </li>
                </ul>
            </nav>
    <div class="header-user">
        <div class="header-lang">
            <div class="header-lang-area ${s_language eq 'ko' ? 'header-lang-kr' : 'header-lang-en'}">
                <a href="#" alt="KOR" class="langChange ${s_language eq 'ko' ? 'on' : ''}" data-lang="ko">KR</a>
                <a href="#" alt="ENG" class="langChange ${s_language eq 'en' ? 'on' : ''}" data-lang="en">EN</a>
            </div>
        </div>
        <div class="header-user-group">
            <c:choose>
                <c:when test="${s_language eq 'ko'}">
                    <p class="header-user-info"><em>${s_memNm}</em><strong>${s_memId}</strong><span><spring:message code="navi.hello"/></span></p>
                </c:when>
                <c:otherwise>
                    <p class="header-user-info"><span><spring:message code="navi.hello"/></span><em>${s_memNm}</em><strong>${s_memId}</strong></p>
                </c:otherwise>
            </c:choose>
            <c:if test="${s_last_login_date ne '' and s_last_login_date ne null}">
                <p class="header-user-access">last Login : <span>${s_last_login_date}</span> / IP : <span>${s_last_login_ip}</span></p>
            </c:if>
        </div>
        <c:if test="${s_server_type ne 'REAL'}">
            <a class="header-logout" href="/login/logout.do" title="로그아웃">로그아웃</a>
        </c:if>
    </div>
        </c:if>
    </div>
    <div class="header-overlay">
    </div>
</header>

<script type="text/javascript">
    $(function () {
        $(".langChange").click(function (e) {
            let langcd = $(this).data("lang");
            cmAjax({
                url : "<c:url value="main/change_language_ajax.do"/>"
                ,type : "POST"
                ,data : {i_sChangeLanguage : langcd}
                ,dataType : "json"
                ,isModal : true
                ,isModalEnd : true
                ,success : function (data, textStatus, jqXHR) {
                    console.log(data);
                    if(data.status =="succ"){
                        location.reload(true);
                    }
                }
            });
        });
        
        $(".bi-update").click(function (e) {
            cmAjax({
                url : "<c:url value="/report/report_update_ajax.do"/>"
                ,type : "GET"
                ,dataType : "json"
                ,isModal: true
                ,isModalEnd :true
                ,success : function (data,textStatus,jqXHR) {
                    console.log(data);
                    if (data.status == "succ") {

                    }
                }
            });
        });
        
        
        $("bi-master-update").click(function (e) {
            cmAjax({
                url: "<c:url value="/guide/guide_data_year_update_ajax.do"/>"
                ,type : "GET"
                ,dataType : "json"
                ,isModal : true
                ,isModalEnd : true
                , success : function (data, textStatus, jqXHR) {
                    console.log(data)
                    if (data.status == "succ"){
                        //location.reload(true);
                    }
                }
            });
        });
        
        $(".gdy-update").click(function (e) {
            cmAjax({
                url : "<c:url value="/guide/guide_data_year_update_ajax.do"/> "
                ,type : "GET"
                ,dataType : "json"
                ,isModal : true
                ,isModalEnd : true
                , success : function (data, textStatus, jqXHR) {
                    console.log(data);
                    if (data.status == "succ"){
                    }
                }
            });
        });
    });
    
</script>