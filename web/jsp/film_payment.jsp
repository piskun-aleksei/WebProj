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
    <c:if test="${filmsToPay != null}">
        <form name="PayForFilm" action="controller" method="POST">
            <select class="select-center" name="filmToPayId">
                <c:forEach var="elem" items="${filmsToPay}" varStatus="status">
                    <option value="${elem.id}">${elem.name} | price: ${elem.price}</option>
                </c:forEach>
            </select>
            <c:forEach var="elem" items="${filmsToPay}" varStatus="status">
                <input type="hidden" name="film${elem.id}" value="${elem.price}"/>
            </c:forEach>
            <input type="hidden" name="command" value="payForFilm"/>
            <input type="hidden" name="page" value="/jsp/profile.jsp"/>
            <div class="error-message">${moneyErrorMessage}</div>
            <br/>
            <input type="submit" name="backButton" class="input-button" value=
                    <fmt:message key="Button.payForFilm" bundle="${rb}"/> />
        </form>
    </c:if>
    <c:if test="${filmsToPay == null}">
        <p class="text-paragraph"> <fmt:message key="Label.noFilmsToPay" bundle="${rb}"/> </p>
    </c:if>
    <form name="BackForm" action="controller" method="POST">
        <input type="hidden" name="command" value="back"/>
        <input type="hidden" name="backPage" value="/jsp/profile.jsp"/>
        <input type="submit" name="backButton" class="input-button" value=
                <fmt:message key="Button.back" bundle="${rb}"/> />
    </form>
</article>
<%@ include file="shared/footer.jsp"%>
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
