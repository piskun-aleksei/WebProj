<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.content" var="rb"/>
<html>
    <head>
        <title><fmt:message key="Label.welcome" bundle="${rb}"/> </title>
        <link rel="stylesheet" href="/css/stylesheet.css">
    </head>
    <body>
        <%@ include file="shared/header.jsp"%>
        <article class="container">
            <form name="FilmsForm" action="controller" method="POST">
                <input type="hidden" name="command" value="showFilms"/>
                <input type="hidden" name="page" value="/jsp/main.jsp" />
                <input type="submit" name="showButton" class="input-button" value=<fmt:message key="Button.showFilms" bundle="${rb}"/> />
            </form>
        </article>
        <%@ include file="shared/footer.jsp"%>
        <script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
        <script type="text/javascript" src="/js/validate.js"></script>
    </body>
</html>