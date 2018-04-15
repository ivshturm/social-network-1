<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/articlesDatatables.js" defer></script>
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
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Название</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="Название">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="text" class="control-label col-xs-3">Текст</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="text" name="text" placeholder="Текст">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>