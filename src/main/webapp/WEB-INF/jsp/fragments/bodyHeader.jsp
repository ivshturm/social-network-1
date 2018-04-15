<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-expand-lg navbar-dark bg-primary" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="home">Мой блог</a>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <sec:authorize access="isAuthenticated()">
                        <form:form class="navbar-form" action="logout" method="post">
                            <a class="btn btn-primary" href="profile"><sec:authentication property="principal.userTo.name"/> изменить профиль</a>
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            </button>
                        </form:form>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <form:form class="navbar-form" action="spring_security_check" method="post">
                            <div class="form-group">
                                <input type="text" placeholder="Email" class="form-control" name="username">
                            </div>
                            <div class="form-group">
                                <input type="password" placeholder="Password" class="form-control" name="password">
                            </div>
                            <button type="submit" class="btn btn-outline-primary">
                                <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                            </button>
                        </form:form>
                    </sec:authorize>
                </li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    var localeCode = "${pageContext.response.locale}";
</script>
