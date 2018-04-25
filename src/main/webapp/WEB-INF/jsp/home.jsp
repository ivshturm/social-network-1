<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/myArticles.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="container">
            <div class="row">
                <div class="col-xs-4">
                    <a class="btn btn-outline-primary" href="/news" methods="get">
                        <button class="btn btn-primary" type="button" class="btn btn-outline-primary">Мои новости</button>
                    </a>
                    <h2 >${userTo.name}  ${userTo.lastName}</h2>
                    <h5 >Возраст: ${userTo.currentYear - userTo.birthdayYear}</h5>
                </div>
                <div class="col-xs-5" align="center">
                    <p></p>
                    <a class="btn btn-outline-primary" href="/followers/${userTo.id}">
                        <button type="button" class="btn btn-outline-primary">Мои подписчики</button>
                    </a>
                    <a class="btn btn-outline-primary" href="/following/${userTo.id}">
                        <button type="button" class="btn btn-outline-primary">Мои подписки</button>
                    </a>
                </div>
                <div class="col-xs-3" align="right">
                    <p>Найти пользователей</p>
                    <form class="form-inline my-2 my-lg-0" action="/search" method="GET">
                        <input class="form-control mr-sm-2" type="text" name="name" placeholder="Введите имя или фамилию">
                        <input class="form-control mr-sm-2" type ="submit" value="Найти">
                    </form>
                    <p></p>
                </div>
            </div>
        </div>

        <div style="margin-top: 200px;"></div>

        <div class="container">
            <div class="row">
                <div class="col-xs-4" align="left">
                    <div style="margin-top: 75px;"></div>
                    <a class="btn btn-primary" onclick="add()">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        Добавить публикацию
                    </a>
                </div>
                <div class="col-xs-8" align="left">
                    <h3>Мои статьи</h3>
                </div>
            </div>
        </div>

        <table class="table table-striped display" id="dataTable">
            <thead>
            <tr>
                <th width="55%">Название</th>
                <th>Дата публикации</th>
                <th>Редактировать</th>
                <th>Удалить</th>
                <th>Смотреть</th>
            </tr>
            </thead>
        </table>
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
                    <textarea type="hidden" id="id" name="id" style="display:none;"></textarea>

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Название</label>

                        <div class="col-xs-9">
                            <textarea type="text" class="form-control" id="name" name="name"
                                      placeholder="Название" rows="5"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="text" class="control-label col-xs-3">Текст</label>

                        <div class="col-xs-9">
                            <textarea type="text" class="form-control" id="text" name="text"
                                      placeholder="Текст" rows="15"></textarea>
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