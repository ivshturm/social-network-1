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
                <div class="col-xs-9">
                    <h2 align="left">${article.name}</h2>
                </div>
                <div class="col-xs-3">
                    <h4 align="right">Опубликована:</h4>
                    <h5 align="right">${dateTime}</h5>
                </div>
            </div>
            <br>
            <hr align="center" width="300" color="blue"/>
            <br>
            <div class="row">
                <div class="col-xs-10">
                    <h4>${article.text}</h4>
                </div>
            </div>

            <br>
            <br>
            <br>
            <br>

            <div class="row">
                <div class="col-xs-7" align="center">

                    <c:if test="${!empty comments}">
                        <h4>Комментарии</h4>
                        <br>
                            <c:forEach items="${comments}" var="comment">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-12">
                                    <%--<h3></h3>--%>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-1">
                                    <div class="thumbnail">
                                        <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <strong>
                                                <a class="btn btn-outline-primary" href="/user/${comment.userId}" methods="get">
                                                        ${comment.userTo.fullName}
                                                </a>
                                            </strong>
                                            <span class="text-muted">Прокоментировал(а) ${comment.dateTime}</span>
                                        </div>
                                        <div class="panel-body">
                                            ${comment.text}
                                        </div>
                                        <c:if test="${comment.userId == authUser.id}">
                                            <div class="panel-body">
                                                <a class="btn btn-outline-primary" href="/comment/delete/${comment.id}/${article.id}" methods="get">
                                                    Удалить
                                                </a>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                    </c:if>
                    <c:if test="${empty comments}">
                        <h4>К этой статье нет комментариев</h4>
                        <h4>Вы можете написать первый комментарий</h4>
                        <br>
                    </c:if>

                </div>
            </div>

            <div class="row">
                <div class="col-xs-7" align="center">
                    <form:form action="/comment/create/${article.id}" modelAttribute="comment">
                        <table class="table" align="center">

                            <tr>
                                <td >
                                    <form:label path="text">
                                        <h4>Комментарий</h4>
                                    </form:label>
                                </td>
                                <td>
                                    <form class="form-inline my-2 my-lg-0"></form>
                                    <form:input path="text"/>
                                    </form>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2" style="text-align: center">
                                    <input type="submit" class="form-inline my-2 my-lg-0"
                                           value="Добавить комментарий"/>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </div>
            </div>

        </div>
    </div>
</div>

        <%--<form:form action="/comment/create/${article.id}" modelAttribute="comment">--%>
        <%--<table class="table" align="center">--%>

            <%--<tr>--%>
                <%--<td >--%>
                    <%--<form:label path="text">--%>
                        <%--<h4>Комментарий</h4>--%>
                    <%--</form:label>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<form class="form-inline my-2 my-lg-0"></form>--%>
                    <%--<form:input path="text"/>--%>
                    <%--</form>--%>
                <%--</td>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td colspan="2" style="text-align: center">--%>
                    <%--<input type="submit" class="form-inline my-2 my-lg-0"--%>
                           <%--value="Добавить комментарий"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</table>--%>
        <%--</form:form>--%>


        <%--<table class="table">--%>
            <%--<thead class="thead-dark">--%>
            <%--<tr>--%>
                <%--<th scope="col">#</th>--%>
                <%--<th scope="col">First</th>--%>
                <%--<th scope="col">Last</th>--%>
                <%--<th scope="col">Handle</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
            <%--<tr>--%>
                <%--<th scope="row">1</th>--%>
                <%--<td>Mark</td>--%>
                <%--<td>Otto</td>--%>
                <%--<td>@mdo</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th scope="row">2</th>--%>
                <%--<td>Jacob</td>--%>
                <%--<td>Thornton</td>--%>
                <%--<td>@fat</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th scope="row">3</th>--%>
                <%--<td>Larry</td>--%>
                <%--<td>the Bird</td>--%>
                <%--<td>@twitter</td>--%>
            <%--</tr>--%>
            <%--</tbody>--%>
        <%--</table>--%>

</body>
</html>
