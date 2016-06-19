<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.content" var="rb"/>
<html>
<head>
    <title><fmt:message key="Label.filmsTitle" bundle="${rb}"/></title>
    <link rel="stylesheet" href="/css/stylesheet.css">
</head>
<body>
<%@ include file="shared/header.jsp" %>
<article class="container">
    <br/>
    <table id="films">
        <thead>
            <tr>
                <th scope="col" rowspan="2"><b> <fmt:message key="Table.filmName" bundle="${rb}"/> </b></th>
                <th scope="col" rowspan="2"><b> <fmt:message key="Table.filmDescription" bundle="${rb}"/></b></th>
                <th scope="col" rowspan="2"><b> <fmt:message key="Table.filmAgeRating" bundle="${rb}"/> </b></th>
                <th scope="col" rowspan="2"><b> <fmt:message key="Table.comments" bundle="${rb}"/> </b></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="elem" items="${films}" varStatus="status">
                <tr>
                    <td><c:out value="${ elem.name}"/></td>
                    <td><c:out value="${ elem.desc }"/></td>
                    <td><c:out value="${ elem.ageRating }+"/></td>
                    <td>
                        <form name="ShowCommentariesPageForm" action="controller" method="POST">
                            <input type="hidden" name="command" value="showComments"/>
                            <input type="hidden" name="page" value="/jsp/comments.jsp"/>
                            <input type="hidden" name="filmId" value="${ elem.id}"/>
                            <input type="submit" name="commentsButton" class="input-button" value=
                                <fmt:message key="Button.showComments" bundle="${rb}"/> />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${login != null}">
        <c:if test="${isAdmin == false}">
            <form name="OrderFilmPageForm" action="controller" method="POST">
                <input type="hidden" name="command" value="orderFilmPreparation"/>
                <input type="hidden" name="page" value="/jsp/order_film.jsp"/>
                <input type="submit" name="orderButton" class="input-button" value=
                        <fmt:message key="Button.orderFilm" bundle="${rb}"/> />
            </form>
            <form name="AddCommentForm" action="controller" method="POST">
                <input type="hidden" name="command" value="addCommentPreparation"/>
                <input type="hidden" name="page" value="/jsp/add_comment.jsp"/>
                <input type="submit" name="showButton" class="input-button" value=
                        <fmt:message key="Button.addComment" bundle="${rb}"/> />
            </form>
        </c:if>
    </c:if>
    <form name="BackForm" action="controller" method="POST">
        <input type="hidden" name="command" value="back"/>
        <input type="hidden" name="backPage" value="/jsp/main.jsp"/>
        <input type="submit" name="backButton" class="input-button" value=
        <fmt:message key="Button.back" bundle="${rb}"/> />
    </form>
</article>
<%@ include file="shared/footer.jsp"%>
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
