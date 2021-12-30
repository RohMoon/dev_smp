<%--
  Created by IntelliJ IDEA.
  User: Moon
  Date: 2021-12-29
  Time: 오후 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ include file="/WEB-INF/view/include/cm_server_common.jsp" %>
<!DOCTYPE html>
<html lang="${s_language}">
<head>
    <meta charset="UTF-8"/>
    <title><decorator:title default="AP GPS 경영목표시스템"/></title>
    <%@ include file="/WEB-INF/view/include/cm_head.jsp" %>
    <decorator:head/>
</head>
<body class="real-popup">
    <div id="wrap">
        <main id="content">
            <h1 class="hidden"><spring:message code="gpsRegPopup.title"/></h1>
            <decorator:body />
        </main>
    </div>

</body>
</html>
