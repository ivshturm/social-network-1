<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message">
                Вы зарегистрировались
            </div>
        </c:if>
        <br/>
        <p align="center">
            <a class="btn btn-lg btn-primary" href="register">Регистрация &raquo;</a>
        </p>

        <%--<p>--%>
            <%--<button type="submit" onclick="setCredentials('user@yandex.ru', 'password')">--%>
                <%--Вход тестовым пользователем--%>
            <%--</button>--%>
        <%--</p>--%>

        <br/>
    </div>
</div>
<div class="container">
</div>
<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>