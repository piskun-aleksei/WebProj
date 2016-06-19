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
    <c:if test="${filmsOnAccount != null}">
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
            <c:forEach var="elem" items="${filmsOnAccount}" varStatus="status">
                <tr>
                    <td><c:out value="${ elem.name}"/></td>
                    <td><c:out value="${ elem.comment }"/></td>
                    <td><c:out value="${ elem.rating }"/></td>
                    <td><c:out value="${ elem.status }"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${filmsOnAccount == null}">
        <p class="text-paragraph"> <fmt:message key="Label.noFilmsOnAccount" bundle="${rb}"/> </p>
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
