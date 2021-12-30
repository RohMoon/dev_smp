<%--
  Created by IntelliJ IDEA.
  User: Moon
  Date: 2021-12-21
  Time: 오후 4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<form name="frm" id="frm" action="" method="post">
    <div class="error-area">
        <div class="error-box error-box2">
            <h2><em>죄송합니다</em>일시적 오류가 발생했습니다</h2>
            <p><em>새로고침</em>하시거나 페이지 확인 후 다시 접속해 주시기 바랍니다</p>
            <p>계속 같은 문제가 반복적으로 발생할 경우<br> 담당자에게 문의해 주세요</p>
            <div class="btn-area">
                <a href="javascript:history.back();" class="btn-ib btn-gray">이전페이지</a>
                <a href="/main/main.do" class="btn-ib btn-black">홈으로 이동</a>
            </div>
        </div>
    </div>
</form>