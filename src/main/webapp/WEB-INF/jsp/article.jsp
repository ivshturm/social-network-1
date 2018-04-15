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
            <div>
                <div class="col-xs-10">
                    <h4>${article.text}</h4>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<div class="modal fade" id="editRow">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <%--<h2 class="modal-title" id="modalTitle"></h2>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form class="form-horizontal" id="detailsForm">--%>
                    <%--<input type="hidden" id="id" name="id">--%>

                    <%--<div class="form-group">--%>
                        <%--<label for="name" class="control-label col-xs-3">Название</label>--%>

                        <%--<div class="col-xs-9">--%>
                            <%--<input type="text" class="form-control" id="name" name="name"--%>
                                   <%--placeholder="Название">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="text" class="control-label col-xs-3">Текст</label>--%>

                        <%--<div class="col-xs-9">--%>
                            <%--<input type="text" class="form-control" id="text" name="text" placeholder="Текст">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<div class="col-xs-offset-3 col-xs-9">--%>
                            <%--<button class="btn btn-primary" type="button" onclick="save()">--%>
                                <%--<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
</body>
</html>
