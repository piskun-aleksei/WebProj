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
<%@ include file="shared/header.jsp" %>
<article class="container">
    <c:if test="${filmsComment != null}">
        <form name="CommentAddingForm" action="controller" method="POST">
            <input type="hidden" name="command" value="addComment"/>
            <input type="hidden" name="page" value="/jsp/add_comment.jsp"/>
            <select class="select-center" name="filmId">
                <c:forEach var="elem" items="${filmsComment}" varStatus="status">
                    <option value="${elem.id}">${elem.name}</option>
                </c:forEach>
            </select>
            <textarea name="desc" placeholder="<fmt:message key="Label.filmDesc" bundle="${rb}"/>" class="desc-field"> </textarea>
            <input type="text" name="rating" placeholder="<fmt:message key="Label.commentRating" bundle="${rb}"/>"
                   class="input-field" pattern="(10)|([0-9]{1,1})"/>
            <div class="error-message">${errorMessage}</div>
            <br/>
            <input type="submit" name="addButton" class="input-button" value=
                <fmt:message key="Button.addComment" bundle="${rb}"/> />
        </form>
    </c:if>
    <c:if test="${filmsComment == null}">
        <p class="text-paragraph"> <fmt:message key="Label.noFilmsToComment" bundle="${rb}"/> </p>
    </c:if>
    <form name="BackForm" action="controller" method="POST">
        <input type="hidden" name="command" value="back"/>
        <input type="hidden" name="backPage" value="/jsp/films.jsp"/>
        <input type="submit" name="backButton" class="input-button" value=
        <fmt:message key="Button.back" bundle="${rb}"/> />
    </form>
</article>
<%@ include file="shared/footer.jsp"%>
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
