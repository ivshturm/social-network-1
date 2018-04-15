<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <%--@elvariable id="userTo" type="ru.sberbank.project.model.UserTo"--%>
        <h2>${userTo.name} ${register ? 'регистрация' : 'профиль'}</h2>

        <form:form modelAttribute="userTo" class="form-horizontal" method="post" action="${register ? 'register' : 'profile'}"
                   charset="utf-8" accept-charset="UTF-8">

            Имя
            <topjava:inputField label='${userName}' name="name"/>

            Фамилия
            <topjava:inputField label='${userLastName}' name="lastName"/>

            email
            <topjava:inputField label='${userEmail}' name="email"/>

            Пароль
            <topjava:inputField label='${userPassword}' name="password" inputType="password"/>

            <p>Дата рождения</p>

            День
            <topjava:inputField label='${birthdayDay}' name="birthdayDay" inputType="number"/>

            Месяц
            <topjava:inputField label='${birthdayMonth}' name="birthdayMonth" inputType="number"/>

            Год
            <topjava:inputField label='${birthdayYear}' name="birthdayYear" inputType="number"/>

            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>