<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/usersArticles.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
    <div class="container">
        <div class="container">
            <div class="row">
                <div class="col-xs-4">
                    <h2 >${user.name}  ${user.lastName}</h2>
                    <h5 >Возраст: ${user.currentYear - user.birthdayYear}</h5>
                    <p></p>
                    <c:if test="${user.id != authUserId}">
                        <c:if test="${checkFollow == 'no'}">
                            <a class="btn btn-outline-primary" href="/subscribe/${user.id}" methods="post">
                                <button class="btn btn-primary" type="button" class="btn btn-outline-primary">Подписаться</button>
                            </a>
                        </c:if>
                        <c:if test="${checkFollow == 'yes'}">
                            <a class="btn btn-outline-primary" href="/unsubscribe/${user.id}"  methods="post">
                                <button type="button" class="btn btn-outline-primary">Отписаться</button>
                            </a>
                        </c:if>
                    </c:if>

                </div>
                <div class="col-xs-5" align="center">
                    <p></p>
                    <a class="btn btn-outline-primary" href="/followers/${user.id}">
                        <button type="button" class="btn btn-outline-primary">Подписчики пользователя</button>
                    </a>
                    <a class="btn btn-outline-primary" href="/following/${user.id}">
                        <button type="button" class="btn btn-outline-primary">Подписки пользователя</button>
                    </a>
                </div>
                <div class="col-xs-3" align="right">
                    <p>Найти пользователей</p>
                    <p>
                    <form class="form-inline my-2 my-lg-0" action="/search" method="GET">
                        <input class="form-control mr-sm-2" type="text" name="name" placeholder="Введите имя или фамилию">
                        <input class="form-control mr-sm-2" type ="submit" value="Найти">
                    </form>
                    </p>
                    <p></p>
                    <p>
                        <a href="/chat?userId=${authUserId}&interlocutorId=${user.id}" onclick="javascript:event.target.port=8090" target="_blank">Чат</a>
                    </p>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-xs-8" align="left">
                    <h3>Статьи пользователя</h3>
                </div>
            </div>
        </div>


        <script type="text/javascript">
            var userIdPage = "${user.id}";
        </script>
        <table class="table table-striped display" id="dataTable">
            <thead>
            <tr>
                <th width="75%">Название</th>
                <th>Дата публикации</th>
                <th>Смотреть</th>
            </tr>
            </thead>
        </table>

    </div>
</div>
</body>
</html>