<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/newsArticles.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
    <div class="container">

        <div class="container">
            <div class="row">
                <div class="col-xs-8" align="left">
                    <h3>Ваши новости</h3>
                </div>
            </div>
        </div>


        <%--<script type="text/javascript">--%>
            <%--var userIdPage = "${user.id}";--%>
        <%--</script>--%>
        <table class="table table-striped display" id="dataTable">
            <thead>
            <tr>
                <th width="20%">Кто написал</th>
                <th width="55%">Название</th>
                <th>Дата публикации</th>
                <th>Смотреть</th>
            </tr>
            </thead>
        </table>

    </div>
</div>
</body>
</html>
