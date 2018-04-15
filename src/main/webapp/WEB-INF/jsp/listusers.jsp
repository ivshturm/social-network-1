<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
    <div class="container">

        <div class="container">
            <div class="row">
                <div class="col-xs-4"></div>
                <div class="col-xs-4" align="center"></div>
                <div class="col-xs-4" align="right">
                    <p>Найти пользователей</p>
                    <p>
                    <form class="form-inline my-2 my-lg-0" action="/search" method="GET">
                        <input class="form-control mr-sm-2" type="text" name="name" placeholder="Введите имя или фамилию">
                        <input class="form-control mr-sm-2" type ="submit" value="Найти">
                    </form>
                    </p>
                </div>
            </div>
        </div>

        <c:if test="${!empty users}">
            <table class="table table-hover">
                <h3>${name}</h3>
                <tr>
                    <th scope="col">Имя Фамилия</th>
                    <th scope="col">Возраст</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><a href="<c:url value='/user/${user.id}'/>">${user.name} ${user.lastName}</td>
                        <td>${user.currentYear - user.birthdayYear}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${empty users}">
            <h3>${emptyList}</h3>
        </c:if>

    </div>
</div>



</body>
</html>
