<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.content" var="rb"/>
<html>
    <head>
        <title><fmt:message key="Label.welcome" bundle="${rb}"/></title>
        <link rel="stylesheet" href="/css/stylesheet.css">
    </head>
    <body>
        <%@ include file="shared/header.jsp"%>
        <article class="container">
            <form name="FilmAddingForm" action="controller" method="POST">
                <input type="hidden" name="command" value="addFilm" />
                <input type="hidden" name="page" value="/jsp/add_film.jsp" />
                <input type="text" name="name" placeholder="<fmt:message key="Label.filmName" bundle="${rb}"/>" class="input-field"/>
                <textarea name="desc" placeholder="<fmt:message key="Label.filmDesc" bundle="${rb}"/>" class="desc-field"> </textarea>
                <input type="text" name="ageRating" placeholder="<fmt:message key="Label.ageRating" bundle="${rb}"/>" class="input-field" pattern="[1-9]{1,1}[0-9]{0,1}"/>
                <input type="text" name="price" placeholder="<fmt:message key="Label.filmPrice" bundle="${rb}"/>" class="input-field" pattern="[1-9]{1,1}[0-9]{0,2}"/>
                <div class="error-message">${errorMessage}</div>
                <br/>
                <input type="submit" name="addButton" class="input-button" value=<fmt:message key="Button.addFilm" bundle="${rb}" /> />
            </form>
            <form name="BackForm" action="controller" method="POST">
                <input type="hidden" name="command" value="back" />
                <input type="hidden" name="backPage" value="/jsp/profile.jsp" />
                <input type="submit" name="backButton" class="input-button" value=<fmt:message key="Button.back" bundle="${rb}"/> />
            </form>
        </article>
        <%@ include file="shared/footer.jsp"%>
        <script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
        <script type="text/javascript" src="/js/validate.js"></script>
    </body>
</html>
